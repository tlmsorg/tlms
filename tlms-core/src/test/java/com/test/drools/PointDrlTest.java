package com.test.drools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PointDrlTest {

	public static void main(String[] args) throws Exception {
		PointRuleEngine pointRuleEngine = new PointRuleEngineImpl();  
        while(true){  
            InputStream is = System.in;  
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  
            String input = br.readLine();  
              
            if(null != input && "s".equals(input)){  
                System.out.println("初始化规则引擎...");  
                pointRuleEngine.initEngine();  
                System.out.println("初始化规则引擎结束.");  
            }else if("e".equals(input)){  
                final PointDomain pointDomain = new PointDomain();  
                pointDomain.setUserName("hello kity");  
                pointDomain.setBackMondy(100d);  
                pointDomain.setBuyMoney(500d);  
                pointDomain.setBackNums(1);  
                pointDomain.setBuyNums(5);  
                pointDomain.setBillThisMonth(5);  
                pointDomain.setBirthDay(true);  
                pointDomain.setPoint(0l);  
                  
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("point", 100);
                map.put("num", 2);
                map.put("birthDay", true);
//                pointRuleEngine.executeRuleEngine(pointDomain);  
                pointRuleEngine.executeRuleEngine(map);
                
                
//                System.out.println("执行完毕BillThisMonth："+pointDomain.getBillThisMonth());  
//                System.out.println("执行完毕BuyMoney："+pointDomain.getBuyMoney());  
//                System.out.println("执行完毕BuyNums："+pointDomain.getBuyNums());  
//                  
                System.out.println("执行完毕规则引擎决定发送积分："+pointDomain.getPoint());  
            } else if("r".equals(input)){  
                System.out.println("刷新规则文件...");  
                pointRuleEngine.refreshEnginRule();  
                System.out.println("刷新规则文件结束.");  
            }  
        }  
	}

}
