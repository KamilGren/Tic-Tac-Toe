package Game;


import Window.AlertBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameState {

    private int numberOfDraws;
    private int roundsToWin;
    private static Player playerOne;
    private static Player playerTwo;
    private RoundState roundState;
    private final static Logger LOG = LogManager.getLogger();


    public GameState(Player playerOne, Player playerTwo, int roundsToWin) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.roundsToWin = roundsToWin;
        numberOfDraws = 0;
        roundState = new RoundState();
    }

    public GameState(Player playerOne, Player playerTwo, int roundsToWin, RoundState roundState) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.roundsToWin = roundsToWin;
        this.roundState = roundState;
        numberOfDraws = 0;
    }

    public RoundState getRoundState() {
        return roundState;
    }

    public static Player getPlayerOne() {
        return playerOne;
    }

    public static Player getPlayerTwo() {
        return playerTwo;
    }

    private void DrawRound() {
        numberOfDraws++;
    }

    private void playerOneWonRound() {
        playerOne.win();
    }

    private void playerTwoWonRound() {
        playerTwo.win();
    }


    public boolean isRoundOver() {
        boolean RoundOver = true;

        if (roundState.hasFigureWon(getPlayerOne().getPlayerFigure()) == true) {
            LOG.info("Gracz pierwszy wygrywa rundę!");
            playerOneWonRound();

        }
        else if (roundState.hasFigureWon(getPlayerTwo().getPlayerFigure()) == true) {
            LOG.info("Gracz drugi wygrywa rundę!");
            playerTwoWonRound();
        }
        else if (roundState.isDraw() == true) {
            LOG.info("Mamy remis!");
        }
        else
        {
            RoundOver = false;
        }

    return RoundOver;
    }

    public boolean isGameOver() {

        if (playerOne.getPlayerRoundWon() < roundsToWin && playerTwo.getPlayerRoundWon() < roundsToWin) {
            return false;
        }
        String winnerName;
        if (playerOne.getPlayerRoundWon() > playerTwo.getPlayerRoundWon()) {
            winnerName = playerOne.getPlayerName();
        }
        else
            {
            winnerName = playerTwo.getPlayerName();
        }

        AlertBox.display("End of game", winnerName + "won the game!");
        return true;
    }


}

