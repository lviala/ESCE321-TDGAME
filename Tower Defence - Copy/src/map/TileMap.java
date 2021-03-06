package map;

import map.path.PathTile;
import org.newdawn.slick.Image;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 1/9/2015 - 5:06 PM
 */

public class TileMap {

    private Tile [][] tiles;
    private PathTile start_tile;
    private PathTile end_tile;
    private int tile_size;


    public TileMap(Image png_map){

        tiles = MapGenerator.generate(png_map); ///< Generates tiles from png file
        start_tile = MapGenerator.setStart(tiles); ///< Sets the start tile variable
        MapGenerator.linkPath(tiles, start_tile); ///< Assigns the last and next variable in every PathTile
        end_tile = MapGenerator.setEnd(tiles); ///< Sets end tile variable

        tile_size = tiles[0][0].getTexture().getWidth(); ///< Sets the pixel size of tiles
    }

    /// Renders map to buffer ///
    public void render(){
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[0].length; j++){
                tiles[i][j].draw();
            }
        }
    }

    /// Returns tile at specified pixel x and y ///
    public Tile getTile(float x, float y){
        int i = (int) (x / tile_size);
        int j = (int) (y / tile_size);

        if (i < tiles.length && j < tiles[0].length){
            return tiles[i][j];
        }

        else throw new RuntimeException("TILE INVALID");
    }

    /// Getters for start and end tiles ///
    public PathTile getStartTile() { return start_tile; }
    public PathTile getEndTile() { return end_tile; }

    /// Setter for map editor ///
    public void setTile(int i, int j, Tile tile){
        tiles[i][j] = tile;
    }

    /// Return true if point is within the map
    public boolean withinMap(vec2 position) {
        if (position.x > 0 && position.x < (tiles.length * tile_size)){
            if (position.y > 0 && position.y < (tiles[0].length * tile_size)){
                return true;
            }
        }

        return false;
    }
}
