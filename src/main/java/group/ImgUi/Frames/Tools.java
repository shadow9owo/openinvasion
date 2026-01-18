package group.ImgUi.Frames;

import imgui.ImGui;

public class Tools {
    static int[] gridsize;
    public static void Render()
    {
        ImGui.begin("Tools");

        if (ImGui.button("Paint")) {
            // select paint tool
        }

        if (ImGui.button("Erase")) {
            // select erase tool
        }

        ImGui.separator();

        ImGui.text("Grid size:");
        ImGui.sliderInt("##gridsize", gridsize, 1, 64);

        ImGui.end();

        return;
    }
}
