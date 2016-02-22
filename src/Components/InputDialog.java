package Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import Utils.Util;

@SuppressWarnings("serial")
public class InputDialog extends JDialog implements ActionListener{
	
	private JButton okBtn;
	private JLabel  message;
	
	public InputDialog(Window parent){
		
		super(parent,"Input Error.",ModalityType.APPLICATION_MODAL);
		
		okBtn = new JButton("Ok");
		okBtn.setFont(Util.createFont("/Fonts/Strong.ttf").deriveFont(Font.BOLD, 13));
		okBtn.addActionListener(this);

		setLayout(new FlowLayout());
		
		message = new JLabel();
		message.setForeground(new Color(0x000000));
		message.setFont(Util.createFont("/Fonts/Strong.ttf").deriveFont(Font.BOLD, 13));
		
		Dimension dim = okBtn.getPreferredSize();
		dim.width = 400;
		message.setPreferredSize(dim);
		
		add(message);
		add(okBtn);
		
		pack();
		setLocationRelativeTo(parent);	
		
		setVisible(false);
	}	
	
	public void setString(String warning){
		message.setText(warning);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button==okBtn){
			setVisible(false);
		}
	}
}
