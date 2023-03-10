$VERBOSE = nil

class Deck

  attr_reader :deck

  def initialize(current_deck = [])
    if current_deck.length > 0
      @deck = current_deck
    else
      suits = ['♠', '♣', '♥', '♦']
      ranks = %w(2 3 4 5 6 7 8 9 10 J Q K A)
      @deck = ranks.product(suits)
      @deck.map! {|card| card.join('')}
    end
  end

  def draw(current_deck)
    first_card = current_deck.sample
    current_deck.delete(first_card)
    second_card = current_deck.sample
    current_deck.delete(second_card)
    return [first_card, second_card]
  end

  def hit(current_deck)
    card = current_deck.sample
    current_deck.delete(card)
    return card
  end
end
