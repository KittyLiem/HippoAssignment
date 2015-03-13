
package com.finalist.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TrajectInformation{
	@JsonProperty("features")
   	private List<Feature> features;
   	@JsonProperty("type")
	private String type;

 	public List<Feature> getFeatures(){
		return this.features;
	}
	public void setFeatures(List<Feature> features){
		this.features = features;
	}
	
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
	
    public String toString() {
        return this.getType();
    }

}
