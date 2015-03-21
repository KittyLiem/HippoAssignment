package com.finalist.beans;

import java.util.List;

import org.hippoecm.hst.content.beans.ContentNodeBindingException;
import org.hippoecm.hst.content.beans.Node;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@HippoEssentialsGenerated(internalName = "myassignment:trajectinformation")
@Node(jcrType = "myassignment:trajectinformation")
public class Trajectinformation extends BaseDocument {
	
	public static final Logger log = LoggerFactory.getLogger(Trajectinformation.class);

	public static final String JCR_TYPE = "myassignment:trajectinformation";
	
	private String trajectId;
	private String trajectName;
	private Long trajectLength;
	private List<Trajectmeasurement> trajectMeasurements;
	
	@HippoEssentialsGenerated(internalName = "myassignment:trajectId")
	public String getTrajectId() {
		return (trajectId == null ? (String) getProperty("myassignment:trajectId") : trajectId);
	}
	
	public void setTrajectId(String trajectId) {
		this.trajectId = trajectId;
	}

	@HippoEssentialsGenerated(internalName = "myassignment:trajectName")
	public String getTrajectName() {
		return (trajectName == null ? (String) getProperty("myassignment:trajectName") : trajectName);
	}
	
	public void setTrajectName(String trajectName) {
		this.trajectName = trajectName;
	}

	@HippoEssentialsGenerated(internalName = "myassignment:trajectLength")
	public Long getTrajectLength() {
		return (trajectLength == null ? (Long) getProperty("myassignment:trajectLength") : trajectLength);
	}
	
	public void setTrajectLength(Long trajectLength) {
		this.trajectLength = trajectLength;
	}

	@HippoEssentialsGenerated(internalName = "myassignment:trajectMeasurement")
	public List<Trajectmeasurement> getTrajectMeasurement() {
		return getChildBeansByName("myassignment:trajectMeasurement",
				Trajectmeasurement.class);
	}
	
	public void setTrajectmeasurment(List<Trajectmeasurement> trajectMeasurments) {
		this.trajectMeasurements = trajectMeasurments;
	}
	
	public List<Trajectmeasurement> addTrajectMeasurement(Trajectmeasurement trajectmeasurement) {
		List<Trajectmeasurement> trajectMeasurements = getTrajectMeasurement();
		trajectMeasurements.add(trajectmeasurement);
		return trajectMeasurements;
	}
	
	public boolean bind(Object content, javax.jcr.Node node)
			throws ContentNodeBindingException {
		if (content instanceof Trajectinformation){
			try {
				Trajectinformation trajectinformation = (Trajectinformation) content;
				node.setProperty("myassignment:trajectId", trajectinformation.getTrajectId());
				node.setProperty("myassignment:trajectName", trajectinformation.getTrajectName());
				node.setProperty("myassignment:trajectLength", trajectinformation.getTrajectLength());
			} catch (Exception e) {
	            log.error("Unable to bind the content to the JCR Node" + e.getMessage(), e);
				throw new ContentNodeBindingException(e);
			}

		}
		return true;
	}

}
