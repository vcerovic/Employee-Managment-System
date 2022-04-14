package com.veljko.project;

import com.veljko.project.ui.components.AppFrame;
import com.veljko.project.ui.components.LinkButton;
import com.veljko.project.ui.components.PageController;
import com.veljko.project.ui.components.SplitWrapper;
import com.veljko.project.ui.pages.EmployeeSearchPage;
import com.veljko.project.ui.pages.EmployeeSettingsPage;
import com.veljko.project.ui.pages.EmployeesPage;
import com.veljko.project.ui.pages.HomePage;
import com.veljko.project.ui.services.Colors;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {
        AppFrame frame = new AppFrame();

        SplitWrapper splitWrapper = handleGuiCreation();

        frame.add(splitWrapper);
        frame.setVisible(true);
    }

    private static SplitWrapper handleGuiCreation() {
        //Controls Panel
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(5, 1));
        controlsPanel.setPreferredSize(new Dimension(150, 720));
        controlsPanel.setBackground(Colors.PRIMARY_COLOR.getColor());

        //Pages Panel
        PageController pageController = new PageController(new CardLayout(), new Dimension(1130, 720));

        //Pages
        HomePage homePage = new HomePage();
        EmployeesPage employeesPage = new EmployeesPage();
        EmployeeSettingsPage employeeSettingsPage = new EmployeeSettingsPage();
        EmployeeSearchPage employeeSearchPage = new EmployeeSearchPage();

        JPanel[] pages = {homePage, employeesPage, employeeSettingsPage, employeeSearchPage};

        for (JPanel page : pages) {
            pageController.add(page, page.getName());
        }


        //Link Buttons
        LinkButton homeBtn = new LinkButton("src/main/java/com/veljko/project/icons/home.png", true, "HomePage");
        LinkButton employeesBtn = new LinkButton("src/main/java/com/veljko/project/icons/employee.png", false, "EmployeesPage");
        LinkButton settingsBtn = new LinkButton("src/main/java/com/veljko/project/icons/employee_settings.png", false, "EmployeeSettingsPage");
        LinkButton searchBtn = new LinkButton("src/main/java/com/veljko/project/icons/employee_search.png", false, "EmployeeSearchPage");

        LinkButton[] buttons = {homeBtn, employeesBtn, settingsBtn, searchBtn};

        for (LinkButton btn : buttons) {
            LinkButton.addHoverEffect(btn);
            LinkButton.addActiveChangingEffect(buttons, btn);
            controlsPanel.add(btn);

            btn.addActionListener(e -> pageController.showPage(btn.getPageName()));
        }

        employeesBtn.addActionListener(e -> employeesPage.fetchEmployees());

        //Split Pane
        return new SplitWrapper(JSplitPane.HORIZONTAL_SPLIT, controlsPanel, pageController);
    }

}