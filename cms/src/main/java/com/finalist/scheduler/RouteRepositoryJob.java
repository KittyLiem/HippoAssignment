package com.finalist.scheduler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.net.ssl.HttpsURLConnection;

import org.onehippo.repository.scheduling.RepositoryJob;
import org.onehippo.repository.scheduling.RepositoryJobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.domain.Feature;
import com.finalist.domain.Properties;
import com.finalist.domain.TrajectInformationObject;
import com.finalist.util.JsonUtil;



public class RouteRepositoryJob implements RepositoryJob {

	public static final Logger log = LoggerFactory.getLogger(RouteRepositoryJob.class);


	@Override
	public void execute(RepositoryJobExecutionContext context)
			throws RepositoryException {
		
		Session session = null;
		try {
			// get session and output
			session = context.createSystemSession();
             
			//log.info("JCR Session retrieved from context: {}" + session);
		// get the route info JSON to POJO's using Jackson
			JsonUtil jsonUtil = new JsonUtil();
			TrajectInformationObject trajectInformatie = jsonUtil.mapTrajectInformation();
        
        // save POJO's in repository in Hippo document using restapi
		


        for (Feature feature: trajectInformatie.getFeatures()) {
        	String trajectId = feature.getId();
        	Properties properties = feature.getProperties();
        	String trajectName = properties.getName();
        	double trajectLength = properties.getLength();

        	
        	// activate POST in site
			String url = "http://localhost:8080/site/resttrajectapi/Trajectinformation/";
			URL obj = new URL(url);
			
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
	 
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("trajectId", trajectId );
	 
			// Send post request
			//con.setDoOutput(true);
			 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(url);
			wr.flush();
			wr.close(); 
			log.info("sending POST" + con.getRequestMethod() + con.getRequestProperties());
	 
		/*	int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + url);
			System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	 
			//print result
			System.out.println(response.toString());
			*/
        		
        	
        	}
        
		}
        catch (MalformedURLException e) {
        	log.info("bad URL");
        }
        catch (IOException e) {
        	log.info("bad URL");
        }

		finally {
			if (session != null) {
				session.logout();
			}
		}

	}

    public String toString() {
        return this.getClass().getName();
    }

}
