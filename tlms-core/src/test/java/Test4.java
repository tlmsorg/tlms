import java.util.ArrayList;
import java.util.List;

public class Test4 {

	/**
	 * 记录去重
	 * @param repeatedRecord "、"分隔的字符串数据，例如："汽车租赁欺诈、汽车租赁欺诈"
	 * @return 去重复后数据 "汽车租赁欺诈"
	 */
	public String getUnRepeatRecord(String repeatedRecord){
		String unRepeatRecord = "";
		String[] riskTypes = repeatedRecord.split("、");
		List<String> list = new ArrayList<String>();
		StringBuffer riskTepeSb = new StringBuffer();
		for (int i = 0; i < riskTypes.length; i++) {
//			System.out.println(riskTypes[i]);
			boolean isExist = false;
			for (String tempRiskType : list) {
				if(riskTypes[i].equals(tempRiskType)){
					isExist = true;
				}
			}
			if(isExist)
				continue;
			else
				list.add(riskTypes[i]);
		}
		for (int i = 0; i < list.size(); i++) {
			String tempRiskType = list.get(i);
			riskTepeSb.append(tempRiskType);
			if(i != list.size() - 1){
				riskTepeSb.append("、");
			}
		}
		unRepeatRecord = riskTepeSb.toString();
		return  unRepeatRecord;
	}
	
	/**
	 * 获取映射后的本地检测平台名称（多平台借贷申请模块）
	 * @param tdPlatFormName 同盾返回借贷平台名称（多平台借贷申请检测模块返回数据）
	 * @return 潽金映射后的平台名称
	 */
	public String getLocalPlatFromName(String tdPlatFormName){
		String localPlatFormName = "";
		boolean isFind = false;
		//本地化平台
		String[] localPlatForms = new String[]{"银行","大型消费金融公司","小额贷款公司","P2P网贷","厂商汽车金融","一般消费分期平台","融资租赁","电商"};
		//同盾平台名称
		String[][] tdPlatFroms = new String[][]{
			{"网上银行","信用卡中心","银行消费金融公司","银行对公业务","银行个人业务","银行小微贷款","直销银行"}
			,{"大型消费金融公司"}
			,{"小额贷款公司"}
			,{"P2P网贷"}
			,{"厂商汽车金融"}
			,{"一般消费分期平台"}
			,{"融资租赁"}
			,{"综合类电商平台","比价类电商平台","跨境电商平台","团购平台","垂直类电商平台"}
		};
		for (int i = 0; i < tdPlatFroms.length; i++) {
			String[] tdPlatForm = tdPlatFroms[i];
			for (int j = 0; j < tdPlatForm.length; j++) {
				String tempPlatFormName = tdPlatForm[j];
				if(tdPlatFormName.equals(tempPlatFormName)){
					localPlatFormName = localPlatForms[i];
					isFind = true;
					break;
				}
			}
		}
		if(!isFind)
			localPlatFormName = "其他";
		return localPlatFormName;
	}
	public static void main(String[] args) {
		
		
		String tdPlatFormName = "网上银行";
		System.out.println(new Test4().getLocalPlatFromName(tdPlatFormName));		
		
		//风险类型去重复：
		String riskType = "汽车租赁欺诈、汽车租赁欺诈";
		System.out.println(new Test4().getUnRepeatRecord(riskType));
		
		System.out.println(false && false);
	}

}
