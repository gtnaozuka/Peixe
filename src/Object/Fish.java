package Object;

import Entity.Point;
import Util.GeomTransform;
import java.awt.event.KeyEvent;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public final class Fish {

    private List<Point> fishPoints;

    private GeneralPath fish;
    private GeneralPath fishEye;

    public Fish() {
        initFishPoints();
        updateFish(true);
    }

    private void initFishPoints() {
        fishPoints = new ArrayList<>();
        getFishPoints().add(new Point(50, 55));
        getFishPoints().add(new Point(60, 75));
        getFishPoints().add(new Point(50, 95));
        getFishPoints().add(new Point(50, 100));
        getFishPoints().add(new Point(55, 95));
        getFishPoints().add(new Point(75, 85));
        getFishPoints().add(new Point(125, 140));
        getFishPoints().add(new Point(200, 88));
        getFishPoints().add(new Point(190, 80));
        getFishPoints().add(new Point(200, 72));
        getFishPoints().add(new Point(125, 0));
        getFishPoints().add(new Point(75, 65));
        getFishPoints().add(new Point(55, 55));
        getFishPoints().add(new Point(50, 50));
        getFishPoints().add(new Point(50, 55));
        getFishPoints().add(new Point(168, 47));
        getFishPoints().add(new Point(90, 15));
        getFishPoints().add(new Point(100, 33));
        getFishPoints().add(new Point(90, 50));
        getFishPoints().add(new Point(175, 92));
        getFishPoints().add(new Point(120, 120));
        getFishPoints().add(new Point(140, 99));
        getFishPoints().add(new Point(130, 119));
        getFishPoints().add(new Point(165, 60));
        getFishPoints().add(new Point(175, 60));
        getFishPoints().add(new Point(170, 65));
        getFishPoints().add(new Point(160, 65));
        getFishPoints().add(new Point(165, 60));
    }

    public void updateFish(boolean isInit) {
        fish = new GeneralPath();
        getFish().moveTo(getFishPoints().get(0).x, getFishPoints().get(0).y);
        getFish().quadTo(getFishPoints().get(1).x, getFishPoints().get(1).y,
                getFishPoints().get(2).x, getFishPoints().get(2).y);
        getFish().quadTo(getFishPoints().get(3).x, getFishPoints().get(3).y,
                getFishPoints().get(4).x, getFishPoints().get(4).y);
        getFish().lineTo(getFishPoints().get(5).x, getFishPoints().get(5).y);
        getFish().quadTo(getFishPoints().get(6).x, getFishPoints().get(6).y,
                getFishPoints().get(7).x, getFishPoints().get(7).y);
        getFish().quadTo(getFishPoints().get(8).x, getFishPoints().get(8).y,
                getFishPoints().get(9).x, getFishPoints().get(9).y);
        getFish().quadTo(getFishPoints().get(10).x, getFishPoints().get(10).y,
                getFishPoints().get(11).x, getFishPoints().get(11).y);
        getFish().lineTo(getFishPoints().get(12).x, getFishPoints().get(12).y);
        getFish().quadTo(getFishPoints().get(13).x, getFishPoints().get(13).y,
                getFishPoints().get(14).x, getFishPoints().get(14).y);
        getFish().moveTo(getFishPoints().get(15).x, getFishPoints().get(15).y);
        getFish().lineTo(getFishPoints().get(16).x, getFishPoints().get(16).y);
        getFish().quadTo(getFishPoints().get(17).x, getFishPoints().get(17).y,
                getFishPoints().get(18).x, getFishPoints().get(18).y);
        getFish().moveTo(getFishPoints().get(19).x, getFishPoints().get(19).y);
        getFish().lineTo(getFishPoints().get(20).x, getFishPoints().get(20).y);
        getFish().quadTo(getFishPoints().get(21).x, getFishPoints().get(21).y,
                getFishPoints().get(22).x, getFishPoints().get(22).y);

        fishEye = new GeneralPath();
        getFishEye().moveTo(getFishPoints().get(23).x, getFishPoints().get(23).y);
        getFishEye().quadTo(getFishPoints().get(24).x, getFishPoints().get(24).y,
                getFishPoints().get(25).x, getFishPoints().get(25).y);
        getFishEye().quadTo(getFishPoints().get(26).x, getFishPoints().get(26).y,
                getFishPoints().get(27).x, getFishPoints().get(27).y);

        if (isInit) {
            GeomTransform.translate(getFishPoints(), 0, 200);
            updateFish(false);
        }
    }

    public void checkFishMove(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_J:
                GeomTransform.translate(fishPoints, -5, 0);
                updateFish(false);
                break;
            case KeyEvent.VK_L:
                GeomTransform.translate(fishPoints, 5, 0);
                updateFish(false);
                break;
            case KeyEvent.VK_I:
                GeomTransform.translate(fishPoints, 0, -5);
                updateFish(false);
                break;
            case KeyEvent.VK_K:
                GeomTransform.translate(fishPoints, 0, 5);
                updateFish(false);
                break;
            case KeyEvent.VK_U:
                GeomTransform.rotate(fishPoints, -Math.toRadians(1));
                updateFish(false);
                break;
            case KeyEvent.VK_O:
                GeomTransform.rotate(fishPoints, Math.toRadians(1));
                updateFish(false);
                break;
        }
    }

    public GeneralPath getFish() {
        return fish;
    }

    public GeneralPath getFishEye() {
        return fishEye;
    }

    public List<Point> getFishPoints() {
        return fishPoints;
    }
}
