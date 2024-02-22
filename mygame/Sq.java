package mygame;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

public class Sq extends Sprite {
Picture gSq = new Picture("graySq.png");

    public Sq(SpriteComponent sc) {
        super(sc);
        setPicture(gSq);
        setX(600);
        setY(265);
    }
}
