package mygame;

import basicgraphics.Clock;
import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;
import basicgraphics.images.Picture;
import basicgraphics.sounds.ReusableClip;

//boss spawns, shoots a random number of projectiles (between 5 and 10)
//then will sit idle and let the player get damage
//once player deals a certain amount of damage, boss will lunge forward
//and go back to original position
//player needs to dodge, repeat this until dead, once dead player wins (maybe add crown and happy jumping animation)
public class Boss extends Sprite {

    public int attack;
    public boolean healing;
    public int health;
    public Picture idle = new Picture("boss.png");
    public Picture damaged = new Picture("t1.png");
    public Picture useAttack = new Picture("t1.png");
    public Picture death = new Picture("t1.png");
    final static ReusableClip clip = new ReusableClip("shock.wav");

    public Boss(SpriteComponent sc, int y) {
        super(sc);
        int initY = y;
        setPicture(idle);
        attack = 12;
        health = 120;
        setY(y);
        healing = false;

        Task t = new Task() {
            @Override
            public void run() { //stopping the boss to start attacks
                if (getX() < 600) {
                    setX(600);
                    setVelX(0);
                    setVelY(0.5);
                } else if (getX() == 600 && getY() == 225) {
                    setVelY(-0.5);
                }
            }
        };
        Clock.addTask(t);
    }

    public int getAttack() {
        setPicture(useAttack);
        return attack;
    }

    public boolean isHealing() {
        return healing;
    }

    public int getHealth() {
        return health;
    }

    public void setAttack(int x) {
        attack = x;
    }

    public void setStatus(boolean x) {
        healing = x;
    }

    public void setHealth(int x) {
        health = x;
    }

    @Override
    public void processEvent(SpriteCollisionEvent se) {
        SpriteComponent sc = getSpriteComponent();
        if (se.ylo) {
            setVelY(0.5);
        }
    }
}
