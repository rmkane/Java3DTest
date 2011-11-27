import java.awt.Color;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;


public class Pyramid extends Shape3D {
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
	
	float tx = 0.0f;
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
	
	
	Point3f frontL = new Point3f(-1.0f, -1.0f, 1.0f); // front left
	Point3f frontR = new Point3f(1.0f, -1.0f, 1.0f); // front right
	Point3f backR = new Point3f(1.0f, -1.0f, -1.0f); // back right
	Point3f backL = new Point3f(-1.0f, -1.0f, -1.0f); // back left
	Point3f top = new Point3f(0.0f, 1.0f, 0.0f); // top

    
    public Pyramid() {
		TriangleArray pyramidGeometry = new TriangleArray(18, TriangleArray.COORDINATES | GeometryArray.COLOR_3);
		pyramidGeometry.setCoordinate(0, frontR);
		pyramidGeometry.setCoordinate(1, top);
		pyramidGeometry.setCoordinate(2, frontL);
		pyramidGeometry.setColor(0, red);
		pyramidGeometry.setColor(1, red);
		pyramidGeometry.setColor(2, red);
 
		pyramidGeometry.setCoordinate(3, backR);
		pyramidGeometry.setCoordinate(4, top);
		pyramidGeometry.setCoordinate(5, frontR);
		pyramidGeometry.setColor(3, blue);
		pyramidGeometry.setColor(4, blue);
		pyramidGeometry.setColor(5, blue);
 
		pyramidGeometry.setCoordinate(6, backL);
		pyramidGeometry.setCoordinate(7, top);
		pyramidGeometry.setCoordinate(8, backR);
		pyramidGeometry.setColor(6, gray);
		pyramidGeometry.setColor(7, gray);
		pyramidGeometry.setColor(8, gray);
 
		pyramidGeometry.setCoordinate(9, frontL);
		pyramidGeometry.setCoordinate(10, top);
		pyramidGeometry.setCoordinate(11, backL);
		pyramidGeometry.setColor(9, green);
		pyramidGeometry.setColor(10, green);
		pyramidGeometry.setColor(11, green);
 
		pyramidGeometry.setCoordinate(12, backL);
		pyramidGeometry.setCoordinate(13, backR);
		pyramidGeometry.setCoordinate(14, frontR);
		pyramidGeometry.setColor(12, purple);
		pyramidGeometry.setColor(13, purple);
		pyramidGeometry.setColor(14, purple);
 
		pyramidGeometry.setCoordinate(15, frontR);
		pyramidGeometry.setCoordinate(16, frontL);
		pyramidGeometry.setCoordinate(17, backL);
		pyramidGeometry.setColor(15, purple);
		pyramidGeometry.setColor(16, purple);
		pyramidGeometry.setColor(17, purple);
		

		this.setGeometry(pyramidGeometry);
		this.setAppearance(new Appearance());
		
		int pyramidCount = SwingTest.getPyramidCount();
		this.setUserData( "Pyramid".concat(Integer.toString(pyramidCount)) );
		
		pyramidCount++;
	 	SwingTest.setPyramidCount(pyramidCount);

	 	Transform3D defaultSize = new Transform3D();
	 	defaultSize.setScale(new Vector3d(1.0, 1.0, 1.0));
	 	setResize(defaultSize);
    }
    
    
    public Node pyramidEdges() {
		TriangleArray pyramidEdgeGeometry = new TriangleArray(18, TriangleArray.COORDINATES | GeometryArray.COLOR_3);
		pyramidEdgeGeometry.setCoordinate(0, frontR);
		pyramidEdgeGeometry.setCoordinate(1, top);
		pyramidEdgeGeometry.setCoordinate(2, frontL);
		pyramidEdgeGeometry.setColor(0, black);
		pyramidEdgeGeometry.setColor(1, black);
		pyramidEdgeGeometry.setColor(2, black);
 
		pyramidEdgeGeometry.setCoordinate(3, backR);
		pyramidEdgeGeometry.setCoordinate(4, top);
		pyramidEdgeGeometry.setCoordinate(5, frontR);
		pyramidEdgeGeometry.setColor(3, black);
		pyramidEdgeGeometry.setColor(4, black);
		pyramidEdgeGeometry.setColor(5, black);
 
		pyramidEdgeGeometry.setCoordinate(6, backL);
		pyramidEdgeGeometry.setCoordinate(7, top);
		pyramidEdgeGeometry.setCoordinate(8, backR);
		pyramidEdgeGeometry.setColor(6, black);
		pyramidEdgeGeometry.setColor(7, black);
		pyramidEdgeGeometry.setColor(8, black);
 
		pyramidEdgeGeometry.setCoordinate(9, frontL);
		pyramidEdgeGeometry.setCoordinate(10, top);
		pyramidEdgeGeometry.setCoordinate(11, backL);
		pyramidEdgeGeometry.setColor(9, black);
		pyramidEdgeGeometry.setColor(10, black);
		pyramidEdgeGeometry.setColor(11, black);
 
		pyramidEdgeGeometry.setCoordinate(12, backL);
		pyramidEdgeGeometry.setCoordinate(13, backR);
		pyramidEdgeGeometry.setCoordinate(14, frontR);
		pyramidEdgeGeometry.setColor(12, black);
		pyramidEdgeGeometry.setColor(13, black);
		pyramidEdgeGeometry.setColor(14, black);
 
		pyramidEdgeGeometry.setCoordinate(15, frontR);
		pyramidEdgeGeometry.setCoordinate(16, frontL);
		pyramidEdgeGeometry.setCoordinate(17, backL);
		pyramidEdgeGeometry.setColor(15, black);
		pyramidEdgeGeometry.setColor(16, black);
		pyramidEdgeGeometry.setColor(17, black);
		
		
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
        
        Shape3D pyramidEdges = new Shape3D();
		pyramidEdges.setGeometry(pyramidEdgeGeometry);
		pyramidEdges.setAppearance(app);
		
		return pyramidEdges;
    }
    
    
    
    TransformGroup createRotator() {
    	Transform3D yAxis = new Transform3D();

		 /* axes of rotation */
	     yAxis.rotZ(Math.PI / 2.0);  	//X AXIS
	     //yAxis.rotY( Math.PI / 2.0 ); //Y AXIS
	     //yAxis.rotX(Math.PI / 2.0);   //Z AXIS
		    
		 TransformGroup spin = new TransformGroup(yAxis);
		 spin.setUserData("TG: SPIN");
		 
		 
		 spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		 

		 spin.addChild(this); //add pyramid shape to the spin TG
		 spin.addChild(pyramidEdges());

		    
		 Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0,  4000, 0, 0, 0, 0, 0);
	     rotator = new RotationInterpolator(rotationAlpha, spin, yAxis, 0.0f, (float) Math.PI* GUI_3D.rotateSpeed );

		    
		 BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		 rotator.setSchedulingBounds(bounds);
		 spin.addChild(rotator);  //add interpolator rotator to the spin TG
		 
		 
		 TransformGroup tg = new TransformGroup() ;
		 tg.setUserData("TG: TG".concat(Integer.toString(SwingTest.getPyramidCount())));
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