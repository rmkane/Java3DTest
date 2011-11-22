import java.awt.Color;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;


public class TriangularPrism extends Shape3D 
{
	private RotationInterpolator rotator;
	private BranchGroup branchGroup;
	
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

    
    public TriangularPrism() {
   		Point3f top1 = new Point3f(0.0f, 0.5f, 1.0f);  //top front
   		Point3f top2 = new Point3f(0.0f, 0.5f, -1.0f);  //top back
   		Point3f frontL = new Point3f(-1.0f,-1.0f, 1.0f); //front left
   		Point3f frontR = new Point3f( 1.0f,-1.0f, 1.0f); //front right
   		Point3f backR = new Point3f( 1.0f,-1.0f, -1.0f);  //back right
   		Point3f backL = new Point3f(-1.0f,-1.0f, -1.0f);  //back left
 
		TriangleArray triPrismGeometry = new TriangleArray(24, TriangleArray.COORDINATES|
				GeometryArray.COLOR_3);
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
	
    }
    
    
    TransformGroup createRotator() {
		 Transform3D yAxis = new Transform3D();

		 /* axes of rotation */
	   //yAxis.rotZ(Math.PI / 2.0);  	//X AXIS
	     yAxis.rotY( Math.PI / 2.0 ); //Y AXIS
	   //yAxis.rotX(Math.PI / 2.0);   //Z AXIS
		    
		 TransformGroup spin = new TransformGroup(yAxis);
		 spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		    
		 spin.addChild(new TriangularPrism()); //add pyramid shape to the spin TG
		// bg.addChild(spin); //add spin TG to the branchGroup

		    
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
		        tg.addChild(myMouseTranslate);
				return tg;
    }
    
    
    public BranchGroup createUserData()
    {
		 branchGroup = new BranchGroup();
		 
		 int triPrismCount = SwingTest.getTriPrismCount();
		 
	 	 branchGroup.setUserData( "TriPrism".concat(Integer.toString(triPrismCount)) );
	 	 
	 	 triPrismCount++;
	 	 SwingTest.setCubeCount(triPrismCount);
		 
		 return branchGroup;
    }


	public RotationInterpolator getRotator() {
		return rotator;
	}


	public void setRotator(RotationInterpolator rotator) {
		this.rotator = rotator;
	}
}
