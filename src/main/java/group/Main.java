package group;

import com.raylib.*;

public class Main {
    public static void main(String[] args) {
        Raylib.InitWindow(800,800,"abc");

        while (!Raylib.WindowShouldClose())
        {
            Raylib.BeginDrawing();
            Raylib.EndDrawing();
        }
    }
}