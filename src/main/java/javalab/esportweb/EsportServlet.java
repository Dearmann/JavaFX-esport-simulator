package javalab.esportweb;

import java.io.*;
import java.util.ArrayList;
import javalab.exception.NotEnoughPlayersException;
import javalab.exception.WrongPlayerPositionException;
import javalab.model.Player;
import javalab.model.Team;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Main class of the servlet displaying esport teams.
 *
 * @author Dominik Uszok
 * @version 1.0
 */
@WebServlet("/")
public class EsportServlet extends HttpServlet {

    private ArrayList<Team> teamList;

    /**
     * Initialize teams
     * @throws ServletException serverlet exception
     */
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        // Create elements of model
        teamList = new ArrayList<>();
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
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();
        createMainResponse(out, "Choose teams to match");
        request.getSession().setAttribute("teamList", teamList);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Team teamOne;
        Team teamTwo;
        response.setContentType("text/html; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();

        String team1Number = request.getParameter("list1");
        String team2Number = request.getParameter("list2");

        String result = "Choose 2 teams to match!";
        if (team1Number == null || team2Number == null) {
            createMainResponse(out, result);
        } else {
            teamOne = teamList.get(Integer.parseInt(team1Number));
            teamTwo = teamList.get(Integer.parseInt(team2Number));
            try {
                if (teamOne.calculateTeamStrength() > teamTwo.calculateTeamStrength()) {
                    result = teamOne.getTeamName() + " vs " + teamTwo.getTeamName() + ": " + teamOne.getTeamName() + " Wins!";
                } else if (teamOne.calculateTeamStrength() < teamTwo.calculateTeamStrength()) {
                    result = teamTwo.getTeamName() + " vs " + teamOne.getTeamName() + ": " + teamTwo.getTeamName() + " Wins!";
                } else {
                    result = teamTwo.getTeamName() + " vs " + teamOne.getTeamName() + ": " + " Draw!";
                }
            } catch (NotEnoughPlayersException ex) {
                System.out.println(ex.getMessage());
            }
            createMainResponse(out, result);
        }
//        Enumeration enumeration = request.getParameterNames();
//        while (enumeration.hasMoreElements()) {
//            String name = (String) enumeration.nextElement();
//            out.println(name + " = " + request.getParameter(name));
//        }
    }

    /**
     * Build HTML representation of teams
     *
     * @param teamListNumber Team list number
     * @return String of HTML representation of teams
     */
    private String createHtmlTeamsList(int teamListNumber) {
        StringBuilder htmlTeams = new StringBuilder();
//        htmlTeams.append("<p>Select team ").append(teamListNumber).append(" to match:</p>");
        int teamCounter = 0;
        String listName = "list" + teamListNumber;
        for (Team team : teamList) {
            String teamID = listName + "team" + teamCounter;
            htmlTeams.append("<input type='radio' id='").append(teamID).append("' name='").append(listName).append("'")
                    .append(" value='").append(teamCounter).append("'>");
            htmlTeams.append(" <label for='").append(teamID).append("'>").append(team.getTeamName()).append("</label>");
            htmlTeams.append("<ul>");
            for (Player player : team.getTeamPlayers()) {
                htmlTeams.append("<li>");
                htmlTeams.append(player.getPlayerName());
                htmlTeams.append(" - ");
                htmlTeams.append(player.getPlayerStrength());
                htmlTeams.append(" STR");
                htmlTeams.append("</li>");
            }
            htmlTeams.append("</ul><br>");
            teamCounter++;
        }
        return htmlTeams.toString();
    }

    private void createMainResponse(PrintWriter out, String additionalHeading) {
        out.println("<html>\n<body>\n"
                + "<h1>"
                + additionalHeading
                + "</h1>"
                + "<form method='POST'><table><tr>"
                + "<td>" + createHtmlTeamsList(1) + "</td>"
                + "<td><button type='submit' style='margin: 50px; width: 100px;'>Match</button><br></form>"
                + "<form action='/EsportWeb/Create'><button type='submit' style='margin: 50px; width: 100px;'>Create team</button></form><br>"
                + "<form action='/EsportWeb/Delete'><button type='submit' style='margin: 50px; width: 100px;'>Delete team</button></form><br>"
                + "<form action='/EsportWeb/Choose'><button type='submit' style='margin: 50px; width: 100px;'>Edit team</button></form></td>"
                + "<td>" + createHtmlTeamsList(2) + "</td>"
                + "</tr></table></form>"
                + "\n</body>\n</html>");
    }
}
