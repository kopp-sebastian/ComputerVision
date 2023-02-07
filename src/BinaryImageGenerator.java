import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public class BinaryImageGenerator {

    public static void main(String[] args) throws IOException {
        URL url = new URL(""); // put your image URL here!!

        InputStream is = url.openStream();
        BufferedImage img = ImageIO.read(is);

        int h = img.getHeight();
        int w = img.getWidth();

        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        if (img == null) {
            System.out.println("No image loaded");
        } else {
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int val = img.getRGB(i, j);
                    int r = (0x00ff0000 & val) >> 16;
                    int g = (0x0000ff00 & val) >> 8;
                    int b = (0x000000ff & val);
                    int m = (r + g + b);
                    if (m >= 383) {
                        bufferedImage.setRGB(i, j, Color.WHITE.getRGB());
                    } else {
                        bufferedImage.setRGB(i, j, 0);
                    }
                }
            }
        }
        File file = new File("BinaryImage.jpg");
        ImageIO.write(bufferedImage, "jpg", file);
    }
}