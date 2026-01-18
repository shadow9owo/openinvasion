package group.Data;

import group.Types.TileIds;
import group.Types.Layer;
import group.Types.interactiontype;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CurrentData {
    public static String FilePath;
    public static int NEXT_LAYER_ID = 0;

    public static class Config
    {
        public static TileIds selectedtile;
        public static String levelname;
        public static group.Types.Level level = new group.Types.Level();
    }

    public static interactiontype interactiontype = group.Types.interactiontype.Paint;

    public static int currentlayer;

    public static String GetPath()
    {
        JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.dir")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("HIL files (*.HIL)", "HIL","hil");

        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = chooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            return file.getAbsolutePath();
        }

        System.exit(1);
        return null;
    }
}
