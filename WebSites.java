package effective_java;

// javac -d class_path WebSites.java

import java.net.*;
import java.util.*;
import java.awt.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.event.*;

public class WebSites extends JApplet {
	private HashMap<String, URL> websiteInfo;
	private ArrayList<String> titles;
	private JList<String> mainList;
	private DefaultListModel<String> model;

	public void init() {

		websiteInfo = new HashMap<String, URL>();
		titles = new ArrayList<String>();
		model = new DefaultListModel<String>();

		grabHTMLInfo();
		add(new JLabel("What website do you want to visit?"), 
			      BorderLayout.NORTH);
		//get "unchecked" warning if using titles
		//mainList = new JList(titles.toArray());
		mainList = new JList<String>(model);

		//add listener
		mainList.addListSelectionListener(
			new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					Object object = mainList.getSelectedValue();
					URL newDocument = websiteInfo.get(object);
					AppletContext browser = getAppletContext();
					browser.showDocument(newDocument);
				}
			}
		);
		add(new JScrollPane(mainList), BorderLayout.CENTER);
	}

	private void grabHTMLInfo() {
		String title;
		String address;
		URL url;
		int counter = 0;
		title = getParameter("title"+counter);
		while (title != null) {
			address = getParameter("address"+counter);
			try {
				url = new URL(address);
				websiteInfo.put(title, url);
				titles.add(title);
				model.addElement(title);
			} catch (MalformedURLException urlException) {
				urlException.printStackTrace();
			}
			counter++;
			title = getParameter("title"+counter);
		}
	}
}