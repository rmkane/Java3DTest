import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Text2D;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class InterpolatorTest1 extends Applet implements ActionListener{
	private static final long serialVersionUID = 1L;
	private SimpleUniverse u = null;
	Button restartButton;
	Alpha[] restartTarget;
	
	Alpha a2;

	public void init(){
		//<set layout of applet, construct canvas3d, add canvas3d>
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas3d = new Canvas3D(config);
		add(canvas3d, BorderLayout.CENTER);
		restartButton = new Button("Restart");
		add(restartButton, BorderLayout.SOUTH);
		restartButton.addActionListener(this);

		//appearance
		Appearance ap1 = new Appearance();
		Material mat1 = new Material();
		Color3f col1 = new Color3f(0, 1, 1);	//light blue
		mat1.setDiffuseColor(col1);
		ap1.setMaterial(mat1);

		Appearance ap2 = new Appearance();
		Material mat2 = new Material();
		mat2.setCapability(Material.ALLOW_COMPONENT_WRITE);	//for ColorInterpolator
		ap2.setMaterial(mat2);

		Appearance ap3 = new Appearance();
		Material mat3 = new Material();
		mat3.setCapability(Material.ALLOW_COMPONENT_WRITE);	//for ColorInterpolator
		ap3.setMaterial(mat3);

		Appearance ap4 = new Appearance();
		Material mat4 = new Material();
		Color3f col4 = new Color3f(0, 1, 0);	//green
		mat4.setDiffuseColor(col4);
		ap4.setMaterial(mat4);

		Color3f white = new Color3f(1, 1, 1);	//white

		//object positions
		Transform3D tr1 = new Transform3D();
		TransformGroup tg1 = new TransformGroup(tr1);

		BranchGroup root = new BranchGroup();

		//objects
		Box box = new Box(0.2f, 0.2f, 0.2f, ap1);

		TransformGroup tg1rot = new TransformGroup();
		tg1rot.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg1rot.addChild(box);
		tr1.rotZ(Math.PI/2);
		tg1.setTransform(tr1);
		tg1.addChild(tg1rot);
		
		root.addChild(tg1);

		
 		//bounding sphere for light & interpolators
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 10.0);

		//directional(parallel) light
		DirectionalLight light = new DirectionalLight(
				white,
				new Vector3f(4.0f, -5.0f, -10.0f)	//direction
		);
		light.setInfluencingBounds(bounds);
		root.addChild(light);

		//Interpolator "alpha"s
		a2 = new Alpha(1, 3000);	//repeat, period


		//Rotation interpolator
		RotationInterpolator ri_rot = new RotationInterpolator(a2, tg1rot);
		ri_rot.setSchedulingBounds(bounds);
		root.addChild(ri_rot);


		restartTarget = new Alpha[] {a2};

		u = new SimpleUniverse(canvas3d);
		u.getViewingPlatform().setNominalViewingTransform();
		u.addBranchGraph(root);
	}

	int speed = 1;
	public void actionPerformed(ActionEvent e) {
		//restart animation
		if (a2.finished()){
			a2.setLoopCount(speed);
			a2.setStartTime(System.currentTimeMillis());
			speed++;
		}
	}
	}