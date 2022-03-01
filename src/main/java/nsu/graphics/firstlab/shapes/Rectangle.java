package nsu.graphics.firstlab.shapes;

import java.awt.*;


public class Rectangle extends Polygon {
    public Rectangle(LineInterface line) {
        super(line);
    }

    public void drawRectangle(Point point) {
        int alpha = 45 - rotation;
        int centerX = point.x;
        int centerY = point.y;
        Point first = new Point(getX(alpha, centerX), getY(alpha, centerY));
        alpha += 90;
        Point second = new Point(getX(alpha, centerX), getY(alpha, centerY));
        line.drawLine(first, second);
        alpha += 90;
        Point third = new Point(getX(alpha, centerX), getY(alpha, centerY));
        line.drawLine(second, third);
        alpha += 90;
        Point fourth = new Point(getX(alpha, centerX), getY(alpha, centerY));
        line.drawLine(third, fourth);
        line.drawLine(fourth, first);
    }
}
