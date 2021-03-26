package Classes.Tiles;

import Classes.Player;

public class TaxTile extends Tile
{
    private int tax;

    public TaxTile(int tax)
    {
        this.tax = tax;
    }

    public void PlayerStands(Player player)
    {
        System.out.println("Pay taxes: -" + tax + "$");
        player.ChangeBalance(-tax);
    }
}
