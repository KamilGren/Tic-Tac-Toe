package Game;

import View.Board;
import View.Field;
import Window.AlertBox;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;


public class Gaming {


    static Player playerFirst;

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
                        changeFigureOnBoardFields(board, field, player);
                        //System.out.println("Ta pozycja została wypełniona przez: " + board.getGameState().getRoundState().getBoardFields()[board.getRowIndex(field)][board.getColumnIndex(field)]);
                        if(board.getGameState().getRoundState().hasFigureWon(Figure.X) == true)
                        {
                            AlertBox.display("Wygrana runda", "Wygrana runda. Gratulacje dla " + player.getPlayerName());
                        }
                    }
                    else
                        {
                        field.setBackground(new Background(Field.O_FIGURE));
                        changeFigureOnBoardFields(board, field, player);
                        System.out.println("ej: " + board.getGameState().getRoundState().getNumberOfMoves());
                        System.out.println("Figura gracza nr 1: " + board.getGameState().getPlayerOne().getPlayerFigure());
                        System.out.println("Figura gracza nr 2: " + board.getGameState().getPlayerTwo().getPlayerFigure());
                            //System.out.println("Ta pozycja została wypełniona przez: " + board.getGameState().getRoundState().getBoardFields()[board.getRowIndex(field)][board.getColumnIndex(field)]);
                            if(board.getGameState().getRoundState().hasFigureWon(Figure.O) == true)
                            {
                                AlertBox.display("Wygrana runda", "Wygrana runda. Gratulacje dla " + player.getPlayerName());
                            }
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
            EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
                    System.out.println("Siema");

                    if(whoIsNext(board) == board.getGameState().getPlayerOne() )
                    {
                        doMove(board.getGameState().getPlayerOne(), board.getGameState().getPlayerOne().getPlayerFigure(),(Field) node, board);
                        System.out.println("cze");
                    }
                    else if(whoIsNext(board) == board.getGameState().getPlayerTwo())
                    {
                        doMove(board.getGameState().getPlayerTwo(), board.getGameState().getPlayerTwo().getPlayerFigure(),(Field) node, board);
                        System.out.println("hej");

                    }
                    else if (whoIsNext(board) == null)
                    {
                        System.out.println("To by było na tyle.");
                    }

                }
            };

            if(node instanceof Field)
            {
                node.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
            }
            else
            {
                System.out.println("To nie jest przestrzeń do grania!");
            }

        }
    }

    public static void changeFigureOnBoardFields(Board board, Node node, Player player)
    {
        int col = board.getColumnIndex(node);
        int row = board.getRowIndex(node);
        System.out.println(board.getGameState().getRoundState().getBoardFields()[row][col]); // przed
        board.getGameState().getRoundState().getBoardFields()[row][col] = player.getPlayerFigure();
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

    public static Player whoIsNext(Board board) {
        int numberOfMoves = board.getGameState().getRoundState().getNumberOfMoves();
        System.out.println("Czy my tutaj w ogole weszlismy?" + numberOfMoves);

        if (numberOfMoves == 0) {
            if (whichPlayerIsStarting(board) == board.getGameState().getPlayerOne()) {
                playerFirst = board.getGameState().getPlayerOne();
            } else {
                playerFirst = board.getGameState().getPlayerTwo();
            }
            return playerFirst;
        }
        else if (numberOfMoves % 2 == 0)
        {
            return playerFirst;
        }
        else if (numberOfMoves % 2 == 1)
        {
            if (playerFirst == board.getGameState().getPlayerOne())
                return board.getGameState().getPlayerTwo();
            else
                return board.getGameState().getPlayerOne();
        }
        else if (numberOfMoves >= 10)
        {
            return null;
        }
        else return null;
    }


}
