package Game;


import Window.AlertBox;

public class GameState {

    private int numberOfDraws;
    private int roundsToWin;
    private Player playerOne;
    private Player playerTwo;
    private RoundState roundState;


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

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
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
            System.out.println("Gracz pierwszy wygrywa rundę!");
        }
        else if (roundState.hasFigureWon(getPlayerTwo().getPlayerFigure()) == true) {
            System.out.println("Gracz drugi wygrywa rundę!");
        }
        else if (roundState.isDraw() == true) {
            System.out.println("Mamy remis!");
        }
        else
        {
            RoundOver = false;
        }

    return RoundOver;
    }

    public boolean isGameOver() {
        boolean isGameOver = true;

        if (playerOne.getPlayerRoundWon() < roundsToWin && playerTwo.getPlayerRoundWon() < roundsToWin)
            isGameOver = false;

        String winnerName;
        if (playerOne.getPlayerRoundWon() > playerTwo.getPlayerRoundWon()) {
            winnerName = playerOne.getPlayerName();
        } else {
            winnerName = playerTwo.getPlayerName();
        }

        AlertBox.display("End of game", winnerName + "won the game!");

        return true;


    }


}

