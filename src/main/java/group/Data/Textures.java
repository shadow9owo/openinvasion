package group.Data;

import group.Types.TileIds;
import com.raylib.java.textures.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.Hashtable;

import group.Main;

public class Textures {

    static Texture2D spritesheet2;
    static Texture2D spritesheet1;
    public static Dictionary<TileIds, Texture2D> text = new Hashtable<>();

    public static Texture2D GetTexture(TileIds id)
    {
        return text.get(id);
    }

    static Texture2D loadTextureFromJar(String path) {
        try (InputStream in = Main.class.getResourceAsStream(path)) {
            if (in == null)
                throw new RuntimeException("Texture not found: " + path);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int r;
            while ((r = in.read(buf)) != -1) {
                out.write(buf, 0, r);
            }

            byte[] data = out.toByteArray();

            Image img =
                    Main.rlj.textures.LoadImageFromMemory(".png", data, data.length);
            return Main.rlj.textures.LoadTextureFromImage(img);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Init()
    {
        spritesheet2 = loadTextureFromJar("/spritesheet2.png");
        spritesheet1 = loadTextureFromJar("/spritesheet1.png");
        for (int i = 0; i < TileIds.values().length; i++) {
            if (i > 100)
            {
                text.put(TileIds.values()[i],spritesheet2);
            }else //dogshit
            {
                text.put(TileIds.values()[i],spritesheet1);
            }
        }
        return;
    }
}
