package aaa.epic.piraattilahtipeli;

import java.awt.Font;
import org.newdawn.slick.*;
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
    private int w, h;
    private int maxhp, hp;
    private Font hpFont;
    private TrueTypeFont hpTTF;
    private Color hpVari = new Color(255, 255, 255);

    public Entity(float x, float y, int width, int height, Image[][] ukonKuvat, int maxhp) {
        pos = new Vector2f(x, y);
        box = new Rectangle(x, y, width, height);
        sprites = new Image[4][3];
        setSprites(ukonKuvat);
        this.sprite = sprites[S][F];
        imageSwitch = true;
        h = sprite.getHeight();
        w = sprite.getWidth();
        this.maxhp = maxhp;
        hp = maxhp;
        hpFont = new Font("Verdana", Font.PLAIN, 10);
        hpTTF = new TrueTypeFont(hpFont, true);
    }

    public void update(GameContainer gc, int mapWidth, int mapHeight, int delta, Collision c) {

        Vector2f trans = new Vector2f(0, 0);

        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) {
            if (!c.isBlocked(pos.x + 5, pos.y - SPEED * delta) && !c.isBlocked(pos.x + w - 5, pos.y - SPEED * delta)){
            trans.y = -SPEED * delta;
            }
            direction = W;
            if (imageSwitch) {
                sprite = sprites[W][L];
                imageSwitch = false;
            } else {
                sprite = sprites[W][R];
                imageSwitch = true;
            }
        } else if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) {
            if (!c.isBlocked(pos.x + 5, pos.y + h + SPEED * delta) && !c.isBlocked(pos.x + w - 5, pos.y + h + SPEED * delta)){
            trans.y = SPEED * delta;
            }
            direction = S;
            if (imageSwitch) {
                sprite = sprites[S][L];
                imageSwitch = false;
            } else {
                sprite = sprites[S][R];
                imageSwitch = true;
            }
        } else if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
            if (!c.isBlocked(pos.x + SPEED * delta + w, pos.y + 5 ) && !c.isBlocked(pos.x + w + SPEED * delta, pos.y + h - 5)){
            trans.x = SPEED * delta;
            }
            direction = D;
            if (imageSwitch) {
                sprite = sprites[D][L];
                imageSwitch = false;
            } else {
                sprite = sprites[D][R];
                imageSwitch = true;
            }
        } else if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
            if (!c.isBlocked(pos.x - SPEED * delta, pos.y + 5 ) && !c.isBlocked(pos.x - SPEED * delta, pos.y + h - 5)){
            trans.x = -SPEED * delta;
            }
            direction = A;
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
        String h = hp + "/" + maxhp;
        hpTTF.drawString(pos.x, pos.y - 12, h, hpVari);
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
    
    public int getHP() {
        return hp;
    }
    
    public int getMaxHP() {
        return maxhp;
    }

    private void setSprites(Image[][] ukonKuvat) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                this.sprites[y][x] = ukonKuvat[y][x];
            }
        }
    }
 
    
}