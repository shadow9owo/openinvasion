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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static group.Data.Helpers.GetPersistantPath;

public class Level {
    public static class YamlTile {
        public String id;
        public int x, y, w, h;
    }

    public static void Init()
    {
        try {
            Path base = GetPersistantPath();
            System.out.println("PERSIST PATH = " + base.toAbsolutePath());
            Files.createDirectories(base);

            Path yamlPath = base.resolve("tiles.yaml");
            Yaml yaml = new Yaml();

            List<Map<String, Object>> list;

            if (!Files.exists(yamlPath)) {
                list = new ArrayList<>();

                for (TileIds id : TileIds.values()) {
                    Rect rect = Helpers.TileIdToRect(id);

                    Map<String, Object> a = new LinkedHashMap<>();
                    a.put("id", id.name());
                    a.put("x", rect.x);
                    a.put("y", rect.y);
                    a.put("w", rect.w);
                    a.put("h", rect.h);

                    list.add(a);
                }

                Files.writeString(yamlPath, yaml.dump(list));
            }

            try (InputStream in = Files.newInputStream(yamlPath)) {
                list = yaml.load(in);
            }

            for (Map<String, Object> m : list) {
                String id = (String) m.get("id");
                int x = (int) m.get("x");
                int y = (int) m.get("y");
                int w = (int) m.get("w");
                int h = (int) m.get("h");

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
