import java.awt.event.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import java.util.Enumeration;

public class FiltreGDClavierBehavior extends Behavior {
 private TransformGroup tgAffecteParBehavior;
 private Transform3D rotation3D = new Transform3D();
 private double angle = 0.0;

 public FiltreGDClavierBehavior(TransformGroup tgAffecteParBehavior) {
  this.tgAffecteParBehavior = tgAffecteParBehavior;
 }

 /*
  * la méthode initialize() d'un comportement Behavior est appelée lorsque cet
  * objet devient vivant
  */
 public void initialize() {
  this.wakeupOn(WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
  // ce comportement est désormais à l'écoute de l'appui sur les touches clavier
 }
 /*
  * la méthode appelée à chaque stimulus. Le stimulus est repositionné chaque
  * fois à la fin de cette méthode. Comme il est toujours positionné à la même
  * valeur WakeupOnAWTEvent (KeyEvent.KEY_PRESSED)? Ce comportement traite les
  * interactions clavier
  */

 private WakeupCondition WakeupOnAWTEvent(int keyPressed) {
  return null;
 }

 public void processStimulus(Enumeration criteria) {
  while (criteria.hasMoreElements()) {
   Object obj = criteria.nextElement();
   if (obj instanceof WakeupOnAWTEvent) {
    WakeupOnAWTEvent evt = (WakeupOnAWTEvent) obj;
    java.awt.AWTEvent[] tabEvent = evt.getAWTEvent();
    for (int i = 0; i < tabEvent.length; i++) {
     if (tabEvent[i] instanceof KeyEvent) {
      KeyEvent kEvt = (KeyEvent) tabEvent[i];
      if (kEvt.getKeyChar() == 'g') {
       angle -= 0.1;
       rotation3D.rotY(angle);
       tgAffecteParBehavior.setTransform(rotation3D);

      } else if (kEvt.getKeyChar() == 'd') {
       angle += 0.1;
       rotation3D.rotY(angle);
       tgAffecteParBehavior.setTransform(rotation3D);
      }
     }
    }

   }
  }
  this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
 }

}
