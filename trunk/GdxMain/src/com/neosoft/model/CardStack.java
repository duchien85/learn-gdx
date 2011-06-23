package com.neosoft.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * @brief any number of cards
 * @author Lakers
 *
 */
public class CardStack {
	
	protected final List<Card> cards = new ArrayList<Card>();

	public void push(Card card) {
		this.cards.add(card);
	}

	public void push(Card[] cards) {
		for (int i = 0; i < cards.length; i++) {
			this.cards.add(cards[i]);
		}
	}

	public void push(CardStack stack) {
		this.cards.addAll(stack.cards);
	}

	public void shuffle() {
		List<Card> tmpList = new ArrayList<Card>(this.cards.size());

		while (this.cards.size() > 0) {
			int randomIdx = Math.abs(new Random(new Date().getTime()).nextInt()) % this.cards.size();
			tmpList.add(this.cards.remove(randomIdx));
		}

		this.cards.addAll(tmpList);
	}
	
	public void clear() {
		this.cards.clear();
	}
	
	public Card pop() {
		if (this.cards.size() > 0) {
			Card card = this.cards.remove(this.cards.size() - 1);
			return card;
		}

		return null;
	}

	public int size() {
		return this.cards.size();
	}

	public Card[] getCards() {
		return this.cards.toArray(new Card[0]);
	}
}
