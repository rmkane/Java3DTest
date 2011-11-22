import java.awt.Color;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;


public class Pyramid extends Shape3D 
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

    
    public Pyramid() {
		Point3f frontL = new Point3f(-1.0f, -1.0f, 1.0f); // front left
		Point3f frontR = new Point3f(1.0f, -1.0f, 1.0f); // front right
		Point3f backR = new Point3f(1.0f, -1.0f, -1.0f); // back right
		Point3f backL = new Point3f(-1.0f, -1.0f, -1.0f); // back left
		Point3f top = new Point3f(0.0f, 1.0f, 0.0f); // top
 
		TriangleArray pyramidGeometry = new TriangleArray(18, TriangleArray.COORDINATES|
				GeometryArray.COLOR_3);
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
	
    }
    
    
    TransformGroup createRotator() {
		 Transform3D yAxis = new Transform3D();

		 /* axes of rotation */
	   //yAxis.rotZ(Math.PI / 2.0);  	//X AXIS
	     yAxis.rotY( Math.PI / 2.0 ); //Y AXIS
	   //yAxis.rotX(Math.PI / 2.0);   //Z AXIS
		    
		 TransformGroup spin = new TransformGroup(yAxis);
		 spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		    
		 spin.addChild(new Pyramid()); //add pyramid shape to the spin TG
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
			
			//bg.addChild( tg ) ;
				return tg;
    }
    
    
    public BranchGroup createUserData()
    {
		 branchGroup = new BranchGroup();
		 
		 int pyramidCount = SwingTest.getPyramidCount();
		 
	 	 branchGroup.setUserData( "Pyramid".concat(Integer.toString(pyramidCount)) );
	 	 
	 	 pyramidCount++;
	 	 SwingTest.setCubeCount(pyramidCount);
		 
		 return branchGroup;
    }


	public RotationInterpolator getRotator() {
		return rotator;
	}


	public void setRotator(RotationInterpolator rotator) {
		this.rotator = rotator;
	}
}
