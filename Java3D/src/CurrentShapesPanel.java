/*	3D Geometric Object Rendering Application
    Copyright (C) 2011  Jennifer Hill, Ryan Kane, Sean Weber, Donald Shaner, Dorothy Kirlew

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class CurrentShapesPanel implements ListSelectionListener  {
	
	public CurrentShapesPanel(JPanel panel)
	{
	    panel.setLayout(new BorderLayout());
	    

	    JLabel resize_title = new JLabel("S H A P E S");
	    resize_title.setOpaque(true);
	    resize_title.setBackground(Color.lightGray);
	    

	    panel.add(resize_title, BorderLayout.PAGE_START);
	    
	    

	    JPanel currentShapesPanel = new JPanel();
	    panel.add(currentShapesPanel);
	    
	    
	    
	    String[] data = {"( shape name )", "( shape name )", "( shape name )", 
	    		"( shape name )", "( shape name )"};
	    JList list = new JList(data);
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //list.setSelectedIndex(0);
        list.addListSelectionListener(this);
	    
	    currentShapesPanel.add(list);
	    currentShapesPanel.add(Box.createVerticalGlue());

	    list.setFixedCellHeight(25);
	    list.setFixedCellWidth(140);
	}
	

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("3D GUI");

		frame.setSize(150, 230);

		
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
	    
		new CurrentShapesPanel(panel);
		
		

		frame.setVisible(true);
	}


	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
