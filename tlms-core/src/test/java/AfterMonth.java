import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AfterMonth {


	public static Date getAfterDay(Date date,int interval){
		String afterYear = "";
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, interval);
		System.out.println("getAfterDay:"+calendar.get(Calendar.YEAR)+"|"+calendar.get(Calendar.MONTH)+"|"+calendar.get(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	public static Date getAfterMonth(Date date,int interval){
		String afterYear = "";
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, interval);
		System.out.println("getAfterMonth"+calendar.get(Calendar.YEAR)+"|"+calendar.get(Calendar.MONTH)+"|"+calendar.get(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	public static Date getAfterYear(Date date,int interval){
		String afterYear = "";
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, interval);
		System.out.println("getAfterYear"+calendar.get(Calendar.YEAR)+"|"+calendar.get(Calendar.MONTH)+"|"+calendar.get(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
		
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int period = 1;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		Date dateNow = calendar.getTime();
		System.out.println(dateNow.getTime());
//		calendar.setTimeInMillis(calendar.getTimeInMillis()+1*24*60*60*1000);

		
		calendar.add(Calendar.MONTH, 6);
		Date dateAfter = calendar.getTime();
		System.out.println(simpleDateFormat.format(dateAfter));
		AfterMonth after = new AfterMonth();
		System.out.println(after.getAfterYear(dateNow, 4));
		System.out.println(after.getAfterMonth(dateNow, 4));
		System.out.println(after.getAfterDay(dateNow, 6));
	}

}
