package com.veljko.project.ui.components;

import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class SplitWrapper extends JSplitPane {

    public SplitWrapper(int newOrientation, JComponent leftComp, JComponent rightComp) {
        super(newOrientation);

        this.setLeftComponent(leftComp);
        this.setRightComponent(rightComp);
        this.setDividerSize(0);
        this.setEnabled(false);
        this.setBorder(new LineBorder(Color.WHITE, 0));
    }

}
