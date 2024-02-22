package mygame;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

public class EProjectile extends Sprite {

    Picture ice = new Picture("Iceball.png");
    public int dmg;

    public EProjectile(SpriteComponent sc, int x, int y) {
        super(sc);
        setPicture(ice);
        dmg = 1;
        setX(x);
        setY(y);
        setVelX(-5);
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
        if (se.xlo) {
            setActive(false);
        }
    }
}
