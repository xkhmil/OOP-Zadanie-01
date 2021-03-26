package Classes.Tiles;

import Classes.GameController;
import Classes.Player;

public class ChanceTile extends Tile
{
    private GameController gameController;

    public ChanceTile(GameController gameController)
    {
        this.gameController = gameController;
    }

    public void PlayerStands(Player player)
    {
        System.out.println("U stands on chance tile, draw chance card");
        gameController.DrawChanceCardByPlayer(player);
    }
}
