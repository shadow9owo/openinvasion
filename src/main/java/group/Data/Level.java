package group.Data;

import com.raylib.java.shapes.Rectangle;
import group.Types.Tile;
import group.Types.TileIds;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

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

            DumperOptions opts = new DumperOptions();
            opts.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

            Representer rep = new Representer(opts);
            rep.getPropertyUtils().setSkipMissingProperties(true);

            LoaderOptions loaderOptions = new LoaderOptions();
            loaderOptions.setTagInspector(tag ->
                    tag.getClassName().equals(TilesConfig.class.getName())
            );

            Constructor constructor = new Constructor(TilesConfig.class, loaderOptions);

            Yaml yaml = new Yaml(constructor, rep, opts, loaderOptions);

            TilesConfig config = new TilesConfig();

            if (!Files.exists(yamlPath)) {
                for (TileIds id : TileIds.values()) {
                    Rectangle rect = Helpers.TileIdToRect(id);
                    if (rect == null || rect.width <= 0 || rect.height <= 0) continue;

                    Tile t = new Tile();
                    t.id = id;
                    t.x = (int)rect.x;
                    t.y = (int)rect.y;
                    t.w = (int)rect.width;
                    t.h = (int)rect.height;

                    config.tiles.add(t);
                }

                Files.writeString(yamlPath, yaml.dump(config));
            }

            try (InputStream in = Files.newInputStream(yamlPath)) {
                config = yaml.loadAs(in, TilesConfig.class);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
