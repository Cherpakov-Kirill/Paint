package nsu.graphics.firstlab.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Span {
    private final Point left;
    private final Point right;

    public Span(Point left, Point right) {
        this.left = left;
        this.right = right;
    }

    public Span(int xLeft, int xRight, int y) {
        this.left = new Point(xLeft, y);
        this.right = new Point(xRight, y);
    }

    public void paint(BufferedImage img, int color) {
        int y = getY();
        int x = getStartX();
        int xFinish = getFinishX();
        while (x <= xFinish) {
            img.setRGB(x, y, color);
            x++;
        }
    }

    public int getY() {
        return left.y;
    }

    public int getStartX() {
        return left.x;
    }

    public int getFinishX() {
        return right.x;
    }
}
