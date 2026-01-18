package group.Types;

import group.Data.CurrentData;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Level {

    public String name;
    public List<Layer> leveldata;
    public int nextid;
    public long steamid = 0;

    public Level() {
        this.leveldata = new ArrayList<>();
    }

    public Level(String name, List<Layer> leveldata,int nextid_,long steamid) {
        this.name = name;
        this.leveldata = (leveldata != null) ? leveldata : new ArrayList<>();
        this.nextid = nextid_;
        this.steamid = steamid;
    }

    public void applyFrom(Level other) {
        if (other == null) return;

        this.name = other.name;

        this.leveldata.clear();

        for (Layer src : other.leveldata) {
            if (src == null) continue;

            Layer copy = new Layer(
                    new ArrayList<>(src.tiles),
                    src.layerid
            );

            this.leveldata.add(copy);
        }
    }

    public static void Load() {
        try (FileInputStream in = new FileInputStream(CurrentData.FilePath)) {

            Yaml yaml = new Yaml();
            Level loaded = yaml.loadAs(in, Level.class);

            if (loaded != null) {
                if (CurrentData.Config.level == null) {
                    CurrentData.Config.level = new Level();
                }
                CurrentData.Config.level.applyFrom(loaded);
                CurrentData.Config.level.leveldata = loaded.leveldata;
                CurrentData.Config.levelname = loaded.name;
                CurrentData.NEXT_LAYER_ID = loaded.nextid;
                CurrentData.Config.level.steamid = loaded.steamid;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Save(String name, List<Layer> layers,int nextid_,long steamid) {
        try {
            Level temp;

            if (!CurrentData.FilePath.toLowerCase().endsWith(".hil")) {
                CurrentData.FilePath = CurrentData.FilePath + ".hil";
            }

            temp = new Level(name, new ArrayList<>(),nextid_,steamid);

            for (Layer l : layers) {
                temp.leveldata.add(l);
            }

            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            options.setPrettyFlow(true);

            Representer representer = new Representer(options);
            representer.addClassTag(Level.class, Tag.MAP);

            Yaml yaml = new Yaml(representer, options);

            try (FileWriter writer = new FileWriter(CurrentData.FilePath)) {
                yaml.dump(temp, writer);
            }

            if (CurrentData.Config.level == null) {
                CurrentData.Config.level = new Level();
            }
            CurrentData.Config.level.applyFrom(temp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
