package Game;

public class Player {

    private String name;
    private boolean isComputer;
    private Figure figure;
    private int roundsWon;


    public Player(String name, Figure figure, boolean isComputer, int roundsWon)
    {
        this.name = name;
        this.figure = figure;
        this.isComputer = isComputer;
        this.roundsWon = roundsWon;


    }

    public void win()
    {
        roundsWon++;
    }

    public String getPlayerName()
    {
        return name;
    }

    public Figure getPlayerFigure()
    {
        return figure;
    }

    public Boolean getPlayerIsComputer()
    {
        return isComputer;
    }

    int getPlayerRoundWon()
    {
        return roundsWon;
    }


}
