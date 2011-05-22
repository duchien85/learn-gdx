package com.neosoft.test;

import com.neosoft.common.Card;
import com.neosoft.common.CardRank;
import com.neosoft.common.CardSuit;
import com.neosoft.common.PlayerID;
import com.neosoft.core.Arbitrator;

public class ArbitratorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Arbitrator arbitrator = new Arbitrator();
		
		Card[][] cardsArray = new Card[4][];
		cardsArray[0] = new Card[] { new Card(CardSuit.SPADES, CardRank.FOUR), new Card(CardSuit.SPADES, CardRank.FOUR) };
		cardsArray[1] = new Card[] { new Card(CardSuit.SPADES, CardRank.FIVE), new Card(CardSuit.SPADES, CardRank.FIVE) };
		cardsArray[2] = new Card[] { new Card(CardSuit.SPADES, CardRank.ACE), new Card(CardSuit.SPADES, CardRank.FOUR) };
		cardsArray[3] = new Card[] { new Card(CardSuit.SPADES, CardRank.THREE), new Card(CardSuit.SPADES, CardRank.FOUR) };
		
		System.out.println(arbitrator.Arbitrate(cardsArray, PlayerID.PID_NORTH));
	}
}
