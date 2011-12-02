import java.awt.Color;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;

public class RectangularPrism extends Shape3D {
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
	private QuadArray rectPrismGeometry;
	private QuadArray rectPrismEdgeGeometry;
	
	private Appearance app;
	
	
	public Appearance getApp() {
		return app;
	}


	public void setApp(Appearance app) {
		this.app = app;
	}


	public QuadArray getRectPrismGeometry() {
		return rectPrismGeometry;
	}


	public void setRectPrismGeometry(QuadArray rectPrismGeometry) {
		this.rectPrismGeometry = rectPrismGeometry;
	}


	public Alpha getRotationAlpha() {
		return rotationAlpha;
	}


	public void setRotationAlpha(Alpha rotationAlpha) {
		this.rotationAlpha = rotationAlpha;
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

	// BOTTOM
	Point3f b_frontL = new Point3f(-1.0f, -1.0f, 1.0f); // front left
	Point3f b_frontR = new Point3f(1.0f, -1.0f, 1.0f); // front right
	Point3f b_backR = new Point3f(1.0f, -1.0f, -1.0f); // back right
	Point3f b_backL = new Point3f(-1.0f, -1.0f, -1.0f); // back left

	// TOP
	Point3f t_frontL = new Point3f(-1.0f, 1.0f, 1.0f); // front left
	Point3f t_frontR = new Point3f(1.0f, 1.0f, 1.0f); // front right
	Point3f t_backR = new Point3f(1.0f, 1.0f, -1.0f); // back right
	Point3f t_backL = new Point3f(-1.0f, 1.0f, -1.0f); // back left

	public RectangularPrism() {
		rectPrismGeometry = new QuadArray(24, QuadArray.COORDINATES | GeometryArray.COLOR_3);
		rectPrismGeometry.setCapability(QuadArray.ALLOW_COLOR_WRITE);
		
		face(rectPrismGeometry, 0, b_frontL, b_frontR, t_frontR, t_frontL, Colors.RED);
		face(rectPrismGeometry, 4, b_frontR, b_backR, t_backR, t_frontR, Colors.BLUE);
		face(rectPrismGeometry, 8, b_backR, b_backL, t_backL, t_backR, Colors.GREEN);
		face(rectPrismGeometry, 12, b_backL, b_frontL, t_frontL, t_backL, Colors.YELLOW);
		face(rectPrismGeometry, 16, t_frontL, t_frontR, t_backR, t_backL, Colors.GRAY);
		face(rectPrismGeometry, 20, b_backL, b_backR, b_frontR, b_frontL, Colors.PURPLE);

		this.setGeometry(rectPrismGeometry);
		
		this.setAppearance(new Appearance());
		
		
		// set userData (id)
		int rectPrismCount = SwingTest.getRectPrismCount();
		this.setUserData("RectPrism".concat(Integer.toString(rectPrismCount)));

		System.out.println("Created: " + this.getUserData());

		rectPrismCount++;
		SwingTest.setRectPrismCount(rectPrismCount);

		Transform3D defaultSize = new Transform3D();
		defaultSize.setScale(new Vector3d(1.0, 1.0, 1.0));
		setResize(defaultSize);

	}

	private void face(QuadArray rectPrismGeometry, int index, Point3f coordinate1, Point3f coordinate2, Point3f coordinate3, Point3f coordinate4, Color3f color) {
		rectPrismGeometry.setCoordinate(index, coordinate1);
		rectPrismGeometry.setCoordinate(index + 1, coordinate2);
		rectPrismGeometry.setCoordinate(index + 2, coordinate3);
		rectPrismGeometry.setCoordinate(index + 3, coordinate4);
		rectPrismGeometry.setColor(index, color);
		rectPrismGeometry.setColor(index + 1, color);
		rectPrismGeometry.setColor(index + 2, color);
		rectPrismGeometry.setColor(index + 3, color);
	}

	private void edge(QuadArray rectPrismEdgeGeometry, int index, Point3f coordinate1, Point3f coordinate2, Point3f coordinate3,	Point3f coordinate4, Color3f color) {
		rectPrismEdgeGeometry.setCoordinate(index, coordinate1);
		rectPrismEdgeGeometry.setCoordinate(index + 1, coordinate2);
		rectPrismEdgeGeometry.setCoordinate(index + 2, coordinate3);
		rectPrismEdgeGeometry.setCoordinate(index + 3, coordinate4);
		rectPrismEdgeGeometry.setColor(index, color);
		rectPrismEdgeGeometry.setColor(index + 1, color);
		rectPrismEdgeGeometry.setColor(index + 2, color);
		rectPrismEdgeGeometry.setColor(index + 3, color);
	}

	public Node rectPrismEdges() {
		rectPrismEdgeGeometry = new QuadArray(24, QuadArray.COORDINATES | QuadArray.NORMALS | GeometryArray.COLOR_3);
		rectPrismEdgeGeometry.setCapability(QuadArray.ALLOW_COLOR_WRITE);
		//rectPrismEdgeGeometry.setCapability(QuadArray.ALLOW_COORDINATE_WRITE);
		
		edge(rectPrismEdgeGeometry, 0, b_frontL, b_frontR, t_frontR, t_frontL, Colors.BLACK);
		edge(rectPrismEdgeGeometry, 4, b_frontR, b_backR, t_backR, t_frontR, Colors.BLACK);
		edge(rectPrismEdgeGeometry, 8, b_backR, b_backL, t_backL, t_backR, Colors.BLACK);
		edge(rectPrismEdgeGeometry, 12, b_backL, b_frontL, t_frontL, t_backL, Colors.BLACK);
		edge(rectPrismEdgeGeometry, 16, t_frontL, t_frontR, t_backR, t_backL, Colors.BLACK);
		edge(rectPrismEdgeGeometry, 20, b_backL, b_backR, b_frontR, b_frontL, Colors.BLACK);

		app = new Appearance();
		app.setCapability(Appearance.ALLOW_LINE_ATTRIBUTES_WRITE);

		// Set up the polygon attributes
		PolygonAttributes pa = new PolygonAttributes();
		pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		pa.setCullFace(PolygonAttributes.CULL_NONE);
		pa.setPolygonOffsetFactor(-0.5f);
		app.setPolygonAttributes(pa);

		LineAttributes lineattributes = new LineAttributes();

		lineattributes.setLineWidth(1.0f);
		lineattributes.setLineAntialiasingEnable(true);
		lineattributes.setLinePattern(LineAttributes.PATTERN_SOLID);

		app.setLineAttributes(lineattributes);

		Shape3D rectPrismEdges = new Shape3D();
		rectPrismEdges.setGeometry(rectPrismEdgeGeometry);
		rectPrismEdges.setAppearance(app);

		return rectPrismEdges;
	}

	public QuadArray getRectPrismEdgeGeometry() {
		return rectPrismEdgeGeometry;
	}


	public void setRectPrismEdgeGeometry(QuadArray rectPrismEdgeGeometry) {
		this.rectPrismEdgeGeometry = rectPrismEdgeGeometry;
	}


	TransformGroup createRotator() {
		Transform3D yAxis = new Transform3D();

		/* axes of rotation */
		//yAxis.rotZ(Math.PI / 2.0); // X AXIS
		// yAxis.rotY( Math.PI / 2.0 ); //Y AXIS
		// yAxis.rotX(Math.PI / 2.0); //Z AXIS

		TransformGroup spin = new TransformGroup(yAxis);
		spin.setUserData("TG: SPIN");

		spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		spin.addChild(this); // add rectPrism shape to the spin TG
		spin.addChild(rectPrismEdges());

		rotationAlpha = new Alpha(0, Alpha.INCREASING_ENABLE, 0, 0, 4000, 0, 0, 0, 0, 0);
		
		rotator = new RotationInterpolator(rotationAlpha, spin, yAxis, 0.0f, (float) Math.PI*2.0f);

		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
		rotator.setSchedulingBounds(bounds);
		spin.addChild(rotator); // add interpolator rotator to the spin TG

		TransformGroup tg = new TransformGroup();
		tg.setUserData("TG: TG".concat(Integer.toString(SwingTest.getRectPrismCount())));
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		setTg(tg);

		tg.addChild(spin);

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
	
	
    public RotationInterpolator getRotator() {
		return rotator;
	}


	public void setRotator(RotationInterpolator rotator) {
		this.rotator = rotator;
	}
}