package nsu.graphics.firstlab;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.*;

import nsu.graphics.firstlab.parameters.LineParameters;
import nsu.graphics.firstlab.parameters.ShapeStandardParameters;
import nsu.graphics.firstlab.parameters.ShapeVerticesParameters;
import nsu.graphics.firstlab.parameters.DrawForParameters;
import ru.nsu.cg.MainFrame;


public class PaintMainWindow extends MainFrame implements ComponentListener {
    private final DrawPanel drawPanel;
    private final String[] extensions;

    public PaintMainWindow() {
        super(700, 500, "Paint");
        try {
            addSubMenu("File", KeyEvent.VK_F);
            addMenuItem("File/Open", "Open a file", KeyEvent.VK_O, "/Open.png", "openFile");
            addMenuItem("File/Save as", "Save your picture as file", KeyEvent.VK_S, "/Save.png", "saveFile");
            addMenuItem("File/Exit", "Exit application", KeyEvent.VK_X, "/Exit.png", "exit");

            addSubMenu("Tools", KeyEvent.VK_T);
            addRadioMenuItem("Tools/Clear", "Clear the draw field", KeyEvent.VK_C, "/Clear.png", "clear");
            addRadioMenuItem("Tools/Fill", "Fill the area", KeyEvent.VK_F, "/Fill.png", "fill");
            addRadioMenuItem("Tools/Pen", "Pen", KeyEvent.VK_B, "/Pen.png", "pen");
            addRadioMenuItem("Tools/Line", "Draw a line", KeyEvent.VK_L, "/Line.png", "drawLine");
            addRadioMenuItem("Tools/Triangle", "Draw a triangle", KeyEvent.VK_T, "/Triangle.png", "drawTriangle");
            addRadioMenuItem("Tools/Rectangle", "Draw a rectangle", KeyEvent.VK_R, "/Rectangle.png", "drawRectangle");
            addRadioMenuItem("Tools/Pentagon", "Draw a pentagon", KeyEvent.VK_P, "/Pentagon.png", "drawPentagon");
            addRadioMenuItem("Tools/Star", "Draw a star", KeyEvent.VK_S, "/Star.png", "drawStar");

            addSubMenu("Colors", KeyEvent.VK_C);
            addRadioMenuItem("Colors/Palette", "Palette", KeyEvent.VK_P, "/Palette.png", "chooseColor");
            addRadioMenuItem("Colors/Black", "Black", KeyEvent.VK_S, "/Colors/Black.png", "selectBlackColor");
            addRadioMenuItem("Colors/White", "White", KeyEvent.VK_W, "/Colors/White.png", "selectWhiteColor");
            addRadioMenuItem("Colors/Gray", "Gray", KeyEvent.VK_D, "/Colors/Gray.png", "selectGrayColor");
            addRadioMenuItem("Colors/Red", "Red", KeyEvent.VK_R, "/Colors/Red.png", "selectRedColor");
            addRadioMenuItem("Colors/Pink", "Pink", KeyEvent.VK_P, "/Colors/Pink.png", "selectPinkColor");
            addRadioMenuItem("Colors/Orange", "Orange", KeyEvent.VK_O, "/Colors/Orange.png", "selectOrangeColor");
            addRadioMenuItem("Colors/Yellow", "Yellow", KeyEvent.VK_Y, "/Colors/Yellow.png", "selectYellowColor");
            addRadioMenuItem("Colors/Green", "Green", KeyEvent.VK_G, "/Colors/Green.png", "selectGreenColor");
            addRadioMenuItem("Colors/Magenta", "Magenta", KeyEvent.VK_M, "/Colors/Magenta.png", "selectMagentaColor");
            addRadioMenuItem("Colors/Cyan", "Cyan", KeyEvent.VK_C, "/Colors/Cyan.png", "selectCyanColor");
            addRadioMenuItem("Colors/Blue", "Blue", KeyEvent.VK_B, "/Colors/Blue.png", "selectBlueColor");

            addSubMenu("Help", KeyEvent.VK_H);
            addMenuItem("Help/About...", "Shows program version and copyright information", KeyEvent.VK_A, "/About.png", "showAbout");

            addToolBarButton("File/Open");
            addToolBarButton("File/Save as");
            addToolBarSeparator();
            addToolBarToggleButton("Tools/Clear");
            addToolBarToggleButton("Tools/Fill");
            addToolBarSeparator();
            addToolBarToggleButton("Tools/Pen");
            addToolBarToggleButton("Tools/Line");
            addToolBarToggleButton("Tools/Triangle");
            addToolBarToggleButton("Tools/Rectangle");
            addToolBarToggleButton("Tools/Pentagon");
            addToolBarToggleButton("Tools/Star");
            addToolBarSeparator();
            addToolBarToggleButton("Colors/Black");
            addToolBarToggleButton("Colors/White");
            addToolBarToggleButton("Colors/Gray");
            addToolBarToggleButton("Colors/Red");
            addToolBarToggleButton("Colors/Pink");
            addToolBarToggleButton("Colors/Orange");
            addToolBarToggleButton("Colors/Yellow");
            addToolBarToggleButton("Colors/Green");
            addToolBarToggleButton("Colors/Magenta");
            addToolBarToggleButton("Colors/Cyan");
            addToolBarToggleButton("Colors/Blue");
            addToolBarSeparator();
            addToolBarToggleButton("Colors/Palette");



            drawPanel = new DrawPanel();
            JScrollPane scrollPane = new JScrollPane(drawPanel);
            add(scrollPane);
            addComponentListener(this);
            setBackground(Color.WHITE);
            selectColor("Colors/Black");
            selectTool("Tools/Line");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        extensions = new String[4];
        extensions[0] = "png";
        extensions[1] = "jpeg";
        extensions[2] = "bmp";
        extensions[3] = "gif";
    }

    //File/Open - opens any image file
    public void openFile() {
        File file = getOpenFileName(extensions);
        drawPanel.openFile(file);
        System.out.println("Opened file " + file.getAbsolutePath());
    }

    //File/Save - saves image file
    public void saveFile() {
        File file = getSaveFileName(extensions);
        drawPanel.saveFile(file);
        System.out.println("Saving file to " + file.getAbsolutePath());
    }

    public void clear() {
        selectTool("Tools/Clear");
        drawPanel.clear();
    }

    //Tools/Fill
    public void fill() {
        selectTool("Tools/Fill");
    }

    //Tools/Pen
    public void pen() {
        selectTool("Tools/Pen");
        LineParameters parameters = new LineParameters(drawPanel);
    }

    //Tools/Line - draw a line
    public void drawLine() {
        selectTool("Tools/Line");
        LineParameters parameters = new LineParameters(drawPanel);
    }

    //Tools/Triangle - draw a triangle
    public void drawTriangle() {
        selectTool("Tools/Triangle");
        ShapeStandardParameters parameters = new ShapeStandardParameters(drawPanel);
    }

    //Tools/Rectangle - draw a rectangle
    public void drawRectangle() {
        selectTool("Tools/Rectangle");
        ShapeStandardParameters parameters = new ShapeStandardParameters(drawPanel);
    }

    //Tools/Pentagon - draw a pentagon
    public void drawPentagon() {
        selectTool("Tools/Pentagon");
        ShapeVerticesParameters parameters = new ShapeVerticesParameters(drawPanel);
    }

    //Tools/Star - draw a star
    public void drawStar() {
        selectTool("Tools/Star");
        ShapeVerticesParameters parameters = new ShapeVerticesParameters(drawPanel);
    }

    //Colors/Palette
    public void chooseColor() {
        Color color = JColorChooser.showDialog(this,
                "Choose color", Color.BLACK);
        if(color != null){
            drawPanel.setSelectedColor(color);
            JRadioButtonMenuItem item = menuColorsMap.get("Colors/Palette");
            item.setSelected(true);
            JToggleButton button = menuToolbarColorsMap.get("Colors/Palette");
            button.setSelected(true);
        }

    }

    //Colors/Black
    public void selectBlackColor() {
        selectColor("Colors/Black");
        System.out.println("Select a black color");
    }

    //Colors/White
    public void selectWhiteColor() {
        selectColor("Colors/White");
        System.out.println("Select a white color");
    }

    //Colors/Gray
    public void selectGrayColor() {
        selectColor("Colors/Gray");
        System.out.println("Select a gray color");
    }

    //Colors/Red
    public void selectRedColor() {
        selectColor("Colors/Red");
        System.out.println("Select a red color");
    }

    //Colors/Pink
    public void selectPinkColor() {
        selectColor("Colors/Pink");
        System.out.println("Select a pink color");
    }

    //Colors/Orange
    public void selectOrangeColor() {
        selectColor("Colors/Orange");
        System.out.println("Select a orange color");
    }

    //Colors/Yellow
    public void selectYellowColor() {
        selectColor("Colors/Yellow");
        System.out.println("Select a yellow color");
    }

    //Colors/Green
    public void selectGreenColor() {
        selectColor("Colors/Green");
        System.out.println("Select a green color");
    }

    //Colors/Magenta
    public void selectMagentaColor() {
        selectColor("Colors/Magenta");
        System.out.println("Select a magenta color");
    }

    //Colors/Cyan
    public void selectCyanColor() {
        selectColor("Colors/Cyan");
        System.out.println("Select a cyan color");
    }

    //Colors/Blue
    public void selectBlueColor() {
        selectColor("Colors/Blue");
        System.out.println("Select a blue color");
    }

    //File/Exit - exits application
    public void exit() {
        System.exit(0);
    }

    public void selectTool(String title) {
        drawPanel.setSelectedTool(title.substring(title.lastIndexOf("/") + 1));
        JRadioButtonMenuItem item = menuToolsMap.get(title);
        item.setSelected(true);
        JToggleButton button = menuToolbarToolsMap.get(title);
        button.setSelected(true);
    }

    public void selectColor(String title) {
        drawPanel.setSelectedColor(title.substring(title.lastIndexOf("/") + 1));
        JRadioButtonMenuItem item = menuColorsMap.get(title);
        item.setSelected(true);
        JToggleButton button = menuToolbarColorsMap.get(title);
        button.setSelected(true);
    }


    //Help/About... - shows program version and copyright information
    public void showAbout() {
        JOptionPane.showMessageDialog(this, "Paint App. ver. 1.0\nCopyright 2022 Cherpakov Kirill, FIT, group 19201\nProgram for drawing.", "About Paint App", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        PaintMainWindow mainFrame = new PaintMainWindow();
        mainFrame.setVisible(true);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        drawPanel.componentResized();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
