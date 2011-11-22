import com.sun.j3d.utils.picking.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import java.awt.event.*;
import java.awt.*;

//import javax.vecmath.AxisAngle4f;


public class Pick extends MouseAdapter {

private PickCanvas pickCanvas;
private Canvas3D canvas;


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






	public Pick()
	{
	    //Frame frame = new Frame("Box and Sphere");
	    
	    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

	    canvas = new Canvas3D(config);
	    canvas.setSize(900, 700);
	
	    SimpleUniverse universe = new SimpleUniverse(canvas);
	
	    BranchGroup group = new BranchGroup();
	    
	    
	    
		Point3f frontL = new Point3f(-0.2f, -0.2f, 0.2f); // south
		Point3f frontR = new Point3f(0.2f, -0.2f, 0.2f); // east
		Point3f backR = new Point3f(0.2f, -0.2f, -0.2f); // west
		Point3f backL = new Point3f(0.0f, 0.0f, -0.2f); // north
		Point3f top = new Point3f(0.0f, 0.2f, 0.0f); // top
 
		TriangleArray pyramidFrontFace = new TriangleArray(3, TriangleArray.COORDINATES);
		pyramidFrontFace.setCoordinate(0, frontR);
		pyramidFrontFace.setCoordinate(1, top);
		pyramidFrontFace.setCoordinate(2, frontL);
 
		/*
		pyramidGeometry.setCoordinate(3, frontL);
		pyramidGeometry.setCoordinate(4, top);
		pyramidGeometry.setCoordinate(5, backR);
 
		pyramidGeometry.setCoordinate(6, backR);
		pyramidGeometry.setCoordinate(7, top);
		pyramidGeometry.setCoordinate(8, backL);
 
		pyramidGeometry.setCoordinate(9, backL);
		pyramidGeometry.setCoordinate(10, top);
		pyramidGeometry.setCoordinate(11, frontR);
 
		pyramidGeometry.setCoordinate(12, frontR);
		pyramidGeometry.setCoordinate(13, frontL);
		pyramidGeometry.setCoordinate(14, backR);
 
		pyramidGeometry.setCoordinate(15, backR);
		pyramidGeometry.setCoordinate(16, backL);
		pyramidGeometry.setCoordinate(17, frontR);
		*/
		

		GeometryInfo geometryInfo = new GeometryInfo(pyramidFrontFace);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(geometryInfo);
		
		
		TriangleArray pyramidRightFace = new TriangleArray(3, TriangleArray.COORDINATES);
		pyramidRightFace.setCoordinate(0, frontR);
		pyramidRightFace.setCoordinate(1, top);
		pyramidRightFace.setCoordinate(2, frontL);
		
		GeometryInfo geometryInfo2 = new GeometryInfo(pyramidFrontFace);
		NormalGenerator ng2 = new NormalGenerator();
		ng.generateNormals(geometryInfo2);
		
		
		
	    Appearance frontFaceAppearance = new Appearance();
	    Appearance rightFaceAppearance = new Appearance();
	    
	    frontFaceAppearance.setColoringAttributes(new ColoringAttributes (red, 1));
	    rightFaceAppearance.setColoringAttributes(new ColoringAttributes (blue, 1));

		Shape3D pyramidFace1 = new Shape3D( pyramidFrontFace, frontFaceAppearance );
		Shape3D pyramidFace2 = new Shape3D(pyramidRightFace, rightFaceAppearance );
	    
	    

	    
	    AxisAngle4f a1 =  new AxisAngle4f(0, 1, 0, 30 );
	    Transform3D transform = new Transform3D();
	    transform.set(a1);
		
	    TransformGroup transformGroup = new TransformGroup(transform);
		transformGroup.addChild(pyramidFace1);
		transformGroup.addChild(pyramidFace2);
		group.addChild(transformGroup);
	    

 
		//GeometryArray result = geometryInfo.getGeometryArray();
	    
	    
	
	    
	    // create a color cube
	    //Vector3f vector = new Vector3f(-0.5f, 0.0f, 0.0f); //translation vector
	    
	   // Transform3D transform = new Transform3D();
	   // transform.setTranslation(vector); //applies translation vector
	   // TransformGroup transformGroup = new TransformGroup(transform);
	
	    
	  //  ColorCube cube = new ColorCube(0.1);
	  //  transformGroup.addChild(cube);
	  //  group.addChild(transformGroup);
	
	    
	    
	    //create a sphere
	    /*
	    Appearance triangle1Appearance = new Appearance();
	    ColoringAttributes color1ca = new ColoringAttributes (red, 1);
		triangle1Appearance.setColoringAttributes(color1ca);

	    
		TriangleArray triangle1 = new TriangleArray (3, TriangleArray.COORDINATES);
		triangle1.setCoordinate (0, new Point3f (-0.2f, -0.2f, 0f));
		triangle1.setCoordinate (1, new Point3f (0.2f, -0.2f, 0f));
		triangle1.setCoordinate (2, new Point3f (0f, 0.2f, 0f));
		group.addChild(new Shape3D(triangle1, triangle1Appearance));
		
		
		
	    Appearance triangle2Appearance = new Appearance();
	    ColoringAttributes color2ca = new ColoringAttributes (blue, 1);
		triangle1Appearance.setColoringAttributes(color1ca);

	    
		TriangleArray triangle2 = new TriangleArray (3, TriangleArray.COORDINATES);
		triangle1.setCoordinate (0, new Point3f (0.2f, -0.2f, 0.0f));
		triangle1.setCoordinate (1, new Point3f (0.2f, -0.2f, -0.4f));
		triangle1.setCoordinate (2, new Point3f (0f, 0.2f, 0f));
		group.addChild(new Shape3D(triangle2, triangle2Appearance));
		
		
	    Appearance triangle3Appearance = new Appearance();
	    ColoringAttributes color3ca = new ColoringAttributes (green, 1);
		triangle1Appearance.setColoringAttributes(color1ca);

	    
		TriangleArray triangle3 = new TriangleArray (3, TriangleArray.COORDINATES);
		triangle1.setCoordinate (0, new Point3f (0.2f, -0.2f, 0.4f));
		triangle1.setCoordinate (1, new Point3f (0.2f, -0.2f, 0.0f));
		triangle1.setCoordinate (2, new Point3f (0f, 0.2f, 0f));
		group.addChild(new Shape3D(triangle3, triangle3Appearance));
		*/
	    
	    
	    Vector3f vector2 = new Vector3f(0.5f, 0.0f, 0.0f); //translation vector
	    
	    Transform3D transform2 = new Transform3D();
	    transform2.setTranslation(vector2); //applies translation vector
	    TransformGroup transformGroup2 = new TransformGroup(transform2);
	
	    Appearance appearance = new Appearance();
	    appearance.setPolygonAttributes(
	       new PolygonAttributes
		       (PolygonAttributes.POLYGON_FILL,
		       PolygonAttributes.CULL_BACK,0.0f)
	       );
	
	    
	    Sphere sphere = new Sphere(0.1f, 1, 30, appearance);
	    
	    transformGroup2.addChild(sphere);
	    group.addChild(transformGroup2);
	    
	    
		BoundingSphere bounds =
		new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		
		
		/* A dull gray background */
		Background background = new Background(pink);
		background.setApplicationBounds(bounds);
		group.addChild(background); 
		
	
	    universe.getViewingPlatform().setNominalViewingTransform();
	    universe.addBranchGraph(group);
	    
	    

	
	    //frame.addWindowListener(new WindowAdapter() 
	    //{
	    //   public void windowClosing(WindowEvent winEvent) {
	    //      System.exit(0);
	    //   }
//
	    //});

	    
	    //frame.add(canvas);
    
	    pickCanvas = new PickCanvas(canvas, group);
	    pickCanvas.setMode(PickCanvas.BOUNDS);
	
	    canvas.addMouseListener(this);
	    
	    //frame.pack();
	    //frame.show();
	}



	public static void main( String[] args ) {
	    new Pick();
	}
	
	public void mouseClicked(MouseEvent e)
	{
	    pickCanvas.setShapeLocation(e);
	    PickResult result = pickCanvas.pickClosest();
	
	    if (result == null) {
	       System.out.println("Nothing picked");
	
	    } else {
	       Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);
	       Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D);
	
	       if (p != null) {
	          System.out.println(p.getClass().getName());
	
	       } else if (s != null) {
	             System.out.println(s.getClass().getName());
	
	       } else {
	          System.out.println("null");
	       }
	    }
	}
	
	
	
	public Canvas3D getCanvas() {
		return canvas;
	}


	public void setCanvas(Canvas3D canvas) {
		this.canvas = canvas;
	}
}