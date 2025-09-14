package main;

import object.OBJ_Battery;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Terminal;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 54 * gp.tileSize;
        gp.obj[0].worldY = 22 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 11 * gp.tileSize;
        gp.obj[1].worldY = 39 * gp.tileSize;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 12 * gp.tileSize;
        gp.obj[2].worldY = 9 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 39 * gp.tileSize;
        gp.obj[3].worldY = 39 * gp.tileSize;

        gp.obj[4] = new OBJ_Terminal();
        gp.obj[4].worldX = 43 * gp.tileSize;
        gp.obj[4].worldY = 36 * gp.tileSize;

        gp.obj[5] = new OBJ_Battery();
        gp.obj[5].worldX = 24 * gp.tileSize;
        gp.obj[5].worldY = 42 * gp.tileSize;

        gp.obj[6] = new OBJ_Battery();
        gp.obj[6].worldX = 55 * gp.tileSize;
        gp.obj[6].worldY = 16 * gp.tileSize;

        gp.obj[7] = new OBJ_Battery();
        gp.obj[7].worldX = 13 * gp.tileSize;
        gp.obj[7].worldY = 26 * gp.tileSize;
    }
}
