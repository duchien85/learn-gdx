package com.neosoft.core;

import java.util.LinkedList;

import com.neosoft.common.Card;
import com.neosoft.common.CardRank;
import com.neosoft.common.CardSuit;
import com.neosoft.common.PlayerID;

public class Arbitrator {
	
	private CardSuit currentSuit = CardSuit.SPADES;
	private CardRank currentRank = CardRank.THREE;
	
	private final LinkedList<CardRank> rankSequence = new LinkedList<CardRank>();
	
	public Arbitrator() {
		this.updateRankSequence();
	}

	public CardSuit getCurrentSuit() {
		return currentSuit;
	}

	public void setCurrentSuit(CardSuit currentSuit) {
		this.currentSuit = currentSuit;
	}

	public CardRank getCurrentRank() {
		return currentRank;
	}

	public void setCurrentRank(CardRank currentRank) {
		this.currentRank = currentRank;
		this.updateRankSequence();
	}

	/**
	 * @brief Determines whose turn it is to act
	 * @param cardsArray [IN] array of each player's cards
	 * @param playerID [IN] ID of the first player who acts
	 * @return
	 */
	public PlayerID Arbitrate(Card[][] cardsArray, PlayerID playerID) {		
		// parameter check
		if (null == cardsArray) {
			return playerID;
		}
		
		// dimension check
		for (Card[] cards : cardsArray) {
			if (0 == cards.length) {
				return playerID;
			} else if (cards.length != cardsArray[0].length) {
				return playerID;
			}
		}
		
		int idxToAct = 0;
		
		for (int i = 1; i < cardsArray.length; i++) {
			if (this.isSingle(cardsArray[0])) {
				if (this.compare(cardsArray[idxToAct][0], cardsArray[i][0]) < 0) {
					idxToAct = i;
				}
			} else if (this.isPair(cardsArray[0]) || this.isTriplet(cardsArray[0])) {
				if (this.isPair(cardsArray[i]) || this.isTriplet(cardsArray[i])) {
					if (this.compare(cardsArray[idxToAct][0], cardsArray[i][0]) < 0) {
						idxToAct = i;
					}	
				}
			} else {
				/*
				 * TODO: special occasion when the first player's cards are all primary ones
				 * and cards of others are all secondary ones.
				 */
			}
		}
				
		return this.indexToPlayerID(idxToAct);
	}
	
	private PlayerID indexToPlayerID(int idx) {
		PlayerID playerID = PlayerID.PID_NORTH;
		
		switch (idx) {
		case 0:
			playerID = PlayerID.PID_NORTH;
			break;
		case 1:
			playerID = PlayerID.PID_WEST;
			break;
		case 2:
			playerID = PlayerID.PID_SOUTH;
			break;
		case 3:
			playerID = PlayerID.PID_EAST;
			break;
		default:
			assert(idx >=0 && idx <= 3);
			break;
		}
		
		return playerID;
	}
	
	private void updateRankSequence() {
		this.rankSequence.clear();
		
		for (CardRank rank : CardRank.values()) {
			if (rank != this.currentRank && rank != CardRank.JOKER) {
				this.rankSequence.addLast(rank);
			}
		}
		
		this.rankSequence.addLast(this.currentRank);
		this.rankSequence.addLast(CardRank.JOKER);
	}
	
	/*private*/public int getWeight(Card card) {
		int weight = 1;
		
		for (int i = 0; i < this.rankSequence.size(); i++) {
			if (card.getRank() == this.rankSequence.get(i)) {
				break;
			}
			
			weight += 2;
		}
		
		if (this.isPrimaryCard(card)) {
			if (this.isJoker(card)) {
				weight++;
			} else if (card.getRank() == CardRank.DEUCE || card.getRank() == this.currentRank) {
				if (card.getSuit() == this.currentSuit) {
					weight++;
				}
			}
		}		
		
		return weight;
	}
	
	private boolean isSingle(Card[] cards) {
		if (null == cards || cards.length != 1) {
			return false;
		}
		
		return true;
	}
	
	private boolean isPair(Card[] cards) {
		if (null == cards || cards.length != 2) {
			return false;
		}
		
		for (Card card : cards) {
			if (card.getSuit() != cards[0].getSuit()) {
				return false;
			} else if (card.getRank() != cards[0].getRank()) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean isTriplet(Card[] cards) {
		if (null == cards || cards.length != 3) {
			return false;
		}
		
		for (Card card : cards) {
			if (card.getSuit() != cards[0].getSuit()) {
				return false;
			} else if (card.getRank() != cards[0].getRank()) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean isJoker(Card card) {
		if (CardRank.JOKER == card.getRank() && CardSuit.SPADES == card.getSuit()) {
			return true;
		}
		
		return false;
	}
	
	private boolean isViceJoker(Card card) {
		if (CardRank.JOKER == card.getRank() && CardSuit.SPADES == card.getSuit()) {
			return true;
		}
		
		return false;
	}
	
	private boolean isPrimaryCard(Card card) {
		if (this.isJoker(card) || this.isViceJoker(card)) {
			return true;
		} else if (CardRank.DEUCE == card.getRank()) {
			return true;
		} else if (this.currentSuit == card.getSuit()) {
			return true;
		} else if (this.currentRank == card.getRank()) {
			return true;
		}
		
		return false;
	}
	
	private boolean isSecondaryCard(Card card) {
		return !this.isPrimaryCard(card);
	}
	
	/**
	 * @brief compare a with b
	 * @param a [IN]
	 * @param b [IN]
	 * @return if a == b, return 0; if a > b, return 1; if a < b, return -1;
	 */
	private int compare(Card a, Card b) {		
		// primary card is always 'greater' than secondary card
		if (this.isPrimaryCard(a) && this.isSecondaryCard(b)) {
			return 1;
		} else if (this.isSecondaryCard(a) && this.isPrimaryCard(b)) {
			return -1;
		}
		
		if (this.getWeight(a) > this.getWeight(b)) {
			return 1;
		} else if (this.getWeight(a) == this.getWeight(b)) {
			return 0;
		} else {
			return -1;
		}
	}
}
