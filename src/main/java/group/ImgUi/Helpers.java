package group.ImgUi;

import imgui.ImGui;
import imgui.ImGuiIO;

import static com.raylib.java.core.input.Keyboard.*;

import static group.Main.rlj;

import group.Main.*;

public class Helpers {
    public static void updateImGuiInput() {
        ImGuiIO io = ImGui.getIO();

        io.setDisplaySize(
                rlj.core.GetScreenWidth(),
                rlj.core.GetScreenHeight()
        );

        io.setDeltaTime(1.0f / 12.0f);

        io.setMousePos(
                rlj.core.GetMouseX(),
                rlj.core.GetMouseY()
        );

        io.setMouseDown(0, rlj.core.IsMouseButtonDown(0));
        io.setMouseDown(1, rlj.core.IsMouseButtonDown(1));
        io.setMouseDown(2, rlj.core.IsMouseButtonDown(2));

        try
        {
            if (rlj.core.GetMouseWheelMove() != 0)
            {
                io.setMouseWheel(rlj.core.GetMouseWheelMove());
            }
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }

        io.setKeyCtrl(rlj.core.IsKeyDown(KEY_LEFT_CONTROL) || rlj.core.IsKeyDown(KEY_RIGHT_CONTROL));
        io.setKeyShift(rlj.core.IsKeyDown(KEY_LEFT_SHIFT) || rlj.core.IsKeyDown(KEY_RIGHT_SHIFT));
        io.setKeyAlt(rlj.core.IsKeyDown(KEY_LEFT_ALT) || rlj.core.IsKeyDown(KEY_RIGHT_ALT));
        io.setKeySuper(rlj.core.IsKeyDown(KEY_LEFT_SUPER) || rlj.core.IsKeyDown(KEY_RIGHT_SUPER));

        int c;
        while ((c = rlj.core.GetCharPressed()) != 0) {
            if (c > -1)
            {
                io.addInputCharacter(c);
            }
        }
    }
}
