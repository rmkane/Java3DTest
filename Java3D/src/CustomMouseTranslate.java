import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;

	/**
	 * MouseTranslate is a Java3D behavior object that lets users control the 
	 * translation (X, Y) of an object via a mouse drag motion with the third
	 * mouse button (alt-click on PC). See MouseRotate for similar usage info.
	 */
	
	public class CustomMouseTranslate extends MouseBehavior {
	
	    double x_factor = .02;
	    double y_factor = .02;
	    Vector3d translation = new Vector3d();
	
	    private MouseBehaviorCallback callback = null;
	
	    /**
	 * Creates a mouse translate behavior given the transform group.
	 * @param transformGroup The transformGroup to operate on.
	 */
	public CustomMouseTranslate(TransformGroup transformGroup) {
	    super (transformGroup);
	}
	
	/**
	 * Creates a default translate behavior.
	 */
	public CustomMouseTranslate() {
	    super (0);
	}
	
	/**
	 * Creates a translate behavior.
	 * Note that this behavior still needs a transform
	 * group to work on (use setTransformGroup(tg)) and
	 * the transform group must add this behavior.
	 * @param flags
	 */
	public CustomMouseTranslate(int flags) {
	    super (flags);
	}
	
	/**
	 * Creates a translate behavior that uses AWT listeners and behavior
	 * posts rather than WakeupOnAWTEvent.  The behavior is added to the
	 * specified Component. A null component can be passed to specify
	 * the behavior should use listeners.  Components can then be added
	 * to the behavior with the addListener(Component c) method.
	 * @param c The Component to add the MouseListener
	 * and MouseMotionListener to.
	 * @since Java 3D 1.2.1
	 */
	public CustomMouseTranslate(Component c) {
	    super (c, 0);
	}
	
	/**
	 * Creates a translate behavior that uses AWT listeners and behavior
	 * posts rather than WakeupOnAWTEvent.  The behaviors is added to
	 * the specified Component and works on the given TransformGroup.
	 * A null component can be passed to specify the behavior should use
	 * listeners.  Components can then be added to the behavior with the
	 * addListener(Component c) method.
	 * @param c The Component to add the MouseListener and
	 * MouseMotionListener to.
	 * @param transformGroup The TransformGroup to operate on.
	 * @since Java 3D 1.2.1
	 */
	public CustomMouseTranslate(Component c, TransformGroup transformGroup) {
	    super (c, transformGroup);
	}
	
	/**
	 * Creates a translate behavior that uses AWT listeners and behavior
	 * posts rather than WakeupOnAWTEvent.  The behavior is added to the
	 * specified Component.  A null component can be passed to specify
	 * the behavior should use listeners.  Components can then be added to
	 * the behavior with the addListener(Component c) method.
	 * Note that this behavior still needs a transform
	 * group to work on (use setTransformGroup(tg)) and the transform
	 * group must add this behavior.
	 * @param flags interesting flags (wakeup conditions).
	 * @since Java 3D 1.2.1
	 */
	public CustomMouseTranslate(Component c, int flags) {
	    super (c, flags);
	}
	
	public void initialize() {
	    super .initialize();
	    if ((flags & INVERT_INPUT) == INVERT_INPUT) {
	        invert = true;
	        x_factor *= -1;
	        y_factor *= -1;
	    }
	}
	
	/**
	 * Return the x-axis movement multipler.
	 **/
	public double getXFactor() {
	    return x_factor;
	}
	
	/**
	 * Return the y-axis movement multipler.
	 **/
	public double getYFactor() {
	    return y_factor;
	}
	
	/**
	 * Set the x-axis amd y-axis movement multipler with factor.
	 **/
	public void setFactor(double factor) {
	    x_factor = y_factor = factor;
	}
	
	/**
	 * Set the x-axis amd y-axis movement multipler with xFactor and yFactor
	 * respectively.
	 **/
	public void setFactor(double xFactor, double yFactor) {
	    x_factor = xFactor;
	    y_factor = yFactor;
	}
	
	public void processStimulus(Enumeration criteria) {
	    WakeupCriterion wakeup;
	    AWTEvent[] events;
	    MouseEvent evt;
	    // 	int id;
	    // 	int dx, dy;
	
	    while (criteria.hasMoreElements()) {
	        wakeup = (WakeupCriterion) criteria.nextElement();
	
	        if (wakeup instanceof  WakeupOnAWTEvent) {
	            events = ((WakeupOnAWTEvent) wakeup).getAWTEvent();
	            if (events.length > 0) {
	                evt = (MouseEvent) events[events.length - 1];
	                doProcess(evt);
	            }
	        }
	
	        else if (wakeup instanceof  WakeupOnBehaviorPost) {
	            while (true) {
	                // access to the queue must be synchronized
	                synchronized (mouseq) {
	                    if (mouseq.isEmpty())
	                        break;
	                    evt = (MouseEvent) mouseq.remove(0);
	                    // consolodate MOUSE_DRAG events
	                    while ((evt.getID() == MouseEvent.MOUSE_DRAGGED)
	                            && !mouseq.isEmpty()
	                            && (((MouseEvent) mouseq.get(0))
	                                    .getID() == MouseEvent.MOUSE_DRAGGED)) {
	                        evt = (MouseEvent) mouseq.remove(0);
	                    }
	                }
	                doProcess(evt);
	            }
	        }
	
	    }
	    wakeupOn(mouseCriterion);
	}
	
	void doProcess(MouseEvent evt) {
	    int id;
	    int dx, dy;
	
	    processMouseEvent(evt);
	
	    if (((buttonPress) && ((flags & MANUAL_WAKEUP) == 0))
	            || ((wakeUp) && ((flags & MANUAL_WAKEUP) != 0))) {
	        id = evt.getID();
	        if ((id == MouseEvent.MOUSE_DRAGGED) && !evt.isAltDown()
	                && evt.isMetaDown()) {
	
	            x = evt.getX();
	            y = evt.getY();
	
	            dx = x - x_last;
	            dy = y - y_last;
	
	            if ((!reset)
	                    && ((Math.abs(dy) < 50) && (Math.abs(dx) < 50))) {
	                //System.out.println("dx " + dx + " dy " + dy);
	                transformGroup.getTransform(currXform);
	
	                translation.x = dx * x_factor;
	                translation.y = -dy * y_factor;
	
	                transformX.set(translation);
	
	                if (invert) {
	                    currXform.mul(currXform, transformX);
	                } else {
	                    currXform.mul(transformX, currXform);
	                }
	
	                transformGroup.setTransform(currXform);
	
	                transformChanged(currXform);
	
	                if (callback != null)
	                    callback.transformChanged(MouseBehaviorCallback.TRANSLATE, currXform);
				    
	
	            } else {
	                reset = false;
	            }

	            
	            x_last = x;
	            y_last = y;
	            
	        } else if (id == MouseEvent.MOUSE_PRESSED) {
	            x_last = evt.getX();
	            y_last = evt.getY();
	        }
	    }
	}
	
	/**
	 * Users can overload this method  which is called every time
	 * the Behavior updates the transform
	 *
	 * Default implementation does nothing
	 */

	public void transformChanged(Transform3D transform) {
        Node shape = ((Group) ((Group) ((Group) transformGroup.getParent()).getChild(0)).getChild(0)).getChild(0);
        
        
        if (shape.getClass().getName().equals("TriangularPrism")) {
            ((TriangularPrism) shape).setTx(((TriangularPrism) shape).getTx() + (float)translation.x);
            ((TriangularPrism) shape).setTy(((TriangularPrism) shape).getTy() + (float)translation.y);
    	}
        
    	else if (shape.getClass().getName().equals("HexagonalPrism")) {
            ((HexagonalPrism) shape).setTx(((HexagonalPrism) shape).getTx() + (float)translation.x);
            ((HexagonalPrism) shape).setTy(((HexagonalPrism) shape).getTy() + (float)translation.y);
    	}
    	else if (shape.getClass().getName().equals("RectangularPrism")) {
            ((RectangularPrism) shape).setTx(((RectangularPrism) shape).getTx() + (float)translation.x);
            ((RectangularPrism) shape).setTy(((RectangularPrism) shape).getTy() + (float)translation.y);
    	}
    	else if (shape.getClass().getName().equals("Pyramid")) {
            ((Pyramid) shape).setTx(((Pyramid) shape).getTx() + (float)translation.x);
            ((Pyramid) shape).setTy(((Pyramid) shape).getTy() + (float)translation.y);
    	}
    	else if (shape.getClass().getName().equals("aSphere")) {
            ((aSphere) shape).setTx(((aSphere) shape).getTx() + (float)translation.x);
            ((aSphere) shape).setTy(((aSphere) shape).getTy() + (float)translation.y);
    	}
    	//else if (shapeClicked.getClass().getName().equals("aCylinder")) {
        //	holdPosition.setTranslation(
        //			new Vector3f(((aCylinder) shapeClicked).getTx(), ((aCylinder) shapeClicked).getTy(), 0.0f));
        //	holdPosition.mul(resize);
        	
        //	((aCylinder) shapeClicked).getTg().setTransform(holdPosition);
        //	((aCylinder) shapeClicked).setResize(resize);
    	//}*/
	}
	
	/**
	 * The transformChanged method in the callback class will
	 * be called every time the transform is updated
	 */
	public void setupCallback(MouseBehaviorCallback callback) {
	    this.callback = callback;
	}
        }