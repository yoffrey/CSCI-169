$VERBOSE = nil

class Deck
  def initialize
    suits = ['♠', '♣', '♥', '♦']
    ranks = %w(2 3 4 5 6 7 8 9 10 J Q K A)
    @deck = ranks.product(suits)
    @deck.map! {|card| card.join('')}
  end

  def draw
    first_card = @deck.sample
    @deck.delete(first_card)
    second_card = @deck.sample
    @deck.delete(second_card)
    hand = [first_card, second_card]
  end

  def hit
    card = @deck.sample
    @deck.delete(card)
  end
end

class Hand

  attr_reader :score, :hand, :cash

  def initialize(player)
    @hand = []
    @score = score_hand(@hand)
    @player = player.capitalize
    @cash = 1000
  end

  def deal
    @hand = @@current_deck.draw
    @score = score_hand(hand)
    if @player == "Dealer"
      puts "#{@player} was dealt #{@hand[0]}"
      puts "#{@player} only reveals his first card."
    else
      puts "#{@player} was dealt #{@hand[0]}"
      puts "#{@player} was dealt #{@hand[1]}"
      puts "#{@player} total: #{@score}"
    end
  end

  def reveal
    puts "#{@player} has a #{@hand[0]}"
    puts "#{@player} has a #{@hand[1]}"
    puts "#{@player} total: #{@score}"
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

  def add_card
    card = @@current_deck.hit
    puts "#{@player} was dealt #{card}"
    @hand << card
    @score = score_hand(@hand)
    puts "#{@player} score: #{@score}"
  end

  def win(bet)
    @cash = @cash + bet
  end

  def lose(bet)
    @cash = @cash - bet
  end
end

puts "Welcome to Blackjack!"
player_hand = Hand.new("player")
dealer_hand = Hand.new("dealer")
quit_check = nil
until quit_check == "Y"
  @@current_deck = Deck.new
  puts "\n"
  bet = 0
  if player_hand.cash <= 0
    puts "You are out of money!"
    break
  end
  puts "You have $#{player_hand.cash}. How much would you like to bet?"
  bet = gets.to_i
  while (bet > player_hand.cash) || bet == 0
    puts "You have $#{player_hand.cash}. How much would you like to bet?"
    bet = gets.chomp
  end
  player_hand.deal
  puts "\n"
  dealer_hand.deal
  puts "\n"
  response = nil
  bust = false

  until response == "S"
    if player_hand.score == 21
      puts "Blackjack!"
      break
    end

    puts "Hit or stand (H/S)"
    response = gets.chomp.capitalize
    if response == "H"
      player_hand.add_card
    elsif response != "S"
      puts "Invalid Response"
    end

    if player_hand.score > 21
      player_hand.lose(bet)
      puts "You lose. You now have $#{player_hand.cash}"
      response = "S"
      bust = true
    end
  end

  if bust == false
    puts "\n"
    dealer_hand.reveal
    until dealer_hand.score >= 17 || dealer_hand.score > player_hand.score || dealer_hand.score == 21
      dealer_hand.add_card
    end
    if dealer_hand.score == 21
      puts "Dealer Blackjack!"
      player_hand.lose(bet)
      puts "You lose. You now have $#{player_hand.cash}"
    elsif dealer_hand.score < 21
      puts "Dealer stands."
      if dealer_hand.score > player_hand.score
        player_hand.lose(bet)
        puts "You lose. You now have $#{player_hand.cash}"
      else
        player_hand.win(bet)
        puts "You win! You now have $#{player_hand.cash}"
      end
    else
      player_hand.win(bet)
      puts "Dealer busts. You win! You now have $#{player_hand.cash}"
    end
  end
  puts "Would you like to quit? (Y/N)?"
  quit_check = gets.chomp.capitalize
end


