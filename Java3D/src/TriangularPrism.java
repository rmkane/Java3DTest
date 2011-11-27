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
	
	
	Point3f top1 = new Point3f(0.0f, 0.5f, 1.0f);  //top front
	Point3f top2 = new Point3f(0.0f, 0.5f, -1.0f);  //top back
	Point3f frontL = new Point3f(-1.0f,-1.0f, 1.0f); //front left
	Point3f frontR = new Point3f( 1.0f,-1.0f, 1.0f); //front right
	Point3f backR = new Point3f( 1.0f,-1.0f, -1.0f);  //back right
	Point3f backL = new Point3f(-1.0f,-1.0f, -1.0f);  //back left

	

    
    public TriangularPrism() {
   		TriangleArray triPrismGeometry = new TriangleArray(24, TriangleArray.COORDINATES | GeometryArray.COLOR_3);
		triPrismGeometry.setCoordinate(0, frontR);
		triPrismGeometry.setCoordinate(1, top1);
		triPrismGeometry.setCoordinate(2, frontL);
		triPrismGeometry.setColor(0, red);
		triPrismGeometry.setColor(1, red);
		triPrismGeometry.setColor(2, red);
		
 
		triPrismGeometry.setCoordinate(3, backR);
		triPrismGeometry.setCoordinate(4, top1);
		triPrismGeometry.setCoordinate(5, frontR);
		triPrismGeometry.setColor(3, blue);
		triPrismGeometry.setColor(4, blue);
		triPrismGeometry.setColor(5, blue);
		
		triPrismGeometry.setCoordinate(6, top1);
		triPrismGeometry.setCoordinate(7, backR);
		triPrismGeometry.setCoordinate(8, top2);
		triPrismGeometry.setColor(6, blue);
		triPrismGeometry.setColor(7, blue);
		triPrismGeometry.setColor(8, blue);
		
 
		triPrismGeometry.setCoordinate(9, backL);
		triPrismGeometry.setCoordinate(10, top2);
		triPrismGeometry.setCoordinate(11, backR);
		triPrismGeometry.setColor(9, green);
		triPrismGeometry.setColor(10, green);
		triPrismGeometry.setColor(11, green);
 
		
		triPrismGeometry.setCoordinate(12, frontL);
		triPrismGeometry.setCoordinate(13, top2);
		triPrismGeometry.setCoordinate(14, backL);
		triPrismGeometry.setColor(12, purple);
		triPrismGeometry.setColor(13, purple);
		triPrismGeometry.setColor(14, purple);
 
		triPrismGeometry.setCoordinate(15, top2);
		triPrismGeometry.setCoordinate(16, frontL);
		triPrismGeometry.setCoordinate(17, top1);
		triPrismGeometry.setColor(15, purple);
		triPrismGeometry.setColor(16, purple);
		triPrismGeometry.setColor(17, purple);
		
		
		triPrismGeometry.setCoordinate(18, frontL);
		triPrismGeometry.setCoordinate(19, backR);
		triPrismGeometry.setCoordinate(20, frontR);
		triPrismGeometry.setColor(18, gray);
		triPrismGeometry.setColor(19, gray);
		triPrismGeometry.setColor(20, gray);
		
		
		triPrismGeometry.setCoordinate(21, backR);
		triPrismGeometry.setCoordinate(22, frontL);
		triPrismGeometry.setCoordinate(23, backL);
		triPrismGeometry.setColor(21, gray);
		triPrismGeometry.setColor(22, gray);
		triPrismGeometry.setColor(23, gray);
		
		
		
		
		this.setGeometry(triPrismGeometry);
		this.setAppearance(new Appearance());
		
		int triPrismCount = SwingTest.getTriPrismCount();
		this.setUserData( "TriPrism".concat(Integer.toString(triPrismCount)) );
		
		triPrismCount++;
	 	SwingTest.setTriPrismCount(triPrismCount);

	 	Transform3D defaultSize = new Transform3D();
	 	defaultSize.setScale(new Vector3d(1.0, 1.0, 1.0));
	 	setResize(defaultSize);
	
    }
    
    
    public Node triPrismEdges() {
		QuadArray triPrismEdgeGeometry = new QuadArray(12, QuadArray.COORDINATES | GeometryArray.COLOR_3);
		
		
		triPrismEdgeGeometry.setCoordinate(0, frontR);
		triPrismEdgeGeometry.setCoordinate(1, backR);
		triPrismEdgeGeometry.setCoordinate(2, top2);
		triPrismEdgeGeometry.setCoordinate(3, top1);
		triPrismEdgeGeometry.setColor(0, black);
		triPrismEdgeGeometry.setColor(1, black);
		triPrismEdgeGeometry.setColor(2, black);
		triPrismEdgeGeometry.setColor(3, black);
		
		triPrismEdgeGeometry.setCoordinate(4, backL);
		triPrismEdgeGeometry.setCoordinate(5, frontL);
		triPrismEdgeGeometry.setCoordinate(6, top1);
		triPrismEdgeGeometry.setCoordinate(7, top2);
		triPrismEdgeGeometry.setColor(4, black);
		triPrismEdgeGeometry.setColor(5, black);
		triPrismEdgeGeometry.setColor(6, black);
		triPrismEdgeGeometry.setColor(7, black);
		
		triPrismEdgeGeometry.setCoordinate(8, frontL);
		triPrismEdgeGeometry.setCoordinate(9, frontR);
		triPrismEdgeGeometry.setCoordinate(10, backR);
		triPrismEdgeGeometry.setCoordinate(11, backL);
		triPrismEdgeGeometry.setColor(8, black);
		triPrismEdgeGeometry.setColor(9, black);
		triPrismEdgeGeometry.setColor(10, black);
		triPrismEdgeGeometry.setColor(11, black);
		
		
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
	     yAxis.rotY( Math.PI / 2.0 ); //Y AXIS
	     //yAxis.rotX(Math.PI / 2.0);   //Z AXIS
		    
		 TransformGroup spin = new TransformGroup(yAxis);
		 spin.setUserData("TG: SPIN");
		 
		 
		 spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		 

		 spin.addChild(this); //add triPrism shape to the spin TG
		 spin.addChild(triPrismEdges());

		    
	      Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0,  4000, 0, 0, 0, 0, 0);

	      //rotator = new RotationInterpolator(rotationAlpha, spin, yAxis, 0.0f, (float) Math.PI*2.0f );
	      rotator = new RotationInterpolator(rotationAlpha, spin, yAxis, 0.0f, (float) Math.PI* GUI_3D.rotateSpeed );
		    
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