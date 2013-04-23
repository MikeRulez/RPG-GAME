package aaa.epic.piraattilahtipeli;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;


    // Kartan mitat tiileillä mitattuna
    public class Collision {

    // Kartan mitat tiileillä mitattuna
    private boolean[][] blocked;
    private TiledMap map;

    public Collision(TiledMap map) throws SlickException {
        this.map = map;
        blocked = new boolean[map.getWidth()][map.getHeight()];
        initializeBlocked();
        sopBlocked();
    }

    public boolean isBlocked(float x, float y) {
        int xBlock = (int) x / map.getTileWidth();
        int yBlock = (int) y / map.getTileHeight();
        return blocked[xBlock][yBlock];
    }

    private void initializeBlocked() {
        for (int l = 0; l < map.getLayerCount(); l++) {
            String layerValue = map.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < map.getWidth(); c++) {
                    for (int r = 0; r < map.getHeight(); r++) {
                        if (map.getTileId(c, r, l) != 0) {
                            blocked[c][r] = true;
                        }
                    }
                }
            }
        }
    }
    
    private void sopBlocked() {
        for (int c = 0; c < map.getWidth(); c++) {
            for (int r = 0; r < map.getHeight(); r++) {
                System.out.println(c + " " + r + " " + blocked[c][r]);
            }
        }
    }
}