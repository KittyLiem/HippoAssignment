
package com.finalist.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Properties{
	@JsonProperty("Length")
   	private Integer length;
	@JsonProperty("Name")
   	private String name;
	@JsonProperty("Timestamp")
   	private String timestamp;
	@JsonProperty("Traveltime")
   	private Integer traveltime;
	@JsonProperty("Velocity")
   	private Integer velocity;

 	public Integer getLength(){
		return this.length;
	}
	public void setLength(Integer length){
		this.length = length;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getTimestamp(){
		return this.timestamp;
	}
	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}
 	public Integer getTraveltime(){
		return this.traveltime;
	}
	public void setTraveltime(Integer traveltime){
		this.traveltime = traveltime;
	}
 	public Integer getVelocity(){
		return this.velocity;
	}
	public void setVelocity(Integer velocity){
		this.velocity = velocity;
	}
}
