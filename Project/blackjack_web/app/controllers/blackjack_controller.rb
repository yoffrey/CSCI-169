class BlackjackController < ApplicationController
  skip_before_action :verify_authenticity_token

  def start
    # If player exists, give user option to continue playing
    if session[:player_hand] and session[:player_hand]["cash"] > 0
      player = session[:player_hand]["player"]
      cash = session[:player_hand]["cash"]
      @continue = "You may continue the game as #{player} with $#{cash}."
    end
  end

  def save_name_cash
    # Check which page the user came from and if inputs are valid
    if params[:redirect_to] == "/continuegame" and session[:player_hand]["cash"] > 0
      redirect_to bet_path
    elsif params[:redirect_to] == "/continuefromprevious" and session[:player_hand]["cash"] > 0
      redirect_to bet_path
    elsif params[:redirect_to] == "/submit" and params[:player] != "" and params[:deposit].to_i>0
      # Start new game
      player_hand = Hand.new(params[:player], params[:deposit].to_i)
      session[:player_hand] = player_hand
      redirect_to bet_path
    else
      if params[:redirect_to] == "/continuegame" and session[:player_hand]["cash"] <= 0
        flash[:error] = "You are out of money."
      elsif  params[:player] == ""
        flash[:error] = "Please enter a name."
      elsif params[:deposit].to_i < 0 or params[:deposit] == ""
        flash[:error] = "Place a deposit."
      end

      redirect_to root_path
    end
  end

  def bet
    player_hand = session[:player_hand]
    @player_cash = player_hand["cash"].to_s
  end

  def save_bet
    player_hand = session[:player_hand]

    # Check if bet is valid
    if params[:redirect_to] == "/submit"
      if params[:bet].to_i > player_hand["cash"]
        flash[:error] = "You do not have enough cash."
        redirect_to bet_path
      elsif params[:bet].to_i <= 0
        flash[:error] = "Bet more."
        redirect_to bet_path
      elsif params[:bet] == ""
        flash[:error] = "Place a bet."
        redirect_to bet_path
      else
        session[:bet] = params[:bet].to_i

        # Create the deck
        current_deck = Deck.new

        # Deal two cards to player and dealer
        player_hand = Hand.new(player_hand["player"], player_hand["cash"], [])
        player_hand.deal(current_deck)

        dealer_hand = Hand.new("Dealer", 0, [])
        dealer_hand.deal(current_deck)

        # Save everything to session
        session[:current_deck] = current_deck
        session[:player_hand] = player_hand
        session[:dealer_hand] = dealer_hand

        redirect_to deal_new_path
      end
    else
      redirect_to root_path
    end
  end

  def deal_new
    # Get the cards and score of player hand
    player_hand = session[:player_hand]
    @player_cards = player_hand["hand"]
    @player_score = player_hand["score"]

    # Get the card and score of the first dealer's card
    dealer_hand = session[:player_hand]
    dealer_hand = Hand.new(dealer_hand["name"], 0, dealer_hand["hand"])
    @dealer_card = dealer_hand.hand
    @dealer_score = dealer_hand.score_hand([dealer_hand.hand.slice(0)])
  end

  def game
    # Get session data for player and deck. Initialize them again
    player_hand = session[:player_hand]
    player_hand = Hand.new(player_hand["player"], player_hand["cash"], player_hand["hand"])

    current_deck = session[:current_deck]["deck"]
    current_deck = Deck.new(current_deck)

    if params[:redirect_to] == "/hit"
      # If player hits, add a card to their hand
      player_hand.add_card(current_deck.deck)
      player_score = player_hand.score

      # Save to session storage
      session[:current_deck] = current_deck
      session[:player_hand] = player_hand

      # Check score to determine which page to go to
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
    # Initialize deck, player hand and dealer hand
    current_deck = session[:current_deck]["deck"]
    current_deck = Deck.new(current_deck)

    player_hand = session[:player_hand]
    player_hand = Hand.new(player_hand["player"], player_hand["cash"], player_hand["hand"])
    player = player_hand.player

    dealer_hand = session[:dealer_hand]
    dealer_hand = Hand.new(dealer_hand["Dealer"], 0, dealer_hand["hand"])

    bet = session[:bet]

    @player_cards = player_hand.hand
    @player_score = player_hand.score

    @dealer_cards = dealer_hand.hand
    @dealer_score = dealer_hand.score

    # If player score > 21, dealer does not need to go.
    if @player_score > 21
      @result = "#{player} busts. #{player} loses!"
      player_hand.lose(bet)
    else
      until @dealer_score >= 17 || @dealer_score > @player_score || @dealer_score == 21
        # Dealer hits until criteria is met
        dealer_hand.add_card(current_deck.deck)
        @dealer_score = dealer_hand.score
      end

      # Determine who won
      if @dealer_score > 21
        @result = "Dealer busts. #{player} wins!"
        player_hand.win(bet)
      elsif @dealer_score == @player_score
        @result = "Tie"
      elsif @dealer_score == 21
        @result = "Dealer Blackjack! #{player} loses!"
        player_hand.lose(bet)
      elsif @dealer_score < 21
        if @player_score == 21
          @result = "Blackjack! #{player} wins!"
          player_hand.win(bet)
        elsif @dealer_score > @player_score
          @result = "Dealer wins!"
          player_hand.lose(bet)
        else
          @result = "#{player} wins!"
          player_hand.win(bet)
        end
      end
    end

    # Only need to save player info to session
    session[:player_hand] = player_hand
  end
end

