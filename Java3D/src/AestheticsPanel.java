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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.j3d.Appearance;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.Shape3D;
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
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;




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
	    

	    final Color3f colors[] = { Colors.RED, Colors.PINK, Colors.ORANGE, Colors.YELLOW, Colors.GREEN, Colors.BLUE, Colors.CYAN,
	    		Colors.PURPLE, Colors.BLACK, Colors.WHITE, Colors.GRAY };
	    
	    String colorNames[] = { "red", "pink", "orng", "ylw", "grn", "blue", "cyan", "prpl", "blk", "wht", "gray" };
	    String weights[] = { "1", "2", "3", "4", "5" };
	    
	    String faces[] = { };
	    

	    final JComboBox faceColors = new JComboBox(colorNames);
	    final JComboBox faceSelection = new JComboBox(faces);
	    final JComboBox edgeColors = new JComboBox(colorNames);
	    final JComboBox edgeWeight = new JComboBox(weights);
	    
	    edgeColors.setSelectedItem("blk");
	    
	    faceColors.setFont(new Font("sansserif",Font.PLAIN,10));
	    faceSelection.setFont(new Font("sansserif",Font.PLAIN,10));
	    edgeColors.setFont(new Font("sansserif",Font.PLAIN,10));
	    edgeWeight.setFont(new Font("sansserif",Font.PLAIN,10));
	    
	    
	    faceColors.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        	    Node shapeClicked = GUI_3D.getSwingTest().getShapeClicked();
        	    
        	    if (shapeClicked.getClass().getName().equals("TriangularPrism")) {
            	    for (int i = 0; i < 3; i++)
            	    	((TriangularPrism)shapeClicked).getTriPrismGeometry().setColor(i, colors[faceColors.getSelectedIndex()]);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("HexagonalPrism")) {
	        	    for (int i = 0; i < 18; i++)
	        	    	((HexagonalPrism)shapeClicked).getHexPrismGeometry().setColor(i, colors[faceColors.getSelectedIndex()]);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("RectangularPrism")) {
	        	    for (int i = 0; i < 4; i++)
	        	    	((RectangularPrism)shapeClicked).getRectPrismGeometry().setColor(i, colors[faceColors.getSelectedIndex()]);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("Pyramid")) {
	        	    for (int i = 0; i < 3; i++)
	        	    	((Pyramid)shapeClicked).getPyramidGeometry().setColor(i, colors[faceColors.getSelectedIndex()]);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("aSphere")) {
	        		((aSphere)shapeClicked).getAp().setMaterial(new Material(Colors.BLACK, Colors.BLACK, 
	        				colors[faceColors.getSelectedIndex()], Colors.WHITE, 30f));
	        	}
	        	else if (shapeClicked.getClass().getName().equals("aCylinder")) {
	        		((aCylinder)shapeClicked).getAp().setMaterial(new Material(Colors.BLACK, Colors.BLACK, 
	        				colors[faceColors.getSelectedIndex()], Colors.WHITE, 30f));
	        	}
            }});
	    
	    
	    edgeColors.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        	    Node shapeClicked = GUI_3D.getSwingTest().getShapeClicked();
        	    
        	    if (shapeClicked.getClass().getName().equals("TriangularPrism")) {
            	    for (int i = 0; i < 12; i++)
            	    	((TriangularPrism)shapeClicked).getTriPrismEdgeGeometry().setColor(i, colors[edgeColors.getSelectedIndex()]);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("HexagonalPrism")) {
	        	    for (int i = 0; i < 24; i++)
	        	    	((HexagonalPrism)shapeClicked).getHexPrismEdgeGeometry().setColor(i, colors[edgeColors.getSelectedIndex()]);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("RectangularPrism")) {
	        	    for (int i = 0; i < 24; i++)
	        	    	((RectangularPrism)shapeClicked).getRectPrismEdgeGeometry().setColor(i, colors[edgeColors.getSelectedIndex()]);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("Pyramid")) {
	        	    for (int i = 0; i < 18; i++)
	        	    	((Pyramid)shapeClicked).getPyramidEdgeGeometry().setColor(i, colors[edgeColors.getSelectedIndex()]);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("aSphere")) {
            		System.out.println("Sphere has no edges!");
	        	}
	        	else if (shapeClicked.getClass().getName().equals("aCylinder")) {
	        		System.out.println("Cylinder has no edges!");
	        		//((aCylinder) shapeClicked).getApp().setLineAttributes(lineattributes);
	        	}
            }});
	    
	    
	    edgeWeight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        	    Node shapeClicked = GUI_3D.getSwingTest().getShapeClicked();
        	                	
        	    LineAttributes lineattributes = new LineAttributes();
        	    lineattributes.setLineWidth(Float.valueOf(edgeWeight.getSelectedItem().toString()).floatValue());
        		lineattributes.setLineAntialiasingEnable(true);
        		lineattributes.setLinePattern(LineAttributes.PATTERN_SOLID);
        	    
        	    if (shapeClicked.getClass().getName().equals("TriangularPrism")) {
            		((TriangularPrism) shapeClicked).getApp().setLineAttributes(lineattributes);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("HexagonalPrism")) {
            		((HexagonalPrism) shapeClicked).getApp().setLineAttributes(lineattributes);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("RectangularPrism")) {
            		((RectangularPrism) shapeClicked).getApp().setLineAttributes(lineattributes);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("Pyramid")) {
            		((Pyramid) shapeClicked).getApp().setLineAttributes(lineattributes);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("aSphere")) {
            		System.out.println("Sphere has no edges!");
	        	}
	        	else if (shapeClicked.getClass().getName().equals("aCylinder")) {
	        		System.out.println("Cylinder has no edges!");
        			//((aCylinder) shapeClicked).getApp().setLineAttributes(lineattributes);
	        	}
            }});
	   
	    
	    
	    
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


	public void valueChanged(ListSelectionEvent arg0) { }
	
}
