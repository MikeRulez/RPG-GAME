package aaa.epic.piraattilahtipeli;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

public class Game extends BasicGame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    Image[][] ukonKuvat;
    float x = 400;
    float y = 300;
    float scale = 1;
    Sound liike = null;
    Music musiikki = null;
    TiledMap map; //The file that contain the world we are
    Entity player; //The moving entity we will follow
    Camera camera; //The camera we are going to use
    int mapHeight, mapWidth;
    Image world;
    Collision c;

    public Game() {
        super("");
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {
        world = new Image("data/world.png");
        musiikki = new Music("data/music/saari1.ogg");
        musiikki.loop();
        map = new TiledMap("data/world.tmx");
        mapWidth = map.getWidth() * map.getTileWidth(); // Map size = Tile Size * number of Tiles
        mapHeight = map.getHeight() * map.getTileHeight();
        ukonKuvat = new Image[4][3];
        loadukonKuvat();
        player = new Entity(200, 200, 32, 32, ukonKuvat);
        camera = new Camera(map, mapWidth, mapHeight);
        ukonKuvat = new Image[4][3];
        c = new Collision(map);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {

        player.update(gc, mapWidth, mapHeight, delta, c);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

        camera.translate(g, player);

        map.render(0, 0);
        player.render();
    }

    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setVSync(true);
        app.start();
    }

    private void loadukonKuvat() throws SlickException {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                ukonKuvat[y][x] = new Image("data/Hero_" + y + x + ".png");
            }
        }
    }
}