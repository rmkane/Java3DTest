/*
 *  @(#)Drag.java 1.14 98/04/13 13:07:16
 *
 * Copyright (c) 1996-1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;

/**
 * Class Drag
 *
 * Author Jim Argabright
 * Date   11/24/97
 */
public class Drag extends Applet {

   boolean appletFlag = true;

   /**
    * Sets the "applet" flag which is used to determine 
    * whether or not this program is running as an applet.
    */
   public void setAppletFlag(boolean flag) {
      appletFlag = flag;
   }

   /**
    *  Create the scenegraph for this program.
    */
   public BranchGroup createSceneGraph() {

        // Define colors
        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
        Color3f red   = new Color3f(0.80f, 0.20f, 0.2f);
        Color3f ambientRed = new Color3f(0.2f, 0.05f, 0.0f);
        Color3f ambient = new Color3f(0.2f, 0.2f, 0.2f);
        Color3f diffuse = new Color3f(0.7f, 0.7f, 0.7f);
        Color3f specular = new Color3f(0.7f, 0.7f, 0.7f);
  Color3f bgColor = new Color3f(0.05f, 0.05f, 0.2f);

        // Create the branch group
  BranchGroup branchGroup = new BranchGroup();

        // Create a Transformgroup to scale all objects so they
        // appear in the scene.
        TransformGroup objScale = new TransformGroup();
        Transform3D t3d = new Transform3D();
        t3d.setScale(0.4);
        objScale.setTransform(t3d);
        branchGroup.addChild(objScale);

        // Create the bounding leaf node
  BoundingSphere bounds = 
           new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        BoundingLeaf boundingLeaf = new BoundingLeaf(bounds);
        objScale.addChild(boundingLeaf);

  // Set up the background
  Background bg = new Background(bgColor);
  bg.setApplicationBounds(bounds);
  objScale.addChild(bg);

        // Create the ambient light
        AmbientLight ambLight = new AmbientLight(white);
        ambLight.setInfluencingBounds(bounds);
        objScale.addChild(ambLight);

        // Create the directional light
        Vector3f dir = new Vector3f(-1.0f, -1.0f, -1.0f);
        DirectionalLight dirLight = new DirectionalLight(white, dir);
        dirLight.setInfluencingBounds(bounds);
        objScale.addChild(dirLight);
     
        // Create the red appearance node
        Material redMaterial = 
           new Material(ambientRed, black, red, specular, 75.0f);
        redMaterial.setLightingEnable(true);
        Appearance redAppearance = new Appearance();
        redAppearance.setMaterial(redMaterial);

        // Create the white appearance node
        Material whiteMaterial = 
           new Material(ambient, black, diffuse, specular, 75.0f);
        whiteMaterial.setLightingEnable(true);
        Appearance whiteAppearance = new Appearance();
        whiteAppearance.setMaterial(whiteMaterial);

        // Create the transform node
  TransformGroup transformGroup = new TransformGroup();
  transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
  transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
  transformGroup.addChild(new Cube(redAppearance).getChild());
//  transformGroup.addChild(new Corners(whiteAppearance).getChild());
  objScale.addChild(transformGroup);
  
  // Create the drag behavior node
  MouseRotate behavior = new MouseRotate();
  behavior.setTransformGroup(transformGroup);
  transformGroup.addChild(behavior);
  behavior.setSchedulingBounds(bounds);
  
  // Create the zoom behavior node
  MouseZoom behavior2 = new MouseZoom();
  behavior2.setTransformGroup(transformGroup);
  transformGroup.addChild(behavior2);
  behavior2.setSchedulingBounds(bounds);
  
  // Create the zoom behavior node
  MouseTranslate behavior3 = new MouseTranslate();
  behavior3.setTransformGroup(transformGroup);
  transformGroup.addChild(behavior3);
  behavior3.setSchedulingBounds(bounds);

        // Let Java 3D perform optimizations on this scene graph.
        branchGroup.compile();

  return branchGroup;
    }

    /**
     * Calls the various methods necessary to initialize the 
     * program.
     */
    public void init() {

        // Set the layout manager
  setLayout(new BorderLayout());

        // Create the 3D canvas
  Canvas3D canvas = new Canvas3D(null);
  add("Center", canvas);

  // Create the scene branch graph
  BranchGroup scene = createSceneGraph();

  // Create the Universe, the Locale, and the view branch graph
  SimpleUniverse u = new SimpleUniverse(canvas);

        // This will move the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        u.getViewingPlatform().setNominalViewingTransform();

  u.addBranchGraph(scene);
   }

