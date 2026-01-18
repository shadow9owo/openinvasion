    package group;

    import com.codedisaster.steamworks.*;
    import com.raylib.java.Raylib;
    import com.raylib.java.core.Color;

    import group.Data.CurrentData;
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
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;

    import group.Steam.*;

    public class Main {

        public static Raylib rlj;
        public static ImGuiImplGl3 imGuiGl3;

        public static void main(String[] args) {

            group.Steam.Helpers.Init();

            SteamUGC ugc = new SteamUGC(new SteamUGCCallback() {
                @Override
                public void onCreateItem(SteamPublishedFileID id,
                                         boolean needsAgreement,
                                         SteamResult result) {
                    System.out.println("CreateItem: " + result + " id=" + id);
                }
            });

            Helpers.OSCheckWarning();

            CurrentData.FilePath = CurrentData.GetPath();

            File f = new File(CurrentData.FilePath);

            if (f.exists() && !f.isDirectory())
            {
                group.Types.Level.Load();
            }else
            {
                CurrentData.Config.levelname = "untitled";
                group.Types.Level.Save("untitled",CurrentData.Config.level.leveldata, CurrentData.NEXT_LAYER_ID);
            }

            rlj = new Raylib();
            rlj.core.InitWindow(800, 800, "openinvasion editor");
            rlj.core.SetTargetFPS(60);

            group.Data.Textures.Init();

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

                Config.Render();
                Layerlist.Render();
                Textures.Render();
                Tools.Render();

                ImGui.render();

                rlj.core.BeginDrawing();
                rlj.core.ClearBackground(Color.DARKGRAY);

                rlj.core.rlgl.rlDisableDepthTest();
                rlj.core.rlgl.rlEnableColorBlend();

                imGuiGl3.renderDrawData(ImGui.getDrawData());

                rlj.core.EndDrawing();

                SteamAPI.runCallbacks();
            }

            imGuiGl3.shutdown();
            ImGui.destroyContext();
            rlj.core.CloseWindow();

            group.Steam.Helpers.Shutdown();
        }
    }
