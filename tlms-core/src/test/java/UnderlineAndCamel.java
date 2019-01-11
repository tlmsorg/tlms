import com.google.common.base.CaseFormat;

/**
 * 下划线和驼峰互转
 * @author 160068
 * 2018年11月20日 下午2:57:37
 */
public class UnderlineAndCamel {

	public static void main(String[] args) {
		String name = "ID_NO,APP_ID,DEALER_TYPE,DEALER_TYPE_NAME,COMPSITE_SERVICE_FEE,GPS_FEE,PRODUCT_NAME,COMPOSITE_SERVICE_FEE_RATE,DEALER_NAME,PRODUCT_CODE,ADDON_YEAR_RATE,SALE_PRICE,ADDON_FINANCE_AMOUNT,END_TIME,FINANCE_AMOUNT,PLAT_FEE_RATE,TENANT_NAME,PLAT_FEE,ADDON_PRODUCT_CODE,";
		name = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
		System.out.println(name);
	}

}
