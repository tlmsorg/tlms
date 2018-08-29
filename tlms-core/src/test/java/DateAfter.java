import java.util.Calendar;
import java.util.Date;

public class DateAfter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = DateAfter.getDateAfterDay(Calendar.getInstance().getTime(), 5);
		System.out.println(date);
	}

	
	public static Date getDateAfterDay(Date date,int interval){
		String afterYear = "";
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.DAY_OF_MONTH, interval);
		return calender.getTime();
	}
}
