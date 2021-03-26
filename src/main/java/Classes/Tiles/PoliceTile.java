package Classes.Tiles;

import Classes.Player;

public class PoliceTile extends Tile
{
    private int turnsCount;

    public PoliceTile(int turnsCount)
    {
        this.turnsCount = turnsCount;
    }

    public void PlayerStands(Player player)
    {
        System.out.println("Go to the jail for " + turnsCount + " turns");
        player.setCoordinate(6);
        player.ChangeSkipTurns(turnsCount);
    }
}
