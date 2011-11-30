import java.awt.Color;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;


public class TriangularPrism extends Shape3D {
	private RotationInterpolator rotator;
	private BranchGroup branchGroup;
	private String id;
	
	private static MouseTranslate myMouseTranslate;
	private TransformGroup tg;
	private Transform3D changeSize;
	private Transform3D resize;
	
	private Alpha rotationAlpha;
	
	public Alpha getRotationAlpha() {
		return rotationAlpha;
	}


	public void setRotationAlpha(Alpha rotationAlpha) {
		this.rotationAlpha = rotationAlpha;
	}


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
	
    
    public RotationInterpolator getRotator() {
		return rotator;
	}


	public void setRotator(RotationInterpolator rotator) {
		this.rotator = rotator;
	}


	private float tx = 0.0f;
	private float ty = 0.0f;
	
	Point3f top1 = new Point3f(0.0f, 0.5f, 1.0f);  //top front
	Point3f top2 = new Point3f(0.0f, 0.5f, -1.0f);  //top back
	Point3f frontL = new Point3f(-1.0f,-1.0f, 1.0f); //front left
	Point3f frontR = new Point3f( 1.0f,-1.0f, 1.0f); //front right
	Point3f backR = new Point3f( 1.0f,-1.0f, -1.0f);  //back right
	Point3f backL = new Point3f(-1.0f,-1.0f, -1.0f);  //back left

	

    
    public TriangularPrism() {
   		TriangleArray triPrismGeometry = new TriangleArray(24, TriangleArray.COORDINATES | GeometryArray.COLOR_3);
		
   		face(triPrismGeometry, 0, frontR, top1, frontL, Colors.PURPLE);	
   		face(triPrismGeometry, 3, backR, top1, frontR, Colors.BLUE);
   		face(triPrismGeometry, 6, top1, backR, top2, Colors.BLUE);
   		face(triPrismGeometry, 9, backL, top2, backR, Colors.GREEN);
   		face(triPrismGeometry, 12, frontL, top2, backL, Colors.YELLOW);
   		face(triPrismGeometry, 15, top2, frontL, top1, Colors.YELLOW);
   		face(triPrismGeometry, 18, frontL, backR, frontR, Colors.GRAY);
   		face(triPrismGeometry, 21, backR, frontL, backL, Colors.GRAY);		
		
		this.setGeometry(triPrismGeometry);
		this.setAppearance(new Appearance());
		
		int triPrismCount = SwingTest.getTriPrismCount();
		this.setUserData( "TriPrism".concat(Integer.toString(triPrismCount)) );
		
		triPrismCount++;
	 	SwingTest.setTriPrismCount(triPrismCount);

	 	Transform3D defaultSize = new Transform3D();
	 	defaultSize.setScale(new Vector3d(1.0, 1.0, 1.0));
	 	setResize(defaultSize);
	 	
	 	//sends message to logger
	 	GUI_3D.sessionLog.add("TriPrism created at " + "0,0,0");
	 	GUI_3D.sessionLog.writeOut(GUI_3D.sessionLog.getFilename(), GUI_3D.sessionLog.getLog());
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
    
    
    public Node triPrismEdges() {
		QuadArray triPrismEdgeGeometry = new QuadArray(12, QuadArray.COORDINATES | GeometryArray.COLOR_3);
		
		edge(triPrismEdgeGeometry, 0, frontR, backR, top2, top1, Colors.BLACK);
		edge(triPrismEdgeGeometry, 4, backL, frontL, top1, top2, Colors.BLACK);
		edge(triPrismEdgeGeometry, 8, frontL, frontR, backR, backL, Colors.BLACK);		
		
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
        
        Shape3D triPrismEdges = new Shape3D();
		triPrismEdges.setGeometry(triPrismEdgeGeometry);
		triPrismEdges.setAppearance(app);
		
		return triPrismEdges;
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
		 

		 spin.addChild(this); //add triPrism shape to the spin TG
		 spin.addChild(triPrismEdges());

		    
	      rotationAlpha = new Alpha(0, Alpha.INCREASING_ENABLE, 0, 0,  4000, 0, 0, 0, 0, 0);

	      rotator = new RotationInterpolator(rotationAlpha, spin, yAxis, 0.0f, (float) Math.PI*2.0f );
		    
		 BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		 rotator.setSchedulingBounds(bounds);
		 spin.addChild(rotator);  //add interpolator rotator to the spin TG
		 

		 
		 
		 TransformGroup tg = new TransformGroup() ;
		 tg.setUserData("TG: TG".concat(Integer.toString(SwingTest.getTriPrismCount())));
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