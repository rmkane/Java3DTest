import java.awt.Color;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.geometry.Cylinder;
//import com.sun.j3d.utils.geometry.Sphere;

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
	private Appearance ap;

	public Appearance getAp() {
		return ap;
	}


	public void setAp(Appearance ap) {
		this.ap = ap;
	}


	public Alpha getRotationAlpha() {
		return rotationAlpha;
	}


	public void setRotationAlpha(Alpha rotationAlpha) {
		this.rotationAlpha = rotationAlpha;
	}


    public RotationInterpolator getRotator() {
		return rotator;
	}


	public void setRotator(RotationInterpolator rotator) {
		this.rotator = rotator;
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

	//private Cylinder cylinder;

    
    public aCylinder() {
   		
    	Cylinder cylinder = new Cylinder();

    	ap = new Appearance();
    	ap.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    	
    	Material mat = new Material(Colors.BLACK, Colors.BLACK, Colors.BLUE, Colors.WHITE, 30f);
    	mat.setCapability(Material.ALLOW_COMPONENT_WRITE);
    	ap.setMaterial(mat);
    	
    	this.setAppearance(ap);

		//set userData (id)
		int cylinderCount = SwingTest.getCylinderCount();
		this.setUserData( "Cylinder".concat(Integer.toString(cylinderCount)) );

		System.out.println("Created: " + getUserData());

		cylinderCount++;
	 	SwingTest.setCylinderCount(cylinderCount);


	 	//getShape(0) returns the body of the cylinder
	 	//getShape(1) gives the bottom
	 	//getShape(2) gives the top
	 	//There should be a way to merge them
	 	this.setGeometry(cylinder.getShape(0).getGeometry());
	 	this.insertGeometry(cylinder.getShape(1).getGeometry(), 0);
	 	this.insertGeometry(cylinder.getShape(2).getGeometry(), 0);

	 	Transform3D defaultSize = new Transform3D();
	 	defaultSize.setScale(new Vector3d(1.0, 1.0, 1.0));
	 	setResize(defaultSize);
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


		rotationAlpha = new Alpha(0, Alpha.INCREASING_ENABLE, 0, 0, 4000, 0, 0, 0, 0, 0);

		rotator = new RotationInterpolator(rotationAlpha, spin, yAxis, 0.0f, (float) Math.PI*2.0f);


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
}