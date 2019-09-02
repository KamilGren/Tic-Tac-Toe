package Game;


import java.util.Arrays;


public class RoundState {

    private Figure[][] boardFields = new Figure[3][3];



    public RoundState() {
        Arrays.stream(boardFields).forEach(a -> Arrays.fill(a, Figure.O));

        /*
        System.out.println("Konstruktor z RoundState!" + boardFields[0][0] + hasFigureWon(Figure.X));
        System.out.println("h: " + hasWonHorizontal(Figure.O));
        System.out.println("V: " + hasWonVertical(Figure.O));
        System.out.println("dU: " + hasWonDiagonallyUpLeft(Figure.O));
        System.out.println("DD: " + hasWonDiagonallyDownLeft(Figure.O));
        System.out.println("DD: " + hasWonDiagonallyDownRight(Figure.O));
        System.out.println("DD: " + hasWonDiagonallyUpRight(Figure.O));

        do wgladu czy pojedyncze metody zwracaja prawidlowe funkcje

        isTrue();
        test(Figure.O);

         */

        System.out.println("Jakas widoczna odpowiedz: " + isDraw());

    }

    public boolean hasFigureWon(Figure figure) {
        if (hasWonHorizontal(figure) == true || hasWonVertical(figure) == true || hasWonDiagonallyDownLeft(figure) == true || hasWonDiagonallyDownRight(figure) == true
            || hasWonDiagonallyUpLeft(figure) == true || hasWonDiagonallyUpRight(figure) == true)
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
                  t++;
                  if(t == 3)
                  {
                      System.out.println("Gracz o figurze " + figure + " wygrał." + t);
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
                        System.out.println("Gracz o figurze " + figure + " wygrał.");
                        return true;
                    }

                }
            }
            t=0;

        }
    return false;
    }

   public boolean hasWonDiagonallyDownLeft(Figure figure)
    {
        int t=0;
        for(int i=0; i <= 2; i++)
        {
            if (boardFields[i][i] == figure)
            {
                t++;
                if (t == 3)
                {
                    System.out.println("Gracz o figurze " + figure + " wygrał.");
                    return true;
                }
            }


        }
        return false;
    }

    public boolean hasWonDiagonallyUpLeft(Figure figure) {
        int t = 0;
        int i = 3;

        while(i > 0)
        {
            i--;
            if (boardFields[i][i] == figure) {
                t++;
                if (t == 3) {
                    System.out.println("Gracz z " + figure + " wygrywa.");
                    return true;
                }
            }




        }
        t = 0;
        return false;
    }

    public boolean hasWonDiagonallyUpRight(Figure figure) {
        int t = 0;
        int i = 2;

        while(i >= 0)
        {
            if(boardFields[i][i] == figure)
            {
                t++;
                i--;
                if(t == 3)
                {
                    System.out.println("Gracz z figurą " + figure + " wygrywa.");
                    return true;
                }
            }
            else i--;
        }
        return false;
    }


    public boolean hasWonDiagonallyDownRight(Figure figure) {
        int t = 0;
        int j = 2;

        for(int i =0; i <= 2; i ++) {

            while (j >= 0) // uzylem while bo z forem byl jakis problem, cos z nullem
            {
                if(boardFields[i][j] == figure)
                {
                    t++;
                    j--;
                    if(t == 3)
                    {
                        System.out.println("Gracz z figurą " + figure + " wygrywa.");
                        return true;
                    }

                }
                else j--;
            }
            j = 2;
            t = 0;
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

}
