package nsu.graphics.firstlab.parameters;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static nsu.graphics.firstlab.MathUtils.isNumeric;

public class ParametersWindow extends JFrame {
    protected JPanel panel;

    ParametersWindow(int numberOfParameters) {
        super("Shape Parameters");
        setSize(300, 150);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);
        panel = new JPanel(new GridLayout(numberOfParameters, 3));
    }

    protected boolean checkValue(String val, int min, int max) {
        if (isNumeric(val)) {
            int rad = Integer.parseInt(val);
            if (rad >= min && rad <= max) {
                return true;
            }
        }
        showMessage(val);
        return false;
    }

    protected void showMessage(String str) {
        JOptionPane.showMessageDialog(this, str + " is bad value", "Parameter error", JOptionPane.INFORMATION_MESSAGE);
    }

    class TextBoxListener implements ActionListener {
        JTextField textBox;
        JSlider slider;
        int min;
        int max;
        public TextBoxListener(JTextField textBox, JSlider slider, int min, int max) {
            this.slider = slider;
            this.textBox = textBox;
            this.min = min;
            this.max = max;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String text = textBox.getText();
            System.out.println("New value: " + text);
            if (checkValue(text, min, max)) {
                int value = Integer.parseInt(text);
                slider.setValue(value);
            }
        }
    }

    class SliderListener implements ChangeListener {
        JTextField textBox;
        JSlider slider;
        public SliderListener(JTextField textBox, JSlider slider) {
            this.slider = slider;
            this.textBox = textBox;
        }
        @Override
        public void stateChanged(ChangeEvent e) {
            int value = ((JSlider)e.getSource()).getValue();
            textBox.setText(String.valueOf(value));
        }
    }
}
