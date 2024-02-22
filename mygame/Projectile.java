package mygame;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

public class Projectile extends Sprite {

    private Picture fireball;
    public static int dmg;

    public Projectile(SpriteComponent sc, int x, int y, int d) {
        super(sc);
        fireball = new Picture("Fireball.png");
        dmg = d;
        setPicture(fireball);
        setX(x);
        setY(y);
        setVelX(5);
    }

    public int getDMG() {
        return dmg;
    }

    public void incDMG(int x) {
        dmg += x;
    }

    @Override
    public void processEvent(SpriteCollisionEvent se) {
        SpriteComponent sc = getSpriteComponent();
        if (se.xhi) {
            setActive(false);
        }
    }

}
