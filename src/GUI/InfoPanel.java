package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Components.CIcon;
import Components.ClockIcon;
import Components.SIcon;
import Utils.Util;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel {
	private static JLabel populationLabel;
	private static JLabel generationLabel;
	
	private CIcon cIcon;
	private SIcon sIcon;
	private ClockIcon clock;
	
	public InfoPanel(){
		iniComponents();
		dimConfig();
		borderConfig();
		gridConfig();
	}

	public void dimConfig(){
		Dimension dim = getPreferredSize();
		dim.width = 400;
		dim.height = 800;
		setPreferredSize(dim);
		setOpaque(false);
	}	
	private void gridConfig() {
			setLayout( new GridBagLayout() );
			GridBagConstraints gc = new GridBagConstraints();
			
			gc.weightx = 1;
			gc.weighty = 0.03;
			
			gc.gridx = 0;
			gc.gridy = 0;
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.NORTH;

			add(generationLabel,gc);
			
			gc.gridx = 0;
			gc.gridy = 1;
			gc.insets = new Insets( 0,0,0,0);
			gc.anchor = GridBagConstraints.NORTH;
			JScrollPane scroll = new JScrollPane(populationLabel);
			Dimension dim = scroll.getPreferredSize();
			dim.width = 300;
			dim.height = 400;
			scroll.setPreferredSize(dim);
			add(scroll,gc);
			
			gc.weightx = 0.03;
			gc.weighty = 0.03;
			
			gc.gridx = 0;
			gc.gridy = 2;
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.NORTH;

			add(clock,gc);
			
			gc.weightx = 0.03;
			gc.weighty = 0.03;
			
			gc.gridx = 1;
			gc.gridy = 2;
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.NORTH;

			add(cIcon,gc);
			
			gc.weightx = 0.03;
			gc.weighty = 0.03;
			
			gc.gridx = 2;
			gc.gridy = 2;
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.NORTH;

			add(sIcon,gc);
			
			
		
	}
	private void borderConfig() {
		Border innerBorder = BorderFactory.createTitledBorder("Statistics:");
		((TitledBorder) innerBorder).setTitleFont(Util.createFont("/Fonts/Streetwear.otf").deriveFont(Font.PLAIN, 30));
		((TitledBorder) innerBorder).setTitleColor(new Color(0xEECD86));
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	}
	private void iniComponents() {
		populationLabel = new JLabel();
		populationLabel.setFont(Util.createFont("/Fonts/journal.ttf").deriveFont(Font.BOLD, 13));
		populationLabel.setForeground(new Color(0x666666));
		generationLabel = new JLabel();
		generationLabel.setForeground(new Color(0x9ffa500));
		generationLabel.setText(" Generation:    ");
		cIcon = new CIcon(Util.createIcon("/Assets/C.png"));
		sIcon = new SIcon(Util.createIcon("/Assets/S.png"));
		clock = new ClockIcon(Util.createIcon("/Assets/clock.png"));
	}
	public static void setPopText(String text){
		populationLabel.setText(text);
	}
	public static void setGenText(String text){
		generationLabel.setText(text);
	}

}
