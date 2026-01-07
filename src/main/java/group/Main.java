package group;

import com.raylib.*;
import group.Data.Level;

public class Main {
    public static void main(String[] args) {
        Level.Init();

        Raylib.InitWindow(800,800,"abc");

        while (!Raylib.WindowShouldClose())
        {
            Raylib.BeginDrawing();
            Raylib.EndDrawing();
        }
    }
}