package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import Core.Element;
import Utils.Util;

@SuppressWarnings("serial")
public class SimulationPanel extends JPanel{
	
	public AnimationPanel animationPanel;
	private LifePanel lifePanel; 
	private JViewport viewport;
	
	public SimulationPanel(){
		iniComponents();
		dimConfig();
		borderConfig();
		gridConfig();
		setOpaque(false);
	}
	
	
	
	public void createPeople(TreeMap<String,Element>_population){
		animationPanel.createPeople(_population);	
	}
	public void createFood(TreeMap<String,Element>_population){
		animationPanel.createFood(_population);	
	}
	public void createBars(TreeMap<String,Element>_population){
		lifePanel.createBars(_population);
	}
	
	public void updateBoard(TreeMap<String,Element>_population){
		animationPanel.updateBoard(_population);
		repaint();
	}
	public void updateBars(TreeMap<String,Element>_population){
		lifePanel.updateBars(_population);
		repaint();
	}
	
	public void clear(){
		lifePanel.clear();
		animationPanel.clear();
	}
	
	public void setColisionListener(ColisionListener cl){
		animationPanel.setColisionListener(cl);
	}
	
	public void gridConfig(){
		setLayout( new GridBagLayout() );
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 0.05;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets( 0,0,0,0);
		add(animationPanel, gc);
		
		gc.weightx = 1;
		gc.weighty = 0.05;
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets( 0,0,0,0);
		JScrollPane scroll = new JScrollPane(lifePanel);
		Dimension dim = lifePanel.getPreferredSize();
		dim.width = 800;
		dim.height = 200;
		scroll.setPreferredSize(dim);
		viewport.setView(lifePanel);
		viewport.setOpaque(false);
		scroll.setViewport(viewport);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		add(scroll, gc);
	}

	public void iniComponents(){
		animationPanel = new AnimationPanel(Util.createIcon("/Assets/Back.png"));
		animationPanel.setBorder(new MatteBorder(15, 15, 15, 15, Util.createIcon("/Assets/border.jpg")));
		animationPanel.setOpaque(false);
		Dimension dim = animationPanel.getPreferredSize();
		dim.width = 915;
		dim.height = 405;
		animationPanel.setPreferredSize(dim);
		lifePanel = new LifePanel();
		lifePanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		viewport = new JViewport();
	}
	public void dimConfig(){
		Dimension dim = getPreferredSize();
		dim.width = 920;
		setPreferredSize(dim);
	}
	public void borderConfig(){
		Border innerBorder = BorderFactory.createTitledBorder("Simulation:");
		((TitledBorder) innerBorder).setTitleFont(Util.createFont("/Fonts/Streetwear.otf").deriveFont(Font.PLAIN, 30));
		((TitledBorder) innerBorder).setTitleColor(new Color(0xEECD86));
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	}

	public void setBarListener(BarListener barListener){
		lifePanel.setBarListener(barListener);
	}
}
