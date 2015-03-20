package com.finalist.test;

import java.util.ArrayList;
import java.util.List;

import org.common.domain.TrajectInfo;
import org.common.domain.TrajectMeting;

import com.finalist.domain.TrajectInformationObject;
import com.finalist.util.JsonUtil;
import com.finalist.util.XmlUtil;

public class XmlMaker {

	public static void main(String[] args) {
        XmlUtil xmlUtil = new XmlUtil();
        
        TrajectInfo trajectInfo = new TrajectInfo();

        TrajectMeting trajectMeting = new TrajectMeting();
        trajectMeting.setMetingDatum("18-03-2015");
        trajectMeting.setReistijd(123);
        trajectMeting.setVelocity(234);
        
        List<TrajectMeting> trajectMetingen = new ArrayList<TrajectMeting> ();
        trajectMetingen.add(trajectMeting);
        trajectMeting.setMetingDatum("19-03-2015");
        trajectMeting.setReistijd(567);
        trajectMeting.setVelocity(890);
        trajectMetingen.add(trajectMeting);
        
        trajectInfo.setId("Test Xml");
        trajectInfo.setName("Test traject naam");
        trajectInfo.setLength(123);
        trajectInfo.setTrajectMetingen(trajectMetingen);

        String xml = xmlUtil.convertToXml(trajectInfo, trajectInfo.getClass());
        
        System.out.println("XML resultaat " + xml);

	}

}
