package Object;

import Util.Util;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.Random;

public class Bubbles {

    private GeneralPath[] bubbles;

    private int older;
    private static final int MAX_BUBBLES = 10;

    private final Thread tCreateBubbles, tUpdateBubbles;

    public Bubbles(Dimension screensize) {
        initBubbles();
        tCreateBubbles = new Thread(createBubbles(screensize));
        tUpdateBubbles = new Thread(updateBubbles());
    }

    private void initBubbles() {
        bubbles = new GeneralPath[MAX_BUBBLES];
        for (int i = 0; i < MAX_BUBBLES; i++) {
            bubbles[i] = new GeneralPath();
        }
    }

    private Runnable createBubbles(Dimension screensize) {
        return () -> {
            Random r = new Random();
            while (true) {
                double x = r.nextInt(screensize.width);
                double y = r.nextInt(screensize.height);
                double radius = r.nextInt(100) + 50;

                bubbles[older % MAX_BUBBLES] = new GeneralPath(new Ellipse2D.Double(x,
                        y, radius, radius));
                older++;

                Util.sleep(3000);
            }
        };
    }

    private Runnable updateBubbles() {
        return () -> {
            while (true) {
                AffineTransform at = new AffineTransform();
                at.setToTranslation(0, -5);
                for (GeneralPath bubble : getBubbles()) {
                    bubble.transform(at);
                }

                Util.sleep(150);
            }
        };
    }

    public GeneralPath[] getBubbles() {
        return bubbles;
    }

    public Thread gettCreateBubbles() {
        return tCreateBubbles;
    }

    public Thread gettUpdateBubbles() {
        return tUpdateBubbles;
    }
}
