import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;

public class ControlledCube extends JFrame implements ActionListener {
 // les boutons de manipulation
 private JButton boutonGauche = new JButton("< -");
 private JButton boutonDroite = new JButton("- >");

 // la TransformGroup qui gère le cube
 private TransformGroup rotationTG;

 public ControlledCube() {
  Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
  BranchGroup scene = construitScene();
  SimpleUniverse univers = new SimpleUniverse(canvas);
  univers.addBranchGraph(scene);

  univers.getViewingPlatform().setNominalViewingTransform();
  setTitle("ControlledCube");
  setSize(300, 300);
  Container contentPane = getContentPane();
  contentPane.setLayout(new BorderLayout());
  contentPane.add(BorderLayout.CENTER, canvas);
  Panel panneauBoutons = new Panel();
  panneauBoutons.add(boutonGauche);
  panneauBoutons.add(boutonDroite);
  contentPane.add(BorderLayout.SOUTH, panneauBoutons);

  boutonGauche.addActionListener(this);
  boutonDroite.addActionListener(this);
  setVisible(true);

 }

 public BranchGroup construitScene() {
  BranchGroup scene = new BranchGroup();
  Transform3D rotation3D = new Transform3D();
  rotation3D.set(new AxisAngle4d(0.0, 1.0, 0.0, Math.PI / 4.0));
  /*
   * initialison le TransformGroup rotationTG. Ce noeud est un champ de la classe
   * et sera manipulé par d'autres méthodes. On lui donne les capabilities ou
   * possibilités TRANSFORM_READ et TRANSFORM_WRITE pour que d'autres méthodes
   * puissent manipuler ses caractéristiques à l'exécution
   */

  rotationTG = new TransformGroup(rotation3D);
  rotationTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
  rotationTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

  scene.addChild(rotationTG);
  rotationTG.addChild(new ColorCube(0.4));
  scene.compile();
  return scene;

 }

 /*
  * Les évènements: un appui sur le bouton: 1 gauche = rotation négative autour
  * de l'axe des y . 2 droit = rotation positive autour de l'axe des y
  */
 public void actionPerformed(ActionEvent e) {
  Transform3D temp3D = new Transform3D();
  rotationTG.getTransform(temp3D);
  /*
   * crée une Transform3D qui est initialisée avec une rotation positive ou
   * négative suivant le choix
   */
  Transform3D tempDelta3D = new Transform3D();
  if (e.getSource() == boutonGauche) {
   tempDelta3D.rotY(-0.3);

  } else if (e.getSource() == boutonDroite) {
   tempDelta3D.rotY(0.3);
  }
  // modification de la rotation gérée par rotationTG
  temp3D.mul(tempDelta3D);
  rotationTG.setTransform(temp3D);
 }

 public static void main(String[] args) {
  new ControlledCube();
 }
}
