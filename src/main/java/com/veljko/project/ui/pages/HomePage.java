package com.veljko.project.ui.pages;

import com.veljko.project.ui.services.Colors;
import com.veljko.project.ui.components.HeadingLabel;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomePage extends JPanel {

    public HomePage() {
        this.setName("HomePage");
        this.add(new HeadingLabel("Employee Management System", Colors.PRIMARY_COLOR.getColor()));

        try {
            BufferedImage infoPicture = ImageIO.read(new File("src/main/java/com/veljko/project/icons/info.png"));
            this.add(new JLabel(new ImageIcon(infoPicture)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
