import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.tlms.core.util.Utils;

public class OffDay {

	public static void main(String[] args) {
		String date = "2017-03-19 12:11:09";
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int dayOfWeek = 0;
		try {
			cl.setTime(sdf.parse(date));
			dayOfWeek = cl.get(Calendar.DAY_OF_WEEK);
			System.out.println(dayOfWeek);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date firstWorkDate = null;
		if(dayOfWeek == 7){//周六
			firstWorkDate = Utils.getDateAfterDay(cl.getTime(), 2);
		}else if(dayOfWeek == 1){//周日
			firstWorkDate = Utils.getDateAfterDay(cl.getTime(), 1);
		}
		System.out.println(sdf.format(firstWorkDate));
			
	}
}
