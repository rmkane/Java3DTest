import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;




public class ResizePanel implements ListSelectionListener  {
	
	//private Scene s = GUI.getScene();
	
	public ResizePanel(JPanel panel)
	{
	    panel.setLayout(new BorderLayout());
	    

	    JLabel resize_title = new JLabel("R E S I Z E");
	    resize_title.setOpaque(true);
	    resize_title.setBackground(Color.lightGray);
	    

	    panel.add(resize_title, BorderLayout.PAGE_START);
	    

	    JPanel leftPanel = new JPanel();
	    JPanel rightPanel = new JPanel();
	    JPanel bottomPanel = new JPanel();
	    leftPanel.setLayout(new GridLayout(3, 2, 5, 15));
	    rightPanel.setLayout(new GridLayout(2, 1));
	    
	    panel.add(leftPanel, BorderLayout.LINE_START);
	    panel.add(rightPanel, BorderLayout.LINE_END);
	    panel.add(bottomPanel, BorderLayout.PAGE_END);
	    leftPanel.setBorder(new EmptyBorder(10, 3, 10, 3) );
	    rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10) );
	    bottomPanel.setBorder(new EmptyBorder(0, 10, 10, 10) );
	    
	    
	    Integer[] list = new Integer[30];
	    for (int i = 0; i < 30; i++)
	    {
	    	list[i] = i+1;
	    }
	    
	    
	    //JComboBox height = new JComboBox(list);
	    SpinnerListModel heightModel = new SpinnerListModel(list);
	    SpinnerListModel widthModel = new SpinnerListModel(list);
	    SpinnerListModel depthModel = new SpinnerListModel(list);

	    
	    final JSpinner height = new JSpinner(heightModel);
	    height.setValue(10);
	    ((JSpinner.DefaultEditor)height.getEditor()).getTextField().setEditable(false); 
	    height.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));

	    
	    final JSpinner width = new JSpinner(widthModel);
	    width.setValue(10);
	    ((JSpinner.DefaultEditor)width.getEditor()).getTextField().setEditable(false); 
	    width.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));

	    
	    final JSpinner depth = new JSpinner(depthModel);
	    depth.setValue(10);
	    ((JSpinner.DefaultEditor)depth.getEditor()).getTextField().setEditable(false); 
	    depth.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
	    
	    

	    leftPanel.add(new JLabel("Height:"));
	    leftPanel.add(height);
	    leftPanel.add(new JLabel("Width:"));
	    leftPanel.add(width);
	    leftPanel.add(new JLabel("Depth:"));
	    leftPanel.add(depth);
	    
	    
	  
	    final JCheckBox maintainRatio = new JCheckBox("Maintain Aspect Ratio");
	    

	    
	    ItemListener listener = new ItemListener()
	    {
	        public void itemStateChanged(ItemEvent e) {
	        	System.out.print("[Maintain aspect ratio: ");
	        	if (maintainRatio.isSelected())
	        		System.out.println("ON]");
	        	else
	        		System.out.println("OFF]");
	        }
	    };
	    
	    maintainRatio.addItemListener(listener);
	    
	    
	    
	    ChangeListener heightListener = new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {

	        	if (maintainRatio.isSelected()) {
	        		width.setValue(height.getValue());
	        		depth.setValue(height.getValue());
	        	}

	        	double h = (Double.valueOf(height.getValue().toString()).doubleValue())/10;
	        	double w = (Double.valueOf(width.getValue().toString()).doubleValue())/10;
	        	double d = (Double.valueOf(depth.getValue().toString()).doubleValue())/10;
	        	
	        	Node shapeClicked = GUI_3D.getSwingTest().getShapeClicked();
	        	
	        	Transform3D resize = new Transform3D();
	        	Transform3D holdPosition = new Transform3D();
	        	resize.setScale(new Vector3d(w, h, d));
	        	
	        	if (shapeClicked.getClass().getName().equals("TriangularPrism")) {
		        	holdPosition.setTranslation(
		        			new Vector3f(((TriangularPrism) shapeClicked).getTx(), ((TriangularPrism) shapeClicked).getTy(), 0.0f));
		        	holdPosition.mul(resize);
		        	
		        	((TriangularPrism) shapeClicked).getTg().setTransform(holdPosition);
		        	((TriangularPrism) shapeClicked).setResize(resize);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("HexagonalPrism")) {
		        	holdPosition.setTranslation(
		        			new Vector3f(((HexagonalPrism) shapeClicked).getTx(), ((HexagonalPrism) shapeClicked).getTy(), 0.0f));
		        	holdPosition.mul(resize);
		        	
		        	((HexagonalPrism) shapeClicked).getTg().setTransform(holdPosition);
		        	((HexagonalPrism) shapeClicked).setResize(resize);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("RectangularPrism")) {
		        	holdPosition.setTranslation(
		        			new Vector3f(((RectangularPrism) shapeClicked).getTx(), ((RectangularPrism) shapeClicked).getTy(), 0.0f));
		        	holdPosition.mul(resize);
		        	
		        	((RectangularPrism) shapeClicked).getTg().setTransform(holdPosition);
		        	((RectangularPrism) shapeClicked).setResize(resize);
		        	
		        	//((RectangularPrism)shapeClicked).getRectPrismGeometry().setColor(0, Colors.GREEN);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("Pyramid")) {
		        	holdPosition.setTranslation(
		        			new Vector3f(((Pyramid) shapeClicked).getTx(), ((Pyramid) shapeClicked).getTy(), 0.0f));
		        	holdPosition.mul(resize);
		        	
		        	((Pyramid) shapeClicked).getTg().setTransform(holdPosition);
		        	((Pyramid) shapeClicked).setResize(resize);
	        	}
	        	else if (shapeClicked.getClass().getName().equals("aSphere")) {
		        	holdPosition.setTranslation(
		        			new Vector3f(((aSphere) shapeClicked).getTx(), ((aSphere) shapeClicked).getTy(), 0.0f));
		        	holdPosition.mul(resize);
		        	
		        	((aSphere) shapeClicked).getTg().setTransform(holdPosition);
		        	((aSphere) shapeClicked).setResize(resize);
	        	}
	        	//else if (shapeClicked.getClass().getName().equals("aCylinder")) {
		        //	holdPosition.setTranslation(
		        //			new Vector3f(((aCylinder) shapeClicked).getTx(), ((aCylinder) shapeClicked).getTy(), 0.0f));
		        //	holdPosition.mul(resize);
		        	
		        //	((aCylinder) shapeClicked).getTg().setTransform(holdPosition);
		        //	((aCylinder) shapeClicked).setResize(resize);
	        	//}
	        	
	        	System.out.println(shapeClicked.getUserData() + ": Height changed - " + height.getValue());
	        }
	      };
	      
	      
		    ChangeListener widthListener = new ChangeListener() {
		        public void stateChanged(ChangeEvent e) {
		        	
		        	if (maintainRatio.isSelected()) {
		        		height.setValue(width.getValue());
		        		depth.setValue(width.getValue());
		        	}

		        	double h = (Double.valueOf(height.getValue().toString()).doubleValue())/10;
		        	double w = (Double.valueOf(width.getValue().toString()).doubleValue())/10;
		        	double d = (Double.valueOf(depth.getValue().toString()).doubleValue())/10;
		        	
		        	Node shapeClicked = GUI_3D.getSwingTest().getShapeClicked();
		        	
		        	Transform3D resize = new Transform3D();
		        	Transform3D holdPosition = new Transform3D();
		        	resize.setScale(new Vector3d(w, h, d));
		        	
		        	if (shapeClicked.getClass().getName().equals("TriangularPrism")) {
			        	holdPosition.setTranslation(
			        			new Vector3f(((TriangularPrism) shapeClicked).getTx(), ((TriangularPrism) shapeClicked).getTy(), 0.0f));
			        	holdPosition.mul(resize);
			        	
			        	((TriangularPrism) shapeClicked).getTg().setTransform(holdPosition);
			        	((TriangularPrism) shapeClicked).setResize(resize);
			        	((TriangularPrism) shapeClicked).setHeight(h);
			        	((TriangularPrism) shapeClicked).setWidth(w);
			        	((TriangularPrism) shapeClicked).setDepth(d);
		        	}
		        	else if (shapeClicked.getClass().getName().equals("HexagonalPrism")) {
			        	holdPosition.setTranslation(
			        			new Vector3f(((HexagonalPrism) shapeClicked).getTx(), ((HexagonalPrism) shapeClicked).getTy(), 0.0f));
			        	holdPosition.mul(resize);
			        	
			        	((HexagonalPrism) shapeClicked).getTg().setTransform(holdPosition);
			        	((HexagonalPrism) shapeClicked).setResize(resize);
		        	}
		        	else if (shapeClicked.getClass().getName().equals("RectangularPrism")) {
			        	holdPosition.setTranslation(
			        			new Vector3f(((RectangularPrism) shapeClicked).getTx(), ((RectangularPrism) shapeClicked).getTy(), 0.0f));
			        	holdPosition.mul(resize);
			        	
			        	((RectangularPrism) shapeClicked).getTg().setTransform(holdPosition);
			        	((RectangularPrism) shapeClicked).setResize(resize);
		        	}
		        	else if (shapeClicked.getClass().getName().equals("Pyramid")) {
			        	holdPosition.setTranslation(
			        			new Vector3f(((Pyramid) shapeClicked).getTx(), ((Pyramid) shapeClicked).getTy(), 0.0f));
			        	holdPosition.mul(resize);
			        	
			        	((Pyramid) shapeClicked).getTg().setTransform(holdPosition);
			        	((Pyramid) shapeClicked).setResize(resize);
		        	}
		        	else if (shapeClicked.getClass().getName().equals("aSphere")) {
			        	holdPosition.setTranslation(
			        			new Vector3f(((aSphere) shapeClicked).getTx(), ((aSphere) shapeClicked).getTy(), 0.0f));
			        	holdPosition.mul(resize);
		        	
			        	((aSphere) shapeClicked).getTg().setTransform(holdPosition);
			        	((aSphere) shapeClicked).setResize(resize);
		        	}
		        	//else if (shapeClicked.getClass().getName().equals("HexagonalPrism")) {
			        //	holdPosition.setTranslation(
			        //			new Vector3f(((HexagonalPrism) shapeClicked).getTx(), ((HexagonalPrism) shapeClicked).getTy(), 0.0f));
			        //	holdPosition.mul(resize);
			        	
			        //	((HexagonalPrism) shapeClicked).getTg().setTransform(holdPosition);
			        //	((HexagonalPrism) shapeClicked).setResize(resize);
		        	//}
		        	
		        	System.out.println(shapeClicked.getUserData() + ": Width changed - " + width.getValue());
		        }
		      };
		      
		      
			    ChangeListener depthListener = new ChangeListener() {
			        public void stateChanged(ChangeEvent e) {
			        	
			        	Node shapeClicked = GUI_3D.getSwingTest().getShapeClicked();
			        	
			        	if (maintainRatio.isSelected()) {
			        		height.setValue(depth.getValue());
			        		width.setValue(depth.getValue());
			        	}

			        	double h = (Double.valueOf(height.getValue().toString()).doubleValue())/10;
			        	double w = (Double.valueOf(width.getValue().toString()).doubleValue())/10;
			        	double d = (Double.valueOf(depth.getValue().toString()).doubleValue())/10;
			        				        	
			        	Transform3D resize = new Transform3D();
			        	Transform3D holdPosition = new Transform3D();
			        	resize.setScale(new Vector3d(w, h, d));
			        	
			        	if (shapeClicked.getClass().getName().equals("TriangularPrism")) {
				        	holdPosition.setTranslation(
				        			new Vector3f(((TriangularPrism) shapeClicked).getTx(), ((TriangularPrism) shapeClicked).getTy(), 0.0f));
				        	holdPosition.mul(resize);
				        	
				        	((TriangularPrism) shapeClicked).getTg().setTransform(holdPosition);
				        	((TriangularPrism) shapeClicked).setResize(resize);
			        	}
			        	else if (shapeClicked.getClass().getName().equals("HexagonalPrism")) {
				        	holdPosition.setTranslation(
				        			new Vector3f(((HexagonalPrism) shapeClicked).getTx(), ((HexagonalPrism) shapeClicked).getTy(), 0.0f));
				        	holdPosition.mul(resize);
				        	
				        	((HexagonalPrism) shapeClicked).getTg().setTransform(holdPosition);
				        	((HexagonalPrism) shapeClicked).setResize(resize);
			        	}
			        	else if (shapeClicked.getClass().getName().equals("RectangularPrism")) {
				        	holdPosition.setTranslation(
				        			new Vector3f(((RectangularPrism) shapeClicked).getTx(), ((RectangularPrism) shapeClicked).getTy(), 0.0f));
				        	holdPosition.mul(resize);
				        	
				        	((RectangularPrism) shapeClicked).getTg().setTransform(holdPosition);
				        	((RectangularPrism) shapeClicked).setResize(resize);
			        	}
			        	else if (shapeClicked.getClass().getName().equals("Pyramid")) {
				        	holdPosition.setTranslation(
				        			new Vector3f(((Pyramid) shapeClicked).getTx(), ((Pyramid) shapeClicked).getTy(), 0.0f));
				        	holdPosition.mul(resize);
				        	
				        	((Pyramid) shapeClicked).getTg().setTransform(holdPosition);
				        	((Pyramid) shapeClicked).setResize(resize);
			        	}
			        	else if (shapeClicked.getClass().getName().equals("aSphere")) {
				        	holdPosition.setTranslation(
				        			new Vector3f(((aSphere) shapeClicked).getTx(), ((aSphere) shapeClicked).getTy(), 0.0f));
				        	holdPosition.mul(resize);
			        		
				        	((aSphere) shapeClicked).getTg().setTransform(holdPosition);
				        	((aSphere) shapeClicked).setResize(resize);
			        	}
			        	//else if (shapeClicked.getClass().getName().equals("HexagonalPrism")) {
				        //	holdPosition.setTranslation(
				        //			new Vector3f(((HexagonalPrism) shapeClicked).getTx(), ((HexagonalPrism) shapeClicked).getTy(), 0.0f));
				        //	holdPosition.mul(resize);
				        	
				        //	((HexagonalPrism) shapeClicked).getTg().setTransform(holdPosition);
				        //	((HexagonalPrism) shapeClicked).setResize(resize);
			        	//}
			        	
			        	System.out.println(shapeClicked.getUserData() + ": Depth changed - " + depth.getValue()); 
			        	}
			      };
	    
	      
	    height.addChangeListener(heightListener);
	    width.addChangeListener(widthListener);
	    depth.addChangeListener(depthListener);
   

	    maintainRatio.setFont(new Font("sansserif",Font.PLAIN,11));
	    bottomPanel.add(maintainRatio);
	  
	}
	

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("3D GUI");

		frame.setSize(150, 200);

		
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
	    
		new ResizePanel(panel);
		
		

		frame.setVisible(true);
	}


	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
