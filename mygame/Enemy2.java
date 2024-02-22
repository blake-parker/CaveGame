package mygame;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import static mygame.Enemy.enemyCount;

public class Enemy2 extends Sprite {

    public int dmg;
    public int health;
    public boolean dead;
    Picture pic = new Picture("KevinE2.png");

    public Enemy2(SpriteComponent sc, int x, int y) {
        super(sc);
        enemyCount++;
        dmg = 3;
        setPicture(pic);
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
