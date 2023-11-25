import java.awt.AWTEvent;
import java.awt.event.*;
import javax.media.j3d.*;
//import javax.vecmath.*;

import java.util.Enumeration;

public class FiltreGDClavierBehavior extends Behavior {
  private TransformGroup tgAffecteParBehavior;
  private Transform3D rotation3D = new Transform3D();
  private double angle = 0.0;

  public FiltreGDClavierBehavior(TransformGroup tgAffecteParBehavior) {
    this.tgAffecteParBehavior = tgAffecteParBehavior;
  }

  public void initialize() {
    this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
  }

  public void processStimulus(Enumeration criteria) {
    while (criteria.hasMoreElements()) {
      WakeupCriterion wakeup = (WakeupCriterion) criteria.nextElement();
      if (wakeup instanceof WakeupOnAWTEvent) {
        AWTEvent[] events = ((WakeupOnAWTEvent) wakeup).getAWTEvent();
        for (int i = 0; i < events.length; i++) {
          if (events[i] instanceof KeyEvent) {
            KeyEvent ke = (KeyEvent) events[i];
            if (ke.getKeyChar() == 'g') {
              angle -= 0.1;
              rotation3D.rotY(angle);
              tgAffecteParBehavior.setTransform(rotation3D);
            } else if (ke.getKeyChar() == 'd') {
              angle += 0.1;
              rotation3D.rotY(angle);
              tgAffecteParBehavior.setTransform(rotation3D);
            }
          }
        }
      }
    }
    wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
  }

  /*
   * public void processStimulus(Enumeration criteria) {
   * while (criteria.hasMoreElements()) {
   * WakeupCriterion wakeup = (WakeupCriterion) criteria.nextElement();
   * if (wakeup instanceof WakeupOnAWTEvent) {
   * AWTEvent[] events = ((WakeupOnAWTEvent) wakeup).getAWTEvent();
   * for (int i = 0; i < events.length; i++) {
   * if (events[i] instanceof KeyEvent) {
   * KeyEvent ke = (KeyEvent) events[i];
   * if (ke.getKeyChar() == 'g') {
   * angle -= 0.1;
   * rotation3D.rotY(angle);
   * tgAffecteParBehavior.setTransform(rotation3D);
   * } else if (ke.getKeyChar() == 'd') {
   * angle += 0.1;
   * rotation3D.rotY(angle);
   * tgAffecteParBehavior.setTransform(rotation3D);
   * }
   * }
   * }
   * }
   * }
   * wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
   * }
   * 
   * 
   * public void processStimulus(Enumeration criteria) {
   * while (criteria.hasMoreElements()) {
   * WakeupCriterion wakeup = (WakeupCriterion) criteria.nextElement();
   * if (wakeup instanceof WakeupOnAWTEvent) {
   * AWTEvent[] events = ((WakeupOnAWTEvent) wakeup).getAWTEvent();
   * for (int i = 0; i < events.length; i++) {
   * if (events[i] instanceof KeyEvent) {
   * KeyEvent ke = (KeyEvent) events[i];
   * if (ke.getKeyChar() == 'g') {
   * angle -= 0.1;
   * rotation3D.rotY(angle);
   * tgAffecteParBehavior.setTransform(rotation3D);
   * } else if (ke.getKeyChar() == 'd') {
   * angle += 0.1;
   * rotation3D.rotY(angle);
   * tgAffecteParBehavior.setTransform(rotation3D);
   * }
   * }
   * }
   * }
   * }
   * wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
   * }
   */
}
