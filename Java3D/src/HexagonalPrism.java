import java.awt.Color;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;


public class HexagonalPrism extends Shape3D {
	private RotationInterpolator rotator;
	private BranchGroup branchGroup;
	private String id;
	
	private static MouseTranslate myMouseTranslate;
	private TransformGroup tg;
	private Transform3D changeSize;
	private Transform3D resize;
	
	private double height = 10; 
	private double width = 10; 
	private double depth = 10;
	
	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	public double getWidth() {
		return width;
	}


	public void setWidth(double width) {
		this.width = width;
	}


	public double getDepth() {
		return depth;
	}


	public void setDepth(double depth) {
		this.depth = depth;
	}
	
	public Transform3D getResize() {
		return resize;
	}


	public void setResize(Transform3D resize) {
		this.resize = resize;
	}


	public Transform3D getChangeSize() {
		return changeSize;
	}


	public void setChangeSize(Transform3D changeSize) {
		this.changeSize = changeSize;
	}
	
	private float tx = 0.0f;
	private float ty = 0.0f;


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
	
	
	//FRONT
	Point3f f_center = new Point3f( 0.0f, 0.0f, 0.5f);  //center
	Point3f f_ne = new Point3f( 0.5f, 0.9f, 0.5f);  //NE
	Point3f f_nw = new Point3f(-0.5f, 0.9f, 0.5f);  //NW
	Point3f f_w = new Point3f(-1.0f, 0.0f, 0.5f);  //W
	Point3f f_sw = new Point3f(-0.5f,-0.9f, 0.5f);  //SW
	Point3f f_se = new Point3f( 0.5f,-0.9f, 0.5f);  //SE
	Point3f f_e = new Point3f( 1.0f, 0.0f, 0.5f);  //E
		
