import java.awt.Color;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.geometry.Sphere;


public class aSphere extends Shape3D 
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

    
    public aSphere() {
   		
    	Sphere sphere = new Sphere(1, 1, 35);
		
    	Appearance ap = new Appearance();
    	ColoringAttributes ca = new ColoringAttributes(red, ColoringAttributes.NICEST); 
    	ap.setColoringAttributes(ca);
    	
    	this.setAppearance(ap);
    	
		//set userData (id)
		int sphereCount = SwingTest.getSphereCount();
		sphere.setUserData( "Sphere".concat(Integer.toString(sphereCount)) );
		
		System.out.println("Created: " + sphere.getUserData());
		
		sphereCount++;
	 	SwingTest.setSphereCount(sphereCount);
    }
    
    
    TransformGroup createRotator() {
    	Appearance ap = new Appearance();
    	ColoringAttributes ca = new ColoringAttributes(red, ColoringAttributes.NICEST); 
    	Material mat = new Material(red, black, red, white, 70f);
    	ap.setColoringAttributes(ca);
    	ap.setMaterial(mat);
    	
		 Transform3D yAxis = new Transform3D();

		 /* axes of rotation */
	   //yAxis.rotZ(Math.PI / 2.0);  	//X AXIS
	     yAxis.rotY( Math.PI / 2.0 ); //Y AXIS
	   //yAxis.rotX(Math.PI / 2.0);   //Z AXIS
		    
		 TransformGroup spin = new TransformGroup(yAxis);
		 spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		    
		 spin.addChild(new Sphere(1, 1, 35, ap)); //add sphere shape to the spin TG
		// bg.addChild(spin); //add spin TG to the branchGroup

		    
		 Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0, 4000, 0, 0, 0, 0, 0);
		    
		 rotator = new RotationInterpolator(rotationAlpha, spin, yAxis, 0.0f, (float) Math.PI*2.0f );
		    
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
		 
		 int sphereCount = SwingTest.getSphereCount();
		 
	 	 branchGroup.setUserData( "Sphere".concat(Integer.toString(sphereCount)) );
	 	 
	 	 sphereCount++;
	 	 SwingTest.setSphereCount(sphereCount);
		 
		 return branchGroup;
    }


	public RotationInterpolator getRotator() {
		return rotator;
	}


	public void setRotator(RotationInterpolator rotator) {
		this.rotator = rotator;
	}
}
