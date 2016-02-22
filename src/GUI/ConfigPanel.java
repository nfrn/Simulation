package GUI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Components.FpsSlider;
import Components.InputDialog;
import Components.ModeList;
import Components.StartBtn;
import Utils.Util;

@SuppressWarnings("serial")
public class ConfigPanel extends JPanel implements ActionListener,ChangeListener{
	
	static final int FPS_MIN = 0;
	static final int FPS_MAX = 100;
	static final int FPS_INIT = 20;
	
	private JLabel nIndLabel, nFoodLabel,fpsLabel;
	private JTextField  nIndField, nFoodField;
	private JSlider fpsSlider;
	private StartBtn startBtn;
	
	private JList<String> mode;
	
	private InputDialog input;
	
	private StringListener stringListener;
	private SliderListener sliderListener;
	
	public ConfigPanel(){
		iniComponents();
		dimConfig();
		borderConfig();
		gridConfig();
	}

	public void iniComponents(){
		nIndLabel = new JLabel(" Number People: ");
		nIndLabel.setForeground(new Color(0xffa500));
		
		nFoodLabel = new JLabel(" Number Food: ");
		nFoodLabel.setForeground(new Color(0xffa500));
		
		nIndField = new JTextField(15);
		nIndField.setFont(Util.createFont("/Fonts/journal.ttf").deriveFont(Font.BOLD, 22));
		nIndField.setForeground(new Color(0x000000));
		nIndField.setHorizontalAlignment(JTextField.CENTER);
		
		nFoodField = new JTextField(15);
		nFoodField.setFont(Util.createFont("/Fonts/journal.ttf").deriveFont(Font.BOLD, 22));
		nFoodField.setForeground(new Color(0x000000));
		nFoodField.setHorizontalAlignment(JTextField.CENTER);
		
		fpsLabel = new JLabel(" Simulation speed: ");
		fpsLabel.setForeground(new Color(0xffa500));
		
		startBtn = new StartBtn(createIcon("/Assets/startBtn.png"));
		startBtn.addActionListener(this);
		
		fpsSlider = new FpsSlider(FPS_MIN,FPS_MAX,FPS_INIT);
		
		mode = new ModeList();
		mode.setSelectedIndex(0);
		
		input = new InputDialog((Window) getParent());
		
	}
	public void dimConfig(){
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		setOpaque(false);
	}
	public void borderConfig(){
		Border innerBorder = BorderFactory.createTitledBorder("Configuration:");
		((TitledBorder) innerBorder).setTitleFont(Util.createFont("/Fonts/Streetwear.otf").deriveFont(Font.PLAIN, 30));
		((TitledBorder) innerBorder).setTitleColor(new Color(0xEECD86));
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	}
	public void gridConfig(){
		setLayout( new GridBagLayout() );
		
		GridBagConstraints gc = new GridBagConstraints();
		
		//1-Row///
		
		gc.weightx = 1;
		gc.weighty = 0.03;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets( 0,0,0,0);
		add(nIndLabel, gc);
		
		//2-Row///
		gc.gridx = 0;
		gc.gridy = 1;
		gc.insets = new Insets( 0,0,0,0);
		gc.anchor = GridBagConstraints.NORTH;
		add(nIndField, gc);

		//3-Row///
		gc.weightx = 1;
		gc.weighty = 0.03;
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.insets = new Insets( 0,0,0,0);
		gc.anchor = GridBagConstraints.CENTER;
		add(nFoodLabel, gc);
		
		//4-Row///
		gc.gridx = 0;
		gc.gridy = 3;
		gc.insets = new Insets( 0,0,0,0);
		gc.anchor = GridBagConstraints.NORTH;
		add(nFoodField, gc);
		
		//7-Row//
		gc.weightx = 1;
		gc.weighty = 0.03;
		
		gc.gridx = 0;
		gc.gridy = 4;
		gc.insets = new Insets( 0,0,0,0);
		gc.anchor = GridBagConstraints.NORTH;
		add(mode, gc);
		
		//8-Row///
		gc.weightx = 1;
		gc.weighty = 0.03;
		
		gc.gridwidth = 2;
		gc.gridx = 0;
		gc.gridy = 5;
		gc.insets = new Insets( 0,0,0,0);
		gc.anchor = GridBagConstraints.NORTH;
		add(startBtn, gc);
		
		//5-Row///	
		gc.weightx = 1;
		gc.weighty = 0.03;
		gc.gridx = 0;
		gc.gridy = 6;
		gc.insets = new Insets( 0,0,0,0);
		gc.anchor = GridBagConstraints.CENTER;
		add(fpsLabel, gc);
			
		//6-Row///
				
		gc.weightx = 1;
		gc.weighty = 1;
				
		gc.gridx = 0;
		gc.gridy = 7;
		gc.insets = new Insets( 0,0,0,0);
		gc.anchor = GridBagConstraints.NORTH;
		add(fpsSlider, gc);
				
	}

	public void actionPerformed(ActionEvent e){
		JButton button = (JButton) e.getSource();
		if (button==startBtn){
			boolean ok = true;
			if (stringListener!=null){
				if(nIndField.getText()==null || nIndField.getText().equals("")){
					input.setString("Forgot to input data in the field: Number People !");
					input.setVisible(true);
				}
				else if(nFoodField.getText()==null || nFoodField.getText().equals("")){
					input.setString("Forgot to input data in the field: Number Food !");
					input.setVisible(true);
				}
				try{
					Integer.parseInt(nIndField.getText());
					Integer.parseInt(nFoodField.getText());
					}catch(NumberFormatException exception){
						input.setString("Input must be a Number!");
						input.setVisible(true);
						ok = false;
						nIndField.setText("");
						nFoodField.setText("");
					}
				}
			if(ok){
				int i = Integer.parseInt(nIndField.getText());
				int f = Integer.parseInt(nFoodField.getText());
				stringListener.inputEmitted(i,f,(int)fpsSlider.getValue(),mode.getSelectedValue());
				fpsSlider.addChangeListener(this);	
			}
		}
	}
	public void stateChanged(ChangeEvent e){
		JSlider slider = (JSlider) e.getSource();
		if (slider == fpsSlider){
			if (sliderListener!=null){
				if (!slider.getValueIsAdjusting()) {
			        int fps = (int)slider.getValue();
			        sliderListener.setFps(fps);
				}
			}
		}
	}
	
	public void setStringListener(StringListener stringListener){
		this.stringListener = stringListener;
	}
	public void setSliderListener(SliderListener sliderListener){
		this.sliderListener=sliderListener;
	}

	private ImageIcon createIcon(String path){
		URL url = getClass().getResource(path);
		if (url == null){
			System.out.println("Error loading image");
		}
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}



}