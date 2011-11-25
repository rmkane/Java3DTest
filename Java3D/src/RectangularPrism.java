import java.awt.Color;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;


public class RectangularPrism extends Shape3D {
	private RotationInterpolator rotator;
	private BranchGroup branchGroup;
	private String id;
	
	private static MouseTranslate myMouseTranslate;
	private TransformGroup tg;
	
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
	
	//BOTTOM
	Point3f b_frontL = new Point3f(-1.0f,-1.0f, 1.0f);  //front left
	Point3f b_frontR = new Point3f( 1.0f,-1.0f, 1.0f);  //front right
	Point3f b_backR = new Point3f( 1.0f,-1.0f,-1.0f);  //back right
	Point3f b_backL = new Point3f(-1.0f,-1.0f,-1.0f);  //back left
		
	//TOP
	Point3f t_frontL = new Point3f(-1.0f, 1.0f, 1.0f);  //front left
	Point3f t_frontR = new Point3f( 1.0f, 1.0f, 1.0f);  //front right
	Point3f t_backR = new Point3f( 1.0f, 1.0f,-1.0f);  //back right
	Point3f t_backL = new Point3f(-1.0f, 1.0f,-1.0f);  //back left
		

    
    public RectangularPrism() {
   		QuadArray rectPrismGeometry = new QuadArray(24, QuadArray.COORDINATES | GeometryArray.COLOR_3);
		rectPrismGeometry.setCoordinate(0, b_frontL);
		rectPrismGeometry.setCoordinate(1, b_frontR);
		rectPrismGeometry.setCoordinate(2, t_frontR);
		rectPrismGeometry.setCoordinate(3, t_frontL);
		rectPrismGeometry.setColor(0, red);
		rectPrismGeometry.setColor(1, red);
		rectPrismGeometry.setColor(2, red);
		rectPrismGeometry.setColor(3, red);
		
		
		rectPrismGeometry.setCoordinate(4, b_frontR);
		rectPrismGeometry.setCoordinate(5, b_backR);
		rectPrismGeometry.setCoordinate(6, t_backR);
		rectPrismGeometry.setCoordinate(7, t_frontR);
		rectPrismGeometry.setColor(4, blue);
		rectPrismGeometry.setColor(5, blue);
		rectPrismGeometry.setColor(6, blue);
		rectPrismGeometry.setColor(7, blue);
		
		
		rectPrismGeometry.setCoordinate(8, b_backR);
		rectPrismGeometry.setCoordinate(9, b_backL);
		rectPrismGeometry.setCoordinate(10, t_backL);
		rectPrismGeometry.setCoordinate(11, t_backR);
		rectPrismGeometry.setColor(8, green);
		rectPrismGeometry.setColor(9, green);
		rectPrismGeometry.setColor(10, green);
		rectPrismGeometry.setColor(11, green);
		
		
		rectPrismGeometry.setCoordinate(12, b_backL);
		rectPrismGeometry.setCoordinate(13, b_frontL);
		rectPrismGeometry.setCoordinate(14, t_frontL);
		rectPrismGeometry.setCoordinate(15, t_backL);
		rectPrismGeometry.setColor(12, yellow);
		rectPrismGeometry.setColor(13, yellow);
		rectPrismGeometry.setColor(14, yellow);
		rectPrismGeometry.setColor(15, yellow);
		
		
		rectPrismGeometry.setCoordinate(16, t_frontL);
		rectPrismGeometry.setCoordinate(17, t_frontR);
		rectPrismGeometry.setCoordinate(18, t_backR);
		rectPrismGeometry.setCoordinate(19, t_backL);
		rectPrismGeometry.setColor(16, gray);
		rectPrismGeometry.setColor(17, gray);
		rectPrismGeometry.setColor(18, gray);
		rectPrismGeometry.setColor(19, gray);
		
		
		rectPrismGeometry.setCoordinate(20, b_backL);
		rectPrismGeometry.setCoordinate(21, b_backR);
		rectPrismGeometry.setCoordinate(22, b_frontR);
		rectPrismGeometry.setCoordinate(23, b_frontL);
		rectPrismGeometry.setColor(20, purple);
		rectPrismGeometry.setColor(21, purple);
		rectPrismGeometry.setColor(22, purple);
		rectPrismGeometry.setColor(23, purple);
		
		
		
		this.setGeometry(rectPrismGeometry);
		this.setAppearance(new Appearance());
		

		//set userData (id)
		int rectPrismCount = SwingTest.getRectPrismCount();
		this.setUserData( "rectPrism".concat(Integer.toString(rectPrismCount)) );
		
		System.out.println("Created: " + this.getUserData());
		
		rectPrismCount++;
	 	SwingTest.setRectPrismCount(rectPrismCount);
	
    }
    
    
    public Node rectPrismEdges() {
  		QuadArray rectPrismEdgeGeometry = new QuadArray(24, QuadArray.COORDINATES | GeometryArray.COLOR_3);
  		rectPrismEdgeGeometry.setCoordinate(0, b_frontL);
  		rectPrismEdgeGeometry.setCoordinate(1, b_frontR);
  		rectPrismEdgeGeometry.setCoordinate(2, t_frontR);
  		rectPrismEdgeGeometry.setCoordinate(3, t_frontL);
  		rectPrismEdgeGeometry.setColor(0, black);
  		rectPrismEdgeGeometry.setColor(1, black);
  		rectPrismEdgeGeometry.setColor(2, black);
  		rectPrismEdgeGeometry.setColor(3, black);
		
		
  		rectPrismEdgeGeometry.setCoordinate(4, b_frontR);
  		rectPrismEdgeGeometry.setCoordinate(5, b_backR);
		rectPrismEdgeGeometry.setCoordinate(6, t_backR);
		rectPrismEdgeGeometry.setCoordinate(7, t_frontR);
		rectPrismEdgeGeometry.setColor(4, black);
		rectPrismEdgeGeometry.setColor(5, black);
		rectPrismEdgeGeometry.setColor(6, black);
		rectPrismEdgeGeometry.setColor(7, black);
		
		
		rectPrismEdgeGeometry.setCoordinate(8, b_backR);
		rectPrismEdgeGeometry.setCoordinate(9, b_backL);
		rectPrismEdgeGeometry.setCoordinate(10, t_backL);
		rectPrismEdgeGeometry.setCoordinate(11, t_backR);
		rectPrismEdgeGeometry.setColor(8, black);
		rectPrismEdgeGeometry.setColor(9, black);
		rectPrismEdgeGeometry.setColor(10, black);
		rectPrismEdgeGeometry.setColor(11, black);
		
		
		rectPrismEdgeGeometry.setCoordinate(12, b_backL);
		rectPrismEdgeGeometry.setCoordinate(13, b_frontL);
		rectPrismEdgeGeometry.setCoordinate(14, t_frontL);
		rectPrismEdgeGeometry.setCoordinate(15, t_backL);
		rectPrismEdgeGeometry.setColor(12, black);
		rectPrismEdgeGeometry.setColor(13, black);
		rectPrismEdgeGeometry.setColor(14, black);
		rectPrismEdgeGeometry.setColor(15, black);
		
		
		rectPrismEdgeGeometry.setCoordinate(16, t_frontL);
		rectPrismEdgeGeometry.setCoordinate(17, t_frontR);
		rectPrismEdgeGeometry.setCoordinate(18, t_backR);
		rectPrismEdgeGeometry.setCoordinate(19, t_backL);
		rectPrismEdgeGeometry.setColor(16, black);
		rectPrismEdgeGeometry.setColor(17, black);
		rectPrismEdgeGeometry.setColor(18, black);
		rectPrismEdgeGeometry.setColor(19, black);
		
		
		rectPrismEdgeGeometry.setCoordinate(20, b_backL);
		rectPrismEdgeGeometry.setCoordinate(21, b_backR);
		rectPrismEdgeGeometry.setCoordinate(22, b_frontR);
		rectPrismEdgeGeometry.setCoordinate(23, b_frontL);
		rectPrismEdgeGeometry.setColor(20, black);
		rectPrismEdgeGeometry.setColor(21, black);
		rectPrismEdgeGeometry.setColor(22, black);
		rectPrismEdgeGeometry.setColor(23, black);
		
		
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
        
        Shape3D rectPrismEdges = new Shape3D();
		rectPrismEdges.setGeometry(rectPrismEdgeGeometry);
		rectPrismEdges.setAppearance(app);
		
		return rectPrismEdges;
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
		 

		 spin.addChild(this); //add rectPrism shape to the spin TG
		 spin.addChild(rectPrismEdges());

		    
		 Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0,  4000, 0, 0, 0, 0, 0);
		    
		 rotator = new RotationInterpolator(rotationAlpha, spin, yAxis, 0.0f, (float) Math.PI*2.0f );
		    
		 BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		 rotator.setSchedulingBounds(bounds);
		 spin.addChild(rotator);  //add interpolator rotator to the spin TG
		 
		 
		 TransformGroup tg = new TransformGroup() ;
		 tg.setUserData("TG: TG".concat(Integer.toString(SwingTest.getRectPrismCount())));
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