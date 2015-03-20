package org.common.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="trajectmeting")
public class TrajectMeting {
	
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