    /**
     *  Inner class used to "kill" the window when running as
     *  an application.
     */
    static class killAdapter extends WindowAdapter {
       public void windowClosing(WindowEvent event) {
          System.exit(0);
       }
    }

    /**
     *  Used when running as an application.
     */
    public static void main(String[] args) {
       Drag drag = new Drag();
       drag.setAppletFlag(false);
       drag.init();

       Frame frame = new Frame("Drag the mouse in the window");
       frame.setSize(640, 480);
       frame.add("Center", drag);
       frame.addWindowListener(new killAdapter());
       frame.setVisible(true);
    }
}

/*
 *  @(#)Cube.java 1.3 98/02/20 14:30:08
 *
 * Copyright (c) 1996-1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

class Cube extends Object {

   private Shape3D shape3D;

   private static final float[] verts = {
   // Front Face
      1.0f, -1.0f,  1.0f,     1.0f,  1.0f,  1.0f,
     -1.0f,  1.0f,  1.0f,    -1.0f, -1.0f,  1.0f,
   // Back Face
     -1.0f, -1.0f, -1.0f,    -1.0f,  1.0f, -1.0f,
      1.0f,  1.0f, -1.0f,     1.0f, -1.0f, -1.0f,
   // Right Face
      1.0f, -1.0f, -1.0f,     1.0f,  1.0f, -1.0f,
      1.0f,  1.0f,  1.0f,     1.0f, -1.0f,  1.0f,
   // Left Face
     -1.0f, -1.0f,  1.0f,    -1.0f,  1.0f,  1.0f,
     -1.0f,  1.0f, -1.0f,    -1.0f, -1.0f, -1.0f,
   // Top Face
      1.0f,  1.0f,  1.0f,     1.0f,  1.0f, -1.0f,
     -1.0f,  1.0f, -1.0f,    -1.0f,  1.0f,  1.0f,
   // Bottom Face
     -1.0f, -1.0f,  1.0f,    -1.0f, -1.0f, -1.0f,
      1.0f, -1.0f, -1.0f,     1.0f, -1.0f,  1.0f,
   };

   private static final float[] normals = {
   // Front Face
      0.0f,  0.0f,  1.0f,     0.0f,  0.0f,  1.0f,
      0.0f,  0.0f,  1.0f,     0.0f,  0.0f,  1.0f,
   // Back Face
      0.0f,  0.0f, -1.0f,     0.0f,  0.0f, -1.0f,
      0.0f,  0.0f, -1.0f,     0.0f,  0.0f, -1.0f,
   // Right Face
      1.0f,  0.0f,  0.0f,     1.0f,  0.0f,  0.0f,
      1.0f,  0.0f,  0.0f,     1.0f,  0.0f,  0.0f,
   // Left Face
     -1.0f,  0.0f,  0.0f,    -1.0f,  0.0f,  0.0f,
     -1.0f,  0.0f,  0.0f,    -1.0f,  0.0f,  0.0f,
   // Top Face
      0.0f,  1.0f,  0.0f,     0.0f,  1.0f,  0.0f,
      0.0f,  1.0f,  0.0f,     0.0f,  1.0f,  0.0f,
   // Bottom Face
      0.0f, -1.0f,  0.0f,     0.0f, -1.0f,  0.0f,
      0.0f, -1.0f,  0.0f,     0.0f, -1.0f,  0.0f,
   };

   private static final float[] textCoords = {
   // Front Face
      1.0f,  0.0f,            1.0f,  1.0f,
      0.0f,  1.0f,            0.0f,  0.0f,
   // Back Face
      1.0f,  0.0f,            1.0f,  1.0f,
      0.0f,  1.0f,            0.0f,  0.0f,
   // Right Face
      1.0f,  0.0f,            1.0f,  1.0f,
      0.0f,  1.0f,            0.0f,  0.0f,
   // Left Face
      1.0f,  0.0f,            1.0f,  1.0f,
      0.0f,  1.0f,            0.0f,  0.0f,
   // Top Face
      1.0f,  0.0f,            1.0f,  1.0f,
      0.0f,  1.0f,            0.0f,  0.0f,
   // Bottom Face
      1.0f,  0.0f,            1.0f,  1.0f,
      0.0f,  1.0f,            0.0f,  0.0f
   };

   public Cube(Appearance appearance) {

      QuadArray quadArray = new QuadArray(24, QuadArray.COORDINATES | 
                                              QuadArray.NORMALS |
                                              QuadArray.TEXTURE_COORDINATE_2);
      quadArray.setCoordinates(0, verts);
      quadArray.setNormals(0, normals);
      quadArray.setTextureCoordinates(0, textCoords);

      shape3D = new Shape3D(quadArray, appearance);
   }

   public Shape3D getChild() {
      return shape3D;
   }
}