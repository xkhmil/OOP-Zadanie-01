package Classes.Tiles;

import Classes.Player;

public class StartTile extends Tile
{
    private int bonus;

    public StartTile(int bonus)
    {
        this.bonus = bonus;
    }

    public void PlayerStands(Player player)
    {
        System.out.println("Gain start bonus: +" + bonus + "$");
        player.ChangeBalance(bonus);
    }
}
