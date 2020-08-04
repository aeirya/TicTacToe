package xoxo.graphics.opengl;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public class Renderer {
    private static final int SCR_WIDTH = 1000;
    private static final int SCR_HEIGHT = 800;

    static final String vertexShaderSource = "#version 330 core\n"
    + "layout (location = 0) in vec3 aPos;\n"
    + "void main()\n"
    + "{\n"
    + "   gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n"
    + "}\0";
    
    static final String fragmentShaderSource = "#version 330 core\n"
    + "out vec4 FragColor;\n"
    + "void main()\n"
    + "{\n"
    + "   FragColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);\n"
    + "}\n\0";

    public static void main(String[] args) {
        // window
        GLFW.glfwInit();
        macOsSupportTweak();
        long window = GLFW.glfwCreateWindow(SCR_WIDTH, SCR_HEIGHT, "Learn OpenGL", MemoryUtil.NULL, MemoryUtil.NULL);
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSetFramebufferSizeCallback(window, Renderer::framebuffer_size_callback);
        
        GL.createCapabilities();
        
        // create and compile shaders
        int vertexShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexShader, vertexShaderSource);
        GL20.glCompileShader(vertexShader);

        int fragmentShader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentShader, fragmentShaderSource);
        GL20.glCompileShader(fragmentShader);

        // link shaders
        int shaderProgram = GL20.glCreateProgram();
        GL20.glAttachShader(shaderProgram, vertexShader);
        GL20.glAttachShader(shaderProgram, fragmentShader);
        GL20.glLinkProgram(shaderProgram);

        // delete shaders
        GL20.glDeleteShader(vertexShader);
        GL20.glDeleteShader(fragmentShader);

        // data
        float vertices[] = {
            0.5f,  0.5f, 0.0f,  // top right
            0.5f, -0.5f, 0.0f,  // bottom right
            -0.5f, -0.5f, 0.0f,  // bottom left
            -0.5f,  0.5f, 0.0f   // top left 
        };

        int indices[] = {  // note that we start from 0!
           0, 1, 3,  // first Triangle
           1, 2, 3   // second Triangle
        };
       
        int vbo, vao, ebo;

        vao = GL30.glGenVertexArrays();
        vbo = GL15.glGenBuffers();
        ebo = GL15.glGenBuffers();

        GL30.glBindVertexArray(vao);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ebo);

        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices , GL15.GL_STATIC_DRAW);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);   
        
        int location = 0;
        int size = 3;
        int stride = 3;
        GL20.glVertexAttribPointer(location, size, GL11.GL_FLOAT, false, stride * (4), MemoryUtil.NULL);
        GL20.glEnableVertexAttribArray(location);
    
        // unbinding vbo
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); 
        // unbinding vao (not necessary)
        GL30.glBindVertexArray(0); 

        // render loop
        while (! GLFW.glfwWindowShouldClose(window)) {
            // input
            Renderer.processInput(window);

            // render
            GL11.glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            // draw our first triangle
            GL20.glUseProgram(shaderProgram);
            GL30.glBindVertexArray(vao);
            
            //glDrawArrays(GL_TRIANGLES, 0, 6);
            GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
    
            // glfw: swap buffers and poll IO events (keys pressed/released, mouse moved etc.)
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }

        // optional: de-allocate all resources once they've outlived their purpose:
        GL30.glDeleteVertexArrays(vao);
        GL15.glDeleteBuffers(vbo);
        GL15.glDeleteBuffers(ebo);
        GL20.glDeleteProgram(shaderProgram);

        // glfw: terminate, clearing all previously allocated GLFW resources.
        // ------------------------------------------------------------------
        GLFW.glfwTerminate();
    }

    public static void framebuffer_size_callback(long window, long width, long height) {
        System.out.println("changed window size!");
    }

    public static void processInput(long window) {
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS)
            GLFW.glfwSetWindowShouldClose(window, true);
    }

    public static void macOsSupportTweak() {
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 1);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
    }
}