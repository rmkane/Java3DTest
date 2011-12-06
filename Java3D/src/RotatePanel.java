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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.j3d.Alpha;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Node;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.Vector3f;




public class RotatePanel  {
	//private static SwingTest swingTest;
	//private static Canvas3D c3d;
	
    JPanel buttonsPanel = new JPanel();
    JPanel blankPanel = new JPanel();
    
	Transform3D yAxis = new Transform3D();
    
	//private Scene s;
	//private GLCanvas c;
    
    //private Scene s = GUI.getScene();
	
	
	public RotatePanel(JPanel panel)
	{
	    panel.setLayout(new BorderLayout());
	    

	    JLabel resize_title = new JLabel("R O T A T I O N");
	    resize_title.setOpaque(true);
	    resize_title.setBackground(Color.lightGray);
	    

	    panel.add(resize_title, BorderLayout.PAGE_START);
	    
	    JPanel rotatePanel1 = new JPanel();
	    rotatePanel1.setLayout(new GridBagLayout());
	    panel.add(rotatePanel1, BorderLayout.LINE_START);
	    
	    
	    GridBagConstraints constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.insets = new Insets(4, 4, 4, 4); // 5-pixel margins on all sides
	    constraints.anchor = GridBagConstraints.NORTHWEST;

	    

	    
	    constraints.gridx = 1;
	    constraints.gridy = 0;
	    constraints.gridwidth = 3;
	    constraints.gridheight = 1;
	    constraints.weightx = constraints.weighty = 1.0;
	    rotatePanel1.add(new JLabel(""), constraints);
	    
	    
	    constraints.gridx = 1;
	    constraints.gridy = 1;
	    constraints.gridwidth = 3;
	    constraints.gridheight = 1;
	    constraints.weightx = constraints.weighty = 1.0;
	    rotatePanel1.add(new JLabel("Axis of Rotation:"), constraints);
	    
	    
	    /**********************************
	     -----------CHECK BUTTONS----------
	     **********************************/
        final JRadioButton x_cb = new JRadioButton("x");
        x_cb.setSelected(true);
        yAxis.rotZ(Math.PI / 2.0);
	    constraints.gridx = 1;
	    constraints.gridy = 2;
	    constraints.gridwidth = 1;
	    constraints.gridheight = 1;
	    constraints.weightx = constraints.weighty = 1.0;
	    rotatePanel1.add(x_cb, constraints);

	    final JRadioButton y_cb = new JRadioButton("y");
	    constraints.gridx = 2;
	    constraints.gridy = 2;
	    constraints.gridwidth = 1;
	    constraints.gridheight = 1;
	    constraints.weightx = constraints.weighty = 1.0;
	    rotatePanel1.add(y_cb, constraints);
	    
	    final JRadioButton z_cb = new JRadioButton("z");
	    constraints.gridx = 3;
	    constraints.gridy = 2;
	    constraints.gridwidth = 1;
	    constraints.gridheight = 1;
	    constraints.weightx = constraints.weighty = 1.0;
	    rotatePanel1.add(z_cb, constraints);
	    
	    //group radio buttons together (when one is clicked, the others are deselected)
	    ButtonGroup group = new ButtonGroup();
	    group.add(x_cb);
	    group.add(y_cb);
	    group.add(z_cb);
	    

	    ItemListener listener = new ItemListener()
	    {
	        public void itemStateChanged(ItemEvent e) {
	        	Node shapeClicked = GUI_3D.getSwingTest().getShapeClicked();
	        	
	        	if (x_cb.isSelected())
	        		yAxis.rotZ(Math.PI / 2.0);
	        	else if (y_cb.isSelected())
	        		yAxis.rotY( Math.PI / 2.0 );
	        	else if (z_cb.isSelected())
	        		yAxis.rotX(Math.PI / 2.0);
	        }
	    };
	    
	    
	    x_cb.addItemListener(listener);
	    y_cb.addItemListener(listener);
	    z_cb.addItemListener(listener);
	    
	    
	    
	    /**********************************
	     ------------BLANK SPACE-----------
	     **********************************/
	    constraints.gridx = 1;
	    constraints.gridy = 3;
	    constraints.gridwidth = 3;
	    constraints.gridheight = 1;
	    constraints.weightx = constraints.weighty = 1.0;
	    rotatePanel1.add( Box.createHorizontalStrut( 10 ), constraints );
	    rotatePanel1.add( Box.createVerticalStrut( 10 ), constraints );
	    
	    
	    /**********************************
	     -------------SPINNERS-------------
	     **********************************/
	    //number of rotations
	    constraints.gridx = 1;
	    constraints.gridy = 4;
	    constraints.gridwidth = 2;
	    constraints.gridheight = 1;
	    constraints.weightx = constraints.weighty = 0.0;
	    rotatePanel1.add(new JLabel("Num. Rotations:"), constraints);
	    
        
        
	    Integer[] numRotList = new Integer[25];
	    for (int i = 0; i < 25; i++)
	    	numRotList[i] = i+1;
	    
	    Integer[] speedList = new Integer[20];
	    for (int i = 0; i < 20; i++)
	    	speedList[i] = i+1;
	    
	    
	    SpinnerListModel numRotModel = new SpinnerListModel(numRotList);
	    SpinnerListModel speedModel = new SpinnerListModel(speedList);

	    
	    final JSpinner numRotations = new JSpinner(numRotModel);
	    ((JSpinner.DefaultEditor)numRotations.getEditor()).getTextField().setColumns(2);
	    ((JSpinner.DefaultEditor)numRotations.getEditor()).getTextField().setEditable(false); 
	    numRotations.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
	    

	    final JSpinner speed = new JSpinner(speedModel);
	    ((JSpinner.DefaultEditor)speed.getEditor()).getTextField().setEditable(false); 
	    speed.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
	    speed.setValue(10);

        

	    constraints.gridx = 3;
	    constraints.gridy = 4;
	    constraints.gridwidth = 2;
	    constraints.gridheight = 1;
	    constraints.weightx = constraints.weighty = 1.0;
	    rotatePanel1.add(numRotations, constraints);
	    
	    
	    //speed
	    constraints.gridx = 1;
	    constraints.gridy = 5;
	    constraints.gridwidth = 2;
	    constraints.gridheight = 1;
	    constraints.weightx = constraints.weighty = 0.0;
	    rotatePanel1.add(new JLabel("Speed (1-20):"), constraints);
	    
	    
	    constraints.gridx = 3;
	    constraints.gridy = 5;
	    constraints.gridwidth = 2;
	    constraints.gridheight = 1;
	    constraints.weightx = constraints.weighty = 1.0;
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    rotatePanel1.add(speed, constraints);
	    
	    
	    
	    /**********************************
	     ------------BLANK SPACE-----------
	     **********************************/
	    constraints.gridx = 1;
	    constraints.gridy = 6;
	    constraints.gridwidth = 3;
	    constraints.gridheight = 1;
	    constraints.weightx = constraints.weighty = 1.0;
	    rotatePanel1.add( Box.createHorizontalStrut( 8 ), constraints );
	    rotatePanel1.add( Box.createVerticalStrut( 8 ), constraints );
	    
	    
	    
	    /**********************************
	     -------START / PAUSE BUTTONS------
	     **********************************/
	    JPanel rotatePanel2 = new JPanel();
	    panel.add(rotatePanel2, BorderLayout.PAGE_END);
	    
	    JButton start_b = new JButton("start");
	    final JButton pause_b = new JButton("pause");
	    start_b.setFont(new Font("sansserif",Font.PLAIN,11));
	    pause_b.setFont(new Font("sansserif",Font.PLAIN,11));
	    
	    

	    
	    pause_b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
        	    Node shapeClicked = GUI_3D.getSwingTest().getShapeClicked();
            	
            	GUI_3D.rotateSpeed = (Integer)speed.getValue();

            	
            	Alpha alpha = new Alpha();
            	
            	
            	if (shapeClicked.getClass().getName().equals("TriangularPrism")) {
            		alpha = ((TriangularPrism) shapeClicked).getRotationAlpha();
	        	}
	        	else if (shapeClicked.getClass().getName().equals("HexagonalPrism")) {
	        		alpha = ((HexagonalPrism) shapeClicked).getRotationAlpha();
	        	}
	        	else if (shapeClicked.getClass().getName().equals("RectangularPrism")) {
	        		alpha = ((RectangularPrism) shapeClicked).getRotationAlpha();
	        	}
	        	else if (shapeClicked.getClass().getName().equals("Pyramid")) {
	        		alpha = ((Pyramid) shapeClicked).getRotationAlpha();
	        	}
	        	else if (shapeClicked.getClass().getName().equals("aSphere")) {
	        		alpha = ((aSphere) shapeClicked).getRotationAlpha();
	        	}
	        	else if (shapeClicked.getClass().getName().equals("aCylinder")) {
            		alpha = ((aCylinder) shapeClicked).getRotationAlpha();
	        	}
            	
        		if (alpha.isPaused()) {
        			alpha.resume();
        			pause_b.setText("pause");
        		}
        		else if (!alpha.finished()) {
        			alpha.pause();
        			pause_b.setText("resume");
        		}
        			
            } 
        });  
	    
	    
	    
	    start_b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
        	    Node shapeClicked = GUI_3D.getSwingTest().getShapeClicked();
            	
            	GUI_3D.rotateSpeed = (Integer)speed.getValue();

            	
            	Alpha alpha = new Alpha();
            	
            	
            	if (shapeClicked.getClass().getName().equals("TriangularPrism")) {
            		alpha = ((TriangularPrism) shapeClicked).getRotationAlpha();
            		((TriangularPrism) shapeClicked).getRotator().setTransformAxis(yAxis);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("HexagonalPrism")) {
	        		alpha = ((HexagonalPrism) shapeClicked).getRotationAlpha();
            		((HexagonalPrism) shapeClicked).getRotator().setTransformAxis(yAxis);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("RectangularPrism")) {
	        		alpha = ((RectangularPrism) shapeClicked).getRotationAlpha();
            		((RectangularPrism) shapeClicked).getRotator().setTransformAxis(yAxis);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("Pyramid")) {
	        		alpha = ((Pyramid) shapeClicked).getRotationAlpha();
            		((Pyramid) shapeClicked).getRotator().setTransformAxis(yAxis);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("aSphere")) {
	        		alpha = ((aSphere) shapeClicked).getRotationAlpha();
            		((aSphere) shapeClicked).getRotator().setTransformAxis(yAxis);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("aCylinder")) {
            		alpha = ((aCylinder) shapeClicked).getRotationAlpha();
        			((aCylinder) shapeClicked).getRotator().setTransformAxis(yAxis);
	        	}
            	
            	
        		if (alpha.finished()){
        			alpha.setLoopCount((Integer)numRotations.getValue());
        			alpha.setStartTime(System.currentTimeMillis());
        			alpha.setIncreasingAlphaDuration((21 - (Integer)speed.getValue())*200);
        			alpha.setMode(Alpha.INCREASING_ENABLE);
        		}
            } 
        }); 
	    
	    
	    //int numRotations1 = 1;
	   
	    buttonsPanel.setLayout(new GridLayout(1,2,7,0));
	    buttonsPanel.add(start_b, constraints);
	    buttonsPanel.add(pause_b, constraints);

	    rotatePanel2.add(buttonsPanel, BorderLayout.PAGE_START);
	    blankPanel.setBorder(new EmptyBorder(10, 10, 15, 10) );
	    rotatePanel2.add(blankPanel, BorderLayout.PAGE_END);

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
	    
		new RotatePanel(panel);
		
		

		frame.setVisible(true);
	}
	
}
