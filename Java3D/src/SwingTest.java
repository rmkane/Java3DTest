import java.awt.*;
import java.awt.event.*;

import javax.media.j3d.*;
import javax.vecmath.*;
import javax.swing.*;

import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
//import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;


public class SwingTest extends JPanel implements MouseListener
{
	private static int sphereCount = 0;
	private static int cubeCount = 0;
	private static int hexPrismCount = 0;
	private static int triPrismCount = 0;
	private int cylinderCount = 0;
	private static int pyramidCount = 0;
	
	private static BranchGroup    sceneBranchGroup = null;
	private RotationInterpolator  rotator = null;
	private BoundingBox generalBounds = new BoundingBox(new Point3d(-1000, -1000, -1000), new Point3d(1000, 1000, 1000));
	
	private static Canvas3D c3d;
	private PickCanvas pickCanvas;

	Color3f red = new Color3f (Color.RED);
	Color3f pink = new Color3f (Color.PINK);
	Color3f orange = new Color3f (Color.ORANGE);
	Color3f yellow = new Color3f (Color.YELLOW);
	Color3f green = new Color3f (Color.GREEN);
	Color3f blue = new Color3f (Color.BLUE);
	Color3f cyan = new Color3f (Color.CYAN);
	Color3f purple = new Color3f (Color.MAGENTA);
	Color3f black = new Color3f (Color.BLACK);
	Color3f white = new Color3f (Color.WHITE);
	Color3f gray = new Color3f (Color.GRAY);
	Color3f lightgray = new Color3f (Color.LIGHT_GRAY);


	
	public SwingTest() {
		//setLayout( new BorderLayout() );
		init();
	}
	

	
	 protected void init()
	 {
		 VirtualUniverse universe = createVirtualUniverse();

		 Locale locale = createLocale( universe );
	
		 BranchGroup sceneBranchGroup = createSceneBranchGroup();
	
		 Background background = createBackground();
		 if( background != null )
			  sceneBranchGroup.addChild( background );
	
		  ViewPlatform vp = createViewPlatform();
	
		  BranchGroup viewBranchGroup = createViewBranchGroup(getViewTransformGroupArray(), vp);
		  
		  locale.addBranchGraph(sceneBranchGroup);
		  addViewBranchGroup(locale, viewBranchGroup);
	
		  createView( vp );
	 }
	

	 
	 protected void addCanvas3D(Canvas3D c3d)  {
		 add( "Center", c3d );
	 }
	

	 
	 protected View createView( ViewPlatform vp )
	 {
	 	View view = new View();
	
	 	PhysicalBody pb = new PhysicalBody();
	 	PhysicalEnvironment pe = new PhysicalEnvironment();
	 	view.setPhysicalEnvironment( pe );
	 	view.setPhysicalBody( pb );

	 	view.attachViewPlatform( vp );
	
	 	view.setBackClipDistance( 100.0 );
	 	view.setFrontClipDistance( 1.0 );
	

	 	c3d = createCanvas3D( false );
	 	view.addCanvas3D(c3d);
	 	//view.addCanvas3D(createOffscreenCanvas3D());
		
	 	addCanvas3D( c3d );
		c3d.addMouseListener(this);
		
		 pickCanvas = new PickCanvas(c3d, sceneBranchGroup);
		 pickCanvas.setMode(PickCanvas.BOUNDS);
	
	 	return view;
	 }
	

	 protected Background createBackground()
	 {
	 	Background background = new Background(white);
		background.setApplicationBounds(createApplicationBounds());
		
		return background;
	 }
	

	protected Bounds createApplicationBounds() {
		return new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
	}
	

	 protected Canvas3D createCanvas3D( boolean offscreen )
	 {
		 GraphicsConfigTemplate3D gc3D = new GraphicsConfigTemplate3D();
		 gc3D.setSceneAntialiasing( GraphicsConfigTemplate.PREFERRED );
	
		 GraphicsDevice gd[] = 
				 GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
	
		 Canvas3D c3d = new Canvas3D(gd[0].getBestConfiguration(gc3D), offscreen);
	
		 //c3d.setSize( 500, 500 );
	
		 return c3d;
	 }
	

	 public TransformGroup[] getViewTransformGroupArray()
	 {
		 TransformGroup[] tgArray = new TransformGroup[1];
		 tgArray[0] = new TransformGroup();
	
		 Transform3D t3d = new Transform3D();
		 //t3d.setScale( 3 );
		 t3d.setTranslation( new Vector3d( 0.0, 0.0, -10.0 ) );
		 t3d.invert(); //moving the viewer, not the scene
		 tgArray[0].setTransform( t3d );
	
		 return tgArray;
	 }
	

	 
	 protected void addViewBranchGroup( Locale locale, BranchGroup bg ) {
		 locale.addBranchGraph( bg );
	 }
	

