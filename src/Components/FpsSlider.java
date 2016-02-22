package Components;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JSlider;

@SuppressWarnings("serial")
public class FpsSlider extends JSlider {
	public FpsSlider(int a, int b, int c){
		super(a,b,c);
		setFont(new Font("Arial",Font.PLAIN ,12));
		setForeground(new Color(0xCCCCCC));
		setMajorTickSpacing(20);
		setMinorTickSpacing(5);
		setPaintTicks(true);
		setPaintLabels(true);
		setOpaque(false);
	}

  
}
