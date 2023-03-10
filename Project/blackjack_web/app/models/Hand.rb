$VERBOSE = nil

class Hand

  attr_reader :score, :hand, :cash, :player

  def initialize(player, deposit=0, hand=[])
    @hand = hand
    @score = score_hand(@hand)
    @player = player
    @cash = deposit
  end

  def deal(current_deck)
    @hand = current_deck.draw(current_deck.deck)
    @score = score_hand(hand)
  end

  def add_card(current_deck)
    current_deck = Deck.new(current_deck)
    @hand.append(current_deck.hit(current_deck.deck))
    @score = score_hand(@hand)
  end

  def clear_hand
    @hand = []
  end

  def clear_suits(card)
    card.delete "♠", "♣", "♦", "♥"
  end

  def score_hand(hand)
    hand.map { |i| clear_suits(i) }
    aces_amt = aces_count(hand)
    hand.delete("A")
    hand.map { |i| i.gsub!(/[JQK]/, '10')}
    hand_sum = 0
    hand.each { |i| hand_sum += i.to_i}

    until aces_amt == 0
      if hand_sum > 10
        hand_sum += 1
        #aces_amt -= 1
      elsif hand_sum < 11 && aces_amt == 1
        hand_sum += 11
        #aces_amt -= 1
      else
        hand_sum += 1
        #aces_amt -= 1
      end
      aces_amt -= 1
    end
    hand_sum
  end

  def aces_count(hand)
    aces = hand.join('').scan('A')
    aces.size
  end

  def win(bet)
    @cash += bet
  end

  def lose(bet)
    @cash -= bet
  end
end