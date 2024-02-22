package mygame;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

public class HealthDisplay extends Sprite {

    private Picture heart;

    public HealthDisplay(SpriteComponent sc, int x) {
        super(sc);
        heart = new Picture("heart.png");
        setPicture(heart);
        setX(x);
        setY(345);
    }
}
