package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import Components.LifeBar;

import Core.Element;
import Core.Individual;

@SuppressWarnings("serial")
public class LifePanel extends JPanel implements MouseListener{
	private ArrayList<JProgressBar> currentlife;  
	
	private BarListener barListener;
	private static final int ROWS= 5;
	public LifePanel(){
		currentlife = new ArrayList<JProgressBar>();
		setOpaque(false);
	}
	public void createBars(TreeMap<String,Element>_population){
		for(Map.Entry<String, Element> entry : _population.entrySet()){
			Individual ind = (Individual) entry.getValue();
			JProgressBar indBar = new JProgressBar();
			indBar.setUI(new LifeBar());
			indBar.setMinimum(0);
			indBar.setMaximum(100);
			indBar.setName(ind.getName());
			indBar.setFocusable(true);
			indBar.addMouseListener(this);
			currentlife.add(indBar);
		}	
	gridConfig();
	}

	public void updateBars(TreeMap<String,Element>_population){
		ListIterator<JProgressBar>  IItr = currentlife.listIterator();
		while(IItr.hasNext()){
			JProgressBar ind = IItr.next();
			Element ind2 = _population.get(ind.getName());
			if(ind2.isAlive()){
				ind.setValue(ind2.getLife());
			}
		}
	}
	
	public void clear(){
		ListIterator<JProgressBar>  FItr = currentlife.listIterator();
		while(FItr.hasNext()){
			JProgressBar ind = FItr.next();
			ind.setVisible(false);
			remove(ind);
		}
		currentlife.clear();
	}
	
	public void gridConfig(){
		setLayout( new GridBagLayout() );
		GridBagConstraints gc = new GridBagConstraints();
		int nRows =currentlife.size()/ROWS;
		int remaining = currentlife.size()%ROWS;

		int ix;
		int iy;
		for(iy = 0; iy < nRows; iy++){
			for(ix = 0 ; ix < ROWS ; ix++){

				gc.gridx = ix;
				gc.gridy = iy;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.CENTER;
				gc.insets = new Insets( 0,0,0,0);
				currentlife.get(ix+(iy*ROWS)).setVisible(true);
				currentlife.get(ix+(iy*ROWS)).setBorderPainted(false);
				add(currentlife.get(ix+(iy*ROWS)), gc);
			}
		}
		
		int i;
		for(i=0; i < remaining; i++){
			gc.gridx = i;
			gc.gridy = iy;
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.CENTER;
			gc.insets = new Insets( 0,0,0,0);
			currentlife.get(i+(iy*ROWS)).setVisible(true);
			currentlife.get(i+(iy*ROWS)).setBorderPainted(false);
			add(currentlife.get(i+(iy*ROWS)), gc);
		}
		Dimension dim = getPreferredSize();
		dim.width = ROWS*150;
		dim.height = (nRows) * 25;
		setPreferredSize(dim);
	}
	
	
	public void setBarListener(BarListener barListener) {
		this.barListener = barListener;
	}
	
	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		JProgressBar pBar = (JProgressBar) e.getSource();
		if(barListener!=null){
			barListener.setFocus(pBar.getName());
		}
	}

	public void mouseExited(MouseEvent e) {
		JProgressBar pBar = (JProgressBar) e.getSource();
		if(barListener!=null){
			barListener.resetFocus(pBar.getName());
		}
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

