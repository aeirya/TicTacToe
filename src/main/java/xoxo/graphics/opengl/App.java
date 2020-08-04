package xoxo.graphics.opengl;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.util.concurrent.TimeUnit;

import org.lwjgl.system.MemoryUtil;

public class App {
    private static final int SRC_WIDTH = 720;
    private static final int SRC_HEIGHT = 720;

    static final String VERTEX_SHADER_SOURCE = "#version 330 core\n"
    + "layout (location = 0) in vec3 aPos;\n"
    + "void main()\n"
    + "{\n"
    + "   gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n"
    + "}\0";

    static final String FRAGMENT_SHADER_SOURCE = "#version 330 core\n"
    + "out vec4 FragColor;\n"
    + "void main()\n"
    + "{\n"
    + "vec3 inverseColor = 1.0 - color.rgb;"
    + "float gray = dot(1.0 - inverseColor.rgb, vec3(1.0/3.0));"
    + "FragColor = vec4(vec3(gray), color.a);"
    // + "   FragColor = vec4(0.5f, 0.1f, 0.8f, 1.0f);\n"
    + "}\n\0";

    static final String FRAGMENT_SHADER_SOURCE_SECOND = "#version 330 core\n"
    + "out vec4 FragColor;\n"
    + "void main()\n"
    + "{\n"
    + "   FragColor = vec4(0.8f, 0.9f, 0.5f, 1.0f);\n"
    + "}\n\0";

    public static void main(String[] args) throws Exception {
        glfwInit();

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_OPENGL_CORE_PROFILE);

        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

        long window = glfwCreateWindow(SRC_WIDTH, SRC_HEIGHT, "OpenGL", MemoryUtil.NULL, MemoryUtil.NULL);

        glfwMakeContextCurrent(window);
        glfwSetFramebufferSizeCallback(window, App::framebufferSizeCallback);

        GL.createCapabilities();

        // WHAT IS GLAD ? 
    
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, VERTEX_SHADER_SOURCE);
        glCompileShader(vertexShader);

        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, FRAGMENT_SHADER_SOURCE);
        glCompileShader(fragmentShader);

        int fragmentShaderSecond = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShaderSecond, FRAGMENT_SHADER_SOURCE_SECOND);
        glCompileShader(fragmentShaderSecond);

        int shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);

        int shaderProgramSecond = glCreateProgram();
        glAttachShader(shaderProgramSecond, vertexShader);
        glAttachShader(shaderProgramSecond, fragmentShaderSecond);
        glLinkProgram(shaderProgramSecond);

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
        glDeleteShader(fragmentShaderSecond);

        float[] firstVertices = {
            // first triangle
            -0.9f, -0.5f, 0.0f,  // left 
            -0.0f, -0.5f, 0.0f,  // right
            -0.45f, 0.5f, 0.0f,  // top 
        };

        float[] secondVertices = {
            // second triangle
            0.0f, 0.5f, 0.0f,  // left
            0.9f, 0.5f, 0.0f,  // right
            0.45f, -0.5f, 0.0f   // top 
        };

        int[] vao = new int[2];
        int[] vbo = new int[2];

        vao[0] = glGenVertexArrays();
        vbo[0] = glGenBuffers();
        
        vao[1] = glGenVertexArrays();
        vbo[1] = glGenBuffers();

        glBindVertexArray(vao[0]);
        glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
        glBufferData(GL_ARRAY_BUFFER, firstVertices, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * (4), MemoryUtil.NULL);
        glEnableVertexAttribArray(0);

        glBindVertexArray(vao[1]);
        glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
        glBufferData(GL_ARRAY_BUFFER, secondVertices, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, MemoryUtil.NULL);
        glEnableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glBindVertexArray(0);

        while (!glfwWindowShouldClose(window)) {
            App.processInput(window);

            glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glUseProgram(shaderProgram);
            glBindVertexArray(vao[0]);
            glDrawArrays(GL_TRIANGLES, 0, 3);
            
            glUseProgram(shaderProgramSecond);
            glBindVertexArray(vao[1]);
            glDrawArrays(GL_TRIANGLES, 0, 3);

            glfwSwapBuffers(window);
            glfwPollEvents();

            TimeUnit.MILLISECONDS.sleep(33);
        }

        glDeleteVertexArrays(vao[0]);
        glDeleteBuffers(vbo[0]);

        glDeleteVertexArrays(vao[1]);
        glDeleteBuffers(vbo[1]);

        glDeleteProgram(shaderProgram);
        glDeleteProgram(shaderProgramSecond);

        glfwTerminate();
    }

    public static void framebufferSizeCallback(long window, long width, long height) {
        // glViewport(0, 0, width, height);
    }

    public static void processInput(long window) {
        if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS)
            glfwSetWindowShouldClose(window, true);
    }
}
