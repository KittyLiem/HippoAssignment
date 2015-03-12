package com.finalist.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import com.finalist.beans.Imageset;

@HippoEssentialsGenerated(internalName = "myassignment:bannerdocument")
@Node(jcrType = "myassignment:bannerdocument")
public class Banner extends BaseDocument {
	@HippoEssentialsGenerated(internalName = "myassignment:title")
	public String getTitle() {
		return getProperty("myassignment:title");
	}

	@HippoEssentialsGenerated(internalName = "myassignment:content")
	public HippoHtml getContent() {
		return getHippoHtml("myassignment:content");
	}

	@HippoEssentialsGenerated(internalName = "myassignment:link")
	public HippoBean getLink() {
		return getLinkedBean("myassignment:link", HippoBean.class);
	}

	@HippoEssentialsGenerated(internalName = "myassignment:image")
	public Imageset getImage() {
		return getLinkedBean("myassignment:image", Imageset.class);
	}
}
