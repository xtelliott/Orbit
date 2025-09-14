package object;

import javax.imageio.ImageIO;

import java.io.IOException;

public class OBJ_Battery extends SuperObject {

    public OBJ_Battery() {

        name = "Battery";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/battery.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


