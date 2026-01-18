package group;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;

import group.ImgUi.Helpers;

public class Main {

    public static Raylib rlj;
    private static ImGuiImplGl3 imGuiGl3;

    public static void main(String[] args) {

        Helpers.OSCheckWarning();

        rlj = new Raylib();
        rlj.core.InitWindow(800, 800, "openinvasion editor");
        rlj.core.SetTargetFPS(60);

        ImGui.createContext();
        ImGui.getIO().addConfigFlags(ImGuiConfigFlags.DockingEnable);

        imGuiGl3 = new ImGuiImplGl3();
        imGuiGl3.init("#version 330 core");

        while (!rlj.core.WindowShouldClose()) {

            ImGuiIO io = ImGui.getIO();
            io.setDisplaySize(800, 800);
            io.setDeltaTime(1.0f / 60.0f);

            Helpers.updateImGuiInput();

            imGuiGl3.newFrame();

            ImGui.newFrame();

            ImGui.showDemoWindow();

            ImGui.render();

            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(Color.DARKGRAY);

            rlj.core.rlgl.rlDisableDepthTest();
            rlj.core.rlgl.rlEnableColorBlend();

            imGuiGl3.renderDrawData(ImGui.getDrawData());

            rlj.core.EndDrawing();
        }


        imGuiGl3.shutdown();
        ImGui.destroyContext();
        rlj.core.CloseWindow();
    }
}
