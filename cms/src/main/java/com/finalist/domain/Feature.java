
package com.finalist.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Feature{
	@JsonProperty("Id")
   	private String id;
	@JsonProperty("geometry")
   	private Geometry geometry;
	@JsonProperty("properties")
   	private Properties properties;
	@JsonProperty("type")
   	private String type;

 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public Geometry getGeometry(){
		return this.geometry;
	}
	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}
 	public Properties getProperties(){
		return this.properties;
	}
	public void setProperties(Properties properties){
		this.properties = properties;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
}
