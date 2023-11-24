import java.awt.*;
import javax.swing.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class OtherCube3D extends JFrame {
 public OtherCube3D() {
  Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
  BranchGroup scene = construitScene(construitCube());
  SimpleUniverse univers = new SimpleUniverse(canvas);
  univers.addBranchGraph(scene);

  univers.getViewingPlatform().setNominalViewingTransform();

  setTitle("Second3D avec SimpleUniverse");
  setSize(400, 400);
  Container contentPane = getContentPane();
  contentPane.setLayout(new BorderLayout());
  contentPane.add(BorderLayout.CENTER, canvas);
  setVisible(true);
 }

 public BranchGroup construitScene(Shape3D forme3D) {
  BranchGroup scene = new BranchGroup();
  Transform3D rotation3D = new Transform3D();
  rotation3D.set(new AxisAngle4d(1.0, 1.0, 00, Math.PI / 4.0));
  TransformGroup rotationTG = new TransformGroup(rotation3D);

  scene.addChild(rotationTG);
  rotationTG.addChild(forme3D);
  return scene;
 }

 public Shape3D construitCube() {
  return new ColorCube(0.5);

 }

 public static void main(String[] args) {
  new OtherCube3D();
 }
}
