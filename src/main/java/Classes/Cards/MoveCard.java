package Classes.Cards;

import Classes.GameController;
import Classes.Player;

public class MoveCard extends Card
{
    private int value;
    private GameController gameController;

    public MoveCard(int value, GameController gameController)
    {
        this.value = value;
        this.gameController = gameController;
    }

    public void DrawCard(Player player)
    {
        System.out.println("You draw move card and moved for " + value + " tiles");
        player.MovePlayer(value, gameController);
    }
}
