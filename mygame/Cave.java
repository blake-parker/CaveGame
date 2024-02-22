package mygame;

import basicgraphics.BasicContainer;
import basicgraphics.BasicFrame;
import basicgraphics.Clock;
import basicgraphics.SpriteComponent;
import basicgraphics.SpriteSpriteCollisionListener;
import basicgraphics.Task;
import basicgraphics.images.Picture;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static mygame.Coin.count;
import static mygame.Enemy.enemyCount;
import static mygame.HealingPot.storedPots;
import static mygame.Player.enemiesKilled;

public class Cave {

    public static Picture blank = new Picture("blank.png");
    public static Picture heart = new Picture("heart.png");

    public static int getRandomInt(int x) {
        return (int) Math.ceil(Math.random() * x);
    }

    public static int totalHealth = 4;
    public static void main(String[] args) {
        final BasicFrame bf = new BasicFrame("Kevin The Cube");
        final Container content = bf.getContentPane();
        final CardLayout cards = new CardLayout();
        content.setLayout(cards);
        BasicContainer bc1 = new BasicContainer();
        content.add(bc1, "Title");
        final BasicContainer bc2 = new BasicContainer();
        content.add(bc2, "Game");
        final BasicContainer bc3 = new BasicContainer();
        content.add(bc3, "Game 2");
        final SpriteComponent sc = new SpriteComponent() {
            @Override
            public void paintBackground(Graphics g) {
                Dimension d = getSize();
                g.setColor(Color.darkGray);
                g.fillRect(0, 0, d.width, d.height);
                g.setColor(Color.GRAY);
                g.fillRect(0, 325, d.width, d.height);
            }
        };
        sc.setPreferredSize(new Dimension(800, 400));
        String[][] splashLayout = {
            {"Title"},
            {"Credits"},
            {"Button"}
        };
        bc1.setStringLayout(splashLayout);
        bc1.add("Title", new JLabel("Kevin The Cube"));
        bc1.add("Credits", new JLabel("Developed by Blake Parker"));
        JButton jstart = new JButton("Begin");
        jstart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "Game");
                bc2.requestFocus();
                Clock.start(10);
            }
        });
        bc1.add("Button", jstart);
        String[][] layout = {
            {"Cave"}
        };
        bc2.setStringLayout(layout);
        bc2.add("Cave", sc);    

        Torch t = new Torch(sc, 150);
        Torch t2 = new Torch(sc, 300);
        Torch t3 = new Torch(sc, 450);
        Torch t4 = new Torch(sc, 600);      
        t.setVelX(-0.5);
        t2.setVelX(-0.5);
        t3.setVelX(-0.5);
        t4.setVelX(-0.5);

        Picture damaged = new Picture("KevinDamaged.png");
        CCounter cc = new CCounter(sc);
        int cStartX = 400;
        int cStartY = 100;
        Coin c1 = new Coin(sc, cStartX, cStartY - 20);
        Coin c2 = new Coin(sc, cStartX + 20, cStartY - 20);
        Coin c3 = new Coin(sc, cStartX + 40, cStartY - 20);
        Coin c4 = new Coin(sc, cStartX + 20, cStartY - 40);
        Coin c5 = new Coin(sc, cStartX + 40, cStartY - 40);
        Coin c6 = new Coin(sc, cStartX + 20, cStartY);
        Coin c7 = new Coin(sc, cStartX + 40, cStartY);
        Coin c8 = new Coin(sc, cStartX + 60, cStartY - 20);
        ArrayList<Coin> cGroup1 = new ArrayList<>();
        cGroup1.add(c1);
        cGroup1.add(c2);
        cGroup1.add(c3);
        cGroup1.add(c4);
        cGroup1.add(c5);
        cGroup1.add(c6);
        cGroup1.add(c7);
        cGroup1.add(c8);

        int cStartX2 = 600;
        int cStartY2 = 100;
        Coin c9 = new Coin(sc, cStartX2, cStartY2 - 20);
        Coin c10 = new Coin(sc, cStartX2 + 20, cStartY2 - 20);
        Coin c11 = new Coin(sc, cStartX2 + 40, cStartY2 - 20);
        Coin c12 = new Coin(sc, cStartX2 + 20, cStartY2 - 40);
        Coin c13 = new Coin(sc, cStartX2 + 40, cStartY2 - 40);
        Coin c14 = new Coin(sc, cStartX2 + 20, cStartY2);
        ArrayList<Coin> cGroup2 = new ArrayList<>();
        cGroup2.add(c9);
        cGroup2.add(c10);
        cGroup2.add(c11);
        cGroup2.add(c12);
        cGroup2.add(c13);
        cGroup2.add(c14);

        int cStartX3 = 600;
        int cStartY3 = 100;
        Coin c15 = new Coin(sc, cStartX, cStartY - 20);
        Coin c16 = new Coin(sc, cStartX + 20, cStartY - 20);
        Coin c17 = new Coin(sc, cStartX + 40, cStartY - 20);
        Coin c18 = new Coin(sc, cStartX + 20, cStartY - 40);
        Coin c19 = new Coin(sc, cStartX + 40, cStartY - 40);
        Coin c20 = new Coin(sc, cStartX + 20, cStartY);
        ArrayList<Coin> cGroup3 = new ArrayList<>();
        cGroup3.add(c15);
        cGroup3.add(c16);
        cGroup3.add(c17);
        cGroup3.add(c18);
        cGroup3.add(c19);
        cGroup3.add(c20);

        HealingPot h1 = new HealingPot(sc); //healing pot. to spawn every 2 enemies killed.
        HealingPot h2 = new HealingPot(sc);
        HealingPot h3 = new HealingPot(sc);
        Player p = new Player(sc, 30, 295); //spawning the player character at a set location
        ArrayList<Obstacle> obstacles = new ArrayList<>(); //array list containing obstacles, for now just spikes.
        int spYGR = 295;
        for (int i = 0; i < 10; i++) {
            obstacles.add(new Obstacle(sc, 250 + (20 * i), spYGR));
        }

        ArrayList<Obstacle> obstacles2 = new ArrayList<>();
        int spYGR2 = 295;
        for (int i = 0; i < 10; i++) {
            obstacles2.add(new Obstacle(sc, 250 + (20 * i), spYGR2));
        }

        ArrayList<Enemy> wave1 = new ArrayList<>(); //first wave of enemies being created
        for (int i = 0; i < 4; i++) {
            Enemy en = new Enemy(sc, 500, getRandomInt(250));
            wave1.add(en);
        }

        ArrayList<Enemy2> wave2 = new ArrayList<>(); //first wave of enemies being created
        for (int i = 0; i < 4; i++) {
            Enemy2 en = new Enemy2(sc, 500, getRandomInt(250));
            wave2.add(en);
        }

        ArrayList<Enemy3> wave3 = new ArrayList<>(); //first wave of enemies being created
        for (int i = 0; i < 4; i++) {
            Enemy3 en = new Enemy3(sc, 500, getRandomInt(250));
            wave3.add(en);
        }

        jstart.addActionListener(new ActionListener() { //moves obstacles when you press begin rather than before, fixed bug for spikes killing you before game start
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < obstacles.size(); i++) {
                    obstacles.get(i).setVelX(-1);
                }
                if (enemiesKilled == 0) { //12 enemies total, need initialize moving to make game work
                    wave1.get(0).setVelX(-1);
                    wave1.get(1).setVelX(-1);
                }
            }
        });

        ArrayList<HealthDisplay> hIcons = new ArrayList<>(); //creating health display icons
        for (int i = 0; i < p.getHealth(); i++) {
            hIcons.add(new HealthDisplay(sc, 30 + (i * 15)));
        }
        Picture kFullH = new Picture("KevinIdle.png");
        KeyAdapter kp = new KeyAdapter() { //listening to key presses
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) { //remove b4 submission
                    p.setVelX(5);
                    p.setState(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) { //remove b4 submission
                    p.setVelX(-5);
                    p.setState(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    p.setVelY(-5);
                    //p.setPicture("t1.png");
                    p.setState(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_H && storedPots > 0 && p.getHealth() != p.getMaxHealth()) {
                    p.setPicture(kFullH);
                    p.setHealth(p.getMaxHealth());
                    if (storedPots == 3) {
                        h3.setActive(false);
                    } else if (storedPots == 2) {
                        h2.setActive(false);
                    } else if(storedPots == 1) {
                        h1.setActive(false);
                    }
                    storedPots--;
                    for (int i = 0; i < hIcons.size(); i++) {
                        hIcons.get(i).setPicture(heart);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                p.setVelX(0);
                p.setVelY(0);
            }
        };
        bc2.addKeyListener(kp);

        Picture kAttk = new Picture("KevinShoot.png");
        Picture kIdle = new Picture("KevinIdle.png");
        MouseAdapter ma = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent me) {
                p.setPicture(kAttk);
                p.clip.play();
                new Projectile(sc, (int) p.getX(), (int) p.getY(), p.getDMG());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                p.setPicture(kIdle);
            }
        };
        sc.addMouseListener(ma);

        Picture dead = new Picture("KevinDead.png");
        sc.addSpriteSpriteCollisionListener(Player.class, Obstacle.class, new SpriteSpriteCollisionListener<Player, Obstacle>() {
            @Override
            public void collision(Player sp1, Obstacle sp2) {
                if (sp1.getHealth() > 0) {
                    hIcons.get(sp1.getHealth() - 1).setPicture(blank);
                    hIcons.get((sp1.getHealth() - 1) - 1).setPicture(blank);
                }
                sp1.setHealth(sp1.getHealth() - 2);
                sp1.setVelX(0);
                sp1.setPicture(damaged);
                if (sp1.getHealth() == 0) {
                    sp1.setPicture(dead);
                    JOptionPane.showMessageDialog(sc, "You lose! You had a score of " + count + "/20");
                    System.exit(0);
                }
            }
        });

        sc.addSpriteSpriteCollisionListener(Player.class, Enemy.class, new SpriteSpriteCollisionListener<Player, Enemy>() {
            @Override
            public void collision(Player sp1, Enemy sp2) {
                hIcons.get(sp1.getHealth() - 1).setPicture(blank);
                hIcons.get((sp1.getHealth() - 1) - 1).setPicture(blank);
                totalHealth -= 2;
                sp1.setHealth(sp1.getHealth() - sp2.getDMG());
                sp2.setActive(false);
                sp1.setPicture(damaged);
                enemyCount--;
                enemiesKilled++;
                if (enemiesKilled == 2) {
                    h1.setVelX(-2);
                    wave1.get(2).setVelX(-2);
                    wave1.get(3).setVelX(-2);
                }
                if (enemiesKilled == 4) {
                    HealingPot h2 = new HealingPot(sc);
                    h2.setVelX(-2);
                    wave2.get(0).setVelX(-2);
                    wave2.get(1).setVelX(-2);
                }
                if (enemiesKilled == 6) {
                    wave2.get(2).setVelX(-2);
                    wave2.get(3).setVelX(-2);
                }
                if (enemiesKilled == 8) {
                    wave3.get(0).setVelX(-2);
                    wave3.get(1).setVelX(-2);
                }
                if (enemiesKilled == 10) {
                    wave3.get(2).setVelX(-2);
                    wave3.get(3).setVelX(-2);
                }
                if (enemiesKilled == 12) {
                    Boss b = new Boss(sc, getRandomInt(200));
                    b.setX(800);
                    b.setVelX(-0.5);
                    if (b.getX() < 700) {
                        b.setVelX(0);
                    }
                }
                if (enemiesKilled % 4 == 0) {
                    PowerUp pUP = new PowerUp(sc);
                    pUP.setVelX(-1);
                }
                if (sp1.getHealth() == 0) {
                    sp1.setPicture(dead);
                    JOptionPane.showMessageDialog(sc, "You lose! You had a score of " + count + "/20");
                    System.exit(0);
                }

            }
        });

        sc.addSpriteSpriteCollisionListener(Player.class, Enemy2.class, new SpriteSpriteCollisionListener<Player, Enemy2>() {
            @Override
            public void collision(Player sp1, Enemy2 sp2) {
                hIcons.get(sp1.getHealth() - 1).setPicture(blank);
                hIcons.get((sp1.getHealth() - 1) - 1).setPicture(blank);
                hIcons.get((sp1.getHealth() - 1) - 2).setPicture(blank);
                totalHealth -= 3;
                sp1.setHealth(sp1.getHealth() - sp2.getDMG());
                sp1.setPicture(damaged);
                sp2.setActive(false);
                enemyCount--;
                enemiesKilled++;
                if (enemiesKilled == 6) {
                    wave2.get(2).setVelX(-2);
                    wave2.get(3).setVelX(-2);
                }
                if (enemiesKilled == 8) {
                    wave3.get(0).setVelX(-2);
                    wave3.get(1).setVelX(-2);
                }
                if (enemiesKilled == 10) {
                    wave3.get(2).setVelX(-2);
                    wave3.get(3).setVelX(-2);
                }
                if (enemiesKilled == 12) {
                    Boss b = new Boss(sc, getRandomInt(200));
                    b.setX(800);
                    b.setVelX(-0.5);
                    if (b.getX() < 700) {
                        b.setVelX(0);
                    }
                }
                if (enemiesKilled % 4 == 0) {
                    PowerUp pUP = new PowerUp(sc);
                    pUP.setVelX(-1);
                }
                if (sp1.getHealth() == 0) {
                    sp1.setPicture(dead);
                    JOptionPane.showMessageDialog(sc, "You lose! You had a score of " + count + "/20");
                    System.exit(0);
                }

            }
        });

        sc.addSpriteSpriteCollisionListener(Player.class, Enemy3.class, new SpriteSpriteCollisionListener<Player, Enemy3>() {
            @Override
            public void collision(Player sp1, Enemy3 sp2) {
                hIcons.get(sp1.getHealth() - 1).setPicture(blank);
                hIcons.get((sp1.getHealth() - 1) - 1).setPicture(blank);
                hIcons.get((sp1.getHealth() - 1) - 2).setPicture(blank);
                hIcons.get((sp1.getHealth() - 1) - 3).setPicture(blank);
                totalHealth = 0;
                sp1.setHealth(sp1.getHealth() - sp2.getDMG());
                sp2.setActive(false);
                sp1.setPicture(damaged);
                enemyCount--;
                enemiesKilled++;
                if (enemiesKilled == 10) {
                    wave3.get(2).setVelX(-2);
                    wave3.get(3).setVelX(-2);
                }
                if (enemiesKilled == 12) {
                    Boss b = new Boss(sc, getRandomInt(200));
                    b.setX(800);
                    b.setVelX(-0.5);
                    if (b.getX() < 700) {
                        b.setVelX(0);
                    }
                }
                if (enemiesKilled % 4 == 0) {
                    PowerUp pUP = new PowerUp(sc);
                    pUP.setVelX(-1);
                }
                if (sp1.getHealth() == 0) {
                    sp1.setPicture(dead);
                    JOptionPane.showMessageDialog(sc, "You lose! You had a score of " + count + "/20");
                    System.exit(0);
                }

            }
        });

        sc.addSpriteSpriteCollisionListener(Projectile.class, Enemy.class, new SpriteSpriteCollisionListener<Projectile, Enemy>() {
            @Override
            public void collision(Projectile sp1, Enemy sp2) {
                sp2.takeDamage(sp1.getDMG());
                if (sp2.getHealth() <= 0) {
                    enemyCount--;
                    enemiesKilled++;
                    sp2.setActive(false);
                    sp1.setActive(false);
                    if (enemiesKilled == 2) {
                        h1.setVelX(-2);
                        wave1.get(2).setVelX(-2);
                        wave1.get(3).setVelX(-2);
                    }
                    if (enemiesKilled == 4) {
                        wave2.get(0).setVelX(-2);
                        wave2.get(1).setVelX(-2);
                    }
                    if (enemiesKilled == 6) {
                        h2.setVelX(-2);
                        wave2.get(2).setVelX(-2);
                        wave2.get(3).setVelX(-2);
                    }
                    if (enemiesKilled == 8) {
                        wave3.get(0).setVelX(-2);
                        wave3.get(1).setVelX(-2);
                    }
                    if (enemiesKilled == 10) {
                        wave3.get(2).setVelX(-2);
                        wave3.get(3).setVelX(-2);
                    }
                    if (enemiesKilled == 12) {
                        Boss b = new Boss(sc, getRandomInt(200));
                        b.setX(800);
                        b.setVelX(-0.5);
                        if (b.getX() < 700) {
                            b.setVelX(0);
                        }
                    }
                    if (enemiesKilled % 4 == 0) {
                        PowerUp pUP = new PowerUp(sc);
                        pUP.setVelX(-1);
                    }
                }
            }
        });

        sc.addSpriteSpriteCollisionListener(Projectile.class, Enemy2.class, new SpriteSpriteCollisionListener<Projectile, Enemy2>() {
            @Override
            public void collision(Projectile sp1, Enemy2 sp2) {
                sp2.takeDamage(sp1.getDMG());
                if (sp2.getHealth() <= 0) {
                    enemyCount--;
                    enemiesKilled++;
                    sp2.setActive(false);
                    sp1.setActive(false);
                    if (enemiesKilled == 6) {
                        HealingPot h2 = new HealingPot(sc);
                        h2.setVelX(-2);
                        wave2.get(2).setVelX(-2);
                        wave2.get(3).setVelX(-2);
                    }
                    if (enemiesKilled == 8) {
                        wave3.get(0).setVelX(-2);
                        wave3.get(1).setVelX(-2);
                    }
                    if (enemiesKilled == 10) {
                        wave3.get(2).setVelX(-2);
                        wave3.get(3).setVelX(-2);
                    }
                    if (enemiesKilled == 12) {
                        Boss b = new Boss(sc, getRandomInt(200));
                        b.setX(800);
                        b.setVelX(-0.5);
                        if (b.getX() < 700) {
                            b.setVelX(0);
                        }
                    }
                    if (enemiesKilled % 4 == 0) {
                        PowerUp pUP = new PowerUp(sc);
                        pUP.setVelX(-1);
                    }
                }
            }
        });

        sc.addSpriteSpriteCollisionListener(Projectile.class, Enemy3.class, new SpriteSpriteCollisionListener<Projectile, Enemy3>() {
            @Override
            public void collision(Projectile sp1, Enemy3 sp2) {
                sp2.takeDamage(sp1.getDMG());
                if (sp2.getHealth() <= 0) {
                    enemyCount--;
                    enemiesKilled++;
                    sp2.setActive(false);
                    sp1.setActive(false);
                    if (enemiesKilled == 10) {
                        h3.setVelX(-2);
                        wave3.get(2).setVelX(-2);
                        wave3.get(3).setVelX(-2);
                    }
                    if (enemiesKilled == 12) {
                        Boss b = new Boss(sc, getRandomInt(200));
                        b.setX(800);
                        b.setVelX(-0.5);
                        if (b.getX() < 700) {
                            b.setVelX(0);
                        }
                    }
                    if (enemiesKilled % 4 == 0) {
                        PowerUp pUP = new PowerUp(sc);
                        pUP.setVelX(-1);
                    }
                }
            }
        });

        sc.addSpriteSpriteCollisionListener(Player.class, PowerUp.class, new SpriteSpriteCollisionListener<Player, PowerUp>() {
            @Override
            public void collision(Player sp1, PowerUp sp2) {
                for (int i = 0; i < obstacles.size(); i++) {
                    obstacles.get(i).setVelX(0);
                }
                for (int i = 0; i < obstacles2.size(); i++) {
                    obstacles2.get(i).setVelX(0);
                }
                for (int i = 0; i < wave1.size(); i++) {
                    wave1.get(i).setVelX(0);
                }
                for (int i = 0; i < wave2.size(); i++) {
                    wave2.get(i).setVelX(0);
                }
                for (int i = 0; i < wave3.size(); i++) {
                    wave3.get(i).setVelX(0);
                }
                for (int i = 0; i < cGroup1.size(); i++) {
                    cGroup1.get(i).setVelX(0);
                }
                for (int i = 0; i < cGroup2.size(); i++) {
                    cGroup2.get(i).setVelX(0);
                }
                for (int i = 0; i < cGroup3.size(); i++) {
                    cGroup3.get(i).setVelX(0);
                }
                t.setVelX(0);
                t2.setVelX(0);
                t3.setVelX(0);
                t4.setVelX(0);
                sp1.incDMG(3);
                sp2.setActive(false);
            }
        });
        //me being lazy checking if enemies overlap so they dont
        sc.addSpriteSpriteCollisionListener(Enemy.class, Enemy.class, new SpriteSpriteCollisionListener<Enemy, Enemy>() {
            @Override
            public void collision(Enemy sp1, Enemy sp2) {
                sp2.setY(sp2.getY() + 40);
            }
        });
        //me being lazy checking if enemies overlap so they dont
        sc.addSpriteSpriteCollisionListener(Enemy2.class, Enemy2.class, new SpriteSpriteCollisionListener<Enemy2, Enemy2>() {
            @Override
            public void collision(Enemy2 sp1, Enemy2 sp2) {
                sp2.setY(sp2.getY() + 40);
            }
        });
        //me being lazy checking if enemies overlap so they dont
        sc.addSpriteSpriteCollisionListener(Enemy3.class, Enemy3.class, new SpriteSpriteCollisionListener<Enemy3, Enemy3>() {
            @Override
            public void collision(Enemy3 sp1, Enemy3 sp2) {
                sp2.setY(sp2.getY() + 40);
            }
        });

        //allows picking up of coins, if statements change the picture bottom right based on # of coins
        sc.addSpriteSpriteCollisionListener(Player.class, Coin.class, new SpriteSpriteCollisionListener<Player, Coin>() {
            @Override
            public void collision(Player sp1, Coin sp2) {
                count++;
                sp2.pickup.play();
                sp2.setActive(false);
                if (count == 1) {
                    cc.setPicture(new Picture("coin1.png"));
                }
                if (count == 2) {
                    cc.setPicture(new Picture("coin2.png"));
                }
                if (count == 3) {
                    cc.setPicture(new Picture("coin3.png"));
                }
                if (count == 4) {
                    cc.setPicture(new Picture("coin4.png"));
                }
                if (count == 5) {
                    cc.setPicture(new Picture("coin5.png"));
                }
                if (count == 6) {
                    cc.setPicture(new Picture("coin6.png"));
                }
                if (count == 7) {
                    cc.setPicture(new Picture("coin7.png"));
                }
                if (count == 8) {
                    cc.setPicture(new Picture("coin8.png"));
                }
                if (count == 9) {
                    cc.setPicture(new Picture("coin8.png"));
                }
                if (count == 10) {
                    cc.setPicture(new Picture("coin8.png"));
                }
                if (count == 11) {
                    cc.setPicture(new Picture("coin8.png"));
                }
                if (count == 12) {
                    cc.setPicture(new Picture("coin8.png"));
                }
                if (count == 13) {
                    cc.setPicture(new Picture("coin8.png"));
                }
                if (count == 14) {
                    cc.setPicture(new Picture("coin8.png"));
                }
                if (count == 15) {
                    cc.setPicture(new Picture("coin8.png"));
                }
                if (count == 16) {
                    cc.setPicture(new Picture("coin8.png"));
                }
                if (count == 17) {
                    cc.setPicture(new Picture("coin8.png"));
                }
                if (count == 18) {
                    cc.setPicture(new Picture("coin8.png"));
                }
                if (count == 19) {
                    cc.setPicture(new Picture("coin8.png"));
                }
                if (count == 20) {
                    cc.setPicture(new Picture("coin8.png"));
                    sp1.incDMG(3);
                    JOptionPane.showMessageDialog(sc, "You collected every coin! Your damage has been increased!");
                }
            }
        });

        sc.addSpriteSpriteCollisionListener(Projectile.class, Boss.class, new SpriteSpriteCollisionListener<Projectile, Boss>() {
            @Override
            public void collision(Projectile sp1, Boss sp2) {
                sp2.setHealth(sp2.getHealth() - sp1.getDMG());
                sp1.setActive(false);
                if (sp2.getHealth() < 175 || sp2.getHealth() < 120) {
                    new EProjectile(sc, (int) sp2.getX(), (int) sp2.getY());
                    new EProjectile(sc, (int) sp2.getX(), (int) sp2.getY());
                    new EProjectile(sc, (int) sp2.getX(), (int) sp2.getY());
                }
                if (sp2.getHealth() <= 0) {
                    sp2.setActive(false);
                    JOptionPane.showMessageDialog(sc, "You win! You had a score of " + count + "/20");
                    System.exit(0);
                }
            }
        });

        sc.addSpriteSpriteCollisionListener(Player.class, HealingPot.class, new SpriteSpriteCollisionListener<Player, HealingPot>() {
            @Override
            public void collision(Player sp1, HealingPot sp2) {
                if (sp1.getHealth() < 4) {
                    for (int i = 0; i < hIcons.size(); i++) {
                        hIcons.get(i).setPicture(heart);
                    }
                    sp2.setActive(false);
                    sp1.setHealth(sp2.getHealing());
                    sp1.setPicture(kIdle);
                } else if(storedPots == 0){
                    storedPots++;
                    sp2.setVelX(0);
                    sp2.setX(hIcons.get(0).getX());
                    sp2.setY(hIcons.get(0).getY() + 15);
                } else if(storedPots == 1){
                    storedPots++;
                    sp2.setVelX(0);
                    sp2.setX(hIcons.get(1).getX());
                    sp2.setY(hIcons.get(1).getY() + 15);
                } else if(storedPots == 2){
                    storedPots++;
                    sp2.setVelX(0);
                    sp2.setX(hIcons.get(2).getX());
                    sp2.setY(hIcons.get(2).getY() + 15);
                }
            }
        });
        sc.addSpriteSpriteCollisionListener(EProjectile.class, Player.class, new SpriteSpriteCollisionListener<EProjectile, Player>() {
            @Override
            public void collision(EProjectile sp1, Player sp2) {
                sp2.setHealth(sp2.getHealth() - sp1.getDMG());
                hIcons.get(sp2.getHealth() - 1).setPicture(blank);
                sp2.setActive(false);
                sp2.setPicture(damaged);
            }
        });
        bf.show();
        Clock.start(10);
        Clock.addTask(sc.moveSprites());
    }
}
