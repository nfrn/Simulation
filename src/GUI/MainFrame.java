package GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.ToolTipManager;



@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
	BackgroundPanel back;
	
	public MainFrame(ImageIcon background){
		super("Simulator");
		ToolTipManager.sharedInstance().setInitialDelay(0);
		
		back  = new BackgroundPanel(background.getImage());
		getContentPane().add(back);
		
		int width = (int) back.infoPanel.getPreferredSize().getWidth() + (int) back.configPanel.getPreferredSize().getWidth() + (int) back.simulationPanel.getPreferredSize().getWidth();
		int height = (int) back.infoPanel.getPreferredSize().getHeight() + (int) back.configPanel.getPreferredSize().getHeight() + (int) back.simulationPanel.getPreferredSize().getHeight();
		
		setBounds(100, 100, width+100, height+ 100);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}