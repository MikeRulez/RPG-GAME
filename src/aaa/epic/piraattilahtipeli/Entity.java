package aaa.epic.piraattilahtipeli;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Entity {

    protected Vector2f pos; // Vector contains a value with components x &amp; y
    protected Rectangle box;
    protected Image[][] sprites;
    protected Image sprite;
    protected boolean imageSwitch;
    private final static int W = 3;
    private final static int S = 0;
    private final static int A = 1;
    private final static int D = 2;
    private final static int L = 0;
    private final static int R = 2;
    private final static int F = 1;
    protected int direction = 0;
    private final static float SPEED = 0.25f;

    public Entity(float x, float y, int width, int height, Image[][] ukonKuvat) {
        pos = new Vector2f(x, y);
        box = new Rectangle(x, y, width, height);
        sprites = new Image[4][3];
        setSprites(ukonKuvat);
        this.sprite = sprites[S][F];
        imageSwitch = true;
    }

    public void update(GameContainer gc, int mapWidth, int mapHeight, int delta) {

        Vector2f trans = new Vector2f(0, 0);

        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) {
            direction = W;
            trans.y = -SPEED * delta;
            if (imageSwitch) {
                sprite = sprites[W][L];
                imageSwitch = false;
            } else {
                sprite = sprites[W][R];
                imageSwitch = true;
            }
        } else if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) {
            direction = S;
            trans.y = SPEED * delta;
            if (imageSwitch) {
                sprite = sprites[S][L];
                imageSwitch = false;
            } else {
                sprite = sprites[S][R];
                imageSwitch = true;
            }
        } else if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
            direction = D;
            trans.x = SPEED * delta;
            if (imageSwitch) {
                sprite = sprites[D][L];
                imageSwitch = false;
            } else {
                sprite = sprites[D][R];
                imageSwitch = true;
            }
        } else if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
            direction = A;
            trans.x = -SPEED * delta;
            if (imageSwitch) {
                sprite = sprites[A][L];
                imageSwitch = false;
            } else {
                sprite = sprites[A][R];
                imageSwitch = true;
            }
        } else {
            switch (direction) {
                case W:
                    sprite = sprites[W][F];
                    break;
                case S:
                    sprite = sprites[S][F];
                    break;
                case A:
                    sprite = sprites[A][F];
                    break;
                case D:
                    sprite = sprites[D][F];
                    break;
            }
        }



        if (trans.x != 0 && trans.y != 0) {
            trans.set(trans.x / 1.5f, trans.y / 1.5f);
        }
        
        if (pos.x + trans.x > 32 && pos.x + trans.x < (mapWidth - 64)) {
            pos.x += trans.x;
        }

        if (pos.y + trans.y > 32 && pos.y + trans.y < (mapHeight - 64)) {
            pos.y += trans.y;
        }

    }

    public void render() {
        sprite.draw(pos.x, pos.y);
    }

    // Getters and Setters
    public Vector2f getPos() {
        return pos;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public Rectangle getBox() {
        return box;
    }

    public void setBox(Rectangle box) {
        this.box = box;
    }

    public Image getSprite() {
        return sprite;
    }

    private void setSprites(Image[][] ukonKuvat) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(ukonKuvat[i][j]);
            }
        }
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                this.sprites[y][x] = ukonKuvat[y][x];
            }
        }
    }
}