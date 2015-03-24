package com.finalist.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.beans.Trajectmeasurement;

@Deprecated
@XmlRootElement(name = "trajectinformation")
public class TrajectinformationRepresentation extends BaseDocumentRepresentation {
	
	public static final Logger log = LoggerFactory.getLogger(TrajectinformationRepresentation.class);
	
	private String trajectId;
	private String trajectName;
	private double trajectLength;


	public String getTrajectId() {
		return trajectId;
	}

	public void setTrajectId(String trajectId) {
		this.trajectId = trajectId;		
	}
	public String getTrajectName() {
		return trajectName;
	}

	public void setTrajectName(String trajectName) {
		this.trajectName = trajectName;
	}

	public double getTrajectLength() {
		return trajectLength;
	}

	public void setTrajectLength (double trajectLength) {
		this.trajectLength = trajectLength;
	}

}
