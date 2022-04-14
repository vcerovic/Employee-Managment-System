package com.veljko.project.ui.components;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Dimension;

public class PageController extends JPanel {

    private final CardLayout cardLayout;

    public PageController(CardLayout cardLayout, Dimension dimension) {
        this.cardLayout = cardLayout;
        this.setLayout(cardLayout);
        this.setPreferredSize(dimension);
    }

    public void showPage(String pageName) {
        cardLayout.show(this, pageName);
    }
}
