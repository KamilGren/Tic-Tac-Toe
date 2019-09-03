package Game;

import View.Board;
import View.Field;
import javafx.scene.Node;
import javafx.scene.layout.Background;


public class Gaming {


    public Gaming(Board board)
    {
        setFieldsReady(board);
        System.out.println(whichPlayerIsStarting(board));


    }

    public static void doMove(Player player, Figure figure, Field field, Board board)
    {
        if (!isFieldFilled(board, board.getRowIndex(field), board.getColumnIndex(field)))
        {

                    if (player.getPlayerFigure() == Figure.X)
                    {
                        field.setBackground(new Background(Field.X_FIGURE));
                        changeFigureOnBoardFields(board, field);
                        System.out.println("ej: " + board.getGameState().getRoundState().getNumberOfMoves());
                        board.getGameState().getRoundState().hasFigureWon(Figure.X);
                    }
                    else
                        {
                        field.setBackground(new Background(Field.O_FIGURE));
                        changeFigureOnBoardFields(board, field);
                        System.out.println("ej: " + board.getGameState().getRoundState().getNumberOfMoves());
                        System.out.println("Figura gracza nr 1: " + board.getGameState().getPlayerOne().getPlayerFigure());
                            System.out.println("Figura gracza nr 2: " + board.getGameState().getPlayerTwo().getPlayerFigure());
                        board.getGameState().getRoundState().hasFigureWon(Figure.O);
                    }

        }
        else
            {
                System.out.println("Pole zajęte!");
            }

    }

    // sprawdzenie czy konkretne pole jest polem pustym
    public static boolean isFieldFilled(Board board, int a, int b)
    {
        if(board.getGameState().getRoundState().getBoardFields()[a][b].equals(Figure.EMPTY))
        {
            System.out.println("Wiemy, ze nie wypelniony.");
            return false;
        }
        return true;
    }

    public static void setFieldsReady(Board board)
    {
        for(Node node : board.getChildren())
        {
            // problem to to, ze petla sama od razu wypelnia wszystko w taki sposob, ze nie podlicza ruchow
            // no bo one nie wystepuja a instrukcje sa skonstruowane tak jakby wystepowaly
            if(node instanceof Field)
            {
                if(whoIsNext(board) == board.getGameState().getPlayerOne() )
                {
                    node.setOnMouseClicked(e -> doMove(board.getGameState().getPlayerOne(), board.getGameState().getPlayerOne().getPlayerFigure(),(Field) node, board));
                    System.out.println("cze");
                }
                else if(whoIsNext(board) == board.getGameState().getPlayerTwo())
                {
                    node.setOnMouseClicked(e -> doMove(board.getGameState().getPlayerTwo(), board.getGameState().getPlayerTwo().getPlayerFigure(),(Field) node, board));
                    System.out.println("hej");
                }
                else if (whoIsNext(board) == null)
                {
                    System.out.println("To by było na tyle.");
                }
            }
            else
            {
                System.out.println("To nie jest przestrzeń do grania!");
            }

        }
    }

    public static void playerClickField(Board board)
    {

    }

    public static void changeFigureOnBoardFields(Board board, Node node)
    {
        int col = board.getColumnIndex(node);
        int row = board.getRowIndex(node);
        System.out.println(board.getGameState().getRoundState().getBoardFields()[row][col]); // przed
        board.getGameState().getRoundState().getBoardFields()[row][col] = board.getGameState().getPlayerOne().getPlayerFigure();
        System.out.println(board.getGameState().getRoundState().getBoardFields()[row][col]); // po
    }

    public static Player whichPlayerIsStarting(Board board)
    {
        // USTALILEM, ZE ZACZYNA GRACZ KTORY MA KRZYZYK
        int numberOfMoves = board.getGameState().getRoundState().getNumberOfMoves();

        if(board.getGameState().getPlayerOne().getPlayerFigure() == Figure.X && numberOfMoves == 0)
        {
            return board.getGameState().getPlayerOne();
        }
        else if (board.getGameState().getPlayerTwo().getPlayerFigure() == Figure.X && numberOfMoves == 0) {
            return board.getGameState().getPlayerTwo();
        }
        return null; // zwraca jak jest wieksza ilosc ruchow niz 0
    }

    public static Player whoIsNext(Board board)
    {
        int numberOfMoves = board.getGameState().getRoundState().getNumberOfMoves();
        System.out.println("Czy my tutaj w ogole weszlismy?" + numberOfMoves);

        if (numberOfMoves == 0)
        {
            return whichPlayerIsStarting(board);
        }
        else if(numberOfMoves % 2 == 0)
        {
            return whichPlayerIsStarting(board);
        }
        else if(numberOfMoves % 2 == 1)
        {
            if (whichPlayerIsStarting(board) == board.getGameState().getPlayerOne())
            {
               return board.getGameState().getPlayerTwo();
            }
            else
                return board.getGameState().getPlayerOne();
        }
        else if(numberOfMoves >= 10)
        {
            return null;
        }
        else return null;
    }


}
