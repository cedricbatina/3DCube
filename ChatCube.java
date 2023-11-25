/*import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;*/

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.opengl.GL;

public class ChatCube {
 private long window;
 private int vaoId;
 private int vboId;
 private int vertexShaderId;
 private int fragmentShaderId;
 private int shaderProgramId;

 public void run() {
  init();
  loop();

  cleanup();
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

  GL.createCapabilities();

  // Setup shaders and buffers
  setupShaders();
  setupBuffers();
 }

 private void setupShaders() {
  // Vertex shader
  String vertexShaderSource = "#version 330\n"
    + "layout(location = 0) in vec3 position;\n"
    + "void main()\n"
    + "{\n"
    + "    gl_Position = vec4(position, 1.0);\n"
    + "}\n";
  vertexShaderId = glCreateShader(GL_VERTEX_SHADER);
  glShaderSource(vertexShaderId, vertexShaderSource);
  glCompileShader(vertexShaderId);
  if (glGetShaderi(vertexShaderId, GL_COMPILE_STATUS) == GL_FALSE) {
   throw new RuntimeException("Error compiling vertex shader");
  }

  // Fragment shader
  String fragmentShaderSource = "#version 330\n"
    + "out vec4 fragColor;\n"
    + "void main()\n"
    + "{\n"
    + "    fragColor = vec4(1.0, 1.0, 1.0, 1.0);\n"
    + "}\n";
  fragmentShaderId = glCreateShader(GL_FRAGMENT_SHADER);
  glShaderSource(fragmentShaderId, fragmentShaderSource);
  glCompileShader(fragmentShaderId);
  if (glGetShaderi(fragmentShaderId, GL_COMPILE_STATUS) == GL_FALSE) {
   throw new RuntimeException("Error compiling fragment shader");
  }

  // Link shaders
  shaderProgramId = glCreateProgram();
  glAttachShader(shaderProgramId, vertexShaderId);
  glAttachShader(shaderProgramId, fragmentShaderId);
  glLinkProgram(shaderProgramId);
  if (glGetProgrami(shaderProgramId, GL_LINK_STATUS) == GL_FALSE) {
   throw new RuntimeException("Error linking shader program");
  }
 }

 private void setupBuffers() {
  // Vertex data
  float[] vertices = new float[] {
    // Front face
    -0.5f, 0.5f, 0.5f,
    0.5f, 0.5f, 0.5f,
    0.5f, -0.5f, 0.5f,
    -0.5f, -0.5f, 0.5f,
    // ... Add other faces ...
  };

  // Create VAO
  vaoId = glGenVertexArrays();
  glBindVertexArray(vaoId);

  // Create VBO
  vboId = glGenBuffers();
  glBindBuffer(GL_ARRAY_BUFFER, vboId);
  glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
  glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
  glEnableVertexAttribArray(0);

  glBindBuffer(GL_ARRAY_BUFFER, 0);
  glBindVertexArray(0);
 }

 private void loop() {
  while (!glfwWindowShouldClose(window)) {
   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

   // Use shader
   glUseProgram(shaderProgramId);

   // Bind VAO
   glBindVertexArray(vaoId);
   glEnableVertexAttribArray(0);

   // Draw cube
   glDrawArrays(GL_TRIANGLES, 0, 36); // Adjust the count based on your vertices

   // Unbind VAO
   glDisableVertexAttribArray(0);
   glBindVertexArray(0);

   glUseProgram(0);

   glfwSwapBuffers(window);
   glfwPollEvents();
  }
 }

 private void cleanup() {
  glDeleteVertexArrays(vaoId);
  glDeleteBuffers(vboId);
  glDeleteShader(vertexShaderId);
  glDeleteShader(fragmentShaderId);
  glDeleteProgram(shaderProgramId);

  glfwFreeCallbacks(window);
  glfwDestroyWindow(window);
  glfwTerminate();
 }

 public static void main(String[] args) {
  new ChatCube().run();
 }
}
