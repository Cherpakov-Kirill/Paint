package nsu.graphics.firstlab;

import nsu.graphics.firstlab.parameters.DrawForParameters;
import nsu.graphics.firstlab.shapes.*;
import nsu.graphics.firstlab.shapes.Rectangle;
import nsu.graphics.firstlab.tools.Fill;
import nsu.graphics.firstlab.tools.Pen;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class DrawPanel extends JPanel implements DrawForShapes, DrawForParameters {
    private String selectedTool;
    private Color selectedColor;
    private BufferedImage image;
    private int widthOfPanel;
    private int heightOfPanel;
    private final Line line;
    private final Pen pen;
    private final Fill fill;
    private final Rectangle rectangle;
    private final Triangle triangle;
    private final Pentagon pentagon;
    private final Star star;
    private final PenMouseListener penMouseListener;
    private final ShapesMouseListener shapesMouseListener;

    public DrawPanel() {
        createEmptyImage(300, 200);


        fill = new Fill(this);
        line = new Line(this);
        rectangle = new Rectangle(line);
        triangle = new Triangle(line);
        pentagon = new Pentagon(line);
        star = new Star(line);
        pen = new Pen(this, line);
        penMouseListener = new PenMouseListener();
        shapesMouseListener = new ShapesMouseListener();
        addMouseListener(shapesMouseListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) g.drawImage(image, 0, 0, null);
    }

    @Override
    public Graphics2D getImageGraphics() {
        return (Graphics2D) image.getGraphics();
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public Color getSelectedColor() {
        return selectedColor;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }

    public void clear() {
        createEmptyImage(getWidth(), getHeight());
        widthOfPanel = getWidth();
        heightOfPanel = getHeight();
        repaint();
    }

    private void createEmptyImage(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.fillRect(0, 0, width, height);
        g.dispose();
    }

    public void componentResized() {
        int width = getWidth();
        int height = getHeight();
        if (!(width < widthOfPanel || height < heightOfPanel)) {
            BufferedImage newResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = newResizedImage.createGraphics();
            g.setComposite(AlphaComposite.Src);
            g.fillRect(0, 0, width, height);
            // configure RenderingHints
            g.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            // draw a new image
            g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
            g.dispose();
            image = newResizedImage;
            widthOfPanel = width;
            heightOfPanel = height;
            repaint();
        }
    }

    class PenMouseListener extends MouseInputAdapter {
        public void mousePressed(MouseEvent e) {
            pen.mousePressed(e.getPoint());
        }

        public void mouseReleased(MouseEvent e) {
            pen.mouseReleased();
        }

        public void mouseDragged(MouseEvent e) {
            pen.mouseDragged(e.getPoint());
            repaint();
        }
    }

    class ShapesMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (selectedTool == null) return;
            switch (selectedTool) {
                case "Fill" -> {
                    fill.makeFill(e.getPoint());
                }
                case "Line" -> {
                    line.setClick(e.getPoint());
                }
                case "Triangle" -> {
                    triangle.drawRectangle(e.getPoint());
                }
                case "Rectangle" -> {
                    rectangle.drawRectangle(e.getPoint());
                }
                case "Pentagon" -> {
                    pentagon.drawPentagon(e.getPoint());
                }
                case "Star" -> {
                    star.drawPentagon(e.getPoint());
                }
            }
            repaint();
        }
    }

    public void setSelectedColor(String selectedColor) {
        switch (selectedColor) {
            case "Black" -> this.selectedColor = Color.BLACK;
            case "White" -> this.selectedColor = Color.WHITE;
            case "Gray" -> this.selectedColor = Color.GRAY;
            case "Red" -> this.selectedColor = Color.RED;
            case "Pink" -> this.selectedColor = Color.PINK;
            case "Orange" -> this.selectedColor = Color.ORANGE;
            case "Yellow" -> this.selectedColor = Color.YELLOW;
            case "Green" -> this.selectedColor = Color.GREEN;
            case "Magenta" -> this.selectedColor = Color.MAGENTA;
            case "Cyan" -> this.selectedColor = Color.CYAN;
            case "Blue" -> this.selectedColor = Color.BLUE;
        }
    }

    public void setSelectedColor(Color color) {
        this.selectedColor = color;
    }

    public void setSelectedTool(String selectedTool) {
        if (Objects.equals(this.selectedTool, "Pen") && !Objects.equals(selectedTool, "Pen")) {
            removeMouseListener(penMouseListener);
            removeMouseMotionListener(penMouseListener);
            addMouseListener(shapesMouseListener);
        }
        if (!Objects.equals(this.selectedTool, "Pen") && Objects.equals(selectedTool, "Pen")) {
            removeMouseListener(shapesMouseListener);
            addMouseListener(penMouseListener);
            addMouseMotionListener(penMouseListener);
        }
        this.selectedTool = selectedTool;
    }

    @Override
    public void setParameters(int thickness) {
        switch (selectedTool) {
            case "Line" -> {
                line.setThickness(thickness);
            }
            case "Pen" -> {
                pen.setThickness(thickness);
            }
        }
    }

    @Override
    public void setParameters(int radius, int rotation) {
        switch (selectedTool) {
            case "Triangle" -> {
                triangle.setRadius(radius);
                triangle.setRotation(rotation);
            }
            case "Rectangle" -> {
                rectangle.setRadius(radius);
                rectangle.setRotation(rotation);
            }
        }
    }

    @Override
    public void setParameters(int radius, int rotation, int vertices) {
        switch (selectedTool) {
            case "Star" -> {
                star.setRadius(radius);
                star.setRotation(rotation);
                star.setVertices(vertices);
            }
            case "Pentagon" -> {
                pentagon.setRadius(radius);
                pentagon.setRotation(rotation);
                pentagon.setVertices(vertices);
            }
        }
    }

    public void saveFile(File file) {
        try {
            String filename = file.getName();
            String extension = filename.substring(filename.lastIndexOf(".")+1);
            System.out.println(extension);
            ImageIO.write(image, extension, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFile(File file) {
        try {
            image = ImageIO.read(file);
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
