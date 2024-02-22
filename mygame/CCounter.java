package mygame;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

public class CCounter extends Sprite {

    Picture intCount = new Picture("coin0.png");

    public CCounter(SpriteComponent sc) {
        super(sc);
        setPicture(intCount);
        setX(650);
        setY(300);
        setVelX(0);
        setVelY(0);
    }
}
