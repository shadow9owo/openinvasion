package group.Types;

import java.util.ArrayList;
import java.util.List;

import group.Types.Tile;

public class Layer {
    public List<Tile> tiles;
    public int layerid;

    public Layer() {
        this.tiles = new ArrayList<>();
        this.layerid = -1;
    }

    public Layer(List<Tile> tiles, int layerid) {
        this.tiles = (tiles != null) ? new ArrayList<>(tiles) : new ArrayList<>();
        this.layerid = layerid;
    }
}
