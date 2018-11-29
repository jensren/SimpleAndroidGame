## Contact information:
* Yu Jie (Andrew): andrew98jiang@gmail.com
* Cassidy: cassidy.wang@mail.utoronto.ca
* Jiaheng: jiahengwl.li@mail.utoronto.ca
* Jenney: jenney.ren@mail.utoronto.ca
* Ashas: ashas.memon@mail.utoronto.ca

## Usernames that appear in the git log:
* Yu Jie (Andrew) Jiang: Andrew Jiang
* Cassidy Wang: cw
* Jiaheng Li: jiahengli
* Jenney Ren: Jenney
* Ashas Memon: ashasmemon

## Communication Tools:
* Facebook
* Skype

## Team Contract:
* I will get my allotted work done on time.
* I will attend every team meeting and actively participate.
* Should an emergency arise that prevents me from attending a team meeting, I will notify my team immediately.
* The work will be divided roughly equally among all team members.
* I will help my team to understand every concept in the project.
* If I do not understand a concept or code, I will immediately ask my team for help.

## Games:
* Card Matching Game: Yu Jie and Jiaheng
* Battle Game: Jenney, Ashas, Cassidy

## Responsibilities:
* Art for the battle game sprites, scoreboard: Jenney
* Sliding Tiles only creates solvable boards: Jiaheng
* Activities and animations for battle game, BattleQueueManager: Cassidy
* BattleQueue, attack mechanisms for battle game, undo: Ashas


## Battle Game Rules:
* HP = health points, MP = mana points
* You can choose to play as a dog or a cat in the game. 
* Dogs you can choose from: Druid, Sir Shibe and Detective
* Cats you can choose from: Shaman, Samurai and Ninja
* Each round, you can choose to use your regular or your special move. Regular moves don't use up MP, but special moves do. 
* The game ends once a player runs out of HP. The winner is the player with HP remaining. 
* For the animations, image 0 and 1 are the standing animation, image 2 and 3 are the regular attack
  animation, and image 4 and 5 are the special attack animation
 

## Card Matching Game Rules:
* A game where you must match pairs of cards.
* Click a card to flip it over, when you flip over the second card, if they match, they will be removed from the board. If they
don't match, they flip back over.
* Game ends when players matches all the cards.
* Your score depends on how many turns it took for you to complete the game.

## Battle Game design decisions:
* Character will be an abstract superclass, and each specific type of playable character in the game will be a subclass that extends it.
* BattleQueue will handle which character will be the next to play. Some special moves will add the character to the BattleQueue more than once. 
* The game will end when a character runs out of HP (health points). 
* The scoreboard will be ordered from highest to lowest score. The score will be HP you cause in damage to the opponent + HP you have left. 
* Regular moves don't use MP (mana points), but special moves use up MP. 
* You can play against the computer, which randomly generates a move. 
* Undo: implemented using a stack by saving HP, MP and BattleQueue after each move
* Saving: save BattleQueue???

## Card Matching Game design decision:
* TBD

## Current bugs that need fixing:

## To-do list:
* Fix code smells (extract superclasses)
* javadoc for listeners
* Utility class for save and loading
* Finish typing readme and team.md


