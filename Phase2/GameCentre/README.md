#Instructions for running the app

1.Open up your terminal to clone the repository. This can be done by searching for terminal in your computer's search bar.
2.Navigate to your desired directory using the "cd" command in terminal.
3.Get the project URL from MarkUs. Clone the project by typing "git clone <url>" into the terminal.
4.Open up Android Studio and click open existing project. In the directory that you cloned to, you should see a folder
called group_0657. Open it, then click Phase 2 folder to open that. You will then see the GameCentre folder. Click this once
and open it in Android Studio.
5.Give Android Studio some time to do some initialization, do not add any files to Git if prompted.
6.Once it is done, you can click the green triangle in the top right to run the app.
7.The first time running it, you will need to create a new account through clicking the SignUp button.
8.In all subsequent times opening the app, you can just click the SignIn button and sign in with your
existing account.
9.After logging in to your account. Choose between Sliding Tiles, Cats vs Dogs or Card Matching to play.
10.Click New Game to start a new game. For Sliding Tiles, The default board size is a 4x4 game.
If you want to change it, use the buttons at the top to switch to 3x3 or 5x5 boards.
For the Battle Game, you can choose to play as a Cat or a Dog which will lead you to chose the
type of cat or dog. You can then choose your opponent in a similar manner.
For Card Matching, you can choose from starting a new game or loading an existing one if you played
before and proceed to complete the 4x4 board.

#Functionalities
* Game launch centre: Users can choose to sign in with an existing username and password or sign up with a new username.
The user can select a game, and then load the game/start a new game.
* Game choice: Users can choose what game to play.
* Scoreboard: The scoreboard will display every time you win the game. It stores the top 10 scores of all players of the game,
and will display the score the user received that game as well as the user's high score.
* Autosave: Slidingtiles and Card Matching automatically saves after every move, so you won't lose any progress if you exit
the game or if the game crashes.
* Undo: SlidingTiles and BattleGame allows the user to press the undo button until there are no more moves left to undo (the game state is reset to a new game state).
* Sliding tile puzzle game complexity: The user can choose between a 3x3, 4x4 or 5x5 grid.
* BattleGame features: The user can choose to play as 1 of 3 cats or 1 of 3 dogs and also choose their opponent. The game continues until one character has 0 HP.

##Rules for SlidingTiles:
* Choose the size of your grid (3x3, 4x4, 5x5)
* Tap on a tile with a blank space next to it to move it to the blank space. 
* Repeat this process until the tiles are in order to win. 
* The score is calculated as the number of moves it took you to win, divided by the board complexity. The lower your score, the higher your ranking.


##Rules for Card Matching Game:
* Click a card to flip it over, when you flip over the second card, if they match, they will be removed from the board. If they
don't match, they flip back over.
* Game ends when players matches all the cards.
* Your score depends on how many turns it took for you to complete the game. The lower your score, the higher your ranking. 


##Rules for Battle Game (i.e. Cats vs. Dogs)
* You can choose to play as a dog or a cat in the game. 
* Dogs you can choose from: Druid, Sir and Detective
* Cats you can choose from: Shaman, Samurai and Ninja
* Each round, you can choose to use your regular or your special move. Regular moves don't use up MP, but special moves do.
* The game ends once a player runs out of HP. The winner is the player with HP remaining.
* There are 3 types of characters: Fighter characters, Stealth characters and Healer characters.
* Regular attacks for all the characters are the same in function, the only difference is the amount of damage they cause. They all add the character performing the attack back onto the battle queue once.
* The stealth characters, Detective Shibe and Ninja Cat add the opponent into the battle queue and then themselves twice during their special move.
* The fighter characters, Sir Shibe and Samurai cat reset the battle queue so that there is only 1 copy of each character and then adds themselves during the special move.
* The healer characters, Druid Shibe and Shaman Cat increase their health points by the amount of damage the opponent takes during the character's special move.
* The score is calculated based on the number of moves made and the amount of HP each character loses. The lower your score, the higher your ranking. 
