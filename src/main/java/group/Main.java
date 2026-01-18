package group;

import com.codedisaster.steamworks.*;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.RenderTexture;
import group.Data.CurrentData;
import group.Renderer.Renderer;
import group.Types.Layer;
import group.Types.Level;
import group.Types.Tile;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;

import group.ImgUi.Frames.*;

import group.ImgUi.Helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import group.Steam.*;

import javax.swing.*;

import static com.raylib.java.core.Color.*;
import static group.Data.Helpers.GetPersistantPath;

public class Main {

    public static RenderTexture renderTexture;

    public static Raylib rlj;
    public static ImGuiImplGl3 imGuiGl3;

    public static SteamUserStats stats;

    public static void main(String[] args) {

        group.Steam.Helpers.Init();

        stats = new SteamUserStats(new SteamUserStatsCallback() {

            @Override
            public void onUserStatsReceived(
                    long gameId,
                    SteamID steamID,
                    SteamResult result
            ) {
                System.out.println("Stats received: " + result);
            }

            @Override
            public void onUserStatsStored(long gameId, SteamResult result) {
            }

            @Override
            public void onUserAchievementStored(
                    long gameId,
                    boolean isGroupAchievement,
                    String achievementName,
                    int curProgress,
                    int maxProgress
            ) {
            }
        });

        Helpers.OSCheckWarning();

        CurrentData.FilePath = CurrentData.GetPath();

        if (CurrentData.FilePath == null)
        {
            System.exit(1);
        }

        File f = new File(CurrentData.FilePath);

        if (f.exists() && !f.isDirectory()) {
            group.Types.Level.Load();
        } else {
            CurrentData.Config.levelname = "untitled";
            group.Types.Level.Save("untitled", CurrentData.Config.level.leveldata, CurrentData.NEXT_LAYER_ID, 0);
        }

        rlj = new Raylib();
        rlj.core.InitWindow(800, 800, "openinvasion editor");
        rlj.core.SetTargetFPS(60);

        group.Data.Textures.Init();

        ImGui.createContext();
        ImGui.getIO().addConfigFlags(ImGuiConfigFlags.DockingEnable);

        imGuiGl3 = new ImGuiImplGl3();
        imGuiGl3.init("#version 330 core");

        renderTexture = rlj.textures.LoadRenderTexture(800,800);

        while (!rlj.core.WindowShouldClose()) {

            ImGuiIO io = ImGui.getIO();
            io.setDisplaySize(800, 800);
            io.setDeltaTime(1.0f / 60.0f);

            Helpers.updateImGuiInput();

            imGuiGl3.newFrame();

            ImGui.newFrame();

            Config.Render();
            Layerlist.Render();
            Textures.Render();
            Tools.Render();

            ImGui.render();

            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(Color.DARKGRAY);

            rlj.core.BeginTextureMode(renderTexture);

            Renderer.Render();

            rlj.core.EndTextureMode();

            rlj.core.rlgl.rlDisableDepthTest();
            rlj.core.rlgl.rlEnableColorBlend();

            imGuiGl3.renderDrawData(ImGui.getDrawData());

            rlj.textures.DrawTexturePro(renderTexture.texture,new Rectangle(0,0,renderTexture.texture.width,renderTexture.texture.height),new Rectangle(0,0,renderTexture.texture.width,-renderTexture.texture.height),new Vector2(0,0),0,WHITE);

            rlj.core.EndDrawing();

            SteamAPI.runCallbacks();
        }

        imGuiGl3.shutdown();
        ImGui.destroyContext();
        rlj.core.CloseWindow();

        group.Steam.Helpers.Shutdown();
    }
}