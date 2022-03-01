package nsu.graphics.firstlab.shapes;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Line implements LineInterface {
    private Point first;
    private int thickness;
    private final DrawForShapes draw;
    private Dimension dim;

    public Line(DrawForShapes draw) {
        this.draw = draw;
        thickness = 1;
    }

    public void setClick(Point p) {
        if (first == null) first = p;
        else {
            drawLine(first, p, draw.getSelectedColor(), thickness);
            first = null;
        }
    }

    public void drawBrezenhamLine(Point start, Point finish, Color color) {
        BufferedImage img = draw.getImage();
        dim = draw.getPreferredSize();
        int x = start.x;
        int y = start.y;
        int dx = finish.x - start.x;
        int dy = finish.y - start.y;
        int directX = Integer.signum(dx);
        int directY = Integer.signum(dy);

        dx = Math.abs(dx);
        dy = Math.abs(dy);
        int stepDx;
        int stepDy;
        int es;
        int el;

        if (dx > dy) {
            stepDx = directX;
            stepDy = 0;
            es = dy;
            el = dx;
        } else {
            stepDx = 0;
            stepDy = directY;
            es = dx;
            el = dy;
        }
        int err = -el;
        if (x >= 0 && x <= dim.width && y >= 0 && y <= dim.height) img.setRGB(x, y, color.getRGB());
        for (int i = 0; i < el; i++) {
            err += 2 * es;
            if (err > 0) {
                err -= 2 * el;
                x += directX;
                y += directY;
            } else {
                x += stepDx;
                y += stepDy;
            }
            if (x >= 0 && x < dim.width && y >= 0 && y < dim.height) img.setRGB(x, y, color.getRGB());

        }
    }

    @Override
    public void drawLine(Point start, Point finish, Color color, int thickness) {
        if (thickness == 1) drawBrezenhamLine(start, finish, color);
        else {
            Graphics2D g = draw.getImageGraphics();
            g.setColor(color);
            g.setStroke(new BasicStroke(thickness));
            g.drawLine(start.x, start.y, finish.x, finish.y);
        }
    }

    @Override
    public void drawLine(Point start, Point finish) {
        drawBrezenhamLine(start, finish, Color.BLACK);
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }
}
