package burp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceParameters {
	
	String regex1 = "=([a-zA-Z0-9%+]+)*&";
	String regex2 = "=[a-zA-Z0-9%+]+$";
	String regex3 = "=[a-zA-Z0-9%+]+(\\s*)";

	Pattern p = Pattern.compile(regex1);
	Pattern p1 = Pattern.compile(regex2);
	Pattern p2 = Pattern.compile(regex3);
	
	public String replaceXSS(String requestBounds, int requestFirst, int requestEnd)
	{
		String requestReplace = requestBounds.substring(requestFirst, requestEnd);
		StringBuilder sb = new StringBuilder(requestBounds);
		
		Matcher m = p.matcher(requestReplace);
		String result = m.replaceAll("=\"</>xss&");

		Matcher m1 = p1.matcher(result);
		result = m1.replaceAll("=\"</>xss");

		Matcher m2 = p2.matcher(result);
		result = m2.replaceAll("=\"</>xss ");

		sb.replace(requestFirst, requestEnd, result);
		
		return sb.toString();
	}

	public String replaceUNIX(String requestBounds, int requestFirst, int requestEnd)
	{
		String requestReplace = requestBounds.substring(requestFirst, requestEnd);
		StringBuilder sb = new StringBuilder(requestBounds);
		
		Matcher m = p.matcher(requestReplace);
		String result = m.replaceAll("='\"<>xss../../../../../../../bin/sleep+60|&");

		Matcher m1 = p1.matcher(result);
		result = m1.replaceAll("='\"<>xss../../../../../../../bin/sleep+60|");

		Matcher m2 = p2.matcher(result);
		result = m2.replaceAll("=\"../../../../../../../bin/sleep+60| ");

		sb.replace(requestFirst, requestEnd, result);
		
		return sb.toString();
	}

	public String replaceWIN(String requestBounds, int requestFirst, int requestEnd)
	{
		String requestReplace = requestBounds.substring(requestFirst, requestEnd);
		StringBuilder sb = new StringBuilder(requestBounds);
		
		Matcher m = p.matcher(requestReplace);
		String result = m.replaceAll("='\"<>xss..¥..¥..¥..¥..¥..¥..¥windows¥system32¥ping+–n+21+127.0.0.1|&");

		Matcher m1 = p1.matcher(result);
		result = m1.replaceAll("='\"<>xss..¥..¥..¥..¥..¥..¥..¥windows¥system32¥ping+–n+21+127.0.0.1|");

		Matcher m2 = p2.matcher(result);
		result = m2.replaceAll("=\"</>xss..¥..¥..¥..¥..¥..¥..¥windows¥system32¥ping+–n+21+127.0.0.1| ");

		sb.replace(requestFirst, requestEnd, result);
		
		return sb.toString();
	}

}
