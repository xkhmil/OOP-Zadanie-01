package Classes.Cards;

import Classes.Player;

public class MoneyCard extends Card
{
    private int value;

    public MoneyCard(int value)
    {
        this.value = value;
    }

    public void DrawCard(Player player)
    {
        System.out.println("You draw money card and gain " + value + "$");
        player.ChangeBalance(value);
    }
}
