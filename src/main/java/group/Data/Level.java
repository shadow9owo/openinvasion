package group.Data;

import group.Types.Rect;
import group.Types.Tile;
import group.Types.TileIds;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static group.Data.Helpers.GetPersistantPath;

public class Level {

    public static class TilesConfig {
        public java.util.List<Tile> tiles = new java.util.ArrayList<>();
    }

    public static void Init() {
        try {
            Path base = GetPersistantPath();
            Files.createDirectories(base);

            Path yamlPath = base.resolve("tiles.yaml");
            Yaml yaml = new Yaml();

            TilesConfig config;

            if (!Files.exists(yamlPath)) {
                config = new TilesConfig();

                for (TileIds id : TileIds.values()) {
                    Rect rect = Helpers.TileIdToRect(id);
                    if (rect == null || rect.w <= 0 || rect.h <= 0) continue;

                    Tile t = new Tile();
                    t.id = id;
                    t.x = rect.x;
                    t.y = rect.y;
                    t.w = rect.w;
                    t.h = rect.h;

                    config.tiles.add(t);
                }

                Files.writeString(yamlPath, yaml.dump(config));
            }

            try (InputStream in = Files.newInputStream(yamlPath)) {
                config = yaml.loadAs(in, TilesConfig.class);
            }

            for (Tile t : config.tiles) {
                TileIds id = t.id;
                int x = t.x;
                int y = t.y;
                int w = t.w;
                int h = t.h;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
