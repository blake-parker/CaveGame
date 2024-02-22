package mygame;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

public class HealingPot extends Sprite {

    public Picture h1 = new Picture("flask.png");
    public int restored;
    public boolean has;
    public static int storedPots = 0;

    public HealingPot(SpriteComponent sc) {
        super(sc);
        setPicture(h1);
        setVelX(0);
        setX(1000);
        setY(250);
        restored = 4;
        has = false;
    }

    public int getHealing() {
        return restored;
    }

    public void setHas(boolean x) {
        has = x;
    }

    @Override
    public void processEvent(SpriteCollisionEvent se) {
        SpriteComponent sc = getSpriteComponent();
        if (se.xlo) {
            setX(sc.getSize().width - getWidth());
        }
    }
}
