package group.ImgUi.Frames;

import javax.swing.JOptionPane;

import com.codedisaster.steamworks.*;
import group.Data.CurrentData;
import group.Data.Helpers;
import group.Data.Level;
import group.Main;
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

        ImGui.text("SteamWorkshop");

        if (ImGui.button("Upload!"))
        {
            if (CurrentData.Config.level.steamid != 0) {
                SteamUGCUpdateHandle handle =
                        ugc.startItemUpdate(
                                4272680,
                                new SteamPublishedFileID(CurrentData.Config.level.steamid)
                        );


                try
                {
                    if (Files.exists(Path.of(GetPersistantPath() + "/tmp"))) {
                        Files.walk(Path.of(GetPersistantPath() + "/tmp"))
                                .sorted(Comparator.reverseOrder())
                                .forEach(path -> {
                                    try {
                                        Files.delete(path);
                                    } catch (IOException e) {
                                        throw new RuntimeException("Failed to wipe tmp dir", e);
                                    }
                                });
                    }
                } catch (Exception e) {
                    //throw new RuntimeException(e);
                }

                try {
                    Files.createDirectories(Path.of(GetPersistantPath() + "/tmp"));
                } catch (IOException e) {
                    throw new RuntimeException("Failed to create tmp dir", e);
                }

                com.raylib.java.textures.Image image = rlj.textures.LoadImageFromTexture(renderTexture.texture);

                rlj.textures.ExportImage(image, GetPersistantPath() + "/tmp/preview.png");

                try {
                    Files.copy(
                            Paths.get(CurrentData.FilePath),
                            Paths.get(String.valueOf(GetPersistantPath()), "tmp", "level.hil"),
                            StandardCopyOption.REPLACE_EXISTING
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                ugc.setItemTitle(handle, CurrentData.Config.levelname);
                ugc.setItemContent(handle, GetPersistantPath() + "/tmp");
                ugc.setItemPreview(handle, GetPersistantPath() + "/tmp/preview.png");

                ugc.submitItemUpdate(handle, "Updated level");
            } else {
                ugc.createItem(4272680, SteamRemoteStorage.WorkshopFileType.Community);

                SteamUserStats steamStats =
                        new SteamUserStats(new SteamUserStatsCallback() {});

                steamStats.requestGlobalStats(0);

                steamStats.setAchievement("CREATOR_AC_8");

                steamStats.storeStats();
            }
        }

        ImGui.end();
    }
}
