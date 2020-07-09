package gui;

import javax.swing.*;
import java.awt.*;

public class UserPanel {

    private final MyFrame frame;
    private final Painter painter;
    private static final UserPanel panel = new UserPanel(new MyFrame());

    private UserPanel(MyFrame frame) {
        this.frame = frame;
        this.painter = new Painter();
        this.setupComponents();
    }

    public static UserPanel getInstance() {
        return panel;
    }

    private void setupComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(200, 400));
        panel.setLocation(0, 0);

        JButton button = new JButton("Draw polygon");
        button.setBounds(10, 210, 140, 25);

        JLabel labelScale = new JLabel("Scale");
        labelScale.setBounds(10, 50, 40, 20);

        JSlider scaleSlider = new JSlider(-10, 10, 0);
        scaleSlider.setBounds(10, 70, 140, 40);
        scaleSlider.setMajorTickSpacing(5);
        scaleSlider.setMinorTickSpacing(1);
        scaleSlider.setPaintTicks(true);
        scaleSlider.setPaintLabels(true);
        scaleSlider.setPaintTrack(true);

        JLabel labelRotate = new JLabel("Rotate");
        labelRotate.setBounds(10, 110, 40, 20);

        JSlider rotateSlider = new JSlider(-10, 10, 0);
        rotateSlider.setBounds(10, 130, 140, 40);
        rotateSlider.setMajorTickSpacing(5);
        rotateSlider.setMinorTickSpacing(1);
        rotateSlider.setPaintTicks(true);
        rotateSlider.setPaintLabels(true);
        rotateSlider.setPaintTrack(true);

        painter.setBounds(200, 0, 400, 400);
        button.addActionListener((e) -> painter.drawPolygon());

        panel.add(button);
        panel.add(labelScale);
        panel.add(scaleSlider);
        panel.add(labelRotate);
        panel.add(rotateSlider);
        panel.add(painter);

        frame.add(panel);
        frame.pack();
    }
}
