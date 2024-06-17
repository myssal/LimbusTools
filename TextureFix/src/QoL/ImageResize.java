package QoL;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResize {

    public static void resizeImage(File originalFile, int width, int height, String format){

        try {
            BufferedImage texture = ImageIO.read(originalFile);
            BufferedImage resized = new BufferedImage(width, height, texture.getType());
            Graphics2D g2 = resized.createGraphics();
            g2.drawImage(texture, 0, 0, width, height, null);
            g2.dispose();
            ImageIO.write(resized, format, originalFile);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