	 protected Locale createLocale( VirtualUniverse u ) {
		 return new Locale( u );
	 }
	

	 protected ViewPlatform createViewPlatform() {
		 ViewPlatform vp = new ViewPlatform();
		 vp.setViewAttachPolicy( View.RELATIVE_TO_FIELD_OF_VIEW );
		 vp.setActivationRadius( 100 );
	
		 return vp;
	 }
	

	 protected BranchGroup createViewBranchGroup(TransformGroup[] tgArray, ViewPlatform vp)
	 {
		 BranchGroup vpBranchGroup = new BranchGroup();
	
		 if( tgArray != null && tgArray.length > 0 )
		 {
			 Group parentGroup = vpBranchGroup;
			 TransformGroup curTg = null;
	
			 for( int n = 0; n < tgArray.length; n++ )
			 {
				 curTg = tgArray[n];
				 parentGroup.addChild( curTg );
				 parentGroup = curTg;
			 }
	
			 tgArray[tgArray.length-1].addChild( vp );
		 }
		 else
			 vpBranchGroup.addChild( vp );
	
		 return vpBranchGroup;
	 }
	
	 
	 protected VirtualUniverse createVirtualUniverse() {
		 return new VirtualUniverse();
	 }
	


	 protected BranchGroup createSceneBranchGroup() {
		 BranchGroup objRoot = new BranchGroup();
	
		 TransformGroup objTrans = new TransformGroup();
		 objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		 objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		

	

		 BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		 

		  sceneBranchGroup = new BranchGroup();
	
		  sceneBranchGroup.setCapability( Group.ALLOW_CHILDREN_EXTEND );
		  sceneBranchGroup.setCapability( Group.ALLOW_CHILDREN_READ );
		  sceneBranchGroup.setCapability( Group.ALLOW_CHILDREN_WRITE );
		
		  //sceneBranchGroup.addChild( createPyramid() );
		  //sceneBranchGroup.addChild( createHexPrism() );
		  //sceneBranchGroup.addChild( createTriPrism() );
		  
		
		  //ambient light
		  AmbientLight aLgt = new AmbientLight( white );
		  aLgt.setInfluencingBounds( bounds );
		
		  //directional light
		  DirectionalLight lgt1 = new DirectionalLight(white, new Vector3f( -1.0f,-1.0f,-1.0f ) );
		  lgt1.setInfluencingBounds( bounds );
		
		  objRoot.addChild(aLgt);
		  objRoot.addChild(lgt1);
		
		  
		  objTrans.addChild( sceneBranchGroup );
		  objRoot.addChild( objTrans );

	
		  return objRoot;
	 }
	

	 protected BranchGroup createPyramid() {
		 BranchGroup bg = new BranchGroup();
	
		 bg.setCapability( BranchGroup.ALLOW_DETACH ); //makes the shape deletable
		 
		 Transform3D yAxis = new Transform3D();

		 /* axes of rotation */
	     yAxis.rotZ(Math.PI / 2.0);  	//X AXIS
	   //yAxis.rotY( Math.PI / 2.0 ); //Y AXIS
	   //yAxis.rotX(Math.PI / 2.0);   //Z AXIS
		    
		 TransformGroup spin = new TransformGroup(yAxis);
		 spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		    
		 spin.addChild(new Pyramid()); //add pyramid shape to the spin TG
		 //bg.addChild(spin); //add spin TG to the branchGroup

		    
		 Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0, 
		    							 4000, 0, 0, 0, 0, 0);
		    
		 rotator = new RotationInterpolator(rotationAlpha, spin, yAxis, 0.0f,
                    						(float) Math.PI*2.0f );
		    
		 BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		 rotator.setSchedulingBounds(bounds);
		 spin.addChild(rotator);  //add interpolator rotator to the spin TG
		 
		 
		 
