package Components;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Utils.Util;

@SuppressWarnings("serial")
public class CIcon extends JButton {
	
	public CIcon(ImageIcon icon){
		super(icon);
		setBorderPainted( false );
		setOpaque(false);
		setContentAreaFilled(false);
		setFocusable(false);
		setToolTipText("Curiosity");
		
		
		addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent evt) {
	        	if (getModel().isRollover()) {
	            	setIcon(Util.createIcon("/Assets/C_focus.png"));
	            	
	            } else {
	            	setIcon(Util.createIcon("/Assets/C.png"));
	            }
	        }
	    });
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