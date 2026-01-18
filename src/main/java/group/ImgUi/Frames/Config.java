package group.ImgUi.Frames;

import javax.swing.JOptionPane;

import group.Data.CurrentData;
import group.Data.Level;
import imgui.ImGui;

public class Config {

    public static void Render() {
        ImGui.begin("Config");

        ImGui.text("Editor");
        ImGui.separator();

        if (ImGui.button("Change level name"))
        {
            String input = JOptionPane.showInputDialog(
                    null,
                    "Input new Level name",
                    "",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (input != null) {
                CurrentData.Config.levelname = input;
            } else {

            }
        }

        ImGui.text("Level name : " + CurrentData.Config.levelname);

        ImGui.separator();

        if (ImGui.button("Save"))
        {
            group.Types.Level.Save(CurrentData.Config.levelname,CurrentData.Config.level.leveldata,CurrentData.NEXT_LAYER_ID);
        }

        ImGui.sameLine();

        if (ImGui.button("Load"))
        {
            group.Types.Level.Load();
        }

        ImGui.newLine();

        ImGui.text("SteamWorkshop");

        if (ImGui.button("Upload!"))
        {

        }

        ImGui.end();
    }
}
