package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font trebuchet_25, trebuchet_60;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        trebuchet_25 = new Font("Trebuchet MS", Font.BOLD, 25);
        trebuchet_60 = new Font("Trebuchet MS", Font.BOLD, 60);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {

        if (gameFinished) {
            g2.setFont(trebuchet_25);
            g2.setColor(new Color(0xFF6A00));

            String text;
            int textLength;
            int x;
            int y;

            text = "Prepare for takeoff...";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            g2.drawString("" + dFormat.format(playTime), 695, 37);

            g2.setFont(trebuchet_60);

            text = "You win!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y += 60;
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }
        else {
            g2.setFont(trebuchet_25);
            g2.setColor(new Color(0xFF6A00));
            g2.drawImage(keyImage, gp.tileSize / 4, gp.tileSize / 8, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 55, 37);

            playTime += (double) 1 / 60;
            g2.drawString("" + dFormat.format(playTime), 695, 37);

            if (messageOn) {
                g2.drawString(message, (gp.tileSize * 5) + 25, 37);
                messageCounter++;

                if (messageCounter > 180) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
