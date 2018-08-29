import java.util.Calendar;

/**
 * 获取当前日期后时间
 * @author tom
 *
 */
public class TimeAafter {
	public static void main(String[] args) {
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.MINUTE, 1);
    	System.out.println(cl.getTime());
	}
}
