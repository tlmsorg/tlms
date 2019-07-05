package com.tlms.core.groovy

import com.alibaba.fastjson.JSONObject

JSONObject json = new JSONObject();
json.put("name", "tom");
TransactionMapData tmd = TransactionMapData.getInstance();
tmd.set("tenantName", "承租人姓名");
