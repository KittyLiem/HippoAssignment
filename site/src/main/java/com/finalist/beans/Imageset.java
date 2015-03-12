package com.finalist.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageBean;

@HippoEssentialsGenerated(internalName = "myassignment:imageset")
@Node(jcrType = "myassignment:imageset")
public class Imageset extends HippoGalleryImageSet {
	@HippoEssentialsGenerated(internalName = "myassignment:small")
	public HippoGalleryImageBean getSmall() {
		return getBean("myassignment:small", HippoGalleryImageBean.class);
	}

	@HippoEssentialsGenerated(internalName = "myassignment:large")
	public HippoGalleryImageBean getLarge() {
		return getBean("myassignment:large", HippoGalleryImageBean.class);
	}

	@HippoEssentialsGenerated(internalName = "myassignment:banner")
	public HippoGalleryImageBean getBanner() {
		return getBean("myassignment:banner", HippoGalleryImageBean.class);
	}
}
