package aaa.epic.piraattilahtipeli;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Collision {

    // Kartan mitat tiileillä mitattuna
    private static final int NUMBEROFTILESINAROW = 10;
    private static final int NUMBEROFTILESINACOLUMN = 10;
    private static final int NUMBEROFLAYERS = 2;
    private boolean[][] blocked;
    private TiledMap map;

    public Collision(TiledMap map) throws SlickException {
        this.map = map;
        blocked = new boolean[NUMBEROFTILESINAROW][NUMBEROFTILESINACOLUMN];
        initializeBlocked();
    }

    public boolean isBlocked(float x, float y) {
        int xBlock = (int) x / map.getTileWidth();
        int yBlock = (int) y / map.getTileHeight();
        return blocked[xBlock][yBlock];
    }

    private void initializeBlocked() {
        for (int l = 0; l < NUMBEROFLAYERS; l++) {
            String layerValue = map.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < NUMBEROFTILESINACOLUMN; c++) {
                    for (int r = 0; r < NUMBEROFTILESINAROW; r++) {
                        if (map.getTileId(c, r, l) != 0) {
                            blocked[c][r] = true;
                        }
                    }
                }
            }
        }
    }
}