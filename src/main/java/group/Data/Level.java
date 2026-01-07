package group.Data;

import group.Types.TileIds;
import org.yaml.snakeyaml.Yaml;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Level {
    public static class YamlTile {
        public String id;
        public int x, y, w, h;
    }

    public static void Init()
    {
        List<YamlTile> list = new ArrayList<>();

        Yaml yaml = new Yaml();

        for (TileIds id : TileIds.values()) {
            group.Types.Rect rect = null;
            YamlTile a = new YamlTile();

            rect = Helpers.TileIdToRect(id);

            a.id = id.toString();
            a.x = rect.x;
            a.y = rect.y;
            a.w = rect.w;
            a.h = rect.h;

            list.add(a);
        }

        //need to load or export x,y,w,h,id and foreach it through all the ids
        return;
    }
}
