package nsu.graphics.firstlab.shapes;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface DrawForShapes {
    Dimension getPreferredSize();
    Graphics2D getImageGraphics();
    BufferedImage getImage();
    Color getSelectedColor();
}