	//BACK
	Point3f b_center = new Point3f( 0.0f, 0.0f, -0.5f);  //center
	Point3f b_ne = new Point3f( 0.5f, 0.9f, -0.5f);  //NE
	Point3f b_nw = new Point3f(-0.5f, 0.9f, -0.5f);  //NW
	Point3f b_w = new Point3f(-1.0f, 0.0f, -0.5f);  //W
	Point3f b_sw = new Point3f(-0.5f, -0.9f,-0.5f);  //SW
	Point3f b_se = new Point3f( 0.5f, -0.9f,-0.5f);  //SE
	Point3f b_e = new Point3f( 1.0f, 0.0f, -0.5f);  //E

	

    
    public HexagonalPrism() {
		TriangleArray hexPrismGeometry = new TriangleArray(72, TriangleArray.COORDINATES | GeometryArray.COLOR_3);
		hexPrismGeometry.setCoordinate(0, f_se);
		hexPrismGeometry.setCoordinate(1, f_center);
		hexPrismGeometry.setCoordinate(2, f_sw);
		hexPrismGeometry.setColor(0, red);
		hexPrismGeometry.setColor(1, red);
		hexPrismGeometry.setColor(2, red);
 
		//front face
		hexPrismGeometry.setCoordinate(3, f_e);
		hexPrismGeometry.setCoordinate(4, f_center);
		hexPrismGeometry.setCoordinate(5, f_se);
		hexPrismGeometry.setColor(3, red);
		hexPrismGeometry.setColor(4, red);
		hexPrismGeometry.setColor(5, red);
		
		hexPrismGeometry.setCoordinate(6, f_ne);
		hexPrismGeometry.setCoordinate(7, f_center);
		hexPrismGeometry.setCoordinate(8, f_e);
		hexPrismGeometry.setColor(6, red);
		hexPrismGeometry.setColor(7, red);
		hexPrismGeometry.setColor(8, red);
 	
		hexPrismGeometry.setCoordinate(9, f_nw);
		hexPrismGeometry.setCoordinate(10, f_center);
		hexPrismGeometry.setCoordinate(11, f_ne);
		hexPrismGeometry.setColor(9, red);
		hexPrismGeometry.setColor(10, red);
		hexPrismGeometry.setColor(11, red);
 
		hexPrismGeometry.setCoordinate(12, f_w);
		hexPrismGeometry.setCoordinate(13, f_center);
		hexPrismGeometry.setCoordinate(14, f_nw);
		hexPrismGeometry.setColor(12, red);
		hexPrismGeometry.setColor(13, red);
		hexPrismGeometry.setColor(14, red);
 
		hexPrismGeometry.setCoordinate(15, f_sw);
		hexPrismGeometry.setCoordinate(16, f_center);
		hexPrismGeometry.setCoordinate(17, f_w);
		hexPrismGeometry.setColor(15, red);
		hexPrismGeometry.setColor(16, red);
		hexPrismGeometry.setColor(17, red);
		
		
		//back face
		hexPrismGeometry.setCoordinate(18, b_sw);
		hexPrismGeometry.setCoordinate(19, b_center);
		hexPrismGeometry.setCoordinate(20, b_se);
		hexPrismGeometry.setColor(18, blue);
		hexPrismGeometry.setColor(19, blue);
		hexPrismGeometry.setColor(20, blue);
		
		hexPrismGeometry.setCoordinate(21, b_se);
		hexPrismGeometry.setCoordinate(22, b_center);
		hexPrismGeometry.setCoordinate(23, b_e);
		hexPrismGeometry.setColor(21, blue);
		hexPrismGeometry.setColor(22, blue);
		hexPrismGeometry.setColor(23, blue);
		
		hexPrismGeometry.setCoordinate(24, b_e);
		hexPrismGeometry.setCoordinate(25, b_center);
		hexPrismGeometry.setCoordinate(26, b_ne);
		hexPrismGeometry.setColor(24, blue);
		hexPrismGeometry.setColor(25, blue);
		hexPrismGeometry.setColor(26, blue);
		
		hexPrismGeometry.setCoordinate(27, b_ne);
		hexPrismGeometry.setCoordinate(28, b_center);
		hexPrismGeometry.setCoordinate(29, b_nw);
		hexPrismGeometry.setColor(27, blue);
		hexPrismGeometry.setColor(28, blue);
		hexPrismGeometry.setColor(29, blue);
		
		hexPrismGeometry.setCoordinate(30, b_nw);
		hexPrismGeometry.setCoordinate(31, b_center);
		hexPrismGeometry.setCoordinate(32, b_w);
		hexPrismGeometry.setColor(30, blue);
		hexPrismGeometry.setColor(31, blue);
		hexPrismGeometry.setColor(32, blue);
		
		hexPrismGeometry.setCoordinate(33, b_w);
		hexPrismGeometry.setCoordinate(34, b_center);
		hexPrismGeometry.setCoordinate(35, b_sw);
		hexPrismGeometry.setColor(33, blue);
		hexPrismGeometry.setColor(34, blue);
		hexPrismGeometry.setColor(35, blue);
		
		
		//north-east face
		hexPrismGeometry.setCoordinate(36, b_e);
		hexPrismGeometry.setCoordinate(37, f_ne);
		hexPrismGeometry.setCoordinate(38, f_e);
		hexPrismGeometry.setColor(36, purple);
		hexPrismGeometry.setColor(37, purple);
		hexPrismGeometry.setColor(38, purple);
		
		hexPrismGeometry.setCoordinate(39, b_ne);
		hexPrismGeometry.setCoordinate(40, f_ne);
		hexPrismGeometry.setCoordinate(41, b_e);
		hexPrismGeometry.setColor(39, purple);
		hexPrismGeometry.setColor(40, purple);
		hexPrismGeometry.setColor(41, purple);
		
		
		//south-east face
		hexPrismGeometry.setCoordinate(42, b_se);
		hexPrismGeometry.setCoordinate(43, f_e);
		hexPrismGeometry.setCoordinate(44, f_se);
		hexPrismGeometry.setColor(42, green);
		hexPrismGeometry.setColor(43, green);
		hexPrismGeometry.setColor(44, green);
		
		hexPrismGeometry.setCoordinate(45, b_e);
		hexPrismGeometry.setCoordinate(46, f_e);
		hexPrismGeometry.setCoordinate(47, b_se);
		hexPrismGeometry.setColor(45, green);
		hexPrismGeometry.setColor(46, green);
		hexPrismGeometry.setColor(47, green);
		
		
		//south-west face
		hexPrismGeometry.setCoordinate(48, f_sw);
		hexPrismGeometry.setCoordinate(49, b_w);
		hexPrismGeometry.setCoordinate(50, b_sw);
		hexPrismGeometry.setColor(48, yellow);
		hexPrismGeometry.setColor(49, yellow);
		hexPrismGeometry.setColor(50, yellow);
		
		hexPrismGeometry.setCoordinate(51, f_w);
		hexPrismGeometry.setCoordinate(52, b_w);
		hexPrismGeometry.setCoordinate(53, f_sw);
		hexPrismGeometry.setColor(51, yellow);
		hexPrismGeometry.setColor(52, yellow);
		hexPrismGeometry.setColor(53, yellow);
		
		
		//north-west face
		hexPrismGeometry.setCoordinate(54, f_w);
		hexPrismGeometry.setCoordinate(55, b_nw);
		hexPrismGeometry.setCoordinate(56, b_w);
		hexPrismGeometry.setColor(54, gray);
		hexPrismGeometry.setColor(55, gray);
		hexPrismGeometry.setColor(56, gray);
		
		hexPrismGeometry.setCoordinate(57, f_nw);
		hexPrismGeometry.setCoordinate(58, b_nw);
		hexPrismGeometry.setCoordinate(59, f_w);
		hexPrismGeometry.setColor(57, gray);
		hexPrismGeometry.setColor(58, gray);
		hexPrismGeometry.setColor(59, gray);
		
		
		//top face
		hexPrismGeometry.setCoordinate(60, f_nw);
		hexPrismGeometry.setCoordinate(61, f_ne);
		hexPrismGeometry.setCoordinate(62, b_nw);
		hexPrismGeometry.setColor(60, orange);
		hexPrismGeometry.setColor(61, orange);
		hexPrismGeometry.setColor(62, orange);
		
		hexPrismGeometry.setCoordinate(63, b_ne);
		hexPrismGeometry.setCoordinate(64, b_nw);
		hexPrismGeometry.setCoordinate(65, f_ne);
		hexPrismGeometry.setColor(63, orange);
		hexPrismGeometry.setColor(64, orange);
		hexPrismGeometry.setColor(65, orange);
		
		
		//bottom face
		hexPrismGeometry.setCoordinate(66, b_se);
		hexPrismGeometry.setCoordinate(67, f_sw);
		hexPrismGeometry.setCoordinate(68, b_sw);
		hexPrismGeometry.setColor(66, cyan);
		hexPrismGeometry.setColor(67, cyan);
		hexPrismGeometry.setColor(68, cyan);
		
		hexPrismGeometry.setCoordinate(69, f_se);
		hexPrismGeometry.setCoordinate(70, f_sw);
		hexPrismGeometry.setCoordinate(71, b_se);
		hexPrismGeometry.setColor(69, cyan);
		hexPrismGeometry.setColor(70, cyan);
		hexPrismGeometry.setColor(71, cyan);
		
		
		this.setGeometry(hexPrismGeometry);
		this.setAppearance(new Appearance());
		
		
		
		this.setGeometry(hexPrismGeometry);
		this.setAppearance(new Appearance());
		

		//set userData (id)
		int hexPrismCount = SwingTest.getHexPrismCount();
		this.setUserData( "HexPrism".concat(Integer.toString(hexPrismCount)) );
		
		System.out.println("Created: " + this.getUserData());
		
		hexPrismCount++;
	 	SwingTest.setHexPrismCount(hexPrismCount);
	 	
	 	Transform3D defaultSize = new Transform3D();
	 	defaultSize.setScale(new Vector3d(1.0, 1.0, 1.0));
	 	setResize(defaultSize);
    }
    
    
    public Node hexPrismEdges() {
		QuadArray hexPrismEdgeGeometry = new QuadArray(24, QuadArray.COORDINATES | GeometryArray.COLOR_3);
		
		hexPrismEdgeGeometry.setCoordinate(0, f_ne);
		hexPrismEdgeGeometry.setCoordinate(1, b_ne);
		hexPrismEdgeGeometry.setCoordinate(2, b_nw);
		hexPrismEdgeGeometry.setCoordinate(3, f_nw);
		hexPrismEdgeGeometry.setColor(0, black);
		hexPrismEdgeGeometry.setColor(1, black);
		hexPrismEdgeGeometry.setColor(2, black);
		hexPrismEdgeGeometry.setColor(3, black);
		
		hexPrismEdgeGeometry.setCoordinate(4, f_e);
		hexPrismEdgeGeometry.setCoordinate(5, b_e);
		hexPrismEdgeGeometry.setCoordinate(6, b_ne);
		hexPrismEdgeGeometry.setCoordinate(7, f_ne);
		hexPrismEdgeGeometry.setColor(4, black);
		hexPrismEdgeGeometry.setColor(5, black);
		hexPrismEdgeGeometry.setColor(6, black);
		hexPrismEdgeGeometry.setColor(7, black);
		
		hexPrismEdgeGeometry.setCoordinate(8, f_se);
		hexPrismEdgeGeometry.setCoordinate(9, b_se);
		hexPrismEdgeGeometry.setCoordinate(10, b_e);
		hexPrismEdgeGeometry.setCoordinate(11, f_e);
		hexPrismEdgeGeometry.setColor(8, black);
		hexPrismEdgeGeometry.setColor(9, black);
		hexPrismEdgeGeometry.setColor(10, black);
		hexPrismEdgeGeometry.setColor(11, black);
		
		
		hexPrismEdgeGeometry.setCoordinate(12, f_sw);
		hexPrismEdgeGeometry.setCoordinate(13, f_se);
		hexPrismEdgeGeometry.setCoordinate(14, b_se);
		hexPrismEdgeGeometry.setCoordinate(15, b_sw);
		hexPrismEdgeGeometry.setColor(12, black);
		hexPrismEdgeGeometry.setColor(13, black);
		hexPrismEdgeGeometry.setColor(14, black);
		hexPrismEdgeGeometry.setColor(15, black);
		
		
		hexPrismEdgeGeometry.setCoordinate(16, b_sw);
		hexPrismEdgeGeometry.setCoordinate(17, f_sw);
		hexPrismEdgeGeometry.setCoordinate(18, f_w);
		hexPrismEdgeGeometry.setCoordinate(19, b_w);
		hexPrismEdgeGeometry.setColor(16, black);
		hexPrismEdgeGeometry.setColor(17, black);
		hexPrismEdgeGeometry.setColor(18, black);
		hexPrismEdgeGeometry.setColor(19, black);
		
		
		hexPrismEdgeGeometry.setCoordinate(20, b_w);
		hexPrismEdgeGeometry.setCoordinate(21, f_w);
		hexPrismEdgeGeometry.setCoordinate(22, f_nw);
		hexPrismEdgeGeometry.setCoordinate(23, b_nw);
		hexPrismEdgeGeometry.setColor(20, black);
		hexPrismEdgeGeometry.setColor(21, black);
		hexPrismEdgeGeometry.setColor(22, black);
		hexPrismEdgeGeometry.setColor(23, black);
		
		
		Appearance app = new Appearance();
		
		// Set up the polygon attributes
        PolygonAttributes pa = new PolygonAttributes();
        pa.setPolygonMode(pa.POLYGON_LINE);
        pa.setCullFace(pa.CULL_NONE);
        pa.setPolygonOffsetFactor(-0.5f);
        app.setPolygonAttributes(pa);
        
        LineAttributes lineattributes = new LineAttributes();
        lineattributes.setLineWidth(5.0f);
        lineattributes.setLineAntialiasingEnable(true);
        lineattributes.setLinePattern(LineAttributes.PATTERN_SOLID);
        
        app.setLineAttributes(lineattributes);
        
        Shape3D hexPrismEdges = new Shape3D();
		hexPrismEdges.setGeometry(hexPrismEdgeGeometry);
		hexPrismEdges.setAppearance(app);
		
		return hexPrismEdges;
    }
    
    
    
