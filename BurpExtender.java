package burp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class BurpExtender implements IBurpExtender, IContextMenuFactory
{
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        // your extension code here
    	callbacks.registerContextMenuFactory(this);
    }
    
    public List<JMenuItem> createMenuItems(final IContextMenuInvocation invocation)
    {
    	List<JMenuItem> menuList = new ArrayList<JMenuItem>();
    	JMenu mainItem = new JMenu("ReplaceParameters");
    	JMenuItem subMenuXSS = new JMenuItem("xss");
    	JMenuItem subMenuUNIX = new JMenuItem("replace(UNIX)");
    	JMenuItem subMenuWIN = new JMenuItem("replace(WIN)");

    	subMenuXSS.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    	    	IHttpRequestResponse[] requestResponseArray = invocation.getSelectedMessages();
    	    	int[] requestResponseBounds = invocation.getSelectionBounds();
    	    	String requestBounds = "";
    	    	int requestFirst = requestResponseBounds[0];
    	    	int requestEnd = requestResponseBounds[1];
    	    	
    			for(IHttpRequestResponse requestResponse:requestResponseArray)
    			{
    				requestBounds = new String(requestResponse.getRequest());
        			ReplaceParameters replaceParameters = new ReplaceParameters();
        			String result = replaceParameters.replaceXSS(requestBounds, requestFirst, requestEnd);
        			
        			requestResponse.setRequest(result.getBytes());
    			}
    		}

    	});
    	
    	subMenuUNIX.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    	    	IHttpRequestResponse[] requestResponseArray = invocation.getSelectedMessages();
    	    	int[] requestResponseBounds = invocation.getSelectionBounds();
    	    	String requestBounds = "";
    	    	int requestFirst = requestResponseBounds[0];
    	    	int requestEnd = requestResponseBounds[1];
    	    	
    			for(IHttpRequestResponse requestResponse:requestResponseArray)
    			{
    				requestBounds = new String(requestResponse.getRequest());
        			ReplaceParameters replaceParameters = new ReplaceParameters();
        			String result = replaceParameters.replaceUNIX(requestBounds, requestFirst, requestEnd);
        			
        			requestResponse.setRequest(result.getBytes());
    			}
    		}

    	});

    	subMenuWIN.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    	    	IHttpRequestResponse[] requestResponseArray = invocation.getSelectedMessages();
    	    	int[] requestResponseBounds = invocation.getSelectionBounds();
    	    	String requestBounds = "";
    	    	int requestFirst = requestResponseBounds[0];
    	    	int requestEnd = requestResponseBounds[1];
    	    	
    			for(IHttpRequestResponse requestResponse:requestResponseArray)
    			{
    				requestBounds = new String(requestResponse.getRequest());
        			ReplaceParameters replaceParameters = new ReplaceParameters();
        			String result = replaceParameters.replaceWIN(requestBounds, requestFirst, requestEnd);
        			
        			requestResponse.setRequest(result.getBytes());
    			}
    		}

    	});
    	
    	mainItem.add(subMenuXSS);
    	mainItem.add(subMenuUNIX);
    	mainItem.add(subMenuWIN);
    	menuList.add(mainItem);
    	return menuList;
    }
}