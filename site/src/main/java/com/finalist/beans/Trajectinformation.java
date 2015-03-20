package com.finalist.beans;

import java.util.List;

import org.hippoecm.hst.content.beans.ContentNodeBindingException;
import org.hippoecm.hst.content.beans.Node;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@HippoEssentialsGenerated(internalName = "myassignment:trajectinformation")
@Node(jcrType = "myassignment:trajectinformation")
public class Trajectinformation extends BaseDocument {
	
	public static final Logger log = LoggerFactory.getLogger(Trajectinformation.class);
	
	private String trajectId;
	private String trajectName;
	private double trajectLength;
	
	@HippoEssentialsGenerated(internalName = "myassignment:trajectId")
	public String getTrajectId() {
		return getProperty("myassignment:trajectId");
	}
	
	public void setTrajectId(String trajectId) {
		this.trajectId = trajectId;
	}

	@HippoEssentialsGenerated(internalName = "myassignment:trajectName")
	public String getTrajectName() {
		return getProperty("myassignment:trajectName");
	}
	
	public void setTrajectName(String trajectName) {
		this.trajectName = trajectName;
	}

	@HippoEssentialsGenerated(internalName = "myassignment:trajectLength")
	public Long getTrajectLength() {
		return getProperty("myassignment:trajectLength");
	}
	
	public void setTrajectLength(double trajectLength) {
		this.trajectLength = trajectLength;
	}

	@HippoEssentialsGenerated(internalName = "myassignment:trajectMeasurement")
	public List<Trajectmeasurement> getTrajectMeasurement() {
		return getChildBeansByName("myassignment:trajectMeasurement",
				Trajectmeasurement.class);
	}
	
	public boolean bind(Object content, javax.jcr.Node node)
			throws ContentNodeBindingException {
		super.bind(content, node);
		try {
			Trajectinformation bean = (Trajectinformation) content;
			node.setProperty("myassignment:trajectId", bean.getTrajectId());
			node.setProperty("myassignment:trajectName", bean.getTrajectName());
			node.setProperty("myassignment:trajectLength", bean.getTrajectLength());
		} catch (Exception e) {
			throw new ContentNodeBindingException(e);
		}
		return true;
	}
}
