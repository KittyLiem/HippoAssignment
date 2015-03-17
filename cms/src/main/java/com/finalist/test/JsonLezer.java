package com.finalist.test;

import com.finalist.domain.TrajectInformationObject;
import com.finalist.util.JsonUtil;

public class JsonLezer {

	public static void main(String[] args) {
        JsonUtil jsonUtil = new JsonUtil();
        TrajectInformationObject trajectInformtie = jsonUtil.mapTrajectInformation();
        System.out.println("After JsonUtil " + trajectInformtie);

	}

}
