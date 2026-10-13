
import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class Helper {
    public static final ArrayList<String> colors = new ArrayList<>(Arrays.asList("black", "blue", "green", "orange", "purple", "red", "white", "yellow", "loco"));

    public static HashMap<String, Integer> getEmptyDeck() {
        HashMap<String, Integer> colorMap = new HashMap<>();
        for (String s : colors) {
            colorMap.put(s, 0);
        }
        return colorMap;
    }

    public static BufferedImage rotate(BufferedImage image, int angle) {
        double sin = Math.abs(Math.sin(Math.toRadians(angle))),
               cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = image.getWidth();
        int h = image.getHeight();
        int neww = (int) Math.floor(w*cos + h*sin),
            newh = (int) Math.floor(h*cos + w*sin);
        BufferedImage rotated = new BufferedImage(neww, newh, image.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.translate((neww-w)/2, (newh-h)/2);
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawRenderedImage(image, null);
        graphic.dispose();
        return rotated;
    }

    public static int getMax(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[max] < array[i]) {
                max = i;
            }
        }
        return max;
    }
}
