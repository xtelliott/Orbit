package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "front";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spacebuddy_back_right.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spacebuddy_back_left.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spacebuddy_forward_left.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spacebuddy_forward_right.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spacebuddy_left_side.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spacebuddy_left_step.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spacebuddy_right_side.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spacebuddy_right_step.png"));
            front = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spacebuddy_front.png"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        direction = "front";

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            }
            else if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            else if (keyH.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if (!collisionOn) {

                switch(direction) {
                    case "up" :
                        worldY -= speed;
                        break;
                    case "down" :
                        worldY += speed;
                        break;
                    case "left" :
                        worldX -= speed;
                        break;
                    case "right" :
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void pickUpObject(int i) {

        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Key" :
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You found a keycard!");
                    break;
                case "Door" :
                    if (hasKey == 3) {
                        gp.obj[i] = null;
                    }
                    else {
                        gp.ui.showMessage(3 - hasKey + " keycards remaining");
                    }
                    break;
                case "Battery" :
                    speed += 1;
                    gp.obj[i] = null;
                    break;
                case "Terminal" :
                    gp.ui.gameFinished = true;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {

            case "up" :
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down" :
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left" :
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right" :
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
            case "front" :
                image = front;
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
