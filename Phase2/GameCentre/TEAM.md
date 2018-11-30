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
* HP = health points, MP = magic points
* You can choose to play as a dog or a cat in the game. 
* Dogs you can choose from: Druid, Sir and Detective
* Cats you can choose from: Shaman, Samurai and Ninja
* Each round, you can choose to use your regular or your special move. Regular moves don't use up MP, but special moves do.
* The game ends once a player runs out of HP. The winner is the player with HP remaining.
* There are 3 types of characters: Fighter characters, Stealth characters and Healer characters.
* Regular attacks for all the characters are the same in function, the only difference is the amount of damage they cause. They all add the character performing the attack back onto the battle queue once.
* The stealth characters, Detective Shibe and Ninja Cat add the opponent into the battle queue and then themselves twice during their special move.
* The fighter characters, Sir Shibe and Samurai cat reset the battle queue so that there is only 1 copy of each character and then adds themselves during the special move.
* The healer characeters, Druid Shibe and Shaman Cat increase their health points by the amount of damage the opponent takes during the character's special move.
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
* The order of characters in the BattleQueue will be modified when the character performs a regular or special move or undo.
* The game will end when a character runs out of HP (health points). 
* The scoreboard will be ordered from highest to lowest score. The score will be HP you cause in damage to the opponent + HP you have left. 
* Regular moves don't use MP (magic points) to avoid there every being a tie, but special moves use up MP. If a character looses up all their MP, they are still able to use the regular move and cause damage.
* Undo: implemented using a stack by saving each character's HP, MP and BattleQueue before a move is performed so the current game state can ge reset to what it was like before the move was performed.

## Card Matching Game design decision:
* Similar design to Sliding Tiles due to also being a board like game.
* We extracted some superclasses from Sliding Tiles classes that this game, and other potential future board games can extend.
* We used a Handler to introduce a delay between flipping cards, so the user has time to view what card he flipped.
* There are two sets of tiles. One of the question mark tiles that the user views on the screen, and another of the actual images to be matched.
* Tiles are flipped by changing the background of the tile in question, in the question mark tile array.
* Your win condition is determined by an integer (tilesMatched) which tracks how many tiles you've correctly matched so far.

## Current bugs that need fixing:

## To-do list:
* Finish extracting superclasses
* Is board abstract class necessary?
* Finish typing readme and team.md


