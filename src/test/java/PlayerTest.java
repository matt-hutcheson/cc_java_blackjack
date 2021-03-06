import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player1;
    Card cardTen;
    Card cardEight;
    Card cardAce;
    @Before
    public void before(){
        player1 = new Player("Lucky Bob");
        cardTen = new Card(SuitType.HEARTS, RankType.TEN);
        cardEight = new Card(SuitType.SPADES, RankType.EIGHT);
        cardAce = new Card(SuitType.DIAMONDS, RankType.ACE);
    }
    @Test
    public void HasName(){
        assertEquals("Lucky Bob", this.player1.getName());
    }
    @Test
    public void canAddCardToHand() {
        player1.addCard(this.cardTen);
        assertEquals(1, player1.getHand().size());
    }
    @Test
    public void canRemoveCardFromHand(){
        player1.addCard(this.cardTen);
        player1.clearHand();
        assertEquals(0, player1.getHand().size());
    }
    @Test
    public void canGetHandMultipleCards(){
        player1.addCard(cardTen);
        player1.addCard(cardEight);
        assertEquals(2, player1.getHand().size());
    }
    @Test
    public void canGetCard(){
        player1.addCard(cardTen);
        assertEquals(cardTen, player1.getCard(0));
    }
    @Test
    public void canGetTotalHandScore__NoAces(){
        player1.addCard(cardTen);
        player1.addCard(cardEight);
        player1.calcHandScores();
        ArrayList<Integer> results = player1.getResults();
        Integer result = 18;
        assertEquals(result, results.get(0));
    }
    @Test
    public void canGetTotalHandScores_1Ace(){
        player1.addCard(cardTen);
        player1.addCard(cardAce);
        player1.calcHandScores();
        ArrayList<Integer> results = player1.getResults();
        Integer result = 11;
        Integer resultAce = 21;
        assertEquals(result, results.get(0));
        assertEquals(resultAce, results.get(1));
    }
    @Test
    public void canGetTotalHandScores_3Aces(){
        player1.addCard(cardAce);
        player1.addCard(cardAce);
        player1.addCard(cardAce);
        player1.calcHandScores();
        ArrayList<Integer> results = player1.getResults();
        Integer result1 = 3;
        Integer result2 = 13;
        Integer result3 = 23;
        Integer result4 = 33;
        assertEquals(result1, results.get(0));
        assertEquals(result2, results.get(1));
        assertEquals(result3, results.get(2));
        assertEquals(result4, results.get(3));
    }
    @Test
    public void canCheckIfPlayerIsBust_FalseNoAces(){
        player1.addCard(cardTen);
        player1.addCard(cardEight);
        player1.calcHandScores();
        player1.checkIfBust();
        assertEquals(false, player1.getBust());
    }
    @Test
    public void canCheckIfPlayerIsBust_TrueNoAces(){
        player1.addCard(cardTen);
        player1.addCard(cardEight);
        player1.addCard(cardTen);
        player1.calcHandScores();
        player1.checkIfBust();
        assertEquals(true, player1.getBust());
    }
    @Test
    public void canCheckIfPlayerIsBust_FalseAces(){
        player1.addCard(cardTen);
        player1.addCard(cardAce);
        player1.addCard(cardAce);
        player1.calcHandScores();
        player1.checkIfBust();
        assertEquals(false, player1.getBust());
    }
    @Test
    public void canCheckIfPlayerIsBust_TrueAces(){
        player1.addCard(cardTen);
        player1.addCard(cardAce);
        player1.addCard(cardAce);
        player1.addCard(cardTen);
        player1.calcHandScores();
        player1.checkIfBust();
        assertEquals(true, player1.getBust());
    }
    @Test
    public void canGetDealer(){
        Player dealer = new Player("Dealer");
        dealer.setDealer();
        assertTrue(dealer.getDealer());
    }
    @Test
    public void playerNotDealer(){
        assertFalse(player1.getDealer());
    }
    @Test
    public void canGetStuck(){
        player1.setStuck(true);
        assertTrue(player1.getStuck());
    }
    @Test
    public void canGetBestScore(){
        player1.addCard(cardAce);
        player1.addCard(cardAce);
        player1.addCard(cardEight);
        player1.addCard(cardTen);
        player1.calcHandScores();
        assertEquals(20, player1.getBestScore());
    }
}
