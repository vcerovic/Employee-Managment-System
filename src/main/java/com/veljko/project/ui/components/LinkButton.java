package com.veljko.project.ui.components;

import com.veljko.project.ui.services.Colors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LinkButton extends JButton {

    private final String pageName;

    public LinkButton(String iconPath, boolean isActive, String pageName) {
        super(new ImageIcon(iconPath));

        this.pageName = pageName;
        this.setName(isActive ? "active" : "no-active");

        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.setBackground(isActive ? Colors.SECONDARY_COLOR.getColor() : Colors.PRIMARY_COLOR.getColor());
        this.setBorder(new LineBorder(Color.WHITE, 0));
        this.setFocusable(false);

    }

    public static void addHoverEffect(LinkButton btn) {
        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!btn.getName().equals("active")) {
                    btn.setBackground(Colors.HOVER_COLOR.getColor());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!btn.getName().equals("active")) {
                    btn.setBackground(Colors.PRIMARY_COLOR.getColor());
                }
            }
        });
    }

    public static void addActiveChangingEffect(LinkButton[] buttons, LinkButton btn) {
        btn.addActionListener(e -> {
            JButton clickedBtn = (JButton) e.getSource();
            clickedBtn.setName("active");
            for (JButton otherBtn : buttons) {
                if (otherBtn != clickedBtn) {
                    otherBtn.setName("no-active");
                    otherBtn.setBackground(Colors.PRIMARY_COLOR.getColor());
                }
            }
            clickedBtn.setBackground(Colors.SECONDARY_COLOR.getColor());
        });
    }

    public String getPageName() {
        return pageName;
    }
}
