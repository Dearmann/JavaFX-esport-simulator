/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javalab.esport;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javalab.model.Team;

/**
 * FXML Controller class
 *
 * @author Dominik Uszok
 */
public class EditTeamController implements Initializable {

    @FXML
    private TextField teamName;
    @FXML
    private TextField player1;
    @FXML
    private TextField strength1;
    @FXML
    private TextField player2;
    @FXML
    private TextField strength2;
    @FXML
    private TextField player3;
    @FXML
    private TextField strength3;
    @FXML
    private TextField player4;
    @FXML
    private TextField strength4;
    @FXML
    private TextField player5;
    @FXML
    private TextField strength5;
    @FXML
    private ListView<Team> teamsToEdit;
    private Team selectedTeam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        teamsToEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                selectedTeam = teamsToEdit.getSelectionModel().getSelectedItem();
                teamName.setText(selectedTeam.getTeamName());
                player1.setText(selectedTeam.getTeamPlayers().get(0).getPlayerName());
                strength1.setText(Integer.toString(selectedTeam.getTeamPlayers().get(0).getPlayerStrength()));
                player2.setText(selectedTeam.getTeamPlayers().get(1).getPlayerName());
                strength2.setText(Integer.toString(selectedTeam.getTeamPlayers().get(1).getPlayerStrength()));
                player3.setText(selectedTeam.getTeamPlayers().get(2).getPlayerName());
                strength3.setText(Integer.toString(selectedTeam.getTeamPlayers().get(2).getPlayerStrength()));
                player4.setText(selectedTeam.getTeamPlayers().get(3).getPlayerName());
                strength4.setText(Integer.toString(selectedTeam.getTeamPlayers().get(3).getPlayerStrength()));
                player5.setText(selectedTeam.getTeamPlayers().get(4).getPlayerName());
                strength5.setText(Integer.toString(selectedTeam.getTeamPlayers().get(4).getPlayerStrength()));
            }
        });

        // Force strength fields to be numeric only
        strength1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    strength1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        strength2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    strength2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        strength3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    strength3.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        strength4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    strength4.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        strength5.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    strength5.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public TextField getTeamName() {
        return teamName;
    }

    public void setTeamName(TextField teamName) {
        this.teamName = teamName;
    }

    public TextField getPlayer1() {
        return player1;
    }

    public void setPlayer1(TextField player1) {
        this.player1 = player1;
    }

    public TextField getStrength1() {
        return strength1;
    }

    public void setStrength1(TextField strength1) {
        this.strength1 = strength1;
    }

    public TextField getPlayer2() {
        return player2;
    }

    public void setPlayer2(TextField player2) {
        this.player2 = player2;
    }

    public TextField getStrength2() {
        return strength2;
    }

    public void setStrength2(TextField strength2) {
        this.strength2 = strength2;
    }

    public TextField getPlayer3() {
        return player3;
    }

    public void setPlayer3(TextField player3) {
        this.player3 = player3;
    }

    public TextField getStrength3() {
        return strength3;
    }

    public void setStrength3(TextField strength3) {
        this.strength3 = strength3;
    }

    public TextField getPlayer4() {
        return player4;
    }

    public void setPlayer4(TextField player4) {
        this.player4 = player4;
    }

    public TextField getStrength4() {
        return strength4;
    }

    public void setStrength4(TextField strength4) {
        this.strength4 = strength4;
    }

    public TextField getPlayer5() {
        return player5;
    }

    public void setPlayer5(TextField player5) {
        this.player5 = player5;
    }

    public TextField getStrength5() {
        return strength5;
    }

    public void setStrength5(TextField strength5) {
        this.strength5 = strength5;
    }

    public ListView<Team> getTeamsToEdit() {
        return teamsToEdit;
    }

    public void setTeamsToEdit(ListView<Team> teamsToEdit) {
        this.teamsToEdit = teamsToEdit;
    }

    public Team getSelectedTeam() {
        return selectedTeam;
    }

}
