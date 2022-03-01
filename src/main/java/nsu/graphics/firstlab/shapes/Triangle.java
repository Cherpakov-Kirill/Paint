package nsu.graphics.firstlab.shapes;

import java.awt.*;

public class Triangle extends Polygon{
    public Triangle(LineInterface line){
        super(line);
    }

    public void drawRectangle(Point point){
        int alpha = 270 - rotation;
        int centerX = point.x;
        int centerY = point.y;
        Point first = new Point(getX(alpha,centerX),getY(alpha,centerY));
        alpha-=120;
        Point second = new Point(getX(alpha,centerX),getY(alpha,centerY));
        line.drawLine(first,second);
        alpha-=120;
        Point third = new Point(getX(alpha,centerX),getY(alpha,centerY));
        line.drawLine(second,third);
        line.drawLine(third,first);
    }
}
