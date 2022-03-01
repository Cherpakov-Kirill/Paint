package nsu.graphics.firstlab.shapes;

import java.awt.*;

import static nsu.graphics.firstlab.MathUtils.cosDeg;
import static nsu.graphics.firstlab.MathUtils.sinDeg;

public class Star extends Polygon {
    public int innerRadius;
    public int numberOfVertices;
    public Star(LineInterface line) {
        super(line);
        numberOfVertices = 5;
        setRadius(50);
    }

    public void setRadius(int radius) {
        this.radius = radius;
        this.innerRadius = (int) ((double) radius / 2.63);
    }

    public void setVertices(int vertices) {
        this.numberOfVertices = vertices;
    }

    protected int getXInner(int angle, int centerX) {
        double cos = cosDeg(angle);
        return centerX + (int) (cos * innerRadius);
    }

    protected int getYInner(int angle, int centerY) {
        double sin = sinDeg(angle);
        return centerY + (int) (sin * innerRadius);
    }

    public void drawPentagon(Point point) {
        int alpha = 270 - rotation;
        int deltaAngleR = 180 / numberOfVertices;
        int centerX = point.x;
        int centerY = point.y;
        Point first = null;
        Point prev = null;
        for (int i = 0; i < numberOfVertices * 2; i++) {
            Point newPoint;
            if (i % 2 == 0) {
                newPoint = new Point(getX(alpha, centerX), getY(alpha, centerY));
                if (i != 0) {
                    line.drawLine(prev, newPoint);
                } else first = newPoint;
            } else {
                newPoint = new Point(getXInner(alpha, centerX), getYInner(alpha, centerY));
                line.drawLine(prev, newPoint);
            }
            prev = newPoint;
            alpha += deltaAngleR;
        }
        line.drawLine(prev, first);
    }
}
