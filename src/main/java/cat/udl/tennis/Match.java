package cat.udl.tennis;

import cat.udl.tennis.game.Player;
import cat.udl.tennis.game.ScoreMessage;
import cat.udl.tennis.game.TennisGameImpl;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Match {
    private TennisGame tennisGame = new TennisGameImpl();

    public static void main(String[] args) {
        Match match = new Match();
        String markerPlayer;
        Scanner in = new Scanner(System.in);
        final ResourceBundle i18n = ResourceBundle.getBundle("bundle", Locale.getDefault());

        System.out.println(i18n.getString("welcome.to.mytennis"));
        System.out.println(i18n.getString("player.1.name"));
        match.getPlayerName(match.tennisGame.getPlayer1(), in);
        System.out.println(i18n.getString("player.2.name"));
        match.getPlayerName(match.tennisGame.getPlayer2(), in);
        do {
            System.out.println(i18n.getString("who.scores") + "(" + match.tennisGame.getPlayer1().getPlayerName()
                    + "/" + match.tennisGame.getPlayer2().getPlayerName() + ")"
            );
            markerPlayer = in.nextLine().trim();
            if (markerPlayer.equals(match.tennisGame.getPlayer1().getPlayerName())) {
                match.tennisGame.player1WonPoint();
            } else if (markerPlayer.equals(match.tennisGame.getPlayer2().getPlayerName())) {
                match.tennisGame.player2WonPoint();
            }
            System.out.println(match.tennisGame.getScore());
        }
        while (!match.tennisGame.getScore().equals(ScoreMessage.WIN_FOR_PLAYER1.getMessage())
                && !match.tennisGame.getScore().equals(ScoreMessage.WIN_FOR_PLAYER2.getMessage()));
        System.out.println(i18n.getString("end.of.match"));
    }

    private void getPlayerName(Player player, Scanner in) {
        String playerName;
        do {
            playerName = in.nextLine().trim();
        } while (playerName.length() == 0);
        player.setPlayerName(playerName);
    }
}
