package Components;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ClockIcon extends JButton {
	
	public ClockIcon(ImageIcon icon){
		super(icon);
		setBorderPainted( false );
		setOpaque(false);
		setContentAreaFilled(false);
		setFocusable(false);
		setToolTipText("Clock");
	}
	Shape shape;
	public boolean contains(int x, int y) {
	   if (shape == null || 
	      !shape.getBounds().equals(getBounds())) {
	      shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
	   }
	   return shape.contains(x, y);
	}
}