package com.finalist.model;

import javax.jcr.RepositoryException;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finalist.beans.Trajectinformation;

@XmlRootElement(name = "trajectinformation")
public class TrajectinformationRepresentation extends BaseDocumentRepresentation {
	
	public static final Logger log = LoggerFactory.getLogger(TrajectinformationRepresentation.class);
		
		private String trajectId;
		private String trajectName;
		private double trajectLength;

		public TrajectinformationRepresentation represent(Trajectinformation bean) throws RepositoryException {
			super.represent(bean);
			this.trajectId = bean.getTrajectId();
			this.trajectName = bean.getTrajectName();
			this.trajectLength = bean.getTrajectLength();
			return this;
			
		}

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

}
