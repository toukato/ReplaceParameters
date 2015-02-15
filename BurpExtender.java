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
    			ReplaceParameters replaceParameters = new ReplaceParameters();
    			replaceParameters.replaceParameters(invocation, "\"<>xss/");
    		}
    	});
    	
    	subMenuUNIX.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			ReplaceParameters replaceParameters = new ReplaceParameters();
    			replaceParameters.replaceParameters(invocation, "'\"<>xss../../../../../../../bin/sleep+60|");
    		}
    	});

    	subMenuWIN.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			ReplaceParameters replaceParameters = new ReplaceParameters();
    			replaceParameters.replaceParameters(invocation, "'\"<>xss..¥..¥..¥..¥..¥..¥..¥windows¥system32¥ping+–n+21+127.0.0.1|");
    		}
    	});
    	
    	mainItem.add(subMenuXSS);
    	mainItem.add(subMenuUNIX);
    	mainItem.add(subMenuWIN);
    	menuList.add(mainItem);
    	return menuList;
    }
}