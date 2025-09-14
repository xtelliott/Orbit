package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {

    public OBJ_Key() {

        name = "Key";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/keycard.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.height = 16;
        solidArea.width = 16;
    }

}
