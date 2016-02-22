package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Core.Element;

@SuppressWarnings("serial")
public class AnimationPanel extends JPanel{
	
	private static final int F_INC = 35; 
	private Image background;
	private ArrayList<JLabel> alive, food;
	private ColisionListener colisionListener; 
	
	public AnimationPanel(ImageIcon background){
		this.background = background.getImage();
		alive = new ArrayList<JLabel>();
		food = new ArrayList<JLabel>();
		setLayout(null);
	}
	
	public void createPeople(TreeMap<String,Element>_population){
		for(Map.Entry<String, Element> entry : _population.entrySet()){
			ImageIcon IndividualIcon = new ImageIcon(entry.getValue().getImage());
			JLabel FoodLabel = new JLabel( IndividualIcon);
			double x = entry.getValue().getCoord().getX();
			double y =entry.getValue().getCoord().getY();
			FoodLabel.setBounds((int)(x*IndividualIcon.getIconWidth()), (int)(y*IndividualIcon.getIconHeight()), IndividualIcon.getIconWidth(), IndividualIcon.getIconHeight());
			FoodLabel.setName(entry.getValue().getName());
			alive.add(FoodLabel);
			add(FoodLabel);
		}	
	}
	public void createFood(TreeMap<String,Element>_population){
		for(Map.Entry<String, Element> entry : _population.entrySet()){
			ImageIcon FoodIcon = new ImageIcon(entry.getValue().getImage());
			JLabel FoodLabel = new JLabel(FoodIcon);
			double x = entry.getValue().getCoord().getX();
			double y = entry.getValue().getCoord().getY();
			FoodLabel.setBounds((int)(x*FoodIcon.getIconWidth()),(int) (y*FoodIcon.getIconHeight()), FoodIcon.getIconWidth(), FoodIcon.getIconHeight());
			FoodLabel.setName(entry.getValue().getName());
			food.add(FoodLabel);
			add(FoodLabel);
		}	
	}
	
	public synchronized void updateBoard(TreeMap<String,Element>_population){
		ListIterator<JLabel>  IItr = alive.listIterator();
		while(IItr.hasNext()){
			JLabel ind = IItr.next();
			Element ind2 = _population.get(ind.getName());
			if(ind2.isAlive()){
				ImageIcon IndividualIcon = new ImageIcon(ind2.getImage());
				double x = ind2.getCoord().getX();
				double y  = ind2.getCoord().getY();
				ind.setBounds((int)(x*IndividualIcon.getIconWidth()), (int)(y*IndividualIcon.getIconHeight()), IndividualIcon.getIconWidth(), IndividualIcon.getIconHeight());;
				ind.setIcon(IndividualIcon);
				ListIterator<JLabel> fItr = food.listIterator();
				while(fItr.hasNext()) {
					JLabel element = fItr.next();
					if(intersect(ind,element)){
						colisionListener.incLife(F_INC,ind.getName());
						fItr.remove();
						element.setVisible(false);
						remove(element);
					}
				}
			}
			else{
				IItr.remove();
				ind.setVisible(false);
				remove(ind);
			}
		}
		revalidate();
		repaint();
	}
	
	public void clear(){
		ListIterator<JLabel>  IItr = alive.listIterator();
		while(IItr.hasNext()){
			JLabel ind = IItr.next();
			ind.setVisible(false);
			remove(ind);
		}
		ListIterator<JLabel>  FItr = food.listIterator();
		while(FItr.hasNext()){
			JLabel ind = FItr.next();
			ind.setVisible(false);
			remove(ind);
		}
		
		alive.clear();
		food.clear();
	}
	
	public boolean intersect(JLabel a, JLabel b){
		Area areaA = new Area(a.getBounds());
	    Area areaB = new Area(b.getBounds());

	    return areaA.intersects(areaB.getBounds2D());
	}
    protected void paintComponent(Graphics g) {
        if (background != null) {                
            Insets insets = getInsets();

            int width = getWidth() - 1 - (insets.left + insets.right);
            int height = getHeight() - 1 - (insets.top + insets.bottom);

            int x = (width - background.getWidth(this)) / 2;
            int y = (height - background.getHeight(this)) / 2;

            g.drawImage(background, x, y, this);                
        }

    }

    public void setColisionListener(ColisionListener colisionListener){
    	this.colisionListener=colisionListener;
    }

}
