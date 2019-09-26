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

    //przelicza ilosc wykonanych ruchow
    //najpierw sprowadza do rzedu, a potem wedlug rzedu przeszukuje i zlicza wszystkie ktore nie sa empty
    // czyli posiadaja znak wykonanej operacji (O/X) a tym samym ruch.
    // i to jest ok
    // przy probie cofania takze wczyta odpowiednia wartosc, bo ruch cofniety = mniej wykonanych ruchow
    // a nie w sumie wiecej (tak tego nie licze)

    public int getNumberOfMoves() {
        return (int) Arrays.stream(boardFields).
                flatMap(row -> Arrays.stream(row)).
                filter(field -> !field.equals(Figure.EMPTY)).
                count();

    }

    // zapis metody polega na pobraniu wartosci i stworzeniu nowej tablicy dwuwymiarowej i jedno
    // dwuwymiarowa to kopia BoardFields,a jedno wymiarowa to wszystkie figury w jednym rzedzie wedlug ich wystapienia w grze
    // kazda figura zostaje zapamietana
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

    // metoda cofania ruchu
    public static void undoMove(Board board, Field field) {
        int actualMove = board.getGameState().getRoundState().getNumberOfMoves(); // pobiera aktualna ilosc ruchow

        // gramy tylko jak jest ich wiecej niz 0 i mniej niz 9
        // implementacja usuwa dane z pola oraz czysci view
        if (actualMove > 1 && actualMove < 9) {
            LOG.info("Oczyszczam boardFields oraz background na tym Fieldzie");
            // odjecie dwoch pozycji ma uzasadnienie - raz bo tablica (ma mniej o 1 wartosc) a dwa prosze wejsc
            // do funkcji getField, tam mam reszte komentarza
            board.getGameState().getRoundState().getBoardFields()[rowIndex[actualMove-2]][colIndex[actualMove-2]] = Figure.EMPTY;
            LOG.info("BoardFields ktory zostal oczyszczony " + "row: " + rowIndex[actualMove-2] + "col: " + colIndex[actualMove-2]);
            field.setBackground(Background.EMPTY);
        }
        else if(actualMove == 1)
        {
            LOG.info("Wykonany zostal tylko jeden ruch. Zmieniam nieco formule wykonania cofania");
            board.getGameState().getRoundState().getBoardFields()[rowIndex[0]][colIndex[0]] = Figure.EMPTY;
            LOG.info("BoardFields ktory zostal oczyszczony " + "row: " + rowIndex[0] + "col: " + colIndex[0]);
            field.setBackground(Background.EMPTY);

        }
    }

    // metoda pobierania pola
    // na poczatku przeszukuje wszystkie obiekty z boardu, czyli wszystkie fieldy
    // do zmiennej r i c przyporzadowuje wartosci rzedu oraz kolumny konkretnego childa czyli obiektu, fielda
    // albo dziecka rodzicy (Boarda)
    // tyle, ze r i c zawsze bedzie 0 bo nie ma tu niczego co by zmienialo ta wartosc przy kolejnych obrotach
    // petli
    public static Field getField(int numberOfMoves, Board board)
    {
        // ilosc ruchow jaka tutaj pobieramy obnizanymy o 1, (tak dzialaja tablice)

        for(Node child :  board.getChildren())
        {
            // przy kazdym obrocie przyporzodkowuje te r i c, czyli do kazdego childa
            // i kazdy child jest sprawdzany

            int r = board.getRowIndex(child);
            int c = board.getColumnIndex(child);

            LOG.info("--------ROW ORAZ COL-- " + rowIndex[0] + colIndex[0] + "r: " + r + "c: " + c + "numberOfMoves:" + numberOfMoves);
            LOG.info("--------ROW ORAZ COL-- " + rowIndex[1] + colIndex[1]);
            LOG.info("--------ROW ORAZ COL-- " + rowIndex[2] + colIndex[2]);
            LOG.info("--------ROW ORAZ COL-- " + rowIndex[3] + colIndex[3]);
            LOG.info("--------ROW ORAZ COL-- " + rowIndex[4] + colIndex[4]);
            LOG.info("--------ROW ORAZ COL-- " + rowIndex[5] + colIndex[5]);

            // pominalem jedna rzecz, to jak bedzie wynosilo 0 to co sie stanie
            // a bedzie po wykonaniu pierwszego ruchu
            // jednak wtedy wyskoczyl mi nullpointerexception wiec na razie sobie to odpuscilem
            // do poprawy w wolnym czasie
            if(numberOfMoves < 0)
            {
                LOG.info("Cofanie wstecz zadziała tylko wtedy gdy wykonany zostanie co najmniej jeden ruch!");
                return null;
            }
            // jezeli zostal wykonany 1 ruch to sprawdz to w ten sposob
            if(numberOfMoves == 0 && r == rowIndex[0] && c == colIndex[0])
            {
                return (Field) child;

            }

            // jesli jego r i c zgadza sie z numerem rzedu i kolumny powszechnej (czyli tej w ktorej nastepuje zapis danych metoda saveField)

            // wychodzi na to, ze trzeba odjac 1 zeby zlapac odpowiedni element tablicy
            // przy 6 figurach, po uzyciu undo na 6 pozycji znajdzie sie 00
            // a na 5 znajdzie sie 12 czyli to co usuwamy, dlatego trzeba to namierzyc poprzez ujecie jednego ruchu
            else if(r == rowIndex[numberOfMoves-1] && c == colIndex[numberOfMoves-1])
            {


                LOG.info("Mamy to! " + "numberOfMove: " + numberOfMoves + "R: " + r + "C: " + c + "RowIndex: " + rowIndex[numberOfMoves-1] + "ColIndex: " + colIndex[numberOfMoves-1]);

                for(int a : rowIndex)
                {
                    System.out.print("RowIndex: " + a + " ");
                }

                System.out.print("RowIndex[" + numberOfMoves + "]" + rowIndex[numberOfMoves]);
                System.out.println("ColIndex[" + numberOfMoves + "]" + colIndex[numberOfMoves]);

                for(int b : colIndex)
                {
                    System.out.print("ColIndex: " + b + " ");

                }

                return (Field) child;
            }
            else
            {
                LOG.warn("Nie udalo sie zwrocic zadnego Fielda spelniajacego zadanie");

                // to moze sie nie zgadzac
                LOG.warn("numberOfMove: " + numberOfMoves + "r: " + r + "c: " + c + "rowIndex: " + rowIndex[numberOfMoves] + "colIndex: " + colIndex[numberOfMoves]);
            }
        }

    return null;
    }



}
