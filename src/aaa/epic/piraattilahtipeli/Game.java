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

        if (input.isKeyDown(Input.KEY_A)) {
            ukko.rotate(-0.3f * delta);
        }

        if (input.isKeyDown(Input.KEY_D)) {
            ukko.rotate(0.3f * delta);
        }
        
        if (input.isKeyDown(Input.KEY_W)) {
            float hip = 0.4f * delta;
            liike.play(0.1f, 1.0f);

            float rotation = ukko.getRotation();

            x += hip * Math.sin(Math.toRadians(rotation));
            y -= hip * Math.cos(Math.toRadians(rotation));
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g)
            throws SlickException {
        land.draw(0, 0);

        ukko.draw(x, y, scale);

    }

    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app =
                new AppGameContainer(new Game());

        app.setDisplayMode(800, 600, false);
        app.start();
    }
}