package mygame;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

public class PowerUp extends Sprite {

    private Picture base;
    public int dmgInc = 3;

    public PowerUp(SpriteComponent sc) {
        super(sc);
        base = new Picture("powerup.png");
        setPicture(base);
        setX(500);
        setY(getRandomInt(250));
    }

    public int getDMGInc() {
        return dmgInc;
    }

    public int getRandomInt(int x) {
        return (int) Math.ceil(Math.random() * x);
    }
}
