package com.veljko.project.ui.components;

import com.veljko.project.ui.services.Colors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ActionButton extends JButton {

    public ActionButton(String imgPath) {
        super(new ImageIcon(imgPath));

        styleActionButton();

    }

    public ActionButton(String imgPath, String actionCommand) {
        super(new ImageIcon(imgPath));
        this.setActionCommand(actionCommand);

        styleActionButton();
    }

    public static void addHoverEffect(ActionButton btn) {

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(btn.isEnabled()){
                    btn.setBackground(Colors.SECONDARY_COLOR.getColor());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(btn.isEnabled()){
                    btn.setBackground(Colors.PRIMARY_COLOR.getColor());
                }
            }
        });

    }

    public static void changeButtonsState(JButton[] btns){
        for(JButton btn : btns){
            if(btn.isEnabled()){
                btn.setBackground(Colors.PRIMARY_COLOR.getColor());
            } else {
                btn.setBackground(Colors.DISABLED_COLOR.getColor());
            }
        }
    }

    private void styleActionButton() {
        this.setBackground(Colors.PRIMARY_COLOR.getColor());
        this.setPreferredSize(new Dimension(100, 50));
        this.setBorder(new LineBorder(Color.WHITE, 0));
        this.setFocusable(false);
    }


}
