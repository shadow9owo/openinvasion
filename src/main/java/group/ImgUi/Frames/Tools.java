package group.ImgUi.Frames;

import group.Data.CurrentData;
import group.Types.interactiontype;
import imgui.ImGui;

public class Tools {
    public static int gridsize;
    public static int spritesize;
    public static void Render()
    {
        ImGui.begin("Tools");

        if (ImGui.button("Paint")) {
            CurrentData.interactiontype = interactiontype.Paint;
        }

        ImGui.sameLine();

        if (ImGui.button("Erase")) {
            CurrentData.interactiontype = interactiontype.Erase;
        }

        ImGui.separator();

        ImGui.text("Selected : ");

        ImGui.sameLine();

        switch (CurrentData.interactiontype)
        {
            case Erase ->
            {
                ImGui.text("Erase");
                break;
            }
            case Paint ->
            {
                ImGui.text("Paint");
                break;
            }
            default ->
            {
                ImGui.text("Paint");
                CurrentData.interactiontype = interactiontype.Paint;
                break;
            }
        }

        ImGui.separator();

        ImGui.text("Grid size:");
        ImGui.sliderInt("##gridsize", new int[]{gridsize}, 1, 256);

        ImGui.separator();

        ImGui.text("Sprite size:");
        ImGui.sliderInt("##spritesize", new int[]{spritesize}, 1, 256);

        ImGui.end();

        return;
    }
}
