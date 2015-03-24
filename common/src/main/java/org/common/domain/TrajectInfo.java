package org.common.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="trajectinformation")
@XmlType(propOrder={"id", "name", "length", "metingDatum", "snelheid", "reistijd"})
public class TrajectInfo {
 	
	private String id;
	private String name;
	private int length;
	private String metingDatum;
	private int snelheid;
	private int reistijd;
	
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
	@XmlAttribute
	public String getMetingDatum(){
		return this.metingDatum;
	}
	public void setMetingDatum(String metingDatum){
		this.metingDatum = metingDatum;
	}
	
	@XmlAttribute
 	public int getReistijd(){
		return this.reistijd;
	}
	public void setReistijd(int reistijd){
		this.reistijd = reistijd;
	}
	
	@XmlAttribute
 	public int getSnelheid(){
		return this.snelheid;
	}
	public void setVelocity(int snelheid){
		this.snelheid = snelheid;
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
