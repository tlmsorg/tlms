import java.util.HashMap;

public class Test3 {

    /**
     * 数字转换人民币大写
     * @param number 数字 不支持科学数字
     * @return
     */
    public static String number2Chn(double money){
    	if(money == 0)
    		return "零元整";
    	String number = money + "";
    	/**
         * 人民币大写单位制
         */
    	HashMap<Integer, String> dws = new HashMap<Integer, String>();
        dws.put(-2, "分");
        dws.put(-1, "角");
        dws.put(0, "元");
        dws.put(1, "拾");
        dws.put(2, "佰");
        dws.put(3, "仟");
        dws.put(4, "万");//
        dws.put(5, "拾");
        dws.put(6, "佰");
        dws.put(7, "仟");
        dws.put(8, "亿");//
        dws.put(9, "拾");
        dws.put(10, "佰");
        dws.put(11, "仟");
        dws.put(12, "万");
        /**
         * 数字对应的中文
         */
        String[] jes = new String[]{"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    	
        StringBuffer su = new StringBuffer();
        // 整数部分
        number = delInvalidZero(number);
        String str = null;
        // 小数部分
        String decimal = null;
        if(number.contains(".")){
            // 截取整数位
            str = number.split("\\.")[0];
            decimal = number.split("\\.")[1];
        }else{
            str = number;
        }
        // 判断是否存在整数位
        if(str.length()>0){
            for (int i = 0; i < str.length() ; i++) {
                String context = str.substring(i, i+1);
                int pow = str.length()-i-1;
                Integer val = Integer.parseInt(context.toString());
                // 获取中文单位
                String sign = dws.get(pow);
                // 获取中文数字
                String name = jes[Integer.parseInt(context)];
                if(val==0){
                    if(pow%4!=0){// 删除单位
                        sign = "";
                    }
                    if(i < str.length()-1){
                        Integer val1 = Integer.parseInt(str.substring(i+1, i+2));
                        if(val==0&&val==val1){
                            name = "";
                        }
                    }else if(i == str.length()-1){
                        name = "";
                    }
                }
                su.append(name+sign);
            }
        }
        // 判断是否存在小数位
        if(decimal!=null){
            str = decimal.substring(0,1);
            if(!"0".equals(str)){
                su.append(jes[Integer.parseInt(str)]+dws.get(-1));
            }
            if(decimal.length()==2){
                str = decimal.substring(1,2);
                if(!"0".equals(str)){
                    su.append(jes[Integer.parseInt(str)]+dws.get(-2));
                }
            }
        }else{
            su.append("整");
        }
        return su.toString();
    }
    /**
     * 清理数字特殊字符
     * @param str
     * @return
     */
    private static String delInvalidZero(String str){
        if("0".equals(str.substring(0, 1))){
            return delInvalidZero(str.substring(1, str.length()));
        }else if(str.contains(",")){
            return delInvalidZero(str.replaceAll(",", ""));
        }else{
            return str;
        }
    }
    public static void main(String[] args) {
        System.out.println(new Test3().number2Chn(0.1));
    }
}
