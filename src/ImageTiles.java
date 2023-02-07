import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageTiles {

    public static void main(String[] args) throws IOException {

        System.setProperty("http.agent", "Chrome");

        try {
            URL url = new URL(""); // put your image URL here!!

            InputStream is = url.openStream();
            BufferedImage image = ImageIO.read(is);
            BufferedImage imgs[] = new BufferedImage[9];

            int rows = 3;
            int columns = 3;

            int subimage_Width = image.getWidth() / columns;
            int subimage_Height = image.getHeight() / rows;

            int current_img = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    imgs[current_img] = new BufferedImage(subimage_Width, subimage_Height, image.getType());
                    Graphics2D img_creator = imgs[current_img].createGraphics();

                    int src_first_x = subimage_Width * j;
                    int src_first_y = subimage_Height * i;

                    int dst_corner_x = subimage_Width * j + subimage_Width;
                    int dst_corner_y = subimage_Height * i + subimage_Height;

                    img_creator.drawImage(image, 0, 0, subimage_Width, subimage_Height, src_first_x, src_first_y, dst_corner_x, dst_corner_y, null);
                    current_img++;
                }
            }

            for (int i = 0; i < 9; i++) {
                File outputFile = new File("img" + i + ".jpg");
                ImageIO.write(imgs[i], "jpg", outputFile);
            }
            System.out.println("Sub-images have been created.");
            ImageIcon img0 = new ImageIcon(ImageIO.read(new File("img0.jpg")));
            ImageIcon img1 = new ImageIcon(ImageIO.read(new File("img1.jpg")));
            ImageIcon img2 = new ImageIcon(ImageIO.read(new File("img2.jpg")));
            ImageIcon img3 = new ImageIcon(ImageIO.read(new File("img3.jpg")));
            ImageIcon img4 = new ImageIcon(ImageIO.read(new File("img4.jpg")));
            ImageIcon img5 = new ImageIcon(ImageIO.read(new File("img5.jpg")));
            ImageIcon img6 = new ImageIcon(ImageIO.read(new File("img6.jpg")));
            ImageIcon img7 = new ImageIcon(ImageIO.read(new File("img7.jpg")));
            ImageIcon img8 = new ImageIcon(ImageIO.read(new File("img8.jpg")));


            BufferedImage mergeImage;
            mergeImage = new BufferedImage(img0.getIconWidth() + img1.getIconWidth() + img2.getIconWidth(), img0.getIconHeight() + img1.getIconHeight() + img2.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = mergeImage.getGraphics();
            ImageObserver io = null;
            g.drawImage(img3.getImage(), 0, 0, io);
            g.drawImage(img8.getImage(), img0.getIconWidth(), 0, io);
            g.drawImage(img0.getImage(), img0.getIconWidth() * 2, 2, io);
            g.drawImage(img1.getImage(), 0, img0.getIconHeight(), io);
            g.drawImage(img6.getImage(), img0.getIconWidth(), img0.getIconHeight(), io);
            g.drawImage(img4.getImage(), img0.getIconWidth() * 2, img0.getIconHeight(), io);
            g.drawImage(img7.getImage(), 0, img0.getIconHeight() * 2, io);
            g.drawImage(img2.getImage(), img0.getIconWidth(), img0.getIconHeight() * 2, io);
            g.drawImage(img5.getImage(), img0.getIconWidth() * 2, img0.getIconHeight() * 2, io);
            File outputFile2 = new File("merged.jpg");
            ImageIO.write(mergeImage, "jpg", outputFile2);
            System.out.println("Image Merged!");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}