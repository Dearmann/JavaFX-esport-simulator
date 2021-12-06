/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalab.controller;

import exception.NotEnoughPlayersException;
import exception.WrongPlayerPositionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javalab.model.Team;
import javalab.view.MainMenuView;

/**
 * Main controller of application.
 *
 * @author Dominik Uszok
 * @version 1.0
 */
public class MainController {

    /**
     * Console output. View part of MVC architecture
     */
    private final MainMenuView mainMenuView;

    /**
     * Scanner class instance for getting user information.
     */
    Scanner sc = new Scanner(System.in);

    /**
     * All teams assigned to e-sports tournament
     */
    private final List<Team> allTeamsInTournament;

    /**
     * Constructor of controller instance.
     *
     * @param TeamViewInstance viewer of MVC architecture
     */
    public MainController(MainMenuView TeamViewInstance) {
        this.mainMenuView = TeamViewInstance;
        this.allTeamsInTournament = new ArrayList<Team>();
    }

    /**
     * Parse input switch parameter
     *
     * @param args input parameters
     * @return true if input switch parameter is correct
     */
    public boolean isInputSwitchCorrect(String[] args) {
        String commandLineSwitch;
        if (args.length == 1) {
            commandLineSwitch = args[0];
            if ("-v".equals(commandLineSwitch)) {
                return true;
            }
        }
        mainMenuView.printInputErrorMessage();
        return false;
    }

    /**
     * Add team to tournament.
     *
     * @param teamToAdd team to add
     */
    public void addTeam(Team teamToAdd) {
        allTeamsInTournament.add(teamToAdd);
    }

    /**
     * Select team to modification based on team number.
     *
     * @param teamNumber team number
     * @return selected team
     */
    private Team selectTeam(int teamNumber) {
        if (allTeamsInTournament.isEmpty()) {
            mainMenuView.noTeamsMessage();
            return null;
        }
        for (Team team : allTeamsInTournament) {
            if (teamNumber == team.getTeamNumber()) {
                return team;
            }
        }
        mainMenuView.invalidTeamNumber();
        return null;
    }

    /**
     * Select team to modification based on team name.
     *
     * @param teamName team name
     * @return selected team
     */
    private Team selectTeam(String teamName) {
        if (allTeamsInTournament.isEmpty()) {
            mainMenuView.noTeamsMessage();
            return null;
        }
        for (Team team : allTeamsInTournament) {
            if (teamName.equals(team.getTeamName())) {
                return team;
            }
        }
        return null;
    }

    /**
     * Remove team for tournament.
     *
     * @param teamToRemove team to remove
     */
    private void removeTeam(Team teamToRemove) {
        allTeamsInTournament.remove(teamToRemove);
    }

    /**
     * Iterate through all teams and players and show their information.
     */
    public void printAllTeams() {
        for (Team teamToPrint : allTeamsInTournament) {
            String teamStrength = "0";
            try {
                teamStrength = "" + teamToPrint.calculateTeamStrength();
            } catch (NotEnoughPlayersException ex) {
                teamStrength = "Not enough players";
            }
            mainMenuView.printTeam(teamToPrint.getTeamNumber(),
                    teamToPrint.getTeamName(),
                    teamToPrint.getTeamRanking(),
                    teamStrength);
            for (int i = 0; i < teamToPrint.getTeamPlayers().size(); i++) {

                String playerName = teamToPrint.getTeamPlayers().get(i).getPlayerName();
                int playerStrength = teamToPrint.getTeamPlayers().get(i).getPlayerStrength();
                mainMenuView.printPlayerInformation(playerName, playerStrength);

            }
        }
    }

    /**
     * Print menu.
     */
    private void printMenu() {
        mainMenuView.printMenu();
    }

    /**
     * Add player to team.
     *
     * @param playerPosition player position
     * @param selectedTeam selected team to add player to
     */
    private void addPlayerToTeam(int playerPosition, Team selectedTeam) {
        mainMenuView.askAboutPlayerName();
        sc.nextLine();
        String playerName = sc.nextLine();
        mainMenuView.askAboutPlayerStrength();
        int playerStrength = sc.nextInt();
        try {
            selectedTeam.addPlayer(playerPosition, playerName, playerStrength);
        } catch (WrongPlayerPositionException ex) {
            mainMenuView.printExceptionMessage(ex);
        }
    }

    /**
     * Determine match winner.
     *
     * @param teamOne team one
     * @param teamTwo team two
     */
    private void determineWinner(Team teamOne, Team teamTwo) {
        try {
            if (teamOne.calculateTeamStrength() > teamTwo.calculateTeamStrength()) {
                mainMenuView.printWinner(teamOne.getTeamName(), teamTwo.getTeamName());
            } else if (teamOne.calculateTeamStrength() < teamTwo.calculateTeamStrength()) {
                mainMenuView.printWinner(teamTwo.getTeamName(), teamOne.getTeamName());
            } else {
                mainMenuView.printDraw(teamOne.getTeamName(), teamTwo.getTeamName());
            }
        } catch (NotEnoughPlayersException ex) {
            mainMenuView.printExceptionMessage(ex);
        }
    }

    /**
     * Activate menu loop
     */
    public void activateMenu() {
        Team selectedTeam;
        while (true) {
            printMenu();
            switch (sc.nextInt()) {
                case 1:
                    printAllTeams();
                    break;
                case 2:
                    mainMenuView.askAboutTeamName();
                    sc.nextLine();
                    String newTeamName = sc.nextLine();
                    mainMenuView.askAboutTeamRank();
                    int teamRank = sc.nextInt();
                    addTeam(new Team(newTeamName, teamRank));
                    break;
                case 3:
                    removeTeam(selectTeam(sc.nextInt()));
                    break;
                case 4:
                    sc.nextLine();
                    removeTeam(selectTeam(sc.nextLine()));
                    break;
                case 5:
                    mainMenuView.askAboutTeamNumber();
                    selectedTeam = selectTeam(sc.nextInt());
                    if (selectedTeam == null) {
                        break;
                    }
                    mainMenuView.askAboutPlayerPosition();
                    int playerPosition = sc.nextInt();
                    addPlayerToTeam(playerPosition - 1, selectedTeam);
                    break;
                case 6:
                    mainMenuView.askAboutTeamNumber();
                    selectedTeam = selectTeam(sc.nextInt());
                    if (selectedTeam == null) {
                        break;
                    }
                    for (int i = 0; i < 5; i++) {
                        mainMenuView.printPlayerPositionToAdd(i + 1);
                        addPlayerToTeam(i, selectedTeam);
                    }
                    break;
                case 7:
                    mainMenuView.askAboutTeamNumber();
                    Team teamOne = selectTeam(sc.nextInt());
                    if (teamOne == null) {
                        break;
                    }
                    mainMenuView.askAboutTeamNumber();
                    Team teamTwo = selectTeam(sc.nextInt());
                    if (teamTwo == null) {
                        break;
                    }
                    determineWinner(teamOne, teamTwo);
                    break;
                case 8:
                    return;
                default:
                    return;
            }
        }
    }
}
