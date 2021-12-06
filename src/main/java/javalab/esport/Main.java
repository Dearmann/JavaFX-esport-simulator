/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalab.esport;

import exception.WrongPlayerPositionException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javalab.controller.MainController;
import javalab.model.Team;
import javalab.view.MainMenuView;

/**
 * Entry point of application, with main method.
 *
 * @author Dominik Uszok
 * @version 1.0
 */
public class Main {

    /**
     * Entry method - generates some starting objects, displays menu or parses
     * input parameter. Valid input parameter is: "-v" which displays all teams.
     *
     * @param args launch parameters
     */
    public static void main(String[] args) {
        // Create view
        MainMenuView mainMenuView = new MainMenuView();

        // Create controller
        MainController mainController = new MainController(mainMenuView);

        // Create elements of model
        List<Team> teamList = new ArrayList<>();
        teamList.add(new Team("SKT1", 1));
        teamList.add(new Team("Misfits", 5));
        teamList.add(new Team("G2", 4));
        teamList.add(new Team("Fnatic", 2));
        teamList.add(new Team("Team Liquid", 3));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                try {
                    teamList.get(i).addPlayer(j, "Player" + (j + 1), j + i);
                } catch (WrongPlayerPositionException ex) {
                    ex.getMessage();
                }
            }
            mainController.addTeam(teamList.get(i));
        }

        // Parse input parameter or display menu
        if (mainController.isInputSwitchCorrect(args)) {
            mainController.printAllTeams();
        } else {
            try {
                mainController.activateMenu();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input type");
            }
        }
    }
}
