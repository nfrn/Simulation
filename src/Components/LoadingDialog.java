package Components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import Utils.Util;

@SuppressWarnings("serial")
public class LoadingDialog extends JDialog{
	
	private JButton cancelBtn;
	private JProgressBar loadingBar;
	
	public LoadingDialog(Window parent){
		
		
		super(parent,"Loading Assets ...",ModalityType.APPLICATION_MODAL);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(Util.createFont("/Fonts/Strong.ttf").deriveFont(Font.BOLD, 13));
		loadingBar = new JProgressBar();
	
		setLayout(new FlowLayout());
		
		Dimension dim = cancelBtn.getPreferredSize();
		dim.width = 400;
		loadingBar.setPreferredSize(dim);
		
		add(loadingBar);
		add(cancelBtn);
		
		pack();
		
		setLocationRelativeTo(parent);
	}
	
	public void setMax(int count){
		loadingBar.setMaximum(count);
	}
	
	public void setValue(int count){
		int current = loadingBar.getValue();
		int dif = count - current;
		for(int i = 0; i <= dif; i++){
			loadingBar.setValue(current + i);
		}
	}

	@Override
	public void setVisible(final boolean visible) {
		SwingUtilities.invokeLater( new Runnable(){
			public void run(){
				
				if(visible==false){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					loadingBar.setValue(0);
				}
				
				LoadingDialog.super.setVisible(visible);
			}
		});
	}
	
}
