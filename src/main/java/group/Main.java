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

import group.Types.OwnerShipState;

import group.Steam.*;

import static com.raylib.java.core.Color.*;
import static group.Data.Helpers.GetPersistantPath;

public class Main {

    public static RenderTexture renderTexture;

    public static OwnerShipState ownershipState = OwnerShipState.NONE;

    public static Raylib rlj;
    public static ImGuiImplGl3 imGuiGl3;

    public static SteamUGC ugc;

    public static void main(String[] args) {

        group.Steam.Helpers.Init();

        SteamUser steamUser = new SteamUser(new SteamUserCallback() {});

        SteamUserStats stats = new SteamUserStats(new SteamUserStatsCallback() {

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


        ugc = new SteamUGC(new SteamUGCCallback() {
            @Override
            public void onUGCQueryCompleted(
                    SteamUGCQuery query,
                    int numResultsReturned,
                    int totalMatchingResults,
                    boolean isCachedData,
                    SteamResult result
            ) {
                if (result != SteamResult.OK || numResultsReturned == 0) {
                    ownershipState = OwnerShipState.ERROR;
                    ugc.releaseQueryUserUGCRequest(query);
                    return;
                }

                SteamUGCDetails details = new SteamUGCDetails();
                ugc.getQueryUGCResult(query, 0, details);

                SteamID creator = details.getOwnerID();
                SteamID me = steamUser.getSteamID();

                ownershipState = creator.equals(me) ?
                        OwnerShipState.OWNED :
                        OwnerShipState.NOT_OWNED;

                ugc.releaseQueryUserUGCRequest(query);
            }

            @Override
            public void onCreateItem(
                    SteamPublishedFileID id,
                    boolean needsAgreement,
                    SteamResult result
            ) {
                if (result != SteamResult.OK || needsAgreement) {
                    return;
                }

                long steamId = SteamNativeHandle.getNativeHandle(id);
                CurrentData.Config.level.steamid = steamId;

                Level.Save(
                        CurrentData.Config.levelname,
                        CurrentData.Config.level.leveldata,
                        CurrentData.NEXT_LAYER_ID,
                        steamId
                );

                SteamUGCUpdateHandle handle =
                        ugc.startItemUpdate(4272680, id);

                Path tmp = Path.of(String.valueOf(GetPersistantPath()), "tmp");

                try {
                    if (Files.exists(tmp)) {
                        Files.walk(tmp)
                                .sorted(Comparator.reverseOrder())
                                .forEach(p -> {
                                    try {
                                        Files.delete(p);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                    }

                    Files.createDirectories(tmp);

                    var image =
                            rlj.textures.LoadImageFromTexture(renderTexture.texture);
                    rlj.textures.ExportImage(
                            image,
                            tmp.resolve("preview.png").toString()
                    );

                    Files.copy(
                            Paths.get(CurrentData.FilePath),
                            tmp.resolve("level.hil"),
                            StandardCopyOption.REPLACE_EXISTING
                    );

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                ugc.setItemContent(handle, tmp.toString());
                ugc.setItemPreview(handle, tmp.resolve("preview.png").toString());

                ugc.submitItemUpdate(handle, "upload");
            }

            @Override
            public void onSubmitItemUpdate(
                    SteamPublishedFileID id,
                    boolean needsAgreement,
                    SteamResult result
            ) {
                System.out.println("SubmitItemUpdate: " + result);

                if (result == SteamResult.OK) {
                    javax.swing.JOptionPane.showMessageDialog(
                            null,
                            "Upload finished successfully!",
                            "Steam Workshop",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                            null,
                            "Upload failed: " + result,
                            "Steam Workshop",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        Helpers.OSCheckWarning();

        CurrentData.FilePath = CurrentData.GetPath();

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