package com.finalist.beans;

import java.util.Calendar;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

@HippoEssentialsGenerated(internalName = "myassignment:trajectmeasurement")
@Node(jcrType = "myassignment:trajectmeasurement")
public class Trajectmeasurement extends HippoDocument {
	
	private Calendar trajectMeasurementDate;
	private Long trajectMeasurementVelocity;
	private Long trajectMeasurementTraveltime;

	@HippoEssentialsGenerated(internalName = "myassignment:trajectMeasurementDate")
	public Calendar getTrajectMeasurementDate() {
		return getProperty("myassignment:trajectMeasurementDate");
	}
		
	public void setTrajectMeasurementDate(Calendar trajectMeasurementDate) {
		this.trajectMeasurementDate = trajectMeasurementDate;
	}

	@HippoEssentialsGenerated(internalName = "myassignment:trajectMeasurementVelocity")
	public Long getTrajectMeasurementVelocity() {
		return getProperty("myassignment:trajectMeasurementVelocity");
	}
	
	public void setTrajectMeasurementVelocity(Long trajectMeasurementVelocity) {
		this.trajectMeasurementVelocity = trajectMeasurementVelocity;
	}

	@HippoEssentialsGenerated(internalName = "myassignment:trajectMeasurementTraveltime")
	public Long getTrajectMeasurementTraveltime() {
		return getProperty("myassignment:trajectMeasurementTraveltime");
	}
	
	public void setTrajectMeasurementTraveltime(Long trajectMeasurementTraveltime) {
		this.trajectMeasurementTraveltime = trajectMeasurementTraveltime;
	}
}
