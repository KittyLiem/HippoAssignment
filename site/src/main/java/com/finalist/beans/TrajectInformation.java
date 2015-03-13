package com.finalist.beans;

import java.util.List;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoBean;

@Node(jcrType = "myhippoassignment:TrajectInformation")
public class TrajectInformation extends BaseDocument {
	/** 
	 * The document type of the traject information document.
	 */
	public final static String DOCUMENT_TYPE = "myhippoassignment:TrajectInformation";
	private final static String TRAJECTNAME = "myhippoassignment:trajectName";
	private final static String TRAJECTLENGTH = "myhippoassignment:trajectLength";
	private final static String TRAJECTMEASUREMENTS = "myhippoassignment:trajectMeasurements";
	
	private String trajectName;

	/** 
	 * Get the trajectName of the document.
	 * @return the trajectName
	 */
	public String getTrajectName() {
		return getProperty(TRAJECTNAME);
	}

	/** 
	 * Set the trajectName of the document.
	 */
	public void setTrajectName(String trajectName) {
		this.trajectName = trajectName;
	}

	/** 
	 * Get the trajectLenth of the document.
	 * @return the trajectLength
	 */
	public String getTrajectLength() {
		return getProperty(TRAJECTLENGTH);
	}
	
	/** 
	 * Get the trajectMeasurements of the document.
	 * @return the trajectMeasurements
	 */
	public List<HippoBean> getTrajectMeasurements() {
		return getLinkedBeans("myhippoassignment:trajectMeasurements", HippoBean.class);
	}
	
}
