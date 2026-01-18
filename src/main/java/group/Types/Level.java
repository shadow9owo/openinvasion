package group.Types;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import group.Data.CurrentData;
import org.yaml.snakeyaml.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Level {
    public static String Name;
    public static List<Layer> Leveldata;

    public Level(String name, List<Layer> leveldata) {
        this.Name = name;
        if (leveldata == null)
        {
            this.Leveldata = new ArrayList<>();
        }else {
            this.Leveldata = leveldata;
        }
    }


    public static void Load() {
        try {
            Yaml yaml = new Yaml();

            try (FileInputStream in = new FileInputStream(CurrentData.FilePath)) {
                Level loaded = yaml.loadAs(in, Level.class);

                if (loaded != null) {
                    Name = loaded.Name;
                    if (loaded.Leveldata == null)
                    {
                        Leveldata = new ArrayList<>();
                    }else {
                        Leveldata = loaded.Leveldata;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

    public static void Save(String name, Layer[] list) {
        try {
            Level level = new Level(name, new ArrayList<>());
            level.Leveldata.addAll(Arrays.asList(list));

            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            options.setPrettyFlow(true);

            Yaml yaml = new Yaml(options);

            try (FileWriter writer = new FileWriter(CurrentData.FilePath)) {
                yaml.dump(level, writer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }
}
