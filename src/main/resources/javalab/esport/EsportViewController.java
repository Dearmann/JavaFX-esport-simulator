/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javalab.esport;

import exception.NotEnoughPlayersException;
import exception.WrongPlayerPositionException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javalab.model.Player;
import javalab.model.Team;

/**
 * FXML Controller class
 *
 * @author Dominik Uszok
 */
public class EsportViewController implements Initializable {

    public ObservableList<Team> teamList;
    private ListView<Team> teamListToDelete;

    @FXML
    private GridPane mainView;
    @FXML
    private ListView<Team> team1;
    @FXML
    private ListView<Team> team2;
    @FXML
    private ListView<Player> players1;
    @FXML
    private ListView<Player> players2;
    @FXML
    private Label matchResult;

    /**
     * Create team
     */
    @FXML
    private void createTeam() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainView.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newTeam.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            NewTeamController controller = fxmlLoader.getController();
            String newTeamName = controller.getNewTeamName();
            teamList.add(new Team(newTeamName, 0));
        } else {

        }
    }

    /**
     * Delete team
     */
    @FXML
    private void deleteTeam() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainView.getScene().getWindow());

        dialog.getDialogPane().setContent(teamListToDelete);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Team teamToDelete = teamListToDelete.getSelectionModel().getSelectedItem();
            System.out.println(teamToDelete.getTeamName());
            teamList.remove(teamToDelete);
            team1.getSelectionModel().clearSelection();
            team2.getSelectionModel().clearSelection();
            players1.getItems().clear();
            players2.getItems().clear();
        } else {

        }
    }

    /**
     * Edit team's name and players
     */
    @FXML
    private void editTeam() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainView.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("editTeam.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        EditTeamController editController = fxmlLoader.getController();
        editController.getTeamsToEdit().setItems(teamList);
        editController.getTeamsToEdit().setCellFactory(param -> new ListCell<Team>() {
            @Override
            protected void updateItem(Team item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getTeamName() == null) {
                    setText(null);
                } else {
                    setText(item.getTeamName());
                }
            }
        });

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (editController.getSelectedTeam() != null) {
                editController.getSelectedTeam().setTeamName(editController.getTeamName().getText());
                if (!editController.getPlayer1().getText().isBlank() && !editController.getStrength1().getText().isBlank()) {
                    editController.getSelectedTeam().getTeamPlayers().get(0).setPlayerName(editController.getPlayer1().getText());
                    editController.getSelectedTeam().getTeamPlayers().get(0).setPlayerStrength(Integer.parseInt(editController.getStrength1().getText()));
                }
                if (!editController.getPlayer2().getText().isBlank() && !editController.getStrength2().getText().isBlank()) {
                    editController.getSelectedTeam().getTeamPlayers().get(1).setPlayerName(editController.getPlayer2().getText());
                    editController.getSelectedTeam().getTeamPlayers().get(1).setPlayerStrength(Integer.parseInt(editController.getStrength2().getText()));
                }

                if (!editController.getPlayer3().getText().isBlank() && !editController.getStrength3().getText().isBlank()) {
                    editController.getSelectedTeam().getTeamPlayers().get(2).setPlayerName(editController.getPlayer3().getText());
                    editController.getSelectedTeam().getTeamPlayers().get(2).setPlayerStrength(Integer.parseInt(editController.getStrength3().getText()));
                }

                if (!editController.getPlayer4().getText().isBlank() && !editController.getStrength4().getText().isBlank()) {
                    editController.getSelectedTeam().getTeamPlayers().get(3).setPlayerName(editController.getPlayer4().getText());
                    editController.getSelectedTeam().getTeamPlayers().get(3).setPlayerStrength(Integer.parseInt(editController.getStrength4().getText()));
                }
                if (!editController.getPlayer5().getText().isBlank() && !editController.getStrength5().getText().isBlank()) {
                    editController.getSelectedTeam().getTeamPlayers().get(4).setPlayerName(editController.getPlayer5().getText());
                    editController.getSelectedTeam().getTeamPlayers().get(4).setPlayerStrength(Integer.parseInt(editController.getStrength5().getText()));
                }
            }
            team1.refresh();
            team2.refresh();
            players1.refresh();
            players2.refresh();
        } else {

        }
    }

    /**
     * Determine match winner.
     *
     * @param teamOne team one
     * @param teamTwo team two
     */
    @FXML
    private void determineWinner() {
        Team teamOne = team1.getSelectionModel().getSelectedItem();
        Team teamTwo = team2.getSelectionModel().getSelectedItem();
        if (teamOne == null || teamTwo == null) {
            matchResult.setText("Choose 2 teams to fight first.");
            return;
        }
        try {
            if (teamOne.calculateTeamStrength() > teamTwo.calculateTeamStrength()) {
                matchResult.setText(teamOne.getTeamName() + " vs " + teamTwo.getTeamName() + ": \n" + teamOne.getTeamName() + " Wins!");
            } else if (teamOne.calculateTeamStrength() < teamTwo.calculateTeamStrength()) {
                matchResult.setText(teamTwo.getTeamName() + " vs " + teamOne.getTeamName() + ": \n" + teamTwo.getTeamName() + " Wins!");
            } else {
                matchResult.setText(teamTwo.getTeamName() + " vs " + teamOne.getTeamName() + ": \n" + " Draw!");
            }
        } catch (NotEnoughPlayersException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Pick left side team.
     *
     * @param arg0 mouse event
     */
    @FXML
    public void pickTeam1(MouseEvent arg0) {
        Team selectedTeam = team1.getSelectionModel().getSelectedItem();
        ObservableList<Player> playerList = FXCollections.observableArrayList(selectedTeam.getTeamPlayers());
        players1.setItems(playerList);
    }

    /**
     * Pick right side team.
     *
     * @param arg0 mouse event
     */
    @FXML
    public void pickTeam2(MouseEvent arg0) {
        Team selectedTeam = team2.getSelectionModel().getSelectedItem();
        ObservableList<Player> playerList = FXCollections.observableArrayList(selectedTeam.getTeamPlayers());
        players2.setItems(playerList);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matchResult.setText("Select teams to match each other!");

        // Create elements of model
        teamList = FXCollections.observableArrayList();
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
        }

        // Populate ListViews
        team1.setItems(teamList);
        team1.setCellFactory(param -> new ListCell<Team>() {
            @Override
            protected void updateItem(Team item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getTeamName() == null) {
                    setText(null);
                } else {
                    setText(item.getTeamName());
                }
            }
        });
        team2.setItems(teamList);
        team2.setCellFactory(param -> new ListCell<Team>() {
            @Override
            protected void updateItem(Team item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getTeamName() == null) {
                    setText(null);
                } else {
                    setText(item.getTeamName());
                }
            }
        });
        teamListToDelete = new ListView<>();
        teamListToDelete.setItems(teamList);
        teamListToDelete.setCellFactory(param -> new ListCell<Team>() {
            @Override
            protected void updateItem(Team item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getTeamName() == null) {
                    setText(null);
                } else {
                    setText(item.getTeamName());
                }
            }
        });
        players1.setCellFactory(param -> new ListCell<Player>() {
            @Override
            protected void updateItem(Player item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getPlayerName() == null) {
                    setText(null);
                } else {
//                    setText(String.format("%-.10s%-15s%s", item.getPosition() + ":", item.getPlayerName() + " - ", item.getPlayerStrength() + " STR"));
//                    setText(item.getPosition() + ": " + item.getPlayerName() + " - " + item.getPlayerStrength() + " STR");
                    setText(item.getPlayerName() + " - " + item.getPlayerStrength() + " STR");
                }
            }
        });
        players2.setCellFactory(param -> new ListCell<Player>() {
            @Override
            protected void updateItem(Player item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getPlayerName() == null) {
                    setText(null);
                } else {
                    setText(item.getPlayerName() + " - " + item.getPlayerStrength() + " STR");
                }
            }
        });
    }
}
