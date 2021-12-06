/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalab.view;

/**
 * Class that prints information to console.
 *
 * @author Dominik Uszok
 * @version 1.0
 */
public class MainMenuView {

    /**
     * Print team information.
     *
     * @param teamNumber team number
     * @param teamName team name
     * @param teamRank team rank
     */
    public void printTeam(int teamNumber, String teamName, int teamRank, String teamStrength) {
        System.out.println("-------------------------");
        System.out.println("Team nr: " + teamNumber);
        System.out.println("Team name: " + teamName);
        System.out.println("Team rank: " + teamRank);
        System.out.println("Team strength: " + teamStrength);
    }

    /**
     * Print when command line switch is invalid.
     */
    public void printInputErrorMessage() {
        System.out.println("Invalid command line switch");
    }

    /**
     * Print menu when input parameter is wrong or there is none.
     */
    public void printMenu() {
        System.out.println("Menu: ");
        System.out.println("1. Display teams");
        System.out.println("2. Create new team");
        System.out.println("3. Delete team by nr");
        System.out.println("4. Delete team by name");
        System.out.println("5. Add player to team");
        System.out.println("6. Add all players to team");
        System.out.println("7. Match two teams agains each other");
        System.out.println("8. Exit");
    }

    /**
     * Ask user for team name.
     */
    public void askAboutTeamName() {
        System.out.println("Enter team name: ");
    }

    /**
     * Ask user for team number.
     */
    public void askAboutTeamNumber() {
        System.out.println("Enter team number: ");
    }

    /**
     * Ask user for team rank.
     */
    public void askAboutTeamRank() {
        System.out.println("Enter team rank: ");
    }

    /**
     * Ask user for player position.
     */
    public void askAboutPlayerPosition() {
        System.out.println("Enter player position (1-5): ");
    }

    /**
     * Ask user for player name.
     */
    public void askAboutPlayerName() {
        System.out.println("Enter player name: ");
    }

    /**
     * Ask user for player strength.
     */
    public void askAboutPlayerStrength() {
        System.out.println("Enter player strength: ");
    }

    /**
     * Inform user about position the player is being added.
     *
     * @param playerPosition player position
     */
    public void printPlayerPositionToAdd(int playerPosition) {
        System.out.println("You are adding player at position: " + playerPosition);
    }

    /**
     * Print player information.
     *
     * @param playerName player name
     * @param playerStrength player strength
     */
    public void printPlayerInformation(String playerName, int playerStrength) {
        System.out.println("  Player: " + playerName + " with strength: " + playerStrength);
    }

    /**
     * Print information about invalid team number.
     */
    public void invalidTeamNumber() {
        System.out.println("This team number is invalid");
    }

    /**
     * Print information about no teams to choose from.
     */
    public void noTeamsMessage() {
        System.out.println("No teams to choose from!");
    }

    /**
     * Print winner of a match.
     *
     * @param winnerName winner name
     * @param loserName loser name
     */
    public void printWinner(String winnerName, String loserName) {
        System.out.println(winnerName + " vs " + loserName + ": " + winnerName + " Wins!");
    }

    /**
     * Print draw of a match.
     *
     * @param winnerName team one name
     * @param loserName team two name
     */
    public void printDraw(String winnerName, String loserName) {
        System.out.println(winnerName + " vs " + loserName + ": Draw!");
    }

    /**
     * Print exception message.
     *
     * @param ex exception message
     */
    public void printExceptionMessage(Exception ex) {
        System.out.println(ex.getMessage());
    }
}