			TransformGroup tg = new TransformGroup() ;
			tg.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE ) ;

			tg.addChild(spin) ;
			
			BoundingSphere bounds1 = 
			           new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
			
			
		    MouseTranslate myMouseTranslate = new MouseTranslate(
		            );
		        myMouseTranslate.setTransformGroup(tg);
		        myMouseTranslate.setSchedulingBounds(bounds1);
		        bg.addChild(myMouseTranslate);
			
			bg.addChild( tg ) ;
		 
		 
		 	 bg.setUserData( "Pyramid".concat(Integer.toString(pyramidCount)) );
		 	 
		 	 System.out.println(bg.getUserData());
		 	 pyramidCount++;
		  
		 return bg;
	 }
	 
	 
	 
	 protected BranchGroup createHexPrism() {
		 BranchGroup bg = new BranchGroup();
			
		 bg.setCapability( BranchGroup.ALLOW_DETACH ); //makes the shape deletable
		 
		 HexagonalPrism hexprism = new HexagonalPrism();
		 bg.addChild(hexprism.createRotator());
	
		 bg.setUserData( "HexPrism".concat(Integer.toString(hexPrismCount)) );
		 	 
		 System.out.println(bg.getUserData());
		 hexPrismCount++;
		  
		 return bg;
	 }
	 
	 
	 
	 
	 protected BranchGroup createCube() {
		 BranchGroup bg = new BranchGroup();
			
		 bg.setCapability( BranchGroup.ALLOW_DETACH ); //makes the shape deletable
		 
		 Cube cube = new Cube();
		 bg.addChild(cube.createRotator());
		 bg.addChild(cube.createUserData());

	 	 //bg.setUserData( "Cube".concat(Integer.toString(cubeCount)) );
	 	 
	 	 //System.out.println(bg.getUserData());
	 	 //cubeCount++;
		 
		 System.out.println(cube.getBranchGroup().getUserData());
		  
		 return bg;
	 }
	 
	 
	 protected BranchGroup createTriPrism() {
		 BranchGroup bg = new BranchGroup();
			
		 bg.setCapability( BranchGroup.ALLOW_DETACH ); //makes the shape deletable
		 
		 TriangularPrism triprism = new TriangularPrism();
		 bg.addChild(triprism.createRotator());
	
	 	 bg.setUserData( "TriPrism".concat(Integer.toString(triPrismCount)) );
	 	 
	 	 System.out.println(bg.getUserData());
	 	 triPrismCount++;
		  
		 return bg;
	 }
	 
	
	 protected BranchGroup createSphere() {
		 BranchGroup bg = new BranchGroup();
			
		 bg.setCapability( BranchGroup.ALLOW_DETACH ); //makes the shape deletable
		 
		 aSphere sphere = new aSphere();
		 bg.addChild(sphere.createRotator());
		 
		 
	 	 bg.setUserData( "Sphere".concat(Integer.toString(sphereCount)) );
	 	 
	 	 System.out.println(bg.getUserData());
	 	 sphereCount++;
	 	
	 	 return bg;
	 }
	
	 
	 //allows dynamic removal of the shape BranchGroups at runtime
	 protected void removeShape(String name) {
		 try {
			 //get all child Nodes from parent of BranchGroups
			 java.util.Enumeration en = sceneBranchGroup.getAllChildren();
			 int index = 0;
	
			 //iterate through Nodes to find User Data to remove
			 while ( en.hasMoreElements() != false ) {
				 SceneGraphObject sgObject = (SceneGraphObject)
				 en.nextElement();
	
				 Object userData = sgObject.getUserData();
	
				 //Compare ScenegraphObject’s User Data with what we are looking for
				 if (userData instanceof String && ((String)userData).compareTo(name) == 0){
					 System.out.println( "Removing: " + sgObject.getUserData() );
					 sceneBranchGroup.removeChild( index );
				 }
	
				 index++;
			 }
	  }
	  catch( Exception e ) { }
	 }
	

		/**
	 public void actionPerformed( ActionEvent ae ) {
		 System.out. println( "Action Performed: " + ae.getActionCommand() );
	
		 java.util.StringTokenizer toker =
				 new java.util.StringTokenizer( ae.getActionCommand(), "|" );
	
		 String menu = toker.nextToken();
		 String command = toker.nextToken();
	
		 if (menu.equals( "File")) {
			 if ( command.equals( "Exit" ) ) {
				 System.exit( 0 );
			 }
		 }
	 
		 else if ( menu.equals( "View" ) ) {
			 if ( command.equals( "Cube" ) ) {
				 removeShape( "Sphere" );
				 sceneBranchGroup.addChild( createPyramid() );
			 }
	   
			 else if ( command.equals( "Sphere" ) ) {
				 removeShape( "Pyramid" );
				 sceneBranchGroup.addChild( createSphere() );
			 }
		 }
	  
		 else if ( menu.equals( "Rotate" ) ) {
			 if ( command.equals( "On" ) ) {
				 //numTimes = 3;
				 //rotator.setAlpha(new Alpha(numTimes, 5000));
				 //rotator.setAlpha(new Alpha(numTimes, 5000));
				 //System.out.println("Should now rotate " + numTimes + " times!");
				 //rotationAlpha.setStartTime(System.currentTimeMillis());
				 getRotator().getAlpha().resume();
				 //rotator.setEnable( true );
			 }
			 
			 else if ( command.equals( "Off" ) ) {
				 //rotator.setAlpha(null);
				 getRotator().getAlpha().pause();
				 //rotator.setEnable( false );
			 }
		 }
	 }



	private JMenuItem createMenuItem( String menuText,
	                                  String buttonText,
	                                  ActionListener listener ) {
		  JMenuItem menuItem = new JMenuItem( buttonText );
		  menuItem.addActionListener( listener );
		  menuItem.setActionCommand( menuText + "|" + buttonText );
		  
		  return menuItem;
	 }
	


	 static protected void registerWindowListener( JFrame frame ) {
		 frame.setDefaultCloseOperation( WindowConstants.DO_NOTHING_ON_CLOSE );
	
		 frame.addWindowListener(new WindowAdapter() {
			 public void windowClosing( WindowEvent e ) {
				 System.exit( 1 );
			 }
		 });
	 }
	 

	 public static void main(String[] args) {
		 JPopupMenu.setDefaultLightWeightPopupEnabled( false );
	
		 ToolTipManager ttm = ToolTipManager.sharedInstance();
		 ttm.setLightWeightPopupEnabled( false );
	
		 JFrame frame = new JFrame();
		 
		 SwingTest swingTest = new SwingTest();
	
		 JMenuBar menuBar = new JMenuBar();
		 JMenu menu = null;
	
		 menu = new JMenu( "File" );
		 menu.add(swingTest.createMenuItem( "File", "Save Image", swingTest ));
		 menu.add(swingTest.createMenuItem( "File", "Exit", swingTest ));
		 menuBar.add( menu );
	
		 menu = new JMenu( "View" );
		 menu.add(swingTest.createMenuItem( "View", "Cube", swingTest ) );
		 menu.add(swingTest.createMenuItem( "View", "Sphere", swingTest ) );
		 menuBar.add( menu );
	
		 menu = new JMenu( "Rotate" );
		 menu.add(swingTest.createMenuItem( "Rotate", "On", swingTest ) );
		 menu.add(swingTest.createMenuItem( "Rotate", "Off", swingTest ) );
		 menuBar.add( menu );
	
		 frame.setJMenuBar( menuBar );
	
		 frame.getContentPane().add( swingTest );
		 frame.setSize( 550, 550 );
	
		 registerWindowListener( frame );
	
		 frame.setVisible( true );
	 }
	 */

	 