    TransformGroup createRotator() {
    	Transform3D yAxis = new Transform3D();

		 /* axes of rotation */
	     //yAxis.rotZ(Math.PI / 2.0);  	//X AXIS
	     //yAxis.rotY( Math.PI / 2.0 ); //Y AXIS
	     yAxis.rotX(Math.PI / 2.0);   //Z AXIS
		    
		 TransformGroup spin = new TransformGroup(yAxis);
		 spin.setUserData("TG: SPIN");
		 
		 
		 spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		 

		 spin.addChild(this); //add hexPrism shape to the spin TG
		 spin.addChild(hexPrismEdges());

		    
		 Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0,  4000, 0, 0, 0, 0, 0);
	     rotator = new RotationInterpolator(rotationAlpha, spin, yAxis, 0.0f, (float) Math.PI* GUI_3D.rotateSpeed );

		    
		 BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		 rotator.setSchedulingBounds(bounds);
		 spin.addChild(rotator);  //add interpolator rotator to the spin TG
		 
		 
		 TransformGroup tg = new TransformGroup() ;
		 tg.setUserData("TG: TG".concat(Integer.toString(SwingTest.getHexPrismCount())));
		 tg.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE ) ;
		 setTg(tg);
		 
		 
		 tg.addChild(spin) ;
		 
		 TransformGroup mouseTG = new TransformGroup();	
		 mouseTG.setUserData("TG: MOUSETG");
		 myMouseTranslate = new MouseTranslate();
		 myMouseTranslate.setTransformGroup(tg);
		 myMouseTranslate.setSchedulingBounds(bounds);
		 mouseTG.addChild(myMouseTranslate);
		              
		        
		 tg.addChild(mouseTG);

		 return tg;
    }
	
	
	public TransformGroup getTg() {
		return tg;
	}


	public void setTg(TransformGroup tg) {
		this.tg = tg;
	}
	
	
	public float getTx() {
		return tx;
	}


	public void setTx(float tx) {
		this.tx = tx;
	}


	public float getTy() {
		return ty;
	}


	public void setTy(float ty) {
		this.ty = ty;
	}
}