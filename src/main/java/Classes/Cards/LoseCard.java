package Classes.Cards;

import Classes.Player;

public class LoseCard extends Card
{
    public void DrawCard(Player player)
    {
        System.out.println("You draw lose card, unlucky");
        player.Lose();
    }
}
