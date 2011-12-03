import java.awt.Color;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;


public class aCylinder extends Shape3D {
	private RotationInterpolator rotator;
	private BranchGroup branchGroup;
	private String id;
	
	private static CustomMouseTranslate myMouseTranslate;
	private TransformGroup tg;
	private Transform3D changeSize;
	private Transform3D resize;
	
	private double height = 10; 
	private double width = 10; 
	private double depth = 10;
	
	private Alpha rotationAlpha;
	private TriangleArray cylinderGeometry;
	private QuadArray CylinderEdgeGeometry;
	
	private Appearance app;
	
	
	public Appearance getApp() {
		return app;
	}


	public void setApp(Appearance app) {
		this.app = app;
	}
	
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
	Point3f f_center = new Point3f( 0.0f, 1.5f, 0.0f);  //center
	Point3f f_ne = new Point3f( 0.5f, 1.5f, 0.9f);  //NE
	Point3f f_nw = new Point3f(-0.5f, 1.5f, 0.9f);  //NW
	Point3f f_w = new Point3f(-1.0f, 1.5f, 0.0f);  //W
	Point3f f_sw = new Point3f(-0.5f, 1.5f,-0.9f);  //SW
	Point3f f_se = new Point3f( 0.5f, 1.5f,-0.9f);  //SE
	Point3f f_e = new Point3f( 1.0f, 1.5f, 0.0f);  //E
		
	//BACK
	Point3f b_center = new Point3f( 0.0f, -1.5f, 0.0f);  //center
	Point3f b_ne = new Point3f( 0.5f, -1.5f, 0.9f);  //NE
	Point3f b_nw = new Point3f(-0.5f, -1.5f, 0.9f);  //NW
	Point3f b_w = new Point3f(-1.0f, -1.5f, 0.0f);  //W
	Point3f b_sw = new Point3f(-0.5f,-1.5f, -0.9f);  //SW
	Point3f b_se = new Point3f( 0.5f,-1.5f, -0.9f);  //SE
	Point3f b_e = new Point3f( 1.0f, -1.5f, 0.0f);  //E

	

    
    public aCylinder() {
    	Color3f mainColor = Colors.GREEN;
		cylinderGeometry = new TriangleArray(72, TriangleArray.COORDINATES | GeometryArray.COLOR_3);
		cylinderGeometry.setCapability(TriangleArray.ALLOW_COLOR_WRITE);
		
		//front face
		face(cylinderGeometry, 0, f_se, f_center, f_sw, mainColor);
		face(cylinderGeometry, 3, f_e, f_center, f_se, mainColor);		
		face(cylinderGeometry, 6, f_ne, f_center, f_e, mainColor);
		face(cylinderGeometry, 9, f_nw, f_center, f_ne, mainColor);
		face(cylinderGeometry, 12, f_w, f_center, f_nw, mainColor);
		face(cylinderGeometry, 15, f_sw, f_center, f_w, mainColor);
		
		//back face
		face(cylinderGeometry, 18, b_sw, b_center, b_se, mainColor);
		face(cylinderGeometry, 21, b_se, b_center, b_e, mainColor);
		face(cylinderGeometry, 24, b_e, b_center, b_ne, mainColor);
		face(cylinderGeometry, 27, b_ne, b_center, b_nw, mainColor);
		face(cylinderGeometry, 30, b_nw, b_center, b_w, mainColor);
		face(cylinderGeometry, 33, b_w, b_center, b_sw, mainColor);
		
		//north-east face
		face(cylinderGeometry, 36, b_e, f_ne, f_e, mainColor);
		face(cylinderGeometry, 39, b_ne, f_ne, b_e, mainColor);
		
		//south-east face
		face(cylinderGeometry, 42, b_se, f_e, f_se, mainColor);
		face(cylinderGeometry, 45, b_e, f_e, b_se, mainColor);
		
		//south-west face
		face(cylinderGeometry, 48, f_sw, b_w, b_sw, mainColor);
		face(cylinderGeometry, 51, f_w, b_w, f_sw, mainColor);
		
		//north-west face
		face(cylinderGeometry, 54, f_w, b_nw, b_w, mainColor);
		face(cylinderGeometry, 57, f_nw, b_nw, f_w, mainColor);	
		
		//top face
		face(cylinderGeometry, 60, f_nw, f_ne, b_nw, mainColor);
		face(cylinderGeometry, 63, b_ne, b_nw, f_ne, mainColor);
		
		//bottom face
		face(cylinderGeometry, 66, b_se, f_sw, b_sw, mainColor);
		face(cylinderGeometry, 69, f_se, f_sw, b_se, mainColor);		
		
		this.setGeometry(cylinderGeometry);
		this.setAppearance(new Appearance());
		
		
		
		this.setGeometry(cylinderGeometry);
		this.setAppearance(new Appearance());
		

		//set userData (id)
		int cylinderCount = SwingTest.getCylinderCount();
		this.setUserData( "Cylinder".concat(Integer.toString(cylinderCount)) );
		
		System.out.println("Created: " + this.getUserData());
		
		cylinderCount++;
	 	SwingTest.setCylinderCount(cylinderCount);
	 	
	 	Transform3D defaultSize = new Transform3D();
	 	defaultSize.setScale(new Vector3d(1.0, 1.0, 1.0));
	 	setResize(defaultSize);
    }
    
