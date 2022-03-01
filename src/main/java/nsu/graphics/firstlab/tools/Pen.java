package nsu.graphics.firstlab.tools;

import nsu.graphics.firstlab.shapes.DrawForShapes;
import nsu.graphics.firstlab.shapes.LineInterface;

import java.awt.*;

public class Pen {
    protected LineInterface line;
    private Point point;
    private int thickness;
    private final DrawForShapes draw;

    public Pen(DrawForShapes draw, LineInterface line) {
        this.draw = draw;
        this.line = line;
        thickness = 1;
    }

    public void mousePressed(Point mousePoint) {
        this.point = mousePoint;
    }

    public void mouseReleased() {
        point = null;
    }

    public void mouseDragged(Point mousePoint) {
        if (point !=null) {
            line.drawLine(point, mousePoint, draw.getSelectedColor(), thickness);
        }
        point = mousePoint;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }
}
