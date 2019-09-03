package Game;


import Window.AlertBox;

import java.util.Arrays;


public class RoundState {

    private Figure[][] boardFields = new Figure[3][3];




    public RoundState() {
        Arrays.stream(boardFields).forEach(a -> Arrays.fill(a, Figure.EMPTY));

    }

    public boolean hasFigureWon(Figure figure) {
        if (hasWonHorizontal(figure) == true || hasWonVertical(figure) == true || hasWonDiagonallyLeft(figure) == true
            ||  hasWonDiagonallyRight(figure) == true)
        return true;
        else
            return false;

    }


    public boolean hasWonHorizontal(Figure figure)
    {
        int t=0;
        for(int i=0; i <= 2; i++)
        {
            for(int j=0; j <=2; j++)
            {
                if(boardFields[i][j] == figure)
                {
                    System.out.println("ekhm: " + t);
                  t++;

                  if(t == 3)
                  {
                      System.out.println("(WonHorizontal)Gracz o figurze " + figure + " wygrał." + t);
                      t=0;
                      return true;

                  }
                }
            }
           t=0;

        }
    return false;
    }

    public boolean hasWonVertical(Figure figure)
    {
        int t=0;
        for(int i=0; i <= 2; i++)
        {
            for(int j=0; j <=2; j++)
            {
                if(boardFields[j][i] == figure)
                {
                    t++;
                    if(t == 3)
                    {
                        System.out.println("(WonVertical)Gracz o figurze " + figure + " wygrał.");
                        return true;
                    }

                }
            }
            t=0;

        }
    return false;
    }

   public boolean hasWonDiagonallyLeft(Figure figure)
    {
        int t=0;
        for(int i=0; i <= 2; i++)
        {
            if (boardFields[i][i] == figure)
            {
                t++;
                if (t == 3)
                {
                    System.out.println("(WonDiagDownLeft)Gracz o figurze " + figure + " wygrał.");
                    return true;
                }
            }


        }
        return false;
    }

    public boolean hasWonDiagonallyRight(Figure figure) {
        int t = 0;
        int i = 2;
        int j = 0;

        while(i >= 0)
        {
            if(boardFields[j][i] == figure)
            {
                t++; // sorry za to
                j++;
                i--;
                if(t == 3)
                {
                    System.out.println("(WonDiagUpRight)Gracz z figurą " + figure + " wygrywa.");
                    return true;
                }
            }
            else i--;
        }
        return false;
    }




    public boolean isDraw() {
        if (hasFigureWon(Figure.O) == false && hasFigureWon(Figure.X) == false && isFilled() == true)
        {
            return true;
        }
        else return false;
    }

    public boolean isFilled()
    {
        for(int i =0; i <= 2; i++)
        {
            for(int j=0; j <=2; j++)
            {
                if(boardFields[i][j] == Figure.EMPTY)
                    return false;
                else
                    return true;
            }
        }

        return false;
    }


    public void newRound()
    {
        if(isFilled() == true)
        {
            Arrays.stream(boardFields).forEach(a -> Arrays.fill(a, Figure.EMPTY));
            System.out.println("Nowa runda!");
        }
    }

    public Figure [][] getBoardFields()
    {
        return boardFields;
    }

    public int getNumberOfMoves() {
        return (int) Arrays.stream(boardFields).
                flatMap(row -> Arrays.stream(row)).
                filter(field -> !field.equals(Figure.EMPTY)).
                count();

    }

}
