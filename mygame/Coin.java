package mygame;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import basicgraphics.sounds.ReusableClip;

public class Coin extends Sprite {

    Picture coin = new Picture("coin.png");
    static int count;
    ReusableClip pickup = new ReusableClip("coin.wav");

    public Coin(SpriteComponent sc, int x, int y) {
        super(sc);
        setPicture(coin);
        setVelX(0); //-0.3 to move
        setX(x);
        setY(y);
    }
}
