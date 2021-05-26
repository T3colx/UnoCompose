package com.example.unocompose.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.unocompose.models.cards.Card
import com.example.unocompose.models.cards.DeckOfCards

class GameScreenViewModel: ViewModel() {

    private val deck = DeckOfCards()
    private val myCardsList = mutableListOf<Card>()
    private var lastPlayedCard = Card("pink","skip")

//    val lastPlayedCards: Queue<String> = LinkedList<String>(listOf("orange_3", "purple_reverse", "pink_skip"))
//    val lastPlayedCardsState = mutableStateOf(lastPlayedCards)



    val lastPlayedCardState = mutableStateOf("")
    val myCardsState = mutableStateOf(listOf<String>())

    val isUnoVisible = mutableStateOf(false)
    val cardCounter = mutableStateOf(myCardsList.size)
    val specialEffectOnMe = mutableStateOf("")
    val mySpecialEffect = mutableStateOf("")

    init {
        deck.createDeck()
        lastPlayedCard = deck.getRandomCard()
        lastPlayedCardState.value = lastPlayedCard.drawableName
        for (iterations in 0..30) {
            addToList(deck.getRandomCard())
        }
    }

    fun changeLastPlayedCard(card: Card) {
        lastPlayedCard = card
        lastPlayedCardState.value = lastPlayedCard.drawableName
    }

    private fun removeFromList(index: Int){
//        addToQueue(myCardsList[index])
        changeLastPlayedCard(myCardsList[index])
        deck.returnCard(myCardsList[index])
        myCardsList.removeAt(index)
        myCardsState.value = myCardsList.map { it.drawableName }.toList()
        cardCounter.value -= 1
        if (cardCounter.value == 2) isUnoVisible.value = true
    }

    private fun addToList(name: Card) {
        myCardsList.add(name)
        myCardsList.sortBy { card ->
            card.color
        }
        myCardsState.value = myCardsList.map { it.drawableName }.toList()
        cardCounter.value += 1
    }

//    fun addToQueue(name: String) {
//        lastPlayedCards.remove()
//        lastPlayedCards.add(name)
//        lastPlayedCardsState.value = lastPlayedCards
//    }


    fun makeMoveWithCard(index: Int) {
        if (
            myCardsList[index].type == lastPlayedCard.type ||
            myCardsList[index].color == lastPlayedCard.color ||
            lastPlayedCard.color == "wild" &&
            myCardsList[index].color == lastPlayedCard.type.split("_")[1]
        ) {
            removeFromList(index)
        }
    }



}