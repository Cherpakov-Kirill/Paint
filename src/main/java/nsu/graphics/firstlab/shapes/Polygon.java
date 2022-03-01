package nsu.graphics.firstlab.shapes;

import static nsu.graphics.firstlab.MathUtils.cosDeg;
import static nsu.graphics.firstlab.MathUtils.sinDeg;

public class Polygon {
    protected LineInterface line;
    protected int radius;
    protected int rotation;
    Polygon(LineInterface line){
        this.line = line;
        radius = 50;
        rotation = 0;
    }

    protected int getX(int angle, int centerX){
        double cos = cosDeg(angle);
        return centerX + (int)(cos * radius);
    }

    protected int getY(int angle, int centerY){
        double sin = sinDeg(angle);
        return centerY + (int)(sin * radius);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
}
