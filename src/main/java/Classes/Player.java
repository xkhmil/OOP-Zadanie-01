package Classes;

import Classes.Tiles.PropertyTile;
import Classes.Tiles.Tile;

import java.util.Vector;

public class Player
{
    private String name;
    private int balance;
    private int coordinate;

    private Vector<PropertyTile> propertyTiles = new Vector<PropertyTile>();

    private int skipTurns;

    public Player(String name)
    {
        this.name = name;
        balance = 5000;
    }

    public void ChangeBalance(int value)
    {
        balance += value;

        if (balance < 0)
        {
            Lose();
        }
    }

    public void TransferBalanceToPlayer(Player player, int value)
    {
        if (value < balance)
        {
            player.ChangeBalance(value);
        }
        else
        {
            player.ChangeBalance(balance);
        }

        ChangeBalance(-value);
    }

    public int getBalance()
    {
        return balance;
    }

    public String getName()
    {
        return name;
    }

    public void Lose()
    {
        System.out.println("Player " + name + " loses!");

        balance = -1;

        for (PropertyTile obj:propertyTiles) {
            obj.setOwner(null);
        }
    }

    public void AddProperty(PropertyTile propertyTile)
    {
        propertyTiles.add(propertyTile);
    }

    public int getSkipTurns()
    {
        return skipTurns;
    }

    public void ChangeSkipTurns(int value)
    {
        skipTurns += value;
    }

    public void setCoordinate(int coordinate)
    {
        this.coordinate = coordinate;
    }

    public int getCoordinate()
    {
        return coordinate;
    }

    public void MovePlayer(int value, GameController gameController)
    {
        if (coordinate + value >= 24)
        {
            //Give start bonus
            coordinate = coordinate + value - 24;
            gameController.getMap().get(0).PlayerStands(this);
        }
        else
        {
            coordinate = coordinate + value;
        }

        //Draw turns queue and map
        gameController.PrintUI();

        //Start bonus was already given
        if (coordinate != 0)
        {
            gameController.getMap().get(coordinate).PlayerStands(this);
        }
    }
}
