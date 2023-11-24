import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.*;

import javax.swing.*;

public class Cube3D extends JFrame {
 public Cube3D() {
  // Création de l'univers virtuel
  SimpleUniverse univers = new SimpleUniverse();
  Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

  univers.getViewingPlatform().setNominalViewingTransform();
  univers.getViewer().getView().setBackClipDistance(100.0);

  // Ajout du cube à l'univers
  univers.addBranchGraph(construitScene(construitCube()));

  // Configuration de la fenêtre
  setTitle("Première scène 3D en Java + Point de vue");
  setSize(400, 400);
  Container contentPane = getContentPane();
  contentPane.setLayout(new BorderLayout());
  contentPane.add(BorderLayout.CENTER, canvas);
  setVisible(true);
 }

 public BranchGroup construitScene(Shape3D forme3D) {
  BranchGroup scene = new BranchGroup();

  // Création d'une transformation pour rotation
  Transform3D rotation3D = new Transform3D();
  rotation3D.set(new AxisAngle4d(1.0, 1.0, 0.0, Math.PI / 4.0));
  TransformGroup rotationTG = new TransformGroup(rotation3D);

  scene.addChild(rotationTG);
  rotationTG.addChild(forme3D);

  return scene;
 }

 public Shape3D construitCube() {
  return new ColorCube(0.5);
 }

 public static void main(String[] args) {
  new Cube3D();
 }
}

/*
 * import java.awt.*;
 * import javax.swing.*;
 * import javax.media.j3d.*;
 * import javax.vecmath.*;
 * import com.sun.j3d.utils.geometry.ColorCube;
 * import com.sun.j3d.utils.SimpleUniverse;
 * 
 * public class Cube3D extends JFrame {
 * public Cube3D() {
 * VirtualUniverse univers = new VirtualUniverse();
 * Locale leLocale = new Locale(univers);
 * 
 * Canvas3D canvas = new Canvas(null);
 * leLocale.addBranchGraph(ConstruitPointDeVue(canvas));
 * leLocale.addBranchGraph(construitScene(construitCube()));
 * 
 * setTitle("First 3D scene in Java + Point de vue");
 * setSize(400, 400);
 * Container contentPane = getContentPane();
 * contentPane.setLayout(new BorderLayout());
 * contentPane.add(BorderLayout.CENTER, canvas);
 * setVisible(true);
 * }
 * 
 * public BranchGroup ConstruitPointDeVue(Canvas3D c) {
 * BranchGroup pointVueBG = new BranchGroup();
 * Transform3D translation = new Transform3D();
 * translation.set(new Vectors3f(0.0f, 0.0f, 2.5f));
 * TransformGroup translationTG = new TransformGroup(translation);
 * 
 * ViewPlatform laViewPlatform = new ViewPlatform();
 * translationTG.addChild(laViewPlatform);
 * pointVueBG.addChild(translationTG);
 * 
 * View laVue = new View();
 * laVue.addCanvas3D(c);
 * laVue.attachViewPlatform(laViewPlatform);
 * PhysicalBody corpsObservateur = new PhysicalBody();
 * PhysicalEnvironment environmentObservateur = new PhysicalEnvironment();
 * return pointVueBG;
 * 
 * }
 * 
 * public BranchGroup construitScene(Shape3D forme3D) {
 * BranchGroup scene = new BranchGroup();
 * 
 * Transform3D rotation3D = new Transform3D();
 * rotation3D.set(new AxisAngle4d(1.0, 1.0, 0.0, Math.PI / 4.0));
 * TransformGroup rotation3D = new TransformGroup(rotation3D);
 * scene.addChild(rotationTG);
 * rotationTG.addChild(forme3D);
 * return scene;
 * 
 * }
 * 
 * public Shape3D construitCube() {
 * return new ColorCube(0.5);
 * }
 * 
 * public static void main(String[] args) {
 * new Cube3D();
 * }
 * }
 */