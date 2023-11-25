import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class NewCube {
 private long window;

 public void run() {
  init();
  loop();

  // Libération des ressources
  glfwFreeCallbacks(window);
  glfwDestroyWindow(window);
  glfwTerminate();
 }

 private void init() {
  if (!glfwInit()) {
   throw new IllegalStateException("Unable to initialize GLFW");
  }

  // Création de la fenêtre
  glfwDefaultWindowHints();
  window = glfwCreateWindow(300, 300, "Interaction Clavier", NULL, NULL);
  if (window == NULL) {
   throw new RuntimeException("Failed to create the GLFW window");
  }

  glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
   int error = glGetError();
   if (error != GL_NO_ERROR) {
    System.out.println("OpenGL error: " + error);
   }

   if (key == GLFW_KEY_G && action == GLFW_PRESS) {
    // Logique pour tourner le cube à gauche
   } else if (key == GLFW_KEY_D && action == GLFW_PRESS) {
    // Logique pour tourner le cube à droite
   }
  });

  glfwMakeContextCurrent(window);
  glfwSwapInterval(1);
  glfwShowWindow(window);
 }

 private void loop() {
  int error = glGetError();
  if (error != GL_NO_ERROR) {
   System.out.println("OpenGL error: " + error);
  }

  GL.createCapabilities();

  while (!glfwWindowShouldClose(window)) {
   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // Nettoyage de l'écran

   // Logique de rendu du cube

   glfwSwapBuffers(window); // Echange des tampons
   glfwPollEvents(); // Traitement des événements
  }
 }

 public static void main(String[] args) {
  new NewCube().run();
 }
}
