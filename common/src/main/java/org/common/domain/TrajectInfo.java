package org.common.domain;

import java.util.List;

import javax.jcr.RepositoryException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.finalist.beans.Trajectinformation;
import com.finalist.beans.Trajectmeasurement;
import com.finalist.model.TrajectinformationRepresentation;

@XmlRootElement(name="trajectinformation")
@XmlType(propOrder={"id", "name", "length", "trajectMetingen"})
public class TrajectInfo {
 	
	private String id;
	private String name;
	private Long length;
	@XmlElement(name="trajectmeting", type=TrajectMeting.class)
	private List<Trajectmeasurement> trajectMetingen;
	
	@XmlAttribute
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	
	@XmlAttribute
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	@XmlAttribute
 	public Long getLength(){
		return this.length;
	}
	public void setLength(Long length){
		this.length = length;
	}

	public List<Trajectmeasurement> getTrajectmetingen(){
		return this.trajectMetingen;
	}
	public void setTrajectMetingen(List<Trajectmeasurement> trajectMetingen) {
		this.trajectMetingen = trajectMetingen;
	} 	
	
	public TrajectInfo represent(Trajectinformation bean) throws RepositoryException {
		//super.represent(bean);
		this.id = bean.getTrajectId();
		this.name = bean.getTrajectName();
		this.length = bean.getTrajectLength();
		this.trajectMetingen = bean.getTrajectMeasurement();
		return this;
	}
}
