package com.test.dozer;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

import com.alibaba.fastjson.JSONObject;

public class DozerTest {

	public static void main(String[] args) {
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		JSONObject json3 = new JSONObject();
		JSONObject json4 = new JSONObject();
		json4.put("userName", "brighttang");
		json3.put("json4", json4);
		json2.put("json3", json3);
		json1.put("json2", json2);
		List mappingFiles = new ArrayList();
		mappingFiles.add("mappingTest.xml");
		DozerBeanMapper mapper = new DozerBeanMapper(mappingFiles);
		
		
		Scene scene = new Scene();
		scene.setOpenId("555555555");
		SceneVo sceneVo = mapper.map(scene, SceneVo.class);
		System.out.println(sceneVo.getOpenId());
	}

}
