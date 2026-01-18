package group.Steam;

import com.codedisaster.steamworks.*;

public class Helpers {
    public static void Init()
    {
        try
        {
            SteamLibraryLoader loader = new SteamLibraryLoader() {
                @Override
                public boolean loadLibrary(String name) {
                    System.loadLibrary(name);
                    return true;
                }
            };

            SteamAPI.loadLibraries(loader);

            if (SteamAPI.init())
            {
                System.err.println("SteamAPI.init() failed");
                return;
            }
            System.out.println("Steam initialized");
        } catch (SteamException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    public static void Shutdown()
    {
        SteamAPI.shutdown();
        return;
    }
}
