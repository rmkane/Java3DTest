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
	
	private Alpha rotationAlpha;
	
	public Alpha getRotationAlpha() {
		return rotationAlpha;
	}


	public void setRotationAlpha(Alpha rotationAlpha) {
		this.rotationAlpha = rotationAlpha;
	}
	
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
		
		//front face
		face(hexPrismGeometry, 0, f_se, f_center, f_sw, Colors.GREEN);
		face(hexPrismGeometry, 3, f_e, f_center, f_se, Colors.GREEN);		
		face(hexPrismGeometry, 6, f_ne, f_center, f_e, Colors.GREEN);
		face(hexPrismGeometry, 9, f_nw, f_center, f_ne, Colors.GREEN);
		face(hexPrismGeometry, 12, f_w, f_center, f_nw, Colors.GREEN);
		face(hexPrismGeometry, 15, f_sw, f_center, f_w, Colors.GREEN);
		
		//back face
		face(hexPrismGeometry, 18, b_sw, b_center, b_se, Colors.BLUE);
		face(hexPrismGeometry, 21, b_se, b_center, b_e, Colors.BLUE);
		face(hexPrismGeometry, 24, b_e, b_center, b_ne, Colors.BLUE);
		face(hexPrismGeometry, 27, b_ne, b_center, b_nw, Colors.BLUE);
		face(hexPrismGeometry, 30, b_nw, b_center, b_w, Colors.BLUE);
		face(hexPrismGeometry, 33, b_w, b_center, b_sw, Colors.BLUE);
		
		//north-east face
		face(hexPrismGeometry, 36, b_e, f_ne, f_e, Colors.PURPLE);
		face(hexPrismGeometry, 39, b_ne, f_ne, b_e, Colors.PURPLE);
		
		//south-east face
		face(hexPrismGeometry, 42, b_se, f_e, f_se, Colors.RED);
		face(hexPrismGeometry, 45, b_e, f_e, b_se, Colors.RED);
		
		//south-west face
		face(hexPrismGeometry, 48, f_sw, b_w, b_sw, Colors.YELLOW);
		face(hexPrismGeometry, 51, f_w, b_w, f_sw, Colors.YELLOW);
		
		//north-west face
		face(hexPrismGeometry, 54, f_w, b_nw, b_w, Colors.GRAY);
		face(hexPrismGeometry, 57, f_nw, b_nw, f_w, Colors.GRAY);	
		
		//top face
		face(hexPrismGeometry, 60, f_nw, f_ne, b_nw, Colors.ORANGE);
		face(hexPrismGeometry, 63, b_ne, b_nw, f_ne, Colors.ORANGE);
		
		//bottom face
		face(hexPrismGeometry, 66, b_se, f_sw, b_sw, Colors.CYAN);
		face(hexPrismGeometry, 69, f_se, f_sw, b_se, Colors.CYAN);		
		
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
    
    private void face(TriangleArray hexPrismGeometry, int index, Point3f coordinate1, Point3f coordinate2, Point3f coordinate3, Color3f color) {
    	hexPrismGeometry.setCoordinate(index, coordinate1);
		hexPrismGeometry.setCoordinate(index+1, coordinate2);
		hexPrismGeometry.setCoordinate(index+2, coordinate3);
		hexPrismGeometry.setColor(index, color);
		hexPrismGeometry.setColor(index+1, color);
		hexPrismGeometry.setColor(index+2, color);
    }
    
    private void edge(QuadArray hexPrismGeometry, int index, Point3f coordinate1, Point3f coordinate2, Point3f coordinate3, Point3f coordinate4, Color3f color) {
    	hexPrismGeometry.setCoordinate(index, coordinate1);
		hexPrismGeometry.setCoordinate(index+1, coordinate2);
		hexPrismGeometry.setCoordinate(index+2, coordinate3);
		hexPrismGeometry.setCoordinate(index+3, coordinate4);
		hexPrismGeometry.setColor(index, color);
		hexPrismGeometry.setColor(index+1, color);
		hexPrismGeometry.setColor(index+2, color);
		hexPrismGeometry.setColor(index+3, color);
    }
    
    
    public Node hexPrismEdges() {
		QuadArray hexPrismEdgeGeometry = new QuadArray(24, QuadArray.COORDINATES | GeometryArray.COLOR_3);
		
		edge(hexPrismEdgeGeometry, 0, f_ne, b_ne, b_nw, f_nw, Colors.BLACK);
		edge(hexPrismEdgeGeometry, 4, f_e, b_e, b_ne, f_ne, Colors.BLACK);
		edge(hexPrismEdgeGeometry, 8, f_se, b_se, b_e, f_e, Colors.BLACK);
		edge(hexPrismEdgeGeometry, 12, f_sw, f_se, b_se, b_sw, Colors.BLACK);
		edge(hexPrismEdgeGeometry, 16, b_sw, f_sw, f_w, b_w, Colors.BLACK);
		edge(hexPrismEdgeGeometry, 20, b_w, f_w, f_nw, b_nw, Colors.BLACK);		
				
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
	     //yAxis.rotX(Math.PI / 2.0);   //Z AXIS
		    
		 TransformGroup spin = new TransformGroup(yAxis);
		 spin.setUserData("TG: SPIN");
		 
		 
		 spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		 

		 spin.addChild(this); //add hexPrism shape to the spin TG
		 spin.addChild(hexPrismEdges());

		    
		rotationAlpha = new Alpha(0, Alpha.INCREASING_ENABLE, 0, 0, 4000, 0, 0, 0, 0, 0);
			
		rotator = new RotationInterpolator(rotationAlpha, spin, yAxis, 0.0f, (float) Math.PI*2.0f);

		    
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
	
	
    public RotationInterpolator getRotator() {
		return rotator;
	}


	public void setRotator(RotationInterpolator rotator) {
		this.rotator = rotator;
	}
}