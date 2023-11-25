import javax.swing.*;
import java.awt.*;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
//import javax.vecmath.*;
//import FiltreGDClavierBehavior;

/*Cette classe affiche ColorCube et utilise un comportement FiltreGDClavierBehavior qui fait tourner le cube à gauche si appui sur touche clavier g, à doirte si appui sur touche clavier d. Il ne se passe rien si on appuie sur une autre touche du clavier */

public class DigitsGDToControlCube extends JFrame {
 public DigitsGDToControlCube() {
  Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
  BranchGroup scene = construitScene();
  SimpleUniverse univers = new SimpleUniverse(canvas);
  univers.addBranchGraph(scene);

  univers.getViewingPlatform().setNominalViewingTransform();
  setTitle("Interaction Clavier");
  setSize(300, 300);
  Container contentPane = getContentPane();
  contentPane.setLayout(new BorderLayout());
  setVisible(true);
 }

 public BranchGroup construitScene() {
  BranchGroup scene = new BranchGroup();

  TransformGroup rotationTG = new TransformGroup();
  rotationTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

  scene.addChild(rotationTG);
  rotationTG.addChild(new ColorCube(0.4));
  FiltreGDClavierBehavior myRotationBehavior = new FiltreGDClavierBehavior(rotationTG);
  myRotationBehavior.setSchedulingBounds(new BoundingSphere());
  scene.addChild(myRotationBehavior);
  // Remarque : la ligne précédente peut être remplacée par :
  // rotationTG.addChild(myRotationBehavior)

  scene.compile();
  return scene;
 }

 public static void main(String[] args) {
  new DigitsGDToControlCube();
 }
}