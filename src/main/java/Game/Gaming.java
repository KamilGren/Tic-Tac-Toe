package Game;

import View.Board;
import View.Field;
import javafx.scene.Node;
import javafx.scene.layout.Background;


public class Gaming {


    public static void doMove(Player player, Figure figure, Field field, Board board)
    {
       if(isFieldFilled(board, 0, 0))
       {
           if(player.getPlayerFigure() == Figure.X) {
               field.setBackground(new Background(Field.X_FIGURE));
           }
           else
           {
               field.setBackground(new Background(Field.O_FIGURE));
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


    public static void setMouseEvent(Board board)
    {


        for(Node node : board.getChildren())
        {
            if(node instanceof Field) {
                //node.setOnMouseClicked(e -> doMove(board.getGameState().getPlayerOne(), board.getGameState().getPlayerOne().getPlayerFigure(),(Field) node ,board));

            }
            else
            {
                System.out.println("To nie jest przestrzeń do grania!");
            }

        }
    }


}
