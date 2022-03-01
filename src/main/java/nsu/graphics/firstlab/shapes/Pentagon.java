package nsu.graphics.firstlab.shapes;

import java.awt.*;

public class Pentagon extends Polygon {
    public int numberOfVertices;

    public Pentagon(LineInterface line) {
        super(line);
        numberOfVertices = 5;
    }

    public void setVertices(int vertices) {
        this.numberOfVertices = vertices;
    }

    public void drawPentagon(Point point) {
        int alpha = 270 - rotation;
        int deltaAngleR = 360 / numberOfVertices;
        int centerX = point.x;
        int centerY = point.y;
        Point first = null;
        Point prev = null;
        Point newPoint;
        for (int i = 0; i < numberOfVertices; i++) {
            newPoint = new Point(getX(alpha, centerX), getY(alpha, centerY));
            if (i == 0) {
                first = newPoint;
            } else {
                line.drawLine(newPoint, prev);
            }
            prev = newPoint;
            alpha += deltaAngleR;
        }
        line.drawLine(prev, first);
    }
}
