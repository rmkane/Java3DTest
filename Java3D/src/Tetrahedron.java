import java.awt.BorderLayout ;
import java.awt.Color ;
import java.awt.Container ;
import java.awt.GraphicsConfiguration ;

import java.awt.event.WindowAdapter ;
import java.awt.event.WindowEvent ;

import javax.media.j3d.Alpha ;
import javax.media.j3d.AmbientLight ;
import javax.media.j3d.Appearance ;
import javax.media.j3d.Background ;
import javax.media.j3d.BoundingSphere ;
import javax.media.j3d.BranchGroup ;
import javax.media.j3d.Canvas3D ;
import javax.media.j3d.ColoringAttributes ;
import javax.media.j3d.DirectionalLight ;
import javax.media.j3d.Geometry ;
import javax.media.j3d.GeometryArray ;
import javax.media.j3d.IndexedTriangleArray ;
import javax.media.j3d.Light ;
import javax.media.j3d.Material ;
import javax.media.j3d.Node ;
import javax.media.j3d.PolygonAttributes ;
import javax.media.j3d.RotationInterpolator ;
import javax.media.j3d.Shape3D ;
import javax.media.j3d.Transform3D ;
import javax.media.j3d.TransformGroup ;
import javax.media.j3d.TriangleArray;

import javax.swing.JFrame ;

import javax.vecmath.Color3f ;
import javax.vecmath.Point3d ;
import javax.vecmath.Point3f ;
import javax.vecmath.Vector3f ;

import com.sun.j3d.utils.behaviors.mouse.MouseBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.geometry.GeometryInfo ;
import com.sun.j3d.utils.geometry.NormalGenerator ;
import com.sun.j3d.utils.universe.SimpleUniverse ;

public class Tetrahedron extends JFrame {
	
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
	

    public static void main(String[] args) {
    	Tetrahedron tetrahedron = new Tetrahedron( "Tetrahedron", 512, 512 ) ;
    }

    
    public Tetrahedron(String title, int width, int height) 
    {
		this.setSize(width, height) ;
		this.setTitle(title) ;
		this.setBackground(Color.white) ;
		this.show() ;
	
		this.addWindowListener (
		  new WindowAdapter() {
		      public void windowClosing( WindowEvent e ) {
			  if( Tetrahedron.this.u != null ) {
			      Tetrahedron.this.u.removeAllLocales() ;
			  } // if
	
			  e.getWindow().dispose() ;
			  System.exit( 0 ) ;
		      } // windowClosing( WindowEvent )
		  } // WindowAdapter()
	          ) ;
	
		GraphicsConfiguration configuration = 
	              SimpleUniverse.getPreferredConfiguration() ;
	
		Canvas3D canvas = new Canvas3D( configuration ) ;
		canvas.setBackground( Color.white ) ;
	
		Container contentPane = this.getContentPane() ;
		contentPane.setLayout( new BorderLayout() ) ;
		canvas.setSize( contentPane.getSize() ) ;
		contentPane.add( "Center", canvas ) ;
	
		this.u = new SimpleUniverse( canvas ) ;
		this.u.getViewingPlatform().setNominalViewingTransform() ;
		this.u.addBranchGraph( this.createSceneGraph() ) ;
	}

    
    
    private BranchGroup createSceneGraph() 
    {
		BranchGroup bg = new BranchGroup() ;
		TransformGroup tg = new TransformGroup() ;
		tg.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE ) ;

	
		tg.addChild( this.createShape() ) ;
		
		BoundingSphere bounds = 
		           new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		
		
	    MouseTranslate myMouseTranslate = new MouseTranslate(
	            );
	        myMouseTranslate.setTransformGroup(tg);
	        myMouseTranslate.setSchedulingBounds(bounds);
	        bg.addChild(myMouseTranslate);
		
		
		//tg.addChild(new TriangularPrism());
		bg.addChild( tg ) ;
		bg.addChild( this.createRotator( tg ) ) ;
		bg.addChild( this.createAmbientLight() ) ;
	
		bg.compile() ;
	
		return bg ;
    }

    
    private RotationInterpolator createRotator( TransformGroup tg ) 
    {
		Transform3D yAxis = new Transform3D() ;
		
		//yAxis.rotZ(Math.PI / 2.0);  //**X AXIS ROTATION**//
		//yAxis.rotY( Math.PI / 2.0 );  //**Y AXIS ROTATION**//
		//yAxis.rotX(Math.PI / 2.0);  //**Z AXIS ROTATION**//
	
		Alpha rotationAlpha = new Alpha( 1, 5000 ) ;  //**NUMBER OF TIMES, SPEED**//
		RotationInterpolator rotator = 
		    new RotationInterpolator( rotationAlpha,
					      tg,
					      yAxis,
					      0.0f,
					      (float) Math.PI * 2.0f ) ;
	
		BoundingSphere bounds = 
		    new BoundingSphere( new Point3d( 0.0, 0.0, 0.0 ), 
					100.0 ) ;
		rotator.setSchedulingBounds( bounds ) ;
	
		return rotator ;
    }

    
    private Light createAmbientLight() {
		AmbientLight light = new AmbientLight() ;
		light.setEnable( true ) ;
	
		return light ;
    }

    
    private Node createShape() {
		return new Shape3D( this.createGeometry(), 
				    this.createAppearance() ) ;
    }

    
    private Geometry createGeometry() 
    {
		Point3f frontL = new Point3f(-0.2f, -0.2f, 0.2f); // south
		Point3f frontR = new Point3f(0.2f, -0.2f, 0.2f); // east
		Point3f backR = new Point3f(0.2f, -0.2f, -0.2f); // west
		Point3f backL = new Point3f(-0.2f, -0.2f, -0.2f); // north
		Point3f top = new Point3f(0.0f, 0.2f, 0.0f); // top
    	
    	
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
		
		
		GeometryInfo geometryInfo = new GeometryInfo(pyramidGeometry);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(geometryInfo);
 
		GeometryArray result = geometryInfo.getGeometryArray();
	
		return result ;
    }

    
    
    private Appearance createAppearance() 
    {
		Appearance appearance = new Appearance() ;
		ColoringAttributes ca = new ColoringAttributes() ;
		ca.setShadeModel( ColoringAttributes.SHADE_FLAT ) ;
		appearance.setColoringAttributes( ca ) ;
	
		PolygonAttributes pa = new PolygonAttributes() ;
		pa.setPolygonMode( PolygonAttributes.POLYGON_FILL ) ;
		appearance.setPolygonAttributes( pa ) ;
	
		return appearance ;
    }

    
    private SimpleUniverse u ;
}
