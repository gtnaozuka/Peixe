package Frame;

import Object.Bubbles;
import Object.Fish;
import Object.Shark;
import Util.Util;
import java.applet.Applet;
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
import java.awt.geom.GeneralPath;

public class MyFrame extends Frame {

    private final Dimension screensize;

    private final Fish fish;
    private final Shark shark;
    private static Bubbles bubbles;

    private static Thread tRepaint;

    public MyFrame() {
        super("Peixe");

        screensize = Toolkit.getDefaultToolkit().getScreenSize();
        fish = new Fish();
        shark = new Shark();
        bubbles = new Bubbles(screensize);
        tRepaint = new Thread(new Animation(this));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });
        this.addKeyListener(new MyFrame.KeyboardAction());
        this.pack();
        this.setResizable(false);
        this.setSize(screensize.width, screensize.height);
        this.setLocation((screensize.width - this.getWidth()) / 2,
                (screensize.height - this.getHeight()) / 2);
        this.setBackground(java.awt.Color.CYAN.darker());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(java.awt.Color.CYAN);
        for (GeneralPath bubble : bubbles.getBubbles()) {
            g2d.draw(bubble);
            g2d.fill(bubble);
        }

        g2d.setColor(java.awt.Color.ORANGE);
        g2d.draw(fish.getFish());
        g2d.fill(fish.getFish());

        g2d.setColor(java.awt.Color.ORANGE.darker());
        g2d.draw(fish.getFishEye());
        g2d.fill(fish.getFishEye());

        g2d.setColor(java.awt.Color.GRAY.darker());
        g2d.draw(shark.getShark());
        g2d.fill(shark.getShark());

        g2d.setColor(java.awt.Color.BLACK.brighter());
        g2d.draw(shark.getSharkEye());
        g2d.fill(shark.getSharkEye());
    }

    public static void main(String[] args) {
        MyFrame mf = new MyFrame();

        bubbles.gettCreateBubbles().start();
        bubbles.gettUpdateBubbles().start();
        tRepaint.start();

        mf.setVisible(true);
    }

    private class KeyboardAction extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            fish.checkFishMove(keyCode);
            shark.checkSharkMove(keyCode);
        }
    }

    private class Animation extends Applet implements Runnable {

        private final Frame frame;

        public Animation(Frame frame) {
            this.frame = frame;
        }

        @Override
        public void run() {
            while (true) {
                Util.sleep(150);
                frame.repaint();
            }
        }
    }
}
