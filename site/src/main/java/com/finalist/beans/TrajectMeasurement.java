package com.finalist.beans;

import java.util.Calendar;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Node(jcrType = "myhippoassignment:TrajectMeasurement")
public class TrajectMeasurement extends HippoCompound {
	
	public static final Logger log = LoggerFactory.getLogger(TrajectMeasurement.class);
			
	/** 
	 * The document type of the traject measurement compound type.
	 */
	public final static String DOCUMENT_TYPE = "myhippoassignment:TrajectMeasurement";
	private final static String TRAJECTDATE = "myhippoassignment:trajectDate";
	private final static String TRAJECTVELOCITY = "myhippoassignment:trajectVelocity";
	private final static String TRAJECTTRAVELTIME = "myhippoassignment:trajectTraveltime";
	
	private Calendar trajectDate;

	/** 
	 * Get the trajectDate.
	 * @return the trajectDate
	 */
	public Calendar getTrajectDate() {
		return getProperty(TRAJECTDATE);
	}

	/** 
	 * Set the trajectDate.
	 */
	public void setTrajectDate(Calendar trajectDate) {
		this.trajectDate = trajectDate;
	}
	
	/** 
	 * Get the trajectVelocity.
	 * @return the trajectVelocity
	 */
	public Integer getTrajectVelocity() {
		return getProperty(TRAJECTVELOCITY);
	}

	/** 
	 * Get the trajectTraveltime.
	 * @return the trajectVelocity
	 */
	public Integer getTrajectTraveltime() {
		return getProperty(TRAJECTTRAVELTIME);
	}

}
