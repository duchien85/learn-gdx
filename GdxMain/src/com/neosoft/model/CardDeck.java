package com.neosoft.model;


/**
 * @brief full 54 cards
 * @author Lakers
 *
 */
public class CardDeck extends CardStack {
	
	public CardDeck()
	{
		super();
		
		// A / 2 /3 ... J / Q / K
		for (CardRank rank : CardRank.values())
		{
			// spade / heart / club / diamond
			for (CardSuit suit : CardSuit.values())
			{
				if (rank != CardRank.JOKER)
				{
					super.push(new Card(suit, rank));
				}
			}
		}		
		
		// add the Jokers
		super.push(new Card(CardSuit.HEARTS, CardRank.JOKER));
		super.push(new Card(CardSuit.SPADES, CardRank.JOKER));
	}
}
