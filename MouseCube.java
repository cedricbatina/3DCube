import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import javax.swing.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;

/*Application qui illustre la possibilité de manipuler une scène avec la souris. On peut faire des rotations, des translations et des zooms à l'aide des différents boutons de la souris */

public class MouseCube extends JFrame {
 public MouseCube() {
  Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
  BranchGroup scene = construitScene();
  SimpleUniverse univers = new SimpleUniverse(canvas);
  univers.addBranchGraph(scene);

  univers.getViewingPlatform().setNominalViewingTransform();
  setTitle("InteractionSouris");
  setSize(300, 300);
  Container contentPane = getContentPane();
  contentPane.setLayout(new BorderLayout());
  contentPane.add(BorderLayout.CENTER, canvas);
  setVisible(true);

 }

 private BranchGroup construitScene() {
  BranchGroup scene = new BranchGroup();
  // crée 3 TransformGroup qui auront chacun un seul comportement associé. On
  // ajoutera ensuite les 3 comportements Behavior de manipulation souris.
  TransformGroup interactionSourisRotationTG = new TransformGroup();
  /*
   * on permet les possibilités capabilities TRANSFORM_READ et TRANSFORM_WRITE
   * pour que les manipulations souris puissent lire et modifier les
   * caractéristiques de ce TransformGroupà l'exécution
   */
  interactionSourisRotationTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
  interactionSourisRotationTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

  // de même que pour les deux comportements

  TransformGroup interactionSourisZoomTG = new TransformGroup();
  interactionSourisZoomTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
  interactionSourisZoomTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

  TransformGroup interactionSourisTranslationTG = new TransformGroup();
  interactionSourisTranslationTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
  interactionSourisTranslationTG.setCapability((TransformGroup.ALLOW_TRANSFORM_READ));

  /*
   * crée un comportement pour les rotations avec la souris bouton gauche enfoncé
   * MouseRotate rotBtSouris
   */
  MouseRotate rotBtSourisGauche = new MouseRotate();
  scene.addChild(rotBtSourisGauche);

  BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
  rotBtSourisGauche.setSchedulingBounds(bounds);
  // associe ce comportement au TransformGroup interactionsSourisRotationTG
  rotBtSourisGauche.setTransformGroup((interactionSourisTranslationTG));
  ;

  /*
   * crée un comportement pour les translations avec la souris bouton droit
   * enfoncé
   */

  MouseTranslate transBtSourisDroit = new MouseTranslate();
  scene.addChild(transBtSourisDroit);
  transBtSourisDroit.setSchedulingBounds(bounds);

  /* associe ce comportement au TransformGroup interactionSourisTranslationTG */
  transBtSourisDroit.setTransformGroup(interactionSourisTranslationTG);

  /* crée un comportement pour les zooms avec la souris bouton milieu enfonceé */
  MouseZoom zoomBtSourisMilieu = new MouseZoom();
  scene.addChild(zoomBtSourisMilieu);
  zoomBtSourisMilieu.setSchedulingBounds(bounds);
  /* associe ce comportement au TransformGroup interactionSourisZoomTG */
  zoomBtSourisMilieu.setTransformGroup(interactionSourisZoomTG);

  interactionSourisRotationTG.addChild(new ColorCube(0.4));
  interactionSourisTranslationTG.addChild(interactionSourisRotationTG);
  interactionSourisZoomTG.addChild(interactionSourisTranslationTG);
  scene.addChild(interactionSourisZoomTG);
  return scene;
 }

 public static void main(String[] args) {
  new MouseCube();
 }

}
