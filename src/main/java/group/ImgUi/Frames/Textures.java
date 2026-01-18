package group.ImgUi.Frames;

import com.raylib.java.textures.Texture2D;
import com.raylib.java.shapes.Rectangle;
import group.Data.CurrentData;
import group.Types.TileIds;
import imgui.ImGui;

import static group.Data.Helpers.TileIdToRect;

public class Textures {

    public static class ImGuiTile {
        public long textureId;
        public float u0, v0, u1, v1;
    }

    public static ImGuiTile getImGuiTile(TileIds id) {
        Texture2D tex = group.Data.Textures.text.get(id);
        Rectangle r = TileIdToRect(id);

        ImGuiTile out = new ImGuiTile();

        out.textureId = Integer.toUnsignedLong(tex.id);

        out.u0 = r.x / (float) tex.width;
        out.v0 = r.y / (float) tex.height;
        out.u1 = (r.x + r.width) / (float) tex.width;
        out.v1 = (r.y + r.height) / (float) tex.height;

        return out;
    }

    public static void Render() {
        ImGui.begin("Textures");

        TileIds[] values = TileIds.values();

        for (int i = 0; i < values.length; i++) {
            TileIds tileId = values[i];
            ImGuiTile t = getImGuiTile(tileId);

            if (ImGui.imageButton(
                    tileId.name(),
                    t.textureId,
                    32, 32,
                    t.u0, t.v0,
                    t.u1, t.v1
            )) {
                CurrentData.Config.selectedtile = values[i];
            }

            if ((i + 1) % 8 != 0) {
                ImGui.sameLine();
            }
        }

        ImGui.end();
    }
}