    private void face(TriangleArray cylinderGeometry, int index, Point3f coordinate1, Point3f coordinate2, Point3f coordinate3, Color3f color) {
    	cylinderGeometry.setCoordinate(index, coordinate1);
		cylinderGeometry.setCoordinate(index+1, coordinate2);
		cylinderGeometry.setCoordinate(index+2, coordinate3);
		cylinderGeometry.setColor(index, color);
		cylinderGeometry.setColor(index+1, color);
		cylinderGeometry.setColor(index+2, color);
    }
    
    private void edge(QuadArray cylinderGeometry, int index, Point3f coordinate1, Point3f coordinate2, Point3f coordinate3, Point3f coordinate4, Color3f color) {
    	cylinderGeometry.setCoordinate(index, coordinate1);
		cylinderGeometry.setCoordinate(index+1, coordinate2);
		cylinderGeometry.setCoordinate(index+2, coordinate3);
		cylinderGeometry.setCoordinate(index+3, coordinate4);
		cylinderGeometry.setColor(index, color);
		cylinderGeometry.setColor(index+1, color);
		cylinderGeometry.setColor(index+2, color);
		cylinderGeometry.setColor(index+3, color);
    }
    
    
    public Node CylinderEdges() {
		CylinderEdgeGeometry = new QuadArray(24, QuadArray.COORDINATES | GeometryArray.COLOR_3);
		CylinderEdgeGeometry.setCapability(QuadArray.ALLOW_COLOR_WRITE);
		
		edge(CylinderEdgeGeometry, 0, f_ne, b_ne, b_nw, f_nw, Colors.BLACK);
		edge(CylinderEdgeGeometry, 4, f_e, b_e, b_ne, f_ne, Colors.BLACK);
		edge(CylinderEdgeGeometry, 8, f_se, b_se, b_e, f_e, Colors.BLACK);
		edge(CylinderEdgeGeometry, 12, f_sw, f_se, b_se, b_sw, Colors.BLACK);
		edge(CylinderEdgeGeometry, 16, b_sw, f_sw, f_w, b_w, Colors.BLACK);
		edge(CylinderEdgeGeometry, 20, b_w, f_w, f_nw, b_nw, Colors.BLACK);		
				
		app = new Appearance();
		app.setCapability(Appearance.ALLOW_LINE_ATTRIBUTES_WRITE);
		
		// Set up the polygon attributes
        PolygonAttributes pa = new PolygonAttributes();
        pa.setPolygonMode(pa.POLYGON_LINE);
        pa.setCullFace(pa.CULL_NONE);
        pa.setPolygonOffsetFactor(-0.5f);
        app.setPolygonAttributes(pa);
        
        LineAttributes lineattributes = new LineAttributes();
        lineattributes.setLineWidth(1.0f);
        lineattributes.setLineAntialiasingEnable(true);
        lineattributes.setLinePattern(LineAttributes.PATTERN_SOLID);
        
        app.setLineAttributes(lineattributes);
        
        Shape3D CylinderEdges = new Shape3D();
		CylinderEdges.setGeometry(CylinderEdgeGeometry);
		CylinderEdges.setAppearance(app);
		
		return CylinderEdges;
    }
    
    
    
    public TriangleArray getcylinderGeometry() {
		return cylinderGeometry;
	}


	public void setcylinderGeometry(TriangleArray cylinderGeometry) {
		this.cylinderGeometry = cylinderGeometry;
	}


	public QuadArray getCylinderEdgeGeometry() {
		return CylinderEdgeGeometry;
	}


	public void setCylinderEdgeGeometry(QuadArray CylinderEdgeGeometry) {
		this.CylinderEdgeGeometry = CylinderEdgeGeometry;
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
		 

		 spin.addChild(this); //add Cylinder shape to the spin TG
		 //spin.addChild(CylinderEdges());

		    
		rotationAlpha = new Alpha(0, Alpha.INCREASING_ENABLE, 0, 0, 4000, 0, 0, 0, 0, 0);
			
		rotator = new RotationInterpolator(rotationAlpha, spin, yAxis, 0.0f, (float) Math.PI*2.0f);

		    
		 BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		 rotator.setSchedulingBounds(bounds);
		 spin.addChild(rotator);  //add interpolator rotator to the spin TG
		 
		 
		 TransformGroup tg = new TransformGroup() ;
		 tg.setUserData("TG: TG".concat(Integer.toString(SwingTest.getCylinderCount())));
		 tg.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE ) ;
		 setTg(tg);
		 
		 
		 tg.addChild(spin) ;
		 
			TransformGroup mouseTG = new TransformGroup();
			mouseTG.setUserData("TG: MOUSETG");
			myMouseTranslate = new CustomMouseTranslate();
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