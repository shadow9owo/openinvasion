package group.Types;

import group.Types.TileIds;

public class Tile {
    public TileIds id = TileIds.placeholdertexture;
    public int x, y, w, h;

    public Tile() {
    }

    public Tile(int x, int y, int w, int h,TileIds id) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.id = id;
    }
}

