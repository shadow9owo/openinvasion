package group.Data;

import group.Types.TileIds;
import group.Types.Layer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class CurrentData {
    public static String FilePath;

    public class Config
    {
        public static TileIds selectedtile;
    }

    public static int currentlayer;
    public static Layer[] layers = new Layer[1024];

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
