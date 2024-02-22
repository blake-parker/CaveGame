package mygame;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

public class Obstacle extends Sprite {

    private Picture spike;
    private int wallCounter;

    public Obstacle(SpriteComponent sc, int x, int y) {
        super(sc);
        spike = new Picture("spike.png");
        setPicture(spike);
        setX(x);
        setY(y);
        setVelX(0);
    }

    @Override
    public void processEvent(SpriteCollisionEvent se) {
        SpriteComponent sc = getSpriteComponent();
        if (se.xlo) {
            wallCounter += 1;
            setX(sc.getSize().width - getWidth());
            if (wallCounter == 10) {
                return;
            }
        }
    }
;
}
