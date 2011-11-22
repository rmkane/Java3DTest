import java.awt.Color;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;


public class HexagonalPrism extends Shape3D 
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

    
    public HexagonalPrism() {
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
   		
   		
 
		TriangleArray triPrismGeometry = new TriangleArray(72, TriangleArray.COORDINATES|
				GeometryArray.COLOR_3);
		triPrismGeometry.setCoordinate(0, f_se);
		triPrismGeometry.setCoordinate(1, f_center);
		triPrismGeometry.setCoordinate(2, f_sw);
		triPrismGeometry.setColor(0, red);
		triPrismGeometry.setColor(1, red);
		triPrismGeometry.setColor(2, red);
 
		//front face
		triPrismGeometry.setCoordinate(3, f_e);
		triPrismGeometry.setCoordinate(4, f_center);
		triPrismGeometry.setCoordinate(5, f_se);
		triPrismGeometry.setColor(3, red);
		triPrismGeometry.setColor(4, red);
		triPrismGeometry.setColor(5, red);
		
		triPrismGeometry.setCoordinate(6, f_ne);
		triPrismGeometry.setCoordinate(7, f_center);
		triPrismGeometry.setCoordinate(8, f_e);
		triPrismGeometry.setColor(6, red);
		triPrismGeometry.setColor(7, red);
		triPrismGeometry.setColor(8, red);
 	
		triPrismGeometry.setCoordinate(9, f_nw);
		triPrismGeometry.setCoordinate(10, f_center);
		triPrismGeometry.setCoordinate(11, f_ne);
		triPrismGeometry.setColor(9, red);
		triPrismGeometry.setColor(10, red);
		triPrismGeometry.setColor(11, red);
 
		triPrismGeometry.setCoordinate(12, f_w);
		triPrismGeometry.setCoordinate(13, f_center);
		triPrismGeometry.setCoordinate(14, f_nw);
		triPrismGeometry.setColor(12, red);
		triPrismGeometry.setColor(13, red);
		triPrismGeometry.setColor(14, red);
 
		triPrismGeometry.setCoordinate(15, f_sw);
		triPrismGeometry.setCoordinate(16, f_center);
		triPrismGeometry.setCoordinate(17, f_w);
		triPrismGeometry.setColor(15, red);
		triPrismGeometry.setColor(16, red);
		triPrismGeometry.setColor(17, red);
		
		
		//back face
		triPrismGeometry.setCoordinate(18, b_sw);
		triPrismGeometry.setCoordinate(19, b_center);
		triPrismGeometry.setCoordinate(20, b_se);
		triPrismGeometry.setColor(18, blue);
		triPrismGeometry.setColor(19, blue);
		triPrismGeometry.setColor(20, blue);
		
		triPrismGeometry.setCoordinate(21, b_se);
		triPrismGeometry.setCoordinate(22, b_center);
		triPrismGeometry.setCoordinate(23, b_e);
		triPrismGeometry.setColor(21, blue);
		triPrismGeometry.setColor(22, blue);
		triPrismGeometry.setColor(23, blue);
		
		triPrismGeometry.setCoordinate(24, b_e);
		triPrismGeometry.setCoordinate(25, b_center);
		triPrismGeometry.setCoordinate(26, b_ne);
		triPrismGeometry.setColor(24, blue);
		triPrismGeometry.setColor(25, blue);
		triPrismGeometry.setColor(26, blue);
		
		triPrismGeometry.setCoordinate(27, b_ne);
		triPrismGeometry.setCoordinate(28, b_center);
		triPrismGeometry.setCoordinate(29, b_nw);
		triPrismGeometry.setColor(27, blue);
		triPrismGeometry.setColor(28, blue);
		triPrismGeometry.setColor(29, blue);
		
		triPrismGeometry.setCoordinate(30, b_nw);
		triPrismGeometry.setCoordinate(31, b_center);
		triPrismGeometry.setCoordinate(32, b_w);
		triPrismGeometry.setColor(30, blue);
		triPrismGeometry.setColor(31, blue);
		triPrismGeometry.setColor(32, blue);
		
		triPrismGeometry.setCoordinate(33, b_w);
		triPrismGeometry.setCoordinate(34, b_center);
		triPrismGeometry.setCoordinate(35, b_sw);
		triPrismGeometry.setColor(33, blue);
		triPrismGeometry.setColor(34, blue);
		triPrismGeometry.setColor(35, blue);
		
		
		//north-east face
		triPrismGeometry.setCoordinate(36, b_e);
		triPrismGeometry.setCoordinate(37, f_ne);
		triPrismGeometry.setCoordinate(38, f_e);
		triPrismGeometry.setColor(36, purple);
		triPrismGeometry.setColor(37, purple);
		triPrismGeometry.setColor(38, purple);
		
		triPrismGeometry.setCoordinate(39, b_ne);
		triPrismGeometry.setCoordinate(40, f_ne);
		triPrismGeometry.setCoordinate(41, b_e);
		triPrismGeometry.setColor(39, purple);
		triPrismGeometry.setColor(40, purple);
		triPrismGeometry.setColor(41, purple);
		
		
		//south-east face
		triPrismGeometry.setCoordinate(42, b_se);
		triPrismGeometry.setCoordinate(43, f_e);
		triPrismGeometry.setCoordinate(44, f_se);
		triPrismGeometry.setColor(42, green);
		triPrismGeometry.setColor(43, green);
		triPrismGeometry.setColor(44, green);
		
		triPrismGeometry.setCoordinate(45, b_e);
		triPrismGeometry.setCoordinate(46, f_e);
		triPrismGeometry.setCoordinate(47, b_se);
		triPrismGeometry.setColor(45, green);
		triPrismGeometry.setColor(46, green);
		triPrismGeometry.setColor(47, green);
		
		
		//south-west face
		triPrismGeometry.setCoordinate(48, f_sw);
		triPrismGeometry.setCoordinate(49, b_w);
		triPrismGeometry.setCoordinate(50, b_sw);
		triPrismGeometry.setColor(48, yellow);
		triPrismGeometry.setColor(49, yellow);
		triPrismGeometry.setColor(50, yellow);
		
		triPrismGeometry.setCoordinate(51, f_w);
		triPrismGeometry.setCoordinate(52, b_w);
		triPrismGeometry.setCoordinate(53, f_sw);
		triPrismGeometry.setColor(51, yellow);
		triPrismGeometry.setColor(52, yellow);
		triPrismGeometry.setColor(53, yellow);
		
		
		//north-west face
		triPrismGeometry.setCoordinate(54, f_w);
		triPrismGeometry.setCoordinate(55, b_nw);
		triPrismGeometry.setCoordinate(56, b_w);
		triPrismGeometry.setColor(54, gray);
		triPrismGeometry.setColor(55, gray);
		triPrismGeometry.setColor(56, gray);
		
		triPrismGeometry.setCoordinate(57, f_nw);
		triPrismGeometry.setCoordinate(58, b_nw);
		triPrismGeometry.setCoordinate(59, f_w);
		triPrismGeometry.setColor(57, gray);
		triPrismGeometry.setColor(58, gray);
		triPrismGeometry.setColor(59, gray);
		
		
		//top face
		triPrismGeometry.setCoordinate(60, f_nw);
		triPrismGeometry.setCoordinate(61, f_ne);
		triPrismGeometry.setCoordinate(62, b_nw);
		triPrismGeometry.setColor(60, orange);
		triPrismGeometry.setColor(61, orange);
		triPrismGeometry.setColor(62, orange);
		
		triPrismGeometry.setCoordinate(63, b_ne);
		triPrismGeometry.setCoordinate(64, b_nw);
		triPrismGeometry.setCoordinate(65, f_ne);
		triPrismGeometry.setColor(63, orange);
		triPrismGeometry.setColor(64, orange);
		triPrismGeometry.setColor(65, orange);
		
		
		//bottom face
		triPrismGeometry.setCoordinate(66, b_se);
		triPrismGeometry.setCoordinate(67, f_sw);
		triPrismGeometry.setCoordinate(68, b_sw);
		triPrismGeometry.setColor(66, cyan);
		triPrismGeometry.setColor(67, cyan);
		triPrismGeometry.setColor(68, cyan);
		
		triPrismGeometry.setCoordinate(69, f_se);
		triPrismGeometry.setCoordinate(70, f_sw);
		triPrismGeometry.setCoordinate(71, b_se);
		triPrismGeometry.setColor(69, cyan);
		triPrismGeometry.setColor(70, cyan);
		triPrismGeometry.setColor(71, cyan);
		
		
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
		    
		 spin.addChild(new HexagonalPrism()); //add pyramid shape to the spin TG
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
		 
		 int hexPrismCount = SwingTest.getHexPrismCount();
		 
	 	 branchGroup.setUserData( "HexPrism".concat(Integer.toString(hexPrismCount)) );
	 	 
	 	 hexPrismCount++;
	 	 SwingTest.setCubeCount(hexPrismCount);
		 
		 return branchGroup;
    }


	public RotationInterpolator getRotator() {
		return rotator;
	}


	public void setRotator(RotationInterpolator rotator) {
		this.rotator = rotator;
	}
}
