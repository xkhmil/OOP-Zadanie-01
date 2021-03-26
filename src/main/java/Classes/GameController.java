package Classes;
import Classes.Cards.Card;
import Classes.Cards.LoseCard;
import Classes.Cards.MoneyCard;
import Classes.Cards.MoveCard;
import Classes.Tiles.*;

import java.lang.reflect.Array;
import java.util.*;

public class GameController
{
    //input
    private Scanner reader;

    //Players
    private int playerCount;
    private static final int MAXPLAYERCOUNT = 4;
    private Vector<Player> players = new Vector<Player>();
    private Queue<Player> queue = new LinkedList<Player>();

    private Vector<Tile> map = new Vector<Tile>();

    private Queue<Card> cardsQueue = new LinkedList<Card>();

    public GameController()
    {
        reader = new Scanner(System.in);
    }

    public void PrepareGame()
    {
        playerCount = GetPlayerCount();

        SetPlayers();

        GenerateMap();

        GenerateChanceCards();

        StartGame();
    }

    public Vector<Tile> getMap()
    {
        return map;
    }

    public void StartGame()
    {
        for (Player obj : players)
        {
            queue.add(obj);
        }

        while (queue.size() != 1)
        {
            Player currentPlayer = queue.peek();

            if (currentPlayer.getSkipTurns() != 0)
            {
                System.out.println("---------------------------------");
                System.out.println("Current player " + currentPlayer.getName() + " skipped turn");

                currentPlayer.ChangeSkipTurns(-1);
            }
            else
            {
                int dice = getRandomNumber(1, 6);

                System.out.println("---------------------------------");
                System.out.println("Current player " + currentPlayer.getName() + " rolled " + dice);

                currentPlayer.MovePlayer(dice, this);
            }

            queue.remove();

            //If player doesn't lose, add to queue
            if (currentPlayer.getBalance() >= 0)
            {
                queue.add(currentPlayer);
            }

            System.out.println("Press enter to go next player");
            reader.nextLine();
        }

        System.out.println("Player " + queue.peek().getName() + " win!!!!");
    }

    public void PrintUI()
    {
        PrintPlayers();
        PrintMap();
    }


    public void PrintMap()
    {
        String string = "";

        for(Tile obj: map)
        {
            if (obj.getClass().equals(PropertyTile.class))
            {
                string += "$";
            }
            else if (obj.getClass().equals(ChanceTile.class))
            {
                string += "c";
            }
            else if (obj.getClass().equals(JailTile.class))
            {
                string += "#";
            }
            else if (obj.getClass().equals(PoliceTile.class))
            {
                string += "p";
            }
            else if (obj.getClass().equals(StartTile.class))
            {
                string += "S";
            }
            else if (obj.getClass().equals(TaxTile.class))
            {
                string += "t";
            }
        }

        char[] charArray = string.toCharArray();

        charArray[queue.peek().getCoordinate()] = '@';

        for (Player obj : queue)
        {
            if (charArray[obj.getCoordinate()] != '@')
            {
                charArray[obj.getCoordinate()] = '*';
            }
        }

        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                if (i == 0)
                {
                    System.out.printf("%c ", charArray[j]);
                    if (j == 6)
                        System.out.printf("%n");
                }
                else if (i == 6)
                {
                    System.out.printf("%c ", charArray[18 - j]);
                    if (j == 6)
                        System.out.printf("%n");
                }
                else if (j == 0)
                {
                    System.out.printf("%c ", charArray[24 - i]);
                }
                else if (j == 6)
                {
                    System.out.printf("%c %n", charArray[6 + i]);
                }
                else
                    System.out.printf("  ");
            }
        }
    }

    private void GenerateChanceCards()
    {
        Vector<Card> cards = new Vector<Card>();

        cards.add(new LoseCard());
        cards.add(new MoneyCard(2000));
        cards.add(new MoneyCard(2500));
        cards.add(new MoveCard(1, this));
        cards.add(new MoveCard(4, this));

        while (cards.size() != 0)
        {
            cardsQueue.add(cards.remove(getRandomNumber(0, cards.size())));
        }
    }

    private void GenerateMap()
    {
        map.add(new StartTile(500));
        map.add(new ChanceTile(this));
        for (int i = 1; i <= 4; i++)
        {
            map.add(new PropertyTile(100*i, 50*i));
        }

        map.add(new JailTile());
        map.add(new ChanceTile(this));
        for (int i = 1; i <= 4; i++)
        {
            map.add(new PropertyTile(250*i, 100*i));
        }

        map.add(new PoliceTile(3));
        map.add(new ChanceTile(this));
        for (int i = 1; i <= 4; i++)
        {
            map.add(new PropertyTile(300*i, 200*i));
        }

        map.add(new TaxTile(2000));
        map.add(new ChanceTile(this));
        for (int i = 1; i <= 4; i++)
        {
            map.add(new PropertyTile(350*i, 800*i));
        }
    }

    private void SetPlayers()
    {
        for (int i = 0; i < playerCount; i++)
        {
            System.out.println("Set Player " + i + " name");
            players.add(new Player(reader.nextLine()));
        }
    }

    private int GetPlayerCount()
    {
        while (true)
        {
            System.out.println("Set players count");

            String input = reader.nextLine();

            try
            {
                int num = Integer.parseInt(input);

                if (num >= 1 && num <= MAXPLAYERCOUNT)
                {
                    return num;
                }
                else
                {
                    System.out.println("Player count should be from 1 to " + MAXPLAYERCOUNT);
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Wrong format");
            }
        }
    }

    public void PrintPlayers()
    {
        System.out.println("Turns:");
        for (Player player:queue)
        {
            System.out.println(player.getName() + "(" + player.getBalance() + "$)" +
                    (player.getSkipTurns() != 0 ? " skip " + player.getSkipTurns() : ""));
        }
    }

    public void DrawChanceCardByPlayer(Player player)
    {
        if (cardsQueue.size() == 0)
            GenerateChanceCards();

        cardsQueue.poll().DrawCard(player);
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
