package mygame;

import basicgraphics.Clock;
import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;
import basicgraphics.images.Picture;

public class Torch extends Sprite {

    public Picture pic1 = new Picture("torch1.png");
    public Picture pic2 = new Picture("torch2.png");
    public Picture pic3 = new Picture("torch3.png");
    public Picture pic4 = new Picture("torch4.png");
    public Picture pic5 = new Picture("torch5.png");
    public static int timer;
    public Torch(SpriteComponent sc, int x) {
        super(sc);
        setX(x);
        setY(200);
        setVelX(0);

        Task t = new Task() {
            @Override
            public void run() {
                switch (timer % 12) {
                    case 0:
                        setPicture(pic1);
                        break;
                    case 1:
                        setPicture(pic2);
                        break;
                    case 2:
                        setPicture(pic3);
                        break;
                    case 3:
                        setPicture(pic4);
                        break;
                    case 4:
                        setPicture(pic5);
                        break;
                }
                timer++;
            }
        };
        Clock.addTask(t);
    }
    @Override
    public void processEvent(SpriteCollisionEvent se) {
        SpriteComponent sc = getSpriteComponent();
        if (se.xlo) {
            setX(sc.getSize().width - getWidth());
        }
        if (se.xhi) {
            setX(0);
        }
    }
}
