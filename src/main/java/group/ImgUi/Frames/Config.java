package group.ImgUi.Frames;

import javax.swing.JOptionPane;

import com.codedisaster.steamworks.*;
import group.Data.CurrentData;
import group.Data.Helpers;
import group.Main;
import group.Types.Level;
import imgui.ImGui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

import static group.Data.Helpers.GetPersistantPath;
import static group.Main.*;

public class Config {

    public static boolean uploadInProgress = false;

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
            group.Types.Level.Save(CurrentData.Config.levelname,CurrentData.Config.level.leveldata,CurrentData.NEXT_LAYER_ID,CurrentData.Config.level.steamid);
        }

        ImGui.sameLine();

        if (ImGui.button("Load"))
        {
            group.Types.Level.Load();
        }

        ImGui.newLine();

        ImGui.text("Export map for game use");

        if (ImGui.button("Export!")) {

            String path = CurrentData.GetPath();

            if (path != null)
            {
                Level.Save(path, CurrentData.Config.level.leveldata, CurrentData.NEXT_LAYER_ID, 0);
                JOptionPane.showMessageDialog(null, "Exported!", "openinvasion",
                        JOptionPane.INFORMATION_MESSAGE);

                stats.setAchievement("CREATOR_AC_8");
            }
        }
        ImGui.end();
    }
}
