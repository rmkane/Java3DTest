import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class AestheticsPanel implements ListSelectionListener  {
	
	public AestheticsPanel(JPanel panel)
	{
	    panel.setLayout(new BorderLayout());
	    
	    
	    

	    JLabel aesthetics_title = new JLabel(" A E S T H E T I C S");
	    aesthetics_title.setOpaque(true);
	    aesthetics_title.setBackground(Color.lightGray);
	    

	    panel.add(aesthetics_title, BorderLayout.PAGE_START);
		
		
	    //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	    JPanel aestheticsPanel = new JPanel();
	    aestheticsPanel.setLayout(new GridBagLayout());
	    panel.add(aestheticsPanel, BorderLayout.LINE_START);
	    
	    
	    GridBagConstraints c = new GridBagConstraints();
	    //c.fill = GridBagConstraints.HORIZONTAL;
	    c.insets = new Insets(10, 2, 2, 2); // 5-pixel margins on all sides
	    c.ipadx = 13;
	    //c.anchor = GridBagConstraints.NORTHWEST;
	    

	    //Color colors[] = { Color.black, Color.blue, Color.cyan, Color.darkGray,
	           // Color.gray, Color.green, Color.lightGray, Color.magenta,
	           // Color.orange, Color.pink, Color.red, Color.white, Color.yellow };
	    
	    String colornames[] = { "", "", "", "", "", "", "", "", "", "", "", "", "" };
	    String faces[] = { "Face 1", "Face 2", "Face 3", "Face 4", "Face 5" };
	    
	    JComboBox faceColors = new JComboBox(colornames);
	    JComboBox faceSelection = new JComboBox(faces);
	    JComboBox edgeColors = new JComboBox(colornames);
	    JComboBox edgeWeight = new JComboBox(colornames);
	    
	    
	    c.gridx = 1;
	    c.gridy = 0;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = 1.0;
	    c.weighty = 0.1;
	    c.anchor = GridBagConstraints.NORTHWEST;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    //aestheticsPanel.add(aesthetics_title, c);
	    

	    faceSelection.setFont(new Font("sansserif",Font.PLAIN,11));
	    
	    
	    
	    c.gridx = 1;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = 0.0;
	    c.weighty = 0.1;
	    c.anchor = GridBagConstraints.NORTHWEST;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = -6;
	    aestheticsPanel.add(faceSelection, c);
	    
	    
	    //faceColors.setMaximumSize(new Dimension(15, 15));
	    c.gridx = 2;
	    c.gridy = 1;
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.weightx = 0.0;
	    c.weighty = 0.1;
	    c.ipady = -6;
	    c.anchor = GridBagConstraints.NORTHWEST;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    aestheticsPanel.add(faceColors, c);
	    
	    
	    
	    c.gridx = 1;
	    c.gridy = 2;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = 0.0;
	    c.weighty = 0.1;
	    c.ipady = 0;
	    c.anchor = GridBagConstraints.LINE_START;
	    c.fill = GridBagConstraints.NONE;
	    
	    aestheticsPanel.add(new JLabel (" Edges:"), c);
	    
	    
	    //edgeColors.setMaximumSize(new Dimension(15, 15));
	    c.gridx = 2;
	    c.gridy = 2;
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.weightx = 0.0;
	    c.weighty = 0.0;
	    c.ipady = -6;
	    c.anchor = GridBagConstraints.LINE_START;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    aestheticsPanel.add(edgeColors, c);
	    
	    
	    
	    c.gridx = 1;
	    c.gridy = 3;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = 0.0;
	    c.weighty = 0.1;
	    c.ipady = 0;
	    c.anchor = GridBagConstraints.LINE_START;
	    c.fill = GridBagConstraints.NONE;
	    aestheticsPanel.add(new JLabel (" Edge Weight:"), c);
	    
	    
	    edgeColors.setPreferredSize(new Dimension(5, 24));
	    c.gridx = 2;
	    c.gridy = 3;
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.weightx = 0.0;
	    c.weighty = 0.1;
	    c.ipady = -6;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    
	    aestheticsPanel.add(edgeWeight, c);



	}
	

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("3D GUI");

		frame.setSize(150, 250);

		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new Thread() {
					public void run() 
					{
						System.exit(0);
					}
				}.start();
			}
		});
		
		
	    JPanel panel = new JPanel();
	    frame.add(panel);
	    
		new AestheticsPanel(panel);
		
		

		frame.setVisible(true);
	}


	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
