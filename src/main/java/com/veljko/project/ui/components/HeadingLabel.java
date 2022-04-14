package com.veljko.project.ui.components;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class HeadingLabel extends JLabel {

    public HeadingLabel(String text, Color color) {
        super(text);
        this.setPreferredSize(new Dimension(1130,120));
        this.setFont(new Font("Times New Roman", Font.BOLD, 53));
        this.setForeground(color);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
    }
}
