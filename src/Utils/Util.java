package Utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;


public class Util {
	public static Font createFont(String path){
		URL url = System.class.getResource(path);
		if (url ==null){
			System.out.println("Unable to load font: "+ path);
		}
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
		} catch (FontFormatException | IOException e) {
			System.out.println("Font recover");
		}
		return font;
	}
	public static Font createFont(String path,String name){
		URL url = System.class.getResource(path);
		if (url ==null){
			System.out.println("Unable to load font: "+ path);
		}
		Font font = null;
		try {
			font = Font.createFont(Font.TYPE1_FONT, url.openStream());
		} catch (FontFormatException | IOException e) {
			System.out.println("Font recover");
		}
		return font;
	}
	public static ImageIcon createIcon(String path){
		URL url = System.class.getResource(path);
		if (url == null){
			System.out.println("Error loading image");
		}
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}
}
