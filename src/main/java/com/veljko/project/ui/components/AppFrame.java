package com.veljko.project.ui.components;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class AppFrame extends JFrame {

    public AppFrame() {
        this.setContentPane(new JPanel());
        this.setTitle("Employee Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1280, 720));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(255, 255, 255));
        g2.fillRect(0, 0, 1280, 36);

    }


}
