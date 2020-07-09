package gui;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame(){
        super();
        setPreferredSize(new Dimension(600,400));
        setSize(new Dimension(600, 400));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(100,100);
        setVisible(true);
    }
}
