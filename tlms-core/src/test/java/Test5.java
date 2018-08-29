import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.tlms.core.enumeration.EIntervalMode;
import com.tlms.core.util.Utils;

public class Test5 {

	/**
	 * @param date
	 *            给定日期
	 * @param interval
	 *            间隔天数，示例：5：5天以后;-6:6天以前
	 * @return 间隔后日期
	 */
	public static Date getDateAfterDay(Date date, int interval) {
		String afterYear = "";
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.DAY_OF_MONTH, interval);
		return calender.getTime();
	}
	
	public static void main(String[] args) throws ParseException {
		int financeAmt = 88117;
		int period = 36;
		double feeRate = 0.03;
		
		/**
		 * 截至2018-07-24，
		 * 逾期30天，逾期日期区间（包含首尾两天）：2018-06-25 至 2018-07-24
		 * 最近一次应还未还款日：2018-06-24
		 */
		String dateAfter = "2018-07-24";
//		String dateAfter = "2018-06-01";
//		String dateAfter = "2018-04-09";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		int iterval = -44;
		Date date = Test5.getDateAfterDay(format.parse(dateAfter), iterval);
		System.out.println(dateAfter+" "+iterval+"天："+format.format(date));
		
		Calendar cl1 = Calendar.getInstance();
		cl1.setTime(Utils.formateString2Date("2018-01-01", "yyyy-MM-dd"));
		Calendar cl2 = Calendar.getInstance();
		cl2.setTime(Utils.formateString2Date("2018-01-01", "yyyy-MM-dd"));
		System.out.println(cl1.before(cl2));
		System.out.println(cl1.after(cl2));
		System.out.println(cl1.equals(cl2));
		
		System.out.println(Utils.getTimeInterval(Utils.formateString2Date("2018-04-10", "yyyy-MM-dd"), Utils.formateString2Date("2018-07-17", "yyyy-MM-dd"), EIntervalMode.DAYS));
		Double db = 78785E2;
		System.out.println(NumberFormat.getInstance().format(db));
		DecimalFormat decimalFormat = new DecimalFormat("###");
		System.out.println(decimalFormat.format(db));
		
	}
}
