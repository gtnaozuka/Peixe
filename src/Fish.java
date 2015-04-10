
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

    private List<Point> fishPoints;
    private List<Point> sharkPoints;

    private GeneralPath fish;
    private GeneralPath fishEye;
    private GeneralPath shark;
    private GeneralPath sharkEye;
    private GeneralPath[] bubbles;

    private int older;
    private final Dimension screensize;
    private static final int MAX_BUBBLES = 10;

    private static Thread tCreateBubbles, tUpdateBubbles;

    public Fish() {
        super("Peixe");

        older = 0;
        screensize = Toolkit.getDefaultToolkit().getScreenSize();

        initFishPoints();
        initSharkPoints();
        updateFish(true);
        updateShark(true);

        initBubbles();
        tCreateBubbles = new Thread(createBubbles());
        tUpdateBubbles = new Thread(updateBubbles());

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

    private void initFishPoints() {
        fishPoints = new ArrayList<>();
        fishPoints.add(new Point(50, 55));
        fishPoints.add(new Point(60, 75));
        fishPoints.add(new Point(50, 95));
        fishPoints.add(new Point(50, 100));
        fishPoints.add(new Point(55, 95));
        fishPoints.add(new Point(75, 85));
        fishPoints.add(new Point(125, 140));
        fishPoints.add(new Point(200, 88));
        fishPoints.add(new Point(190, 80));
        fishPoints.add(new Point(200, 72));
        fishPoints.add(new Point(125, 0));
        fishPoints.add(new Point(75, 65));
        fishPoints.add(new Point(55, 55));
        fishPoints.add(new Point(50, 50));
        fishPoints.add(new Point(50, 55));
        fishPoints.add(new Point(168, 47));
        fishPoints.add(new Point(90, 15));
        fishPoints.add(new Point(100, 33));
        fishPoints.add(new Point(90, 50));
        fishPoints.add(new Point(175, 92));
        fishPoints.add(new Point(120, 120));
        fishPoints.add(new Point(140, 99));
        fishPoints.add(new Point(130, 119));
        fishPoints.add(new Point(165, 60));
        fishPoints.add(new Point(175, 60));
        fishPoints.add(new Point(170, 65));
        fishPoints.add(new Point(160, 65));
        fishPoints.add(new Point(165, 60));
    }

    private void initSharkPoints() {
        sharkPoints = new ArrayList<>();
        sharkPoints.add(new Point(10, 90));
        sharkPoints.add(new Point(50, 150));
        sharkPoints.add(new Point(30, 180));
        sharkPoints.add(new Point(80, 160));
        sharkPoints.add(new Point(200, 190));
        sharkPoints.add(new Point(300, 150));
        sharkPoints.add(new Point(325, 140));
        sharkPoints.add(new Point(300, 143));
        sharkPoints.add(new Point(330, 135));
        sharkPoints.add(new Point(285, 105));
        sharkPoints.add(new Point(210, 100));
        sharkPoints.add(new Point(160, 75));
        sharkPoints.add(new Point(175, 92.5));
        sharkPoints.add(new Point(170, 110));
        sharkPoints.add(new Point(130, 120));
        sharkPoints.add(new Point(80, 135));
        sharkPoints.add(new Point(10, 90));
        sharkPoints.add(new Point(195, 173));
        sharkPoints.add(new Point(180, 200));
        sharkPoints.add(new Point(235, 169));
        sharkPoints.add(new Point(235, 135));
        sharkPoints.add(new Point(237, 142));
        sharkPoints.add(new Point(235, 165));
        sharkPoints.add(new Point(240, 135));
        sharkPoints.add(new Point(242, 142));
        sharkPoints.add(new Point(240, 165));
        sharkPoints.add(new Point(245, 135));
        sharkPoints.add(new Point(247, 142));
        sharkPoints.add(new Point(245, 165));
        sharkPoints.add(new Point(200, 200));
        sharkPoints.add(new Point(295, 125));
        sharkPoints.add(new Point(305, 125));
        sharkPoints.add(new Point(300, 130));
        sharkPoints.add(new Point(290, 130));
        sharkPoints.add(new Point(295, 125));
    }

    private void updateFish(boolean isInit) {
        fish = new GeneralPath();
        fish.moveTo(fishPoints.get(0).x, fishPoints.get(0).y);
        fish.quadTo(fishPoints.get(1).x, fishPoints.get(1).y,
                fishPoints.get(2).x, fishPoints.get(2).y);
        fish.quadTo(fishPoints.get(3).x, fishPoints.get(3).y,
                fishPoints.get(4).x, fishPoints.get(4).y);
        fish.lineTo(fishPoints.get(5).x, fishPoints.get(5).y);
        fish.quadTo(fishPoints.get(6).x, fishPoints.get(6).y,
                fishPoints.get(7).x, fishPoints.get(7).y);
        fish.quadTo(fishPoints.get(8).x, fishPoints.get(8).y,
                fishPoints.get(9).x, fishPoints.get(9).y);
        fish.quadTo(fishPoints.get(10).x, fishPoints.get(10).y,
                fishPoints.get(11).x, fishPoints.get(11).y);
        fish.lineTo(fishPoints.get(12).x, fishPoints.get(12).y);
        fish.quadTo(fishPoints.get(13).x, fishPoints.get(13).y,
                fishPoints.get(14).x, fishPoints.get(14).y);
        fish.moveTo(fishPoints.get(15).x, fishPoints.get(15).y);
        fish.lineTo(fishPoints.get(16).x, fishPoints.get(16).y);
        fish.quadTo(fishPoints.get(17).x, fishPoints.get(17).y,
                fishPoints.get(18).x, fishPoints.get(18).y);
        fish.moveTo(fishPoints.get(19).x, fishPoints.get(19).y);
        fish.lineTo(fishPoints.get(20).x, fishPoints.get(20).y);
        fish.quadTo(fishPoints.get(21).x, fishPoints.get(21).y,
                fishPoints.get(22).x, fishPoints.get(22).y);

        fishEye = new GeneralPath();
        fishEye.moveTo(fishPoints.get(23).x, fishPoints.get(23).y);
        fishEye.quadTo(fishPoints.get(24).x, fishPoints.get(24).y,
                fishPoints.get(25).x, fishPoints.get(25).y);
        fishEye.quadTo(fishPoints.get(26).x, fishPoints.get(26).y,
                fishPoints.get(27).x, fishPoints.get(27).y);

        if (isInit) {
            translate(fishPoints, 0, 200);
            updateFish(false);
        }
    }

    private void updateShark(boolean isInit) {
        shark = new GeneralPath();
        shark.moveTo(sharkPoints.get(0).x, sharkPoints.get(0).y);
        shark.lineTo(sharkPoints.get(1).x, sharkPoints.get(1).y);
        shark.lineTo(sharkPoints.get(2).x, sharkPoints.get(2).y);
        shark.lineTo(sharkPoints.get(3).x, sharkPoints.get(3).y);
        shark.quadTo(sharkPoints.get(4).x, sharkPoints.get(4).y,
                sharkPoints.get(5).x, sharkPoints.get(5).y);
        shark.lineTo(sharkPoints.get(6).x, sharkPoints.get(6).y);
        shark.lineTo(sharkPoints.get(7).x, sharkPoints.get(7).y);
        shark.lineTo(sharkPoints.get(8).x, sharkPoints.get(8).y);
        shark.quadTo(sharkPoints.get(9).x, sharkPoints.get(9).y,
                sharkPoints.get(10).x, sharkPoints.get(10).y);
        shark.lineTo(sharkPoints.get(11).x, sharkPoints.get(11).y);
        shark.quadTo(sharkPoints.get(12).x, sharkPoints.get(12).y,
                sharkPoints.get(13).x, sharkPoints.get(13).y);
        shark.quadTo(sharkPoints.get(14).x, sharkPoints.get(14).y,
                sharkPoints.get(15).x, sharkPoints.get(15).y);
        shark.lineTo(sharkPoints.get(16).x, sharkPoints.get(16).y);
        shark.moveTo(sharkPoints.get(17).x, sharkPoints.get(17).y);
        shark.lineTo(sharkPoints.get(18).x, sharkPoints.get(18).y);
        shark.quadTo(sharkPoints.get(29).x, sharkPoints.get(29).y,
                sharkPoints.get(19).x, sharkPoints.get(19).y);
        shark.moveTo(sharkPoints.get(20).x, sharkPoints.get(20).y);
        shark.lineTo(sharkPoints.get(21).x, sharkPoints.get(21).y);
        shark.lineTo(sharkPoints.get(22).x, sharkPoints.get(22).y);
        shark.moveTo(sharkPoints.get(23).x, sharkPoints.get(23).y);
        shark.lineTo(sharkPoints.get(24).x, sharkPoints.get(24).y);
        shark.lineTo(sharkPoints.get(25).x, sharkPoints.get(25).y);
        shark.moveTo(sharkPoints.get(26).x, sharkPoints.get(26).y);
        shark.lineTo(sharkPoints.get(27).x, sharkPoints.get(27).y);
        shark.lineTo(sharkPoints.get(28).x, sharkPoints.get(28).y);

        sharkEye = new GeneralPath();
        sharkEye.moveTo(sharkPoints.get(30).x, sharkPoints.get(30).y);
        sharkEye.quadTo(sharkPoints.get(31).x, sharkPoints.get(31).y,
                sharkPoints.get(32).x, sharkPoints.get(32).y);
        sharkEye.quadTo(sharkPoints.get(33).x, sharkPoints.get(33).y,
                sharkPoints.get(34).x, sharkPoints.get(34).y);

        if (isInit) {
            translate(sharkPoints, 0, 300);
            updateShark(false);
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

        g2d.setColor(java.awt.Color.ORANGE.darker());
        g2d.draw(fishEye);
        g2d.fill(fishEye);

        g2d.setColor(java.awt.Color.GRAY.darker());
        g2d.draw(shark);
        g2d.fill(shark);

        g2d.setColor(java.awt.Color.BLACK.brighter());
        g2d.draw(sharkEye);
        g2d.fill(sharkEye);
    }

    public static void main(String[] args) {
        Fish f = new Fish();
        tCreateBubbles.start();
        tUpdateBubbles.start();
        f.setVisible(true);
    }

    private class KeyboardAction extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            checkFishMove(keyCode);
            checkSharkMove(keyCode);
        }

        private void checkSharkMove(int keyCode) {
            switch (keyCode) {
                case KeyEvent.VK_A:
                    translate(sharkPoints, -5, 0);
                    updateShark(false);
                    break;
                case KeyEvent.VK_D:
                    translate(sharkPoints, 5, 0);
                    updateShark(false);
                    break;
                case KeyEvent.VK_W:
                    translate(sharkPoints, 0, -5);
                    updateShark(false);
                    break;
                case KeyEvent.VK_S:
                    translate(sharkPoints, 0, 5);
                    updateShark(false);
                    break;
                case KeyEvent.VK_Q:
                    rotate(sharkPoints, -Math.toRadians(1));
                    updateShark(false);
                    break;
                case KeyEvent.VK_E:
                    rotate(sharkPoints, Math.toRadians(1));
                    updateShark(false);
                    break;
            }
        }

        private void checkFishMove(int keyCode) {
            switch (keyCode) {
                case KeyEvent.VK_J:
                    translate(fishPoints, -5, 0);
                    updateFish(false);
                    break;
                case KeyEvent.VK_L:
                    translate(fishPoints, 5, 0);
                    updateFish(false);
                    break;
                case KeyEvent.VK_I:
                    translate(fishPoints, 0, -5);
                    updateFish(false);
                    break;
                case KeyEvent.VK_K:
                    translate(fishPoints, 0, 5);
                    updateFish(false);
                    break;
                case KeyEvent.VK_U:
                    rotate(fishPoints, -Math.toRadians(1));
                    updateFish(false);
                    break;
                case KeyEvent.VK_O:
                    rotate(fishPoints, Math.toRadians(1));
                    updateFish(false);
                    break;
            }
        }
    }

    private void translate(List<Point> points, double x, double y) {
        points.stream().map((p) -> {
            p.x += x;
            return p;
        }).forEach((p) -> {
            p.y += y;
        });
    }

    private void rotate(List<Point> points, double rad) {
        points.stream().forEach((p) -> {
            double x = p.x;
            double y = p.y;
            p.x = x * Math.cos(rad) - y * Math.sin(rad);
            p.y = x * Math.sin(rad) + y * Math.cos(rad);
        });
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

    private Runnable updateBubbles() {
        return () -> {
            while (true) {
                AffineTransform at = new AffineTransform();
                at.setToTranslation(0, -5);
                for (GeneralPath bubble : bubbles) {
                    bubble.transform(at);
                }
                repaint();

                sleep(150);
            }
        };
    }
}
