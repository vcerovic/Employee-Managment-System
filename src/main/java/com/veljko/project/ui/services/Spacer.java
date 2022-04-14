package com.veljko.project.ui.services;

import javax.swing.JPanel;
import java.awt.Dimension;

public class Spacer extends JPanel {

    public Spacer(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
    }

}
