package com.finalist.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "trajectmeting")
@XmlAccessorType(XmlAccessType.NONE)
public class TrajectmetingRepresentation {
	
	private String metingDatum;
	private int snelheid;
	private int reistijd;
	
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

}
