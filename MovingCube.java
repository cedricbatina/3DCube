import java.awt.*;
import javax.swing.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class MovingCube extends JFrame {
 private SimpleUniverse u = null;

 public MovingCube() {
  Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
  BranchGroup scene = construitScene();
  SimpleUniverse univers = new SimpleUniverse(canvas);
  univers.addBranchGraph(scene);

  univers.getViewingPlatform().setNominalViewingTransform();
  setTitle("MovingCube");
  setSize(300, 300);
  Container contentPane = getContentPane();
  contentPane.setLayout(new BorderLayout());
  contentPane.add(BorderLayout.CENTER, canvas);
  setVisible(true);
 }

 public BranchGroup construitScene() {
  BranchGroup scene = new BranchGroup();
  // crée un noeud TransformGroup. Permet la possibilité TRANSFORM_WRITE
  // capability pour pouvoir modifier les caractéristiques de ce TransformGroup à
  // l'exécution
  TransformGroup objTrans = new TransformGroup();
  objTrans.setCapability((TransformGroup.ALLOW_TRANSFORM_WRITE));

  scene.addChild(objTrans);
  objTrans.addChild(new ColorCube(0.4));

  /*
   * Crée un comportement qui est une rotation sur l'objet TransformGroup père du
   * cube. La rotation s'effectue autour de l'axe y. Indique que la rotation dure
   * éternelllement et qu'un tour complet est effectué en 4 secondes à l'aide de
   * l'objet Alpha. C'est l'objet RotationInterpolator qui relie l'objet Alpha à
   * l'objet TransformGroup
   */
  Transform3D axeY = new Transform3D();
  Alpha rotationAlpha = new Alpha(-1, 4000);
  RotationInterpolator rotator = new RotationInterpolator(rotationAlpha, objTrans, axeY, 0.0f, (float) Math.PI * 2.0f);
  // fixe une grande région de mouvement
  BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
  rotator.setSchedulingBounds(bounds);
  // place le comportement dans l'arbre de la scène
  objTrans.addChild(rotator);
  // compile toute la scène pour optimiser

  scene.compile();
  return scene;

 }

 public static void main(String[] args) {
  new MovingCube();
 }
}
