package com.finalist.scheduler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.common.domain.TrajectInfo;
import org.onehippo.repository.scheduling.RepositoryJob;
import org.onehippo.repository.scheduling.RepositoryJobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.domain.Feature;
import com.finalist.domain.TrajectInformationObject;
import com.finalist.util.JsonUtil;
import com.finalist.util.XmlUtil;

public class RouteRepositoryJob implements RepositoryJob {

   public static final Logger log = LoggerFactory.getLogger(RouteRepositoryJob.class);

   @Override
   public void execute(RepositoryJobExecutionContext context) throws RepositoryException {

      Session session = null;
      try {
         // get session and output
         session = context.createSystemSession();

         // get the route info JSON to POJO's using Jackson
         JsonUtil jsonUtil = new JsonUtil();
         TrajectInformationObject trajectInformatie = jsonUtil.mapTrajectInformation();

         // save POJO's in repository in Hippo document using restapi

         XmlUtil xmlUtil = new XmlUtil();
         String xml;

         for (Feature feature : trajectInformatie.getFeatures()) {

            // set trajectinformation to XML
            xml = xmlUtil.convertToXml(filterTrajectInfo(feature), TrajectInfo.class);

            // activate POST in site
            String url = "http://localhost:8080/site/resttrajectapi/Trajectinformation/";
            URL obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // add request headers etc
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/xml");
            con.setRequestProperty("charset", "utf-8");
            con.setDoOutput(true);

            // Send post request
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xml);
            wr.flush();
            wr.close();
            log.info("Sending " + con.getRequestMethod() + " " + xml);

            log.info("Responsecode " + con.getResponseCode());

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
               response.append(inputLine);
            }
            in.close();

            // log result
            log.info("Result: " + response.toString());

         }
      } catch (MalformedURLException e) {
         log.info("Bad URL");
      } catch (IOException e) {
         log.info("IOexception");
      } finally {
         if (session != null) {
            session.logout();
         }
      }
   }

   public TrajectInfo filterTrajectInfo(Feature feature) {
      TrajectInfo trajectInfo = new TrajectInfo();

      trajectInfo.setId(feature.getId());
      trajectInfo.setName(feature.getProperties().getName());
      trajectInfo.setLength(feature.getProperties().getLength());

      trajectInfo.setMetingDatum(feature.getProperties().getTimestamp());
      trajectInfo.setReistijd(feature.getProperties().getTraveltime());
      trajectInfo.setSnelheid(feature.getProperties().getVelocity());

      return trajectInfo;
   }

   public String toString() {
      return this.getClass().getName();
   }

}
