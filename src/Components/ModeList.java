package Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Utils.Util;

	@SuppressWarnings("serial")
	public class ModeList extends JList<String>{
		public ModeList(){
		DefaultListModel<String> model = new DefaultListModel<String>();
	
		model.addElement("Grid Mode");
		model.addElement("Free mode");

		Border innerBorder = BorderFactory.createTitledBorder("Movement mode:");
		((TitledBorder) innerBorder).setTitleFont(Util.createFont("/Fonts/journal.ttf").deriveFont(Font.BOLD, 40));
		((TitledBorder) innerBorder).setTitleColor(new Color(0xffa500));
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder));

		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		setFont(Util.createFont("/Fonts/Classic.ttf").deriveFont(Font.BOLD, 17));
		setModel(model);
		setCellRenderer(new TransparentListRenderer());
		setPreferredSize(new Dimension(250,120));
		
		setFocusable(false);
		
		setOpaque(false);
	}
}