package Game;


import View.Board;
import View.Field;
import Window.AlertBox;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;


public class RoundState {

    public static Figure[][] boardFields = new Figure[3][3];
    private final static Logger LOG = LogManager.getLogger();
    public static Figure[][] saveField = new Figure[3][3];
    public static Figure[] tableOfFigures = new Figure[8];
    public static int[] rowIndex = new int[8];
    public static int[] colIndex = new int[8];
    public static int Column, Row;

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
                  t++;

                  if(t == 3)
                  {
                      LOG.info("(WonHorizontal)Gracz o figurze " + figure + " wygrał." + t);
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
                        LOG.info("(WonVertical)Gracz o figurze " + figure + " wygrał.");
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
                    LOG.info("(WonDiagDownLeft)Gracz o figurze " + figure + " wygrał.");
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
                    LOG.info("(WonDiagUpRight)Gracz z figurą " + figure + " wygrywa.");
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
            LOG.info("Nowa runda!");
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

    public static void saveField(int row, int col, Figure figure, int numberOfMove) {

        //tutaj jest problem zapisu do rzedow i kolumn
        Column = col;
        Row = row;


        if (numberOfMove == 0) {
            rowIndex[0] = Row; // numer rzedu
            colIndex[0] = Column;
        }
        else if (numberOfMove >=1)
        {
            rowIndex[numberOfMove] = Row; // numer rzedu
            colIndex[numberOfMove] = Column; // numer kolumny
            saveField[Row][Column] = figure; // jaka figura
            tableOfFigures[numberOfMove] = saveField[Row][Column]; // konkretna figura na konkretnym polu (o numerze wykonanego ruchu)
        }

        LOG.info("Zapisuje dane tego Fielda: " + rowIndex[numberOfMove] + " " + colIndex[numberOfMove] + " " +  saveField[Row][Column] + " " + tableOfFigures[numberOfMove]);

        // fieldsNumber.add(numberOfMove, );  (chwila zamyslu nad ArrayList fieldsNumber = new ArrayList();)
}

    public static void undoMove(Board board, Field field) {
        int actualMove = board.getGameState().getRoundState().getNumberOfMoves();

        if (actualMove > 0 && actualMove < 9) {
            LOG.info("Oczyszczam boardFields oraz background na tym Fieldzie");
            board.getGameState().getRoundState().getBoardFields()[rowIndex[actualMove-1]][colIndex[actualMove-1]] = Figure.EMPTY;
            field.setBackground(Background.EMPTY);

        }
    }

    public static Field getField(int numberOfMoves, Board board)
    {

        for(Node child :  board.getChildren())
        {
            int r = board.getRowIndex(child);
            int c = board.getColumnIndex(child);


            if(numberOfMoves == 0)
            {
                LOG.info("Ilosc ruchow: " + (numberOfMoves));
                return null;
            }
            else if(r == rowIndex[numberOfMoves] && c == colIndex[numberOfMoves])
            {
                LOG.info("Mamy to! " + "numberOfMove: " + numberOfMoves + "R: " + r + "C: " + c + "RowIndex: " + rowIndex[numberOfMoves] + "ColIndex: " + colIndex[numberOfMoves]);

                for(int a : rowIndex)
                {
                    System.out.print("RowIndex: " + a + " ");
                }

                System.out.print("RowIndex[numberOfMoves] " + rowIndex[numberOfMoves]);
                System.out.println("ColIndex[numberOfMoves] " + colIndex[numberOfMoves]);
                for(int b : colIndex)
                {
                    System.out.print("ColIndex: " + b + " ");

                }

                return (Field) child;
            }
            else
            {
                LOG.warn("Nie udalo sie zwrocic zadnego Fielda spelniajacego zadanie");

                LOG.warn("numberOfMove: " + numberOfMoves + "r: " + r + "c: " + c + "rowIndex: " + rowIndex + "colIndex: " + colIndex);
                return null;
            }
        }

    return null;
    }



}
