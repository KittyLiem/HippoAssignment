package com.finalist.util;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.domain.TrajectInformationObject;


public class JsonUtil {
	
	public static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
	
	TrajectInformationObject trajectInformation = null;

	public TrajectInformationObject mapTrajectInformation() {
	ObjectMapper mapper = new ObjectMapper(); 
	
	try {
		URL url = new URL("http://www.trafficlink-online.nl/trafficlinkdata/wegdata/TrajectSensorsNH.GeoJSON"); 
		trajectInformation = mapper.readValue(url, TrajectInformationObject.class);

	}
	catch (JsonGenerationException e){
		log.info("Cannot generate JSON: " + e );
	}
	catch (JsonMappingException e1) {
		log.info("Cannot map JSON: " + e1 );
	}
	catch (IOException e2) {
		log.info("IO exception JSON: " + e2 );
	}
	return trajectInformation;
	}

}
