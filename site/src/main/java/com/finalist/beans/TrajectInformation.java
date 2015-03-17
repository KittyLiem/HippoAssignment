package com.finalist.beans;

import java.util.List;

import org.hippoecm.hst.content.beans.ContentNodeBindingException;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Node(jcrType = "myhippoassignment:TrajectInformation")
public class TrajectInformation extends BaseDocument {
	
	public static final Logger log = LoggerFactory.getLogger(TrajectInformation.class);
	
	/** 
	 * The document type of the traject information document.
	 */
	public final static String DOCUMENT_TYPE = "myhippoassignment:TrajectInformation";
	private final static String TRAJECTID = "myhippoassignment:trajectId";
	private final static String TRAJECTNAME = "myhippoassignment:trajectName";
	private final static String TRAJECTLENGTH = "myhippoassignment:trajectLength";
	private final static String TRAJECTMEASUREMENTS = "myhippoassignment:trajectMeasurements";
	
	private String trajectId;
	private String trajectName;	
	private double trajectLength;


	/** 
	 * Get the trajectId of the document.
	 * @return the trajectId
	 */
	public String getTrajectId() {
		return getProperty(TRAJECTID);
	}

	/** 
	 * Set the trajectId of the document.
	 */
	public void setTrajectId(String trajectId) {
		this.trajectId = trajectId;
	}

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
	public double getTrajectLength() {
		return getProperty(TRAJECTLENGTH);
	}
	

	/** 
	 * Set the trajectLength of the document.
	 */
	public void setTrajectLength(double trajectLength) {
		this.trajectLength = trajectLength;
	}

	
	/** 
	 * Get the trajectMeasurements of the document.
	 * @return the trajectMeasurements
	 */
	public List<HippoBean> getTrajectMeasurements() {
		return getLinkedBeans("myhippoassignment:trajectMeasurements", HippoBean.class);
	}
	
	
	public boolean bind(Object content, javax.jcr.Node node) throws ContentNodeBindingException {
		 super.bind(content, node);
	        try {
	        	TrajectInformation bean =  (TrajectInformation) content;
	        	node.setProperty("myassignment:trajectId", bean.getTrajectId());
	        	node.setProperty("myassignment:trajectName", bean.getTrajectName());
	        	node.setProperty("myassignment:trajectLength", bean.getTrajectLength());
	        }
	        catch (Exception e) {
	            throw new ContentNodeBindingException(e);
	        }
	        return true;
	}
	
	
}
