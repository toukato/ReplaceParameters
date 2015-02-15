package burp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceParameters {
	
	String regex1 = "=([^=^&]+)*(&|\\?)";
	String regex2 = "=([^=^&]+)*[ ]";

	Pattern p1 = Pattern.compile(regex1);
	Pattern p2 = Pattern.compile(regex2);
	
	public void replaceParameters(IContextMenuInvocation invocation, String replaceString)
	{
		try
		{
		IHttpRequestResponse[] requestResponseArray = invocation.getSelectedMessages();
    	int[] requestResponseBounds = invocation.getSelectionBounds();
    	String requestBounds = "";
    	int requestFirst = requestResponseBounds[0];
    	int requestEnd = requestResponseBounds[1];
    	
		for(IHttpRequestResponse requestResponse:requestResponseArray)
		{
			requestBounds = new String(requestResponse.getRequest());
			
			String requestReplace = requestBounds.substring(requestFirst, requestEnd);
			StringBuilder sb = new StringBuilder(requestBounds);
			
			Matcher m = p1.matcher(requestReplace);
			String result = m.replaceAll("=" + replaceString + "&");

			Matcher m1 = p2.matcher(result);
			result = m1.replaceAll("=" + replaceString + " ");

			sb.replace(requestFirst, requestEnd, result);
			
			requestResponse.setRequest(sb.toString().getBytes());
		}
		}
		catch(UnsupportedOperationException exception)
		{
			
		}
	}
}
