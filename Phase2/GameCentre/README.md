#Instructions for running the app

1.Open up your terminal to clone the repository. This can be done by searching for terminal in your computer's search bar.
2.Navigate to your desired directory using the "cd" command in terminal.
3.Get the project URL from MarkUs. Clone the project by typing "git clone <url>" into the terminal.
4.Open up Android Studio and click open existing project. In the directory that you cloned to, you should see a folder
called group_0512. Open it, then click Phase 1 folder to open that. You will then see the GameCentre folder. Click this once
and open it in Android Studio.
5.Give Android Studio some time to do some initialization, do not add any files to Git if prompted.
6.Once it is done, you can click the green triangle in the top right to run the app.
7.The first time running it, you will need to create a new account through clicking the SignUp button.
8.In all subsequent times opening the app, you can just click the SignIn button and sign in with your
existing account.
9.After logging in to your account. Choose SlidingTiles since that is the only game available to play.
10.Click New Game to start a new game. The default board size is a 4x4 game. If you want to change it,
use the buttons at the top to switch to 3x3 or 5x5 boards.

#Functionalities
* Game launch centre: Users can choose to sign in with an existing username and password or sign up with a new username.
The user can select a game, and then load the game/start a new game.
* Game choice: Users can choose what game to play. Currently, only Sliding Tiles is available.
* Scoreboard: The scoreboard will display every time you win the game. It stores the top 10 scores of all players of the game,
and will display the score the user received that game as well as the user's high score.
The score is calculated as the number of moves it took you to win, divided by the board complexity. The lower your score, the higher your ranking.
* Autosave: The game automatically saves after every move, so you won't lose any progress if you exit
the game or if the game crashes.
* Undo: The user can press the undo button until there are no more moves left to undo (the board is completely reset).
* Sliding tile puzzle game complexity: The user can choose between a 3x3, 4x4 or 5x5 grid.

