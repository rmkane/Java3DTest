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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

import javax.media.j3d.Group;
import javax.media.j3d.SceneGraphObject;
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




public class CurrentShapesPanel implements ListSelectionListener, KeyListener  {
	
	DefaultListModel listModel;
	JList list;
	
	public JList getList() {
		return list;
	}


	public void setList(JList list) {
		this.list = list;
	}


	public CurrentShapesPanel(JPanel panel)
	{
	    panel.setLayout(new BorderLayout());
	    
	    //panel.addKeyListener(this);
	    

	    JLabel resize_title = new JLabel("S H A P E S");
	    resize_title.setOpaque(true);
	    resize_title.setBackground(Color.lightGray);
	    

	    panel.add(resize_title, BorderLayout.PAGE_START);
	    
	    

	    JPanel currentShapesPanel = new JPanel();
	    panel.add(currentShapesPanel);
	    
	    
	    
	    listModel = new DefaultListModel();
	    
	    list = new JList(listModel);
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        list.addKeyListener(this);
        
        
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
            	//System.out.println(list.getSelectedValue());
            	
            	
              Enumeration e = GUI_3D.getSwingTest().getSceneBranchGroup().getAllChildren();
  		      int index = 0;
  		      
  		      while (e.hasMoreElements() != false) {
  		    	  Object sgObject = ((SceneGraphObject) (e.nextElement()));
  		    	  
  		    	  if (((String)((Group)((Group)(((Group)sgObject).getChild(0))).getChild(0)).getChild(0).getUserData())
  		    			  .equalsIgnoreCase((String) list.getSelectedValue())) {
  	            	GUI_3D.getSwingTest().setShapeClicked(((Group)((Group)(((Group)sgObject).getChild(0))).getChild(0)).getChild(0));
  	            	System.out.println(GUI_3D.getSwingTest().getShapeClicked());
  		    	  }
  		      }		     
            	
              //if (evt.getValueIsAdjusting())
              //  return;
              //System.out.println("Selected from " + evt.getFirstIndex() + " to " + evt.getLastIndex());
            }
          });
        
        
        JScrollPane listScroll = new JScrollPane(list);
        listScroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
		//listScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listScroll.setPreferredSize(new Dimension(147, 143));
	    
	    currentShapesPanel.add(listScroll);
	    currentShapesPanel.add(Box.createVerticalGlue());

	    list.setFixedCellHeight(27);
	    //list.setFixedCellWidth(128);
	}
	
	
	public DefaultListModel getListModel() {
		return listModel;
	}


	public void setListModel(DefaultListModel listModel) {
		this.listModel = listModel;
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


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void keyPressed(KeyEvent e) {
	//	if (e.getKeyCode() == KeyEvent.VK_DELETE) {
	//		System.out.println("selected = " + list.getSelectedValue());
	//		System.out.println("shapeclicked = " + GUI_3D.getSwingTest().getShapeClicked().getUserData());
			//listModel.removeElement(list.getSelectedValue());
	//		GUI_3D.getSwingTest().removeShape(((String) GUI_3D.getSwingTest().getShapeClicked().getUserData()));
	//	}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
