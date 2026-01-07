package group.Data;

import group.Types.Rect;
import group.Types.TileIds;
import org.yaml.snakeyaml.Yaml;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static group.Data.Helpers.GetPersistantPath;

public class Level {
    public static class YamlTile {
        public String id;
        public int x, y, w, h;
    }

    public static void Init()
    {
        Path yamlPath = GetPersistantPath().resolve("tiles.yaml");
        List<YamlTile> list = new ArrayList<>();

        Yaml yaml = new Yaml();

        if (!Files.exists(yamlPath)) {
            list = new ArrayList<>();

            for (TileIds id : TileIds.values()) {
                Rect rect = Helpers.TileIdToRect(id);

                YamlTile a = new YamlTile();
                a.id = id.name();
                a.x = rect.x;
                a.y = rect.y;
                a.w = rect.w;
                a.h = rect.h;

                list.add(a);
            }

            try {
                Files.writeString(yamlPath, yaml.dump(list));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (InputStream in = Files.newInputStream(yamlPath)) {
            list = yaml.loadAs(in, ArrayList.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //need to load or export x,y,w,h,id and foreach it through all the ids
        return;
    }
}
