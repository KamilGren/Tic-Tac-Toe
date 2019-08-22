package Game;

import java.util.Arrays;
import java.util.stream.Stream;

public class RoundState {

    private Figure[][] boardFields = new Figure[3][3];



    public RoundState() {
        Arrays.stream(boardFields).forEach(a -> Arrays.fill(a, Figure.O));
        System.out.println("Konstruktor z RoundState!" + boardFields[0][0] + hasFigureWon(Figure.X)); // sprawdzam czy dziala
        System.out.println(hasWonHorizontal(Figure.X)); // to tylko do wgladu czy mniejsze metody dzialaja
        System.out.println(boardFields[0][0].equals(Figure.X)); //i czy equals dziala prawidlowo

        //czyli sieka tablice na pojedyncze czlony i kazdy z nich wypelnia stanem Figury (jedna z figur)
        //- pustą
    }

    public boolean hasFigureWon(Figure figure)
    {
        if ( hasWonHorizontal(figure) == true || hasWonVertical(figure) == true || hasWonDiagonallyDown(figure) == true || hasWonDiagonallyUp(figure) == true)
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
                if(boardFields[i][j].equals(figure.O))
                {
                  t++;
                  if(t == 2)
                  {
                      System.out.println("Gracz o figurze " + figure.O + " wygrał." + t);
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
                if(boardFields[j][i].equals(figure.O))
                {
                    t++;
                    if(t == 2)
                    {
                        System.out.println("Gracz o figurze " + figure.O + " wygrał.");
                        return true;
                    }

                }
            }
            t=0;

        }
    return false;
    }

   public boolean hasWonDiagonallyDown(Figure figure)
    {
        int t=0;
        for(int i=0; i <= 2; i++)
        {
            if (boardFields[i][i].equals(figure.O))
            {
                t++;
                if (t == 2)
                {
                    System.out.println("Gracz o figurze " + figure.O + " wygrał.");
                    return true;
                }
            }


        }
        return false;
    }

    public boolean hasWonDiagonallyUp(Figure figure)
    {
        int t=0;
        for(int i=2; i == 0; i--)
        {
            if (boardFields[i][i].equals(figure.O))
            {
                t++;
                if (t == 2)
                {
                    System.out.println("Gracz o figurze " + figure.O + " wygrał.");
                    return true;
                }
            }


        }
        return false;
    }



    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        return true;
    }

}
