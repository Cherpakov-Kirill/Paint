package nsu.graphics.firstlab.parameters;

import javax.swing.*;

public class LineParameters extends ParametersWindow {
    public LineParameters(DrawForParameters draw) {
        super(1);

        JTextField thickness = new JTextField("5", 3);
        JLabel label1 = new JLabel("Thickness = ");
        JSlider thicknessSlider = new JSlider(1,500,5);
        thickness.addActionListener(new TextBoxListener(thickness,thicknessSlider, 0, 500));
        thicknessSlider.addChangeListener(new SliderListener(thickness,thicknessSlider));
        panel.add(label1);
        panel.add(thickness);
        panel.add(thicknessSlider);

        int res = JOptionPane.showConfirmDialog(this, panel, "Line Parameters", JOptionPane.OK_CANCEL_OPTION);
        if (res == 0 && checkValue(thickness.getText(), 1, 500)){
            draw.setParameters(Integer.parseInt(thickness.getText()));
        }
    }
}
