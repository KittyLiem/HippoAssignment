package com.finalist.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@HippoEssentialsGenerated(internalName = "myassignment:trajectmeasurement")
@Node(jcrType = "myassignment:trajectmeasurement")
public class Trajectmeasurement extends HippoDocument {
	
	@HippoEssentialsGenerated(internalName = "myassignment:trajectMeasurementDate")
	public Calendar getTrajectMeasurementDate() {
		return getProperty("myassignment:trajectMeasurementDate");
	}

	
	@HippoEssentialsGenerated(internalName = "myassignment:trajectMeasurementVelocity")
	public Long getTrajectMeasurementVelocity() {
		return getProperty("myassignment:trajectMeasurementVelocity");
	}


	@HippoEssentialsGenerated(internalName = "myassignment:trajectMeasurementTraveltime")
	public Long getTrajectMeasurementTraveltime() {
		return getProperty("myassignment:trajectMeasurementTraveltime");
	}
}
