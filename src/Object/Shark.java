package Object;

import Entity.Point;
import Util.GeomTransform;
import java.awt.event.KeyEvent;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public final class Shark {

    private List<Point> sharkPoints;

    private GeneralPath shark;
    private GeneralPath sharkEye;

    public Shark() {
        initSharkPoints();
        updateShark(true);
    }

    private void initSharkPoints() {
        sharkPoints = new ArrayList<>();
        getSharkPoints().add(new Point(10, 90));
        getSharkPoints().add(new Point(50, 150));
        getSharkPoints().add(new Point(30, 180));
        getSharkPoints().add(new Point(80, 160));
        getSharkPoints().add(new Point(200, 190));
        getSharkPoints().add(new Point(300, 150));
        getSharkPoints().add(new Point(325, 140));
        getSharkPoints().add(new Point(300, 143));
        getSharkPoints().add(new Point(330, 135));
        getSharkPoints().add(new Point(285, 105));
        getSharkPoints().add(new Point(210, 100));
        getSharkPoints().add(new Point(160, 75));
        getSharkPoints().add(new Point(175, 92.5));
        getSharkPoints().add(new Point(170, 110));
        getSharkPoints().add(new Point(130, 120));
        getSharkPoints().add(new Point(80, 135));
        getSharkPoints().add(new Point(10, 90));
        getSharkPoints().add(new Point(195, 173));
        getSharkPoints().add(new Point(180, 200));
        getSharkPoints().add(new Point(235, 169));
        getSharkPoints().add(new Point(235, 135));
        getSharkPoints().add(new Point(237, 142));
        getSharkPoints().add(new Point(235, 165));
        getSharkPoints().add(new Point(240, 135));
        getSharkPoints().add(new Point(242, 142));
        getSharkPoints().add(new Point(240, 165));
        getSharkPoints().add(new Point(245, 135));
        getSharkPoints().add(new Point(247, 142));
        getSharkPoints().add(new Point(245, 165));
        getSharkPoints().add(new Point(200, 200));
        getSharkPoints().add(new Point(295, 125));
        getSharkPoints().add(new Point(305, 125));
        getSharkPoints().add(new Point(300, 130));
        getSharkPoints().add(new Point(290, 130));
        getSharkPoints().add(new Point(295, 125));
    }

    public void updateShark(boolean isInit) {
        shark = new GeneralPath();
        getShark().moveTo(getSharkPoints().get(0).x, getSharkPoints().get(0).y);
        getShark().lineTo(getSharkPoints().get(1).x, getSharkPoints().get(1).y);
        getShark().lineTo(getSharkPoints().get(2).x, getSharkPoints().get(2).y);
        getShark().lineTo(getSharkPoints().get(3).x, getSharkPoints().get(3).y);
        getShark().quadTo(getSharkPoints().get(4).x, getSharkPoints().get(4).y,
                getSharkPoints().get(5).x, getSharkPoints().get(5).y);
        getShark().lineTo(getSharkPoints().get(6).x, getSharkPoints().get(6).y);
        getShark().lineTo(getSharkPoints().get(7).x, getSharkPoints().get(7).y);
        getShark().lineTo(getSharkPoints().get(8).x, getSharkPoints().get(8).y);
        getShark().quadTo(getSharkPoints().get(9).x, getSharkPoints().get(9).y,
                getSharkPoints().get(10).x, getSharkPoints().get(10).y);
        getShark().lineTo(getSharkPoints().get(11).x, getSharkPoints().get(11).y);
        getShark().quadTo(getSharkPoints().get(12).x, getSharkPoints().get(12).y,
                getSharkPoints().get(13).x, getSharkPoints().get(13).y);
        getShark().quadTo(getSharkPoints().get(14).x, getSharkPoints().get(14).y,
                getSharkPoints().get(15).x, getSharkPoints().get(15).y);
        getShark().lineTo(getSharkPoints().get(16).x, getSharkPoints().get(16).y);
        getShark().moveTo(getSharkPoints().get(17).x, getSharkPoints().get(17).y);
        getShark().lineTo(getSharkPoints().get(18).x, getSharkPoints().get(18).y);
        getShark().quadTo(getSharkPoints().get(29).x, getSharkPoints().get(29).y,
                getSharkPoints().get(19).x, getSharkPoints().get(19).y);
        getShark().moveTo(getSharkPoints().get(20).x, getSharkPoints().get(20).y);
        getShark().lineTo(getSharkPoints().get(21).x, getSharkPoints().get(21).y);
        getShark().lineTo(getSharkPoints().get(22).x, getSharkPoints().get(22).y);
        getShark().moveTo(getSharkPoints().get(23).x, getSharkPoints().get(23).y);
        getShark().lineTo(getSharkPoints().get(24).x, getSharkPoints().get(24).y);
        getShark().lineTo(getSharkPoints().get(25).x, getSharkPoints().get(25).y);
        getShark().moveTo(getSharkPoints().get(26).x, getSharkPoints().get(26).y);
        getShark().lineTo(getSharkPoints().get(27).x, getSharkPoints().get(27).y);
        getShark().lineTo(getSharkPoints().get(28).x, getSharkPoints().get(28).y);

        sharkEye = new GeneralPath();
        getSharkEye().moveTo(getSharkPoints().get(30).x, getSharkPoints().get(30).y);
        getSharkEye().quadTo(getSharkPoints().get(31).x, getSharkPoints().get(31).y,
                getSharkPoints().get(32).x, getSharkPoints().get(32).y);
        getSharkEye().quadTo(getSharkPoints().get(33).x, getSharkPoints().get(33).y,
                getSharkPoints().get(34).x, getSharkPoints().get(34).y);

        if (isInit) {
            GeomTransform.translate(getSharkPoints(), 0, 300);
            updateShark(false);
        }
    }

    public void checkSharkMove(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A:
                GeomTransform.translate(sharkPoints, -5, 0);
                updateShark(false);
                break;
            case KeyEvent.VK_D:
                GeomTransform.translate(sharkPoints, 5, 0);
                updateShark(false);
                break;
            case KeyEvent.VK_W:
                GeomTransform.translate(sharkPoints, 0, -5);
                updateShark(false);
                break;
            case KeyEvent.VK_S:
                GeomTransform.translate(sharkPoints, 0, 5);
                updateShark(false);
                break;
            case KeyEvent.VK_Q:
                GeomTransform.rotate(sharkPoints, -Math.toRadians(1));
                updateShark(false);
                break;
            case KeyEvent.VK_E:
                GeomTransform.rotate(sharkPoints, Math.toRadians(1));
                updateShark(false);
                break;
        }
    }

    public GeneralPath getShark() {
        return shark;
    }

    public GeneralPath getSharkEye() {
        return sharkEye;
    }

    public List<Point> getSharkPoints() {
        return sharkPoints;
    }
}
