import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	public static void main(String[] args) {
//		"@RequestMapping *\\( *(value=|)\\\"[^\\\"]*\\\" *\\)"
		Pattern pattern = Pattern.compile("@RequestMapping *\\( *(value=|)\\\"[^\\\"]*\\\" *\\)");
		Matcher matcher = pattern.matcher("@RequestMapping(\"/product\")");
	
		System.out.println(matcher.find());
		
		Pattern pattern2 = Pattern.compile("\\^");
		Matcher matcher2 = pattern2.matcher("\\^");
		System.out.println(matcher2.find());
		
		System.out.println("\\\"");
		System.out.println("\\^");
	}
}