/***********************************************************************/
/************************** ACCESSORS/MUTATORS *************************/
/***********************************************************************/	
	public RotationInterpolator getRotator() {
		return rotator;
	}

	public void setRotator(RotationInterpolator rotator) {
		this.rotator = rotator;
	}
	 
	public static BranchGroup getSceneBranchGroup() {
		return sceneBranchGroup;
	}

	public void setSceneBranchGroup(BranchGroup sceneBranchGroup) {
		this.sceneBranchGroup = sceneBranchGroup;
	}
	
	public static Canvas3D getC3d() {
		return c3d;
	}


	public void setC3d(Canvas3D c3d) {
		this.c3d = c3d;
	}



	public void mouseClicked(MouseEvent e)
	{
		
	    pickCanvas.setShapeLocation(e);
	    PickResult result = pickCanvas.pickClosest();
	
	    if (result == null) {
	       System.out.println("Nothing picked");
	
	    } else {
	       Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);
	       Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D);
	
	       if (p != null) {
	          System.out.println(p.getUserData());
	
	       } else if (s != null) {
	             System.out.println(s.getUserData());
	
	       } else {
	          System.out.println("null");
	       }
	    }
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {

	}



	public static int getCubeCount() {
		return cubeCount;
	}


	public static void setCubeCount(int cubeCount2) {
		cubeCount = cubeCount2;
	}
	
	
	public static int getPyramidCount() {
		return pyramidCount;
	}


	public static void setPyramidCount(int pyramidCount2) {
		pyramidCount = pyramidCount2;
	}
	
	
	public static int getTriPrismCount() {
		return triPrismCount;
	}


	public static void setTriPrismCount(int triPrismCount2) {
		triPrismCount = triPrismCount2;
	}
	
	
	public static int getSphereCount() {
		return sphereCount;
	}


	public static void setSphereCount(int sphereCount2) {
		sphereCount = sphereCount2;
	}
	
	
	public static int getHexPrismCount() {
		return hexPrismCount;
	}


	public static void setHexPrismCount(int hexPrismCount2) {
		hexPrismCount = hexPrismCount2;
	}
}