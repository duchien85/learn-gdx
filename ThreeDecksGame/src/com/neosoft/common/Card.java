package com.neosoft.common;

import java.io.Serializable;

/**
 * @brief Card info
 * @author  Lakers
 */
public class Card implements Serializable {

	private static final long serialVersionUID = -6789137170697532546L;
	
	private CardSuit suit;
	private CardRank rank;
	
	public Card(CardSuit suit, CardRank rank) {
		super();

		this.suit = suit;
		this.rank = rank;
	}

	public CardSuit getSuit() {
		return suit;
	}

	public CardRank getRank() {
		return rank;
	}
	
}
