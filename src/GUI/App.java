package GUI;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.UIManager;

import Utils.Util;

public class App {
	public static void main(String[] args) {
		Font font = Util.createFont("/Fonts/Aqua.ttf").deriveFont(Font.PLAIN,30);
		setUIFont(new javax.swing.plaf.FontUIResource(font));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					new MainFrame(Utils.Util.createIcon("/Assets/configImage.jpg"));
				}
			}
		);
	}
	private static void setUIFont(javax.swing.plaf.FontUIResource f){
	    @SuppressWarnings("rawtypes")
		java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements())
	    {
	        Object key = keys.nextElement();
	        Object value = UIManager.get(key);
	        if (value instanceof javax.swing.plaf.FontUIResource)
	        {
	            UIManager.put(key, f);
	        }
	    }
	}
}

