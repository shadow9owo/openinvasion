package group.Types;

import java.util.ArrayList;
import java.util.List;

import group.Types.Tile;

public class Layer {
    public List<Tile> tiles;
    public int layerid;

    public Layer(List<Tile> tiles,int layer)
    {
        tiles = new ArrayList<Tile>();
        this.tiles = tiles;
        this.layerid = layer;
    }
}
