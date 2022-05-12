package com.veljko.project.ui.services;

import java.awt.Color;

public enum Colors {
    PRIMARY_COLOR(new Color(24, 43, 65)),
    SECONDARY_COLOR(new Color(255, 128, 0)),
    HOVER_COLOR(new Color(184, 207, 229)),
    DISABLED_COLOR(new Color(74, 93, 124));

    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
