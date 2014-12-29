package burp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceParameters {
	
	public String replaceXSS(String requestBounds, int requestFirst, int requestEnd)
	{
		String requestReplace = requestBounds.substring(requestFirst, requestEnd);
		StringBuilder sb = new StringBuilder(requestBounds);
		
		String regex1 = "=[a-zA-Z0-9%+]+&";
		String regex2 = "=[a-zA-Z0-9%+]+$";
		
		Pattern p = Pattern.compile(regex1);
		Matcher m = p.matcher(requestReplace);
		String result = m.replaceAll("=\"</>xss&");

		Pattern p1 = Pattern.compile(regex2);
		Matcher m1 = p1.matcher(result);
		result = m1.replaceAll("=\"</>xss");

		sb.replace(requestFirst, requestEnd, result);
		
		return sb.toString();
	}

	public String replaceUNIX(String requestBounds, int requestFirst, int requestEnd)
	{
		String requestReplace = requestBounds.substring(requestFirst, requestEnd);
		StringBuilder sb = new StringBuilder(requestBounds);
		
		String regex1 = "=[a-zA-Z0-9%+]+&";
		String regex2 = "=[a-zA-Z0-9%+]+$";
		
		Pattern p = Pattern.compile(regex1);
		Matcher m = p.matcher(requestReplace);
		String result = m.replaceAll("='\"<>xss/../../../../../../../../etc/passwd&");

		Pattern p1 = Pattern.compile(regex2);
		Matcher m1 = p1.matcher(result);
		result = m1.replaceAll("='\"<>xss/../../../../../../../../etc/passwd");

		sb.replace(requestFirst, requestEnd, result);
		
		return sb.toString();
	}

	public String replaceWIN(String requestBounds, int requestFirst, int requestEnd)
	{
		String requestReplace = requestBounds.substring(requestFirst, requestEnd);
		StringBuilder sb = new StringBuilder(requestBounds);
		
		String regex1 = "=[a-zA-Z0-9%+]+&";
		String regex2 = "=[a-zA-Z0-9%+]+$";
		
		Pattern p = Pattern.compile(regex1);
		Matcher m = p.matcher(requestReplace);
		String result = m.replaceAll("='\"<>xss¥..¥..¥..¥..¥..¥..¥..¥..¥windows¥win.ini&");

		Pattern p1 = Pattern.compile(regex2);
		Matcher m1 = p1.matcher(result);
		result = m1.replaceAll("='\"<>xss¥..¥..¥..¥..¥..¥..¥..¥..¥windows¥win.ini");

		sb.replace(requestFirst, requestEnd, result);
		
		return sb.toString();
	}

}
