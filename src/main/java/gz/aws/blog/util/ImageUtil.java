package gz.aws.blog.util;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Kane
 * Date: 12/12/10
 * Time: 11:07 PM
 */
public class ImageUtil {
    public static byte[] resize(byte[] bytes, int widht, int height) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            BufferedImage oldImage = ImageIO.read(bais);
            if(oldImage.getHeight()<height){
                return bytes;
            }
            int newHeight=height;
            int newWidth=oldImage.getWidth()*newHeight/oldImage.getHeight();
            System.out.println(newWidth);
            BufferedImage newImage = new BufferedImage(newWidth, newHeight, oldImage.getType());
            Graphics2D graphics = newImage.createGraphics();
            graphics.drawImage(oldImage, 0, 0, newImage.getWidth(), newImage.getHeight(), null);
            graphics.dispose();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(newImage, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage oldImage = ImageIO.read(new File("F:\\picture\\baby\\jx0619辛妍琳-g038.JPG"));
        System.out.println(oldImage.getWidth());
        System.out.println(oldImage.getTileWidth());
        System.out.println(oldImage.getHeight());
        System.out.println(oldImage.getTileHeight());
        float ratio=oldImage.getWidth()*1.0f/oldImage.getHeight();
        System.out.println(ratio);
        int height=588;
         if(oldImage.getHeight()<height){
                return ;
            }
            int newHeight=height;
            int newWidth=oldImage.getWidth()*newHeight/oldImage.getHeight();
            System.out.println(newWidth);
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, oldImage.getType());
        Graphics2D graphics = newImage.createGraphics();
//        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics.setComposite(AlphaComposite.Src);

        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.drawImage(oldImage, 0, 0, newImage.getWidth(), newImage.getHeight(), null);
        graphics.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(newImage, "png", new File("d:/tmp/c.png"));
    }
}
