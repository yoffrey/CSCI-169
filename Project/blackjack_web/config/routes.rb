Rails.application.routes.draw do
  root to: 'blackjack#start'
  post '/save_name_cash', to: 'blackjack#save_name_cash'
  get '/bet', to: 'blackjack#bet'
  post 'save_bet', to: 'blackjack#save_bet'
  get '/deal_new', to: 'blackjack#deal_new'
  post '/game' , to: 'blackjack#game'
  get '/game_stand', to: 'blackjack#game_stand'
end
