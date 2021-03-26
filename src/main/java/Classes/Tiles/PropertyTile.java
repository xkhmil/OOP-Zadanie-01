package Classes.Tiles;

import Classes.Player;

import java.util.Scanner;

public class PropertyTile extends Tile
{
    private int cost;
    private int fee;

    private Player owner;

    public PropertyTile(int cost, int fee)
    {
        this.cost = cost;
        this.fee = fee;
    }

    public void PlayerStands(Player player)
    {
        if (owner != null)
        {
            if (player != owner)
            {
                System.out.println("You stands on tile which owned by " + owner.getName() + " player");
                System.out.println("You should pay " + fee + "$");
                player.TransferBalanceToPlayer(owner, fee);
            }
            else
            {
                System.out.println("You stands on your own tile");
            }
        }
        else
        {
            if (player.getBalance() >= cost)
            {
                SellTile(player);
            }
        }
    }

    private void SellTile(Player player)
    {
        Scanner reader = new Scanner(System.in);

        while (true)
        {
            System.out.println("You have enough money to buy this tile");
            System.out.println("Are u wanna buy in for " + cost + "$ (visiting fee +" + fee + "$)");
            System.out.println("(Y)es/(N)o");

            String input = reader.nextLine();

            if (input.equals("Y") || input.equals("Yes"))
            {
                player.ChangeBalance(-cost);
                player.AddProperty(this);
                owner = player;

                return;
            }
            if (input.equals("N") || input.equals("No"))
            {
                return;
            }
        }
    }

    public void setOwner(Player owner)
    {
        this.owner = owner;
    }
}
