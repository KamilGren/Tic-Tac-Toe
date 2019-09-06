package Game;

import View.Board;
import View.Field;
import Window.AlertBox;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Gaming {

    static Player playerFirst;

    public static final Logger LOG = LogManager.getLogger();

    public Gaming(Board board)
    {
        setFieldsReady(board);
        LOG.info(whichPlayerIsStarting(board));

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
                            board.checkState(board); // sprawdzanie czy zakonczyla sie runda, jesli tak to stworzenie nowej oraz reset boardu
                        }
                    }
                    else
                        {
                        field.setBackground(new Background(Field.O_FIGURE));
                        changeFigureOnBoardFields(board, field, player);
                        LOG.info("ej: " + board.getGameState().getRoundState().getNumberOfMoves());
                        LOG.info("Figura gracza nr 1: " + board.getGameState().getPlayerOne().getPlayerFigure());
                        LOG.info("Figura gracza nr 2: " + board.getGameState().getPlayerTwo().getPlayerFigure());
                            //System.out.println("Ta pozycja została wypełniona przez: " + board.getGameState().getRoundState().getBoardFields()[board.getRowIndex(field)][board.getColumnIndex(field)]);
                            if(board.getGameState().getRoundState().hasFigureWon(Figure.O) == true)
                            {
                                AlertBox.display("Wygrana runda", "Wygrana runda. Gratulacje dla " + player.getPlayerName());
                                board.checkState(board);
                            }
                    }



        }
        else
            {
                LOG.warn("Pole zajęte!");
            }

    }

    // sprawdzenie czy konkretne pole jest polem pustym
    public static boolean isFieldFilled(Board board, int a, int b)
    {
        if(board.getGameState().getRoundState().getBoardFields()[a][b].equals(Figure.EMPTY))
        {
            LOG.info("Odczyt metody isFieldFilled: wiemy, ze pole jest niewypelnione."); // inne odczyty mnie nie interesuja, po prostu zwroci wtedy true
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
                    if(whoIsNext(board) == board.getGameState().getPlayerOne() )
                    {
                        doMove(board.getGameState().getPlayerOne(), board.getGameState().getPlayerOne().getPlayerFigure(),(Field) node, board);
                    }
                    else if(whoIsNext(board) == board.getGameState().getPlayerTwo())
                    {
                        doMove(board.getGameState().getPlayerTwo(), board.getGameState().getPlayerTwo().getPlayerFigure(),(Field) node, board);

                    }
                    else if (whoIsNext(board) == null)
                    {
                        LOG.info("");
                    }

                }
            };

            if(node instanceof Field)
            {
                node.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
            }
            else
            {
                LOG.warn("To nie jest przestrzeń do grania!"); // jesli klikniete jest cos poza Fieldami w Board
            }

        }
    }

    static int i = 0; // zliczanie ilosci wywolanej metody
    public static Figure [] TabOfFigure = new Figure [8];

    public static void changeFigureOnBoardFields(Board board, Node node, Player player)
    {
        int col = board.getColumnIndex(node);
        int row = board.getRowIndex(node);
        Figure figure =  player.getPlayerFigure();
        int numberOfMoves = board.getGameState().getRoundState().getNumberOfMoves();
        System.out.println("col: " + col);


        if(numberOfMoves >=1) { // ilosc ruchow nie moze miec 0 jesli chce zapisac ruch
            board.getGameState().getRoundState().saveField(row, col, figure, board.getGameState().getRoundState().getNumberOfMoves()-1);
        }

        // System.out.println(board.getGameState().getRoundState().getBoardFields()[row][col]); // figura przed
        board.getGameState().getRoundState().getBoardFields()[row][col] = player.getPlayerFigure();

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
