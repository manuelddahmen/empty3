package be.manudahmen.empty3.library.testing;


import java.awt.image.BufferedImage;

public class ImageContainer {

    private BufferedImage biic;
    private String str = "";

    public BufferedImage getImage() {
        return biic;
    }

    public void setImage(BufferedImage biic1) {
        biic = biic1;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}