import java.awt.Color;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;


public class Cube extends Shape3D 
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

    
    public Cube() {
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
 
   		QuadArray cubeGeometry = new QuadArray(24, QuadArray.COORDINATES|
				GeometryArray.COLOR_3);
		cubeGeometry.setCoordinate(0, b_frontL);
		cubeGeometry.setCoordinate(1, b_frontR);
		cubeGeometry.setCoordinate(2, t_frontR);
		cubeGeometry.setCoordinate(3, t_frontL);
		cubeGeometry.setColor(0, red);
		cubeGeometry.setColor(1, red);
		cubeGeometry.setColor(2, red);
		cubeGeometry.setColor(3, red);
		
		
		cubeGeometry.setCoordinate(4, b_frontR);
		cubeGeometry.setCoordinate(5, b_backR);
		cubeGeometry.setCoordinate(6, t_backR);
		cubeGeometry.setCoordinate(7, t_frontR);
		cubeGeometry.setColor(4, blue);
		cubeGeometry.setColor(5, blue);
		cubeGeometry.setColor(6, blue);
		cubeGeometry.setColor(7, blue);
		
		
		cubeGeometry.setCoordinate(8, b_backR);
		cubeGeometry.setCoordinate(9, b_backL);
		cubeGeometry.setCoordinate(10, t_backL);
		cubeGeometry.setCoordinate(11, t_backR);
		cubeGeometry.setColor(8, green);
		cubeGeometry.setColor(9, green);
		cubeGeometry.setColor(10, green);
		cubeGeometry.setColor(11, green);
		
		
		cubeGeometry.setCoordinate(12, b_backL);
		cubeGeometry.setCoordinate(13, b_frontL);
		cubeGeometry.setCoordinate(14, t_frontL);
		cubeGeometry.setCoordinate(15, t_backL);
		cubeGeometry.setColor(12, yellow);
		cubeGeometry.setColor(13, yellow);
		cubeGeometry.setColor(14, yellow);
		cubeGeometry.setColor(15, yellow);
		
		
		cubeGeometry.setCoordinate(16, t_frontL);
		cubeGeometry.setCoordinate(17, t_frontR);
		cubeGeometry.setCoordinate(18, t_backR);
		cubeGeometry.setCoordinate(19, t_backL);
		cubeGeometry.setColor(16, gray);
		cubeGeometry.setColor(17, gray);
		cubeGeometry.setColor(18, gray);
		cubeGeometry.setColor(19, gray);
		
		
		cubeGeometry.setCoordinate(20, b_backL);
		cubeGeometry.setCoordinate(21, b_backR);
		cubeGeometry.setCoordinate(22, b_frontR);
		cubeGeometry.setCoordinate(23, b_frontL);
		cubeGeometry.setColor(20, purple);
		cubeGeometry.setColor(21, purple);
		cubeGeometry.setColor(22, purple);
		cubeGeometry.setColor(23, purple);
		
		
		
		this.setGeometry(cubeGeometry);
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
		    
		 spin.addChild(new Cube()); //add pyramid shape to the spin TG
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
			
			
		    //MouseTranslate myMouseTranslate = new MouseTranslate(
		    //        );
		    //    myMouseTranslate.setTransformGroup(tg);
		    //    myMouseTranslate.setSchedulingBounds(bounds1);
		    //    tg.addChild(myMouseTranslate);
			
			//bg.addChild( tg ) ;
				return tg;
    }
    
    
    public BranchGroup createUserData()
    {
		 branchGroup = new BranchGroup();
		 
		 int cubeCount = SwingTest.getCubeCount();
		 
	 	 branchGroup.setUserData( "Cube".concat(Integer.toString(cubeCount)) );
	 	 
	 	 cubeCount++;
	 	 SwingTest.setCubeCount(cubeCount);
		 
		 return branchGroup;
    }


	public RotationInterpolator getRotator() {
		return rotator;
	}


	public void setRotator(RotationInterpolator rotator) {
		this.rotator = rotator;
	}
	
	
	public BranchGroup getBranchGroup() {
		return branchGroup;
	}


	public void setBranchGroup(BranchGroup bg) {
		this.branchGroup = bg;
	}
}
