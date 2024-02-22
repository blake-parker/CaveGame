package mygame;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

public class Enemy extends Sprite {

    static int enemyCount;
    public boolean dead;
    public int health;
    public int dmg;

    public Enemy(SpriteComponent sc, int x, int y) {
        super(sc);
        enemyCount++;
        dmg = 2;
        health = 0;
        dead = false;
        Picture e1 = new Picture("KevinE1.png");
        setPicture(e1);
        setX(x);
        setY(y);
    }

    public void setDMG(int x) {
        dmg = x;
    }

    public int getDMG() {
        return dmg;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int x) {
        health = x;
    }

    public void setState(boolean x) {
        dead = x;
    }

    public boolean getState() {
        return dead;
    }

    public int takeDamage(int x) {
        health -= x;
        return health;
    }

    @Override
    public void processEvent(SpriteCollisionEvent se) {
        SpriteComponent sc = getSpriteComponent();
        if (se.xlo) {
            setX(sc.getSize().width - getWidth());
        }
    }
}
