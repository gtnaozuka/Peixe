
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fish extends Frame {

    private List<Point> points;

    private GeneralPath fish;
    private GeneralPath fishEye;
    private GeneralPath[] bubbles;

    private int older;
    private final Dimension screensize;
    private static final int MAX_BUBBLES = 10;

    private static Thread tCreate, tUpdate;

    public Fish() {
        super("Peixe");

        older = 0;
        screensize = Toolkit.getDefaultToolkit().getScreenSize();
        initPoints();
        updateFish(true);
        initBubbles();
        tCreate = new Thread(createBubbles());
        tUpdate = new Thread(updateBubble());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyboardAction());
        this.pack();
        this.setResizable(false);
        this.setSize(screensize.width, screensize.height);
        this.setLocation((screensize.width - this.getWidth()) / 2,
                (screensize.height - this.getHeight()) / 2);
        this.setBackground(java.awt.Color.CYAN.darker());
    }

    private void initPoints() {
        points = new ArrayList<>();
        points.add(new Point(50, 55));
        points.add(new Point(60, 75));
        points.add(new Point(50, 95));
        points.add(new Point(50, 100));
        points.add(new Point(55, 95));
        points.add(new Point(75, 85));
        points.add(new Point(125, 140));
        points.add(new Point(200, 88));
        points.add(new Point(190, 80));
        points.add(new Point(200, 72));
        points.add(new Point(125, 0));
        points.add(new Point(75, 65));
        points.add(new Point(55, 55));
        points.add(new Point(50, 50));
        points.add(new Point(50, 55));
        points.add(new Point(168, 47));
        points.add(new Point(90, 15));
        points.add(new Point(100, 33));
        points.add(new Point(90, 50));
        points.add(new Point(175, 92));
        points.add(new Point(120, 120));
        points.add(new Point(140, 99));
        points.add(new Point(130, 119));
        points.add(new Point(165, 60));
        points.add(new Point(175, 60));
        points.add(new Point(170, 65));
        points.add(new Point(160, 65));
        points.add(new Point(165, 60));
    }

    private void updateFish(boolean isInit) {
        fish = new GeneralPath();
        fish.moveTo(points.get(0).x, points.get(0).y);
        fish.quadTo(points.get(1).x, points.get(1).y,
                points.get(2).x, points.get(2).y);
        fish.quadTo(points.get(3).x, points.get(3).y,
                points.get(4).x, points.get(4).y);
        fish.lineTo(points.get(5).x, points.get(5).y);
        fish.quadTo(points.get(6).x, points.get(6).y,
                points.get(7).x, points.get(7).y);
        fish.quadTo(points.get(8).x, points.get(8).y,
                points.get(9).x, points.get(9).y);
        fish.quadTo(points.get(10).x, points.get(10).y,
                points.get(11).x, points.get(11).y);
        fish.lineTo(points.get(12).x, points.get(12).y);
        fish.quadTo(points.get(13).x, points.get(13).y,
                points.get(14).x, points.get(14).y);
        fish.moveTo(points.get(15).x, points.get(15).y);
        fish.lineTo(points.get(16).x, points.get(16).y);
        fish.quadTo(points.get(17).x, points.get(17).y,
                points.get(18).x, points.get(18).y);
        fish.moveTo(points.get(19).x, points.get(19).y);
        fish.lineTo(points.get(20).x, points.get(20).y);
        fish.quadTo(points.get(21).x, points.get(21).y,
                points.get(22).x, points.get(22).y);

        fishEye = new GeneralPath();
        fishEye.moveTo(points.get(23).x, points.get(23).y);
        fishEye.quadTo(points.get(24).x, points.get(24).y,
                points.get(25).x, points.get(25).y);
        fishEye.quadTo(points.get(26).x, points.get(26).y,
                points.get(27).x, points.get(27).y);

        if (isInit) {
            translate(0, 250);
        }
    }

    private void initBubbles() {
        bubbles = new GeneralPath[MAX_BUBBLES];
        for (int i = 0; i < MAX_BUBBLES; i++) {
            bubbles[i] = new GeneralPath();
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(java.awt.Color.CYAN);
        for (GeneralPath bubble : bubbles) {
            g2d.draw(bubble);
            g2d.fill(bubble);
        }

        g2d.setColor(java.awt.Color.ORANGE);
        g2d.draw(fish);
        g2d.fill(fish);

        g2d.setColor(java.awt.Color.ORANGE.brighter());
        g2d.draw(fishEye);
        g2d.fill(fishEye);
    }

    public static void main(String[] args) {
        Fish f = new Fish();
        tCreate.start();
        tUpdate.start();
        f.setVisible(true);
    }

    private class KeyboardAction extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    translate(-5, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    translate(5, 0);
                    break;
                case KeyEvent.VK_UP:
                    translate(0, -5);
                    break;
                case KeyEvent.VK_DOWN:
                    translate(0, 5);
                    break;
                case KeyEvent.VK_W:
                    rotate(-Math.toRadians(1));
                    break;
                case KeyEvent.VK_S:
                    rotate(Math.toRadians(1));
                    break;
            }
        }
    }

    private void translate(double x, double y) {
        points.stream().map((p) -> {
            p.x += x;
            return p;
        }).forEach((p) -> {
            p.y += y;
        });
        updateFish(false);
        repaint();
    }

    private void rotate(double rad) {
        points.stream().forEach((p) -> {
            double x = p.x;
            double y = p.y;
            p.x = x * Math.cos(rad) - y * Math.sin(rad);
            p.y = x * Math.sin(rad) + y * Math.cos(rad);
        });
        updateFish(false);
        repaint();
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Fish.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Runnable createBubbles() {
        return () -> {
            Random r = new Random();
            while (true) {
                double x = r.nextInt(screensize.width);
                double y = r.nextInt(screensize.height);
                double radius = r.nextInt(100) + 50;

                bubbles[older % MAX_BUBBLES] = new GeneralPath(new Ellipse2D.Double(x,
                        y, radius, radius));
                older++;

                sleep(3000);
            }
        };
    }

    private Runnable updateBubble() {
        return () -> {
            while (true) {
                AffineTransform at = new AffineTransform();
                at.setToTranslation(0, -5);
                for (GeneralPath bubble : bubbles) {
                    bubble.transform(at);
                }
                repaint();

                sleep(200);
            }
        };
    }
}
