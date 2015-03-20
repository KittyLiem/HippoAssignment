package org.common.domain;

import java.util.List;

import javax.jcr.RepositoryException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="trajectinformation")
@XmlType(propOrder={"id", "name", "length", "trajectMetingen"})
public class TrajectInfo {
 	
	private String id;
	private String name;
	private int length;
	@XmlElement(name="trajectmeting", type=TrajectMeting.class)
	private List<TrajectMeting> trajectMetingen;
	
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
 	public int getLength(){
		return this.length;
	}
	public void setLength(int length){
		this.length = length;
	}

	public List<TrajectMeting> getTrajectmetingen(){
		return this.trajectMetingen;
	}
	public void setTrajectMetingen(List<TrajectMeting> trajectMetingen) {
		this.trajectMetingen = trajectMetingen;
	} 	
	
	/* public TrajectInfo represent(Trajectinformation bean) throws RepositoryException {
		//super.represent(bean);
		this.id = bean.getTrajectId();
		this.name = bean.getTrajectName();
		this.length = bean.getTrajectLength();
		//this.trajectMetingen = (List<TrajectMeting>) bean.getTrajectMeasurement();
		
		return this;
	} */
}
