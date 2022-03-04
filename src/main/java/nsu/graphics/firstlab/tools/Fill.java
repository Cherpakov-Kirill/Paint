package nsu.graphics.firstlab.tools;

import nsu.graphics.firstlab.shapes.DrawForShapes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Fill {
    private final DrawForShapes draw;
    private BufferedImage img;
    private int previousColor;
    private int fillColor;
    private Stack<Span> stack;
    private int width;
    private int height;

    public Fill(DrawForShapes draw) {
        this.draw = draw;
    }

    private int getColor(int x, int y) {
        return img.getRGB(x, y);
    }

    private Span findFirstSpan(Point start) {
        int y = start.y;
        int x = start.x;
        int xLeft;
        int xRight;
        while (previousColor == getColor(x - 1, y)) {
            x--;
            if(x==0) break;
        }
        xLeft = x;
        x = start.x;
        while (previousColor == getColor(x + 1, y)) {
            x++;
            if(x==width-1) break;
        }
        xRight = x;
        return new Span(new Point(xLeft, y), new Point(xRight, y));
    }

    private void findAllNewSpans(Span currentSpan, boolean findUnder) {
        int xLeft = currentSpan.getStartX();
        int xRight = currentSpan.getFinishX();
        int y;
        int x = xLeft;
        int xLeftSpan = -1;
        if (findUnder) y = currentSpan.getY() + 1;
        else y = currentSpan.getY() - 1;
        if(y==-1) return;
        if(y==height) return;
        if (getColor(x, y) == previousColor){
            xLeftSpan = x;
            while (xLeftSpan!=0 && previousColor == getColor(xLeftSpan - 1, y)) {
                xLeftSpan--;
            }
        }
        while (x <= xRight) {
            if(x==width-1) break;
            if (getColor(x + 1, y) == previousColor) {
                if (xLeftSpan == -1) {
                    xLeftSpan = x + 1;
                }
            } else {
                if (xLeftSpan != -1) {
                    stack.push(new Span(xLeftSpan, x, y));
                    xLeftSpan = -1;
                }
            }
            x++;
        }
        if (xLeftSpan != -1 && xLeftSpan!=x) {
            while (previousColor == getColor(x + 1, y)) {
                x++;
                if(x==width-1) break;
            }
            stack.push(new Span(xLeftSpan, x, y));
        }
    }

    public void makeFill(Point p) {
        Dimension dim = draw.getPreferredSize();
        width = dim.width;
        height = dim.height;
        img = draw.getImage();
        previousColor = getColor(p.x, p.y);
        fillColor = draw.getSelectedColor().getRGB();
        stack = new Stack<>();
        stack.push(findFirstSpan(p));

        while (!stack.isEmpty()) {
            Span span = stack.pop();
            span.paint(img, fillColor);
            findAllNewSpans(span,true);
            findAllNewSpans(span,false);
        }
    }
}
