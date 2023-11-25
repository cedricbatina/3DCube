
/*import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
*/
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.opengl.GL;

public class MyCube {
 private long window;

 public void run() {
  init();
  loop();

  glfwFreeCallbacks(window);
  glfwDestroyWindow(window);
  glfwTerminate();
 }

 private void init() {
  if (!glfwInit()) {
   throw new IllegalStateException("Unable to initialize GLFW");
  }

  glfwDefaultWindowHints();
  window = glfwCreateWindow(300, 300, "Cube 3D", NULL, NULL);
  if (window == NULL) {
   throw new RuntimeException("Failed to create the GLFW window");
  }

  glfwMakeContextCurrent(window);
  glfwSwapInterval(1);
  glfwShowWindow(window);
 }

 private void loop() {
  GL.createCapabilities();
  glEnable(GL_DEPTH_TEST);

  while (!glfwWindowShouldClose(window)) {
   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
   glLoadIdentity();

   // Positionnement de la caméra
   glTranslatef(0.0f, 0.0f, -5.0f);

   drawCube();

   glfwSwapBuffers(window);
   glfwPollEvents();
  }
 }

 private void drawCube() {
  glBegin(GL_QUADS);

  // Front
  glColor3f(1.0f, 0.0f, 0.0f);
  glVertex3f(-1.0f, -1.0f, 1.0f);
  glVertex3f(1.0f, -1.0f, 1.0f);
  glVertex3f(1.0f, 1.0f, 1.0f);
  glVertex3f(-1.0f, 1.0f, 1.0f);

  // Right side
  glColor3f(0.0f, 1.0f, 0.0f);
  glVertex3f(1.0f, -1.0f, -1.0f);
  glVertex3f(1.0f, 1.0f, -1.0f);
  glVertex3f(1.0f, 1.0f, 1.0f);
  glVertex3f(1.0f, -1.0f, 1.0f);

  // Left side
  glColor3f(0.0f, 0.0f, 1.0f);
  glVertex3f(-1.0f, -1.0f, -1.0f);
  glVertex3f(-1.0f, -1.0f, 1.0f);
  glVertex3f(-1.0f, 1.0f, 1.0f);
  glVertex3f(-1.0f, 1.0f, -1.0f);

  // ... Ajoutez les autres faces ici ...

  glEnd();
 }

 public static void main(String[] args) {
  new MyCube().run();
 }
}
