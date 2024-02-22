package mygame;

import basicgraphics.Clock;
import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.SpriteSpriteCollisionListener;
import basicgraphics.Task;
import basicgraphics.images.Picture;
import basicgraphics.sounds.ReusableClip;

public class Player extends Sprite {

    private Picture p1 = new Picture("KevinIdle.png");
    static int enemiesKilled;
    public boolean alive;
    public boolean idle;
    public int health;
    public int maxHealth;
    public int dmg;
    final static ReusableClip clip = new ReusableClip("cast.wav");

    public Player(SpriteComponent sc, int x, int y) {
        super(sc);
        alive = true;
        health = 4;
        idle = true;
        maxHealth = health;
        dmg = 3;
        setPicture(p1);
        setX(x);
        setY(y);
        setVelX(0);
        setVelY(0);

        Task t = new Task() {
            @Override
            public void run() { //jumping & gravity
                if (getY() > 295) {
                    setY(295);
                } else {
                    double velY = getVelY() + 0.05;
                    setVelY(velY);
                }
            }
        };
        Clock.addTask(t);
    }

    public int getDMG() {
        return dmg;
    }

    public void incDMG(int x) {
        dmg += x;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int x) {
        health = x;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int x) {
        maxHealth = x;
    }

    public boolean lifeStatus() {
        return alive;
    }

    public void setState(boolean x) {
        idle = x;
    }

    public void setLifeStatus(boolean x) {
        alive = x;
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
