package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import Components.LoadingDialog;
import Core.Population;

@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel{
	
	public InfoPanel  infoPanel; 
	public ConfigPanel configPanel;
	public SimulationPanel simulationPanel;
	
	private LoadingDialog loadingDialog;
	
	private Population population;
	private Timer timer;
	private Image img;
	
	public BackgroundPanel(Image img){
		this.img = img ;

		setLayout(new BorderLayout());
		
		infoPanel = new InfoPanel();
		configPanel = new ConfigPanel();
		simulationPanel = new SimulationPanel();
		
		loadingDialog = new LoadingDialog((Window) getParent());
		
		infoPanel.setBackground(new Color(0,0,0,65));
		configPanel.setBackground(new Color(0,0,0,65));
		simulationPanel.setBackground(new Color(0,0,0,65));
		
		add(infoPanel,BorderLayout.EAST);
		add(configPanel, BorderLayout.WEST);
		add(simulationPanel,BorderLayout.CENTER);
		
		int width = (int) infoPanel.getPreferredSize().getWidth() + (int) configPanel.getPreferredSize().getWidth() + (int) simulationPanel.getPreferredSize().getWidth();
		int height = (int) infoPanel.getPreferredSize().getHeight() + (int) configPanel.getPreferredSize().getHeight() + (int) simulationPanel.getPreferredSize().getHeight();
		setBounds(100, 100, width+100, height+ 100);
		setVisible(true);
		
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		simulationPanel.setBarListener( new BarListener(){
			public void setFocus(String name) {
			population.setFocus(name);
			}
			public void resetFocus(String name) {
			population.clearFocus(name);
			}	
		});
		
		configPanel.setStringListener(new StringListener(){

			public void inputEmitted(int i , int f, int s, String m) {
				if(timer!=null){
					timer.stop();
				}
				if(population!=null){
					simulationPanel.clear();
					population.clear();
				}
				setInicialAssets(i,f,s,m);
			}
		});
		configPanel.setSliderListener(new SliderListener(){
			public void setFps(int fps) {
				timer.stop();
				if(fps!=0){
					timer.setDelay(1000/fps);
					timer.start();
				}
			}
		});
		simulationPanel.setColisionListener(new ColisionListener(){
			public void incLife(int value, String name) {
				population.get_population().get(name).incLife(value);
			}
			
		});
	}
	private void updatePopulation(){
			population.update();
			if(population.isDied()){
				timer.stop();
			}
		}
		@SuppressWarnings("static-access")
	private void updateInfo(){
			infoPanel.setPopText(population.toString());
			infoPanel.setGenText("  Generation: " + population.getGeneration() + " ");
		}
	private void updateSimulation(){
			simulationPanel.updateBoard(population.get_population());
			simulationPanel.updateBars(population.get_population());
			repaint();
		}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img,0,0,null);
	}

	@SuppressWarnings("static-access")
	public void setInicialAssets(int i, int f, int s, String m){
		
		loadingDialog.setMax(100);
		
		loadingDialog.setVisible(true);
		
		SwingWorker<List<String>,Integer> worker = new SwingWorker<List<String>,Integer>(){

			protected List<String> doInBackground() throws Exception {
				int count = 0;
				List<String> status = new ArrayList<String>();
				population = new Population(i,f);
				population.setMode(m);
				count+=15;
				publish(count);
				infoPanel.setPopText(population.toString());
				count+=35;
				publish(count);
				infoPanel.setGenText("  Generation: " + population.getGeneration() + " ");
				count+=10;
				publish(count);
				simulationPanel.createBars(population.get_population());
				count+=10;
				publish(count);
				simulationPanel.createFood(population.get_food());
				count+=15;
				publish(count);
				simulationPanel.createPeople(population.get_population());
				count+=15;
				publish(count);
				return status;
			}

			@SuppressWarnings("unused")
			@Override
			protected void done() {
				try {
					List<String> status = get();
				} catch (InterruptedException | ExecutionException e) {
					System.out.println("SwingWorker: error");
					e.printStackTrace();
				}
				timer = new Timer(s,new ActionListener(){
					public void actionPerformed(ActionEvent e){
						updatePopulation();
						updateInfo();
						updateSimulation();
						System.out.println("update");
					}
				});
				timer.setInitialDelay(0);
				timer.start();
				loadingDialog.setVisible(false);
			}

			@Override
			protected void process(List<Integer> count) {
				int loaded = count.get(count.size()-1);
				loadingDialog.setValue(loaded);
			}
			
		};
		worker.execute();
	}
}
