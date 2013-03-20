package aaa.epic.piraattilahtipeli;

import org.newdawn.slick.*;

public class Game extends BasicGame {

    Image ukko = null;
    Image land = null;
    float x = 400;
    float y = 300;
    float scale = 1;
    Sound liike = null;
    Music musiikki = null;
    int WORLD_SIZE_X = 1600;
    int WORLD_SIZE_Y = 1200;
    int VIEWPORT_SIZE_X = 800;
    int VIEWPORT_SIZE_Y = 600;
    float camX = x - VIEWPORT_SIZE_X / 2;
    float camY = y - VIEWPORT_SIZE_Y / 2;
    float offsetMaxX = WORLD_SIZE_X - VIEWPORT_SIZE_X;
    float offsetMaxY = WORLD_SIZE_Y - VIEWPORT_SIZE_Y;
    float offsetMinX = 0;
    float offsetMinY = 0;

    public Game() {
        super("Piraattilahti - return of the Pirates!");
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {
        ukko = new Image("data/Ukko.png");
        land = new Image("data/land.jpg");
        liike = new Sound("data/engine2.ogg");
        musiikki = new Music("data/music.ogg");
        musiikki.play();
        musiikki.loop();
    }

    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
            x -= 2f;
        } else if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
            x += 2f;
        } else if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
            y += 2f;
        } else if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
            y -= 2f;
        }

        if (this.camX > this.offsetMaxX) {
            camX = offsetMaxX;
        } else if (camX < offsetMinX) {
            camX = offsetMinX;
        }

        if (camY > offsetMaxY) {
            camY = offsetMaxY;
        } else if (camY < offsetMinY) {
            camY = offsetMinY;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g)
            throws SlickException {
        land.draw(0, 0);
        ukko.draw(x, y, scale);
        g.translate(camX, camY);

    }

    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(800, 600, false);
        app.start();
    }
}