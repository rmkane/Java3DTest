import java.awt.*;
import javax.swing.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.picking.behaviors.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.picking.PickIntersection;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.picking.PickTool;
import javax.vecmath.Point3d;

public class PointTracker
        extends JFrame
{

    public PointTracker()
    {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);

        BranchGroup scene = createSceneGraph();

        // SimpleUniverse is a Convenience Utility class
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

        // This moves the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        simpleU.getViewingPlatform().setNominalViewingTransform();

        BoundingSphere behaveBounds = new BoundingSphere();
        ExamplePickBehavior behavior = new ExamplePickBehavior(canvas3D, scene, behaveBounds);
        scene.addChild(behavior);

        scene.compile();
        simpleU.addBranchGraph(scene);

        getContentPane().add(canvas3D, BorderLayout.CENTER);
    } // end of HelloJava3D (constructor)

    public BranchGroup createSceneGraph()
    {
        // Create the root of the branch graph
        BranchGroup objRoot = new BranchGroup();

        // Create a simple shape leaf node, add it to the scene graph.
        // ColorCube is a Convenience Utility class
        ColorCube cube = new ColorCube(0.4);
        cube.setCapability(Node.ENABLE_PICK_REPORTING);
        PickTool.setCapabilities(cube, PickTool.INTERSECT_FULL);
        objRoot.addChild(cube);

        return objRoot;
    } // end of createSceneGraph method of HelloJava3D

    public static void main(String[] args)
    {
        JFrame frame = new PointTracker();
        frame.setTitle("Hello Java3D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 400, 300);
        frame.setVisible(true);
    }

    private class ExamplePickBehavior extends PickMouseBehavior
    {

        public ExamplePickBehavior(Canvas3D canvas, BranchGroup bg, Bounds bounds)
        {
            super(canvas, bg, bounds);
            setSchedulingBounds(bounds);

            pickCanvas.setMode(PickTool.GEOMETRY_INTERSECT_INFO);
            // allows PickIntersection objects to be returned
        }

        public void updateScene(int xpos, int ypos)
        {
            pickCanvas.setShapeLocation(xpos, ypos);
            // register mouse pointer location on the screen (canvas)

            Point3d eyePos = pickCanvas.getStartPosition();
            // get the viewer's eye location

            PickResult pickResult = null;
            pickResult = pickCanvas.pickClosest();
            // get the intersected shape closest to the viewer

            if (pickResult != null) {
                PickIntersection pi = pickResult.getClosestIntersection(eyePos);
                // get the closest intersect to the eyePos point
                Point3d intercept = pi.getPointCoordinatesVW();
                String s = String.format("(%.2f, %.2f, %.2f)", intercept.x, intercept.y, intercept.z);
                System.out.println(s);
                // extract the intersection pt in scene coords space
                // use the intersection pt in some way...
            }
        } // end of updateScene(  )
    } // end of ExamplePickBehavior class
}