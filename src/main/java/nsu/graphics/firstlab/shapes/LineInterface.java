package nsu.graphics.firstlab.shapes;

import java.awt.*;

public interface LineInterface {
    void drawLine(Point start, Point finish);
    void drawLine(Point start, Point finish, Color color, int thickness);
}
