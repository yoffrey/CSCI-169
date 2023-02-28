$VERBOSE = nil
class BlackjackController < ApplicationController
  skip_before_action :verify_authenticity_token
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
  def start

  end
  def save_name_cash
    if (params[:redirect_to] == "/continue" or params[:redirect_to] == "/bet") and session[:player_name] and session[:cash] > 0
      redirect_to bet_path
    elsif params[:player] and params[:deposit].to_i>0
      if params[:player] == ""
        flash[:error] = "Enter a name"
        redirect_to root_path
      end
      session[:player_name] = params[:player]
      session[:cash] = params[:deposit].to_i
      redirect_to bet_path
    else
      redirect_to root_path
    end
  end

  def bet
    @player_cash = session[:cash]
  end

  def save_bet
    if params[:redirect_to] == "/submit"
      if params[:bet].to_i > session[:cash]
        flash[:error] = "You do not have enough cash."
        redirect_to bet_path
      elsif params[:bet].to_i <= 0
        flash[:error] = "Bet more."
      elsif params[:bet] ==""
        redirect_to bet_path
      else
        session[:bet] = params[:bet].to_i
        # Create the deck
        suits = ['♠', '♣', '♥', '♦']
        ranks = %w(2 3 4 5 6 7 8 9 10 J Q K A)
        @deck = ranks.product(suits)
        @deck.map! {|card| card.join('')}
        session[:deck] = @deck.shuffle

        session[:player_cards] = []
        session[:dealer_cards] = []
        # Player gets 2 cards
        session[:player_cards] << session[:deck].pop
        session[:player_cards] << session[:deck].pop
        # Dealer gets 2 cards
        session[:dealer_cards] << session[:deck].pop
        session[:dealer_cards] << session[:deck].pop
        redirect_to deal_new_path
      end
    else
      redirect_to root_path
    end
  end

  def deal_new
    @player_cards = session[:player_cards]
    player_cards = session[:player_cards]
    @player_score = score_hand(player_cards).to_s

    dealer_cards = session[:dealer_cards]
    @dealer_card = dealer_cards[0]
    @dealer_score = score_hand(dealer_cards.slice(0, 1)).to_s
  end

  def game
    if params[:redirect_to] == "/hit"
      session[:player_cards] << session[:deck].pop
      player_cards = session[:player_cards]
      player_score = score_hand(player_cards)
      if player_score >= 21
        redirect_to game_stand_path
      else
        redirect_back(fallback_location: root_path)
      end
    else
      redirect_to game_stand_path
    end
  end

  def game_stand
    @player_cards = session[:player_cards]
    player_cards = session[:player_cards]
    player_score = score_hand(player_cards)
    @player_score = score_hand(player_cards).to_s
    player = session[:player_name]

    dealer_cards = session[:dealer_cards]
    dealer_score = score_hand(dealer_cards)
    if player_score > 21
      @result = "#{player} busts. #{player} loses!"
      session[:cash] = session[:cash] - session[:bet]
    else
      until dealer_score >= 17 || dealer_score > player_score ||dealer_score == 21
        session[:dealer_cards] << session[:deck].pop
        dealer_cards = session[:dealer_cards]
        dealer_score = score_hand(dealer_cards)
      end
      if dealer_score > 21
        @result = "Dealer busts. #{player} Wins!"
        session[:cash] = session[:cash] + session[:bet]
      elsif dealer_score == player_score
        @result = "Tie"
        # session[:cash] = session[:cash]
      elsif dealer_score == 21
        @result = "Dealer Blackjack! #{player} Loses!"
        session[:cash] = session[:cash] - session[:bet]
      elsif dealer_score < 21
        if player_score == 21
          @result = "#{player} Blackjack! #{player} Wins!"
          session[:cash] = session[:cash] + session[:bet]
        elsif dealer_score > player_score
          @result = "Dealer Wins!"
          session[:cash] = session[:cash] - session[:bet]
        else
          @result = "#{player} Wins!"
          session[:cash] = session[:cash] + session[:bet]
        end
      end

    end
    @dealer_cards = session[:dealer_cards]
    @dealer_score = score_hand(dealer_cards)
  end
end

