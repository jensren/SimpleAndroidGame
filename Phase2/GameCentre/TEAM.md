## Contact information:
* Yu Jie: andrew98jiang@gmail.com
* Cassidy: cassidy.wang@mail.utoronto.ca
* Jiaheng: jiahengwl.li@mail.utoronto.ca
* Jenney: jenney.ren@mail.utoronto.ca
* Ashas: ashas.memon@mail.utoronto.ca

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
* Art for the battle game sprites: Jenney
* 
* 

## Battle Game Rules:
* HP = health points, MP = mana points
* You can choose to play as a dog or a cat in the game. 
* Dogs you can choose from: Druid, Sir Shibe and Detective
* Cats you can choose from: Shaman, Samurai and Ninja
* Each round, you can choose to use your regular or your special move. Regular moves don't use up MP, but special moves do. 
* The game ends once a player runs out of HP. The winner is the player with HP remaining. 
 

## Card Matching Game Rules:
* 

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
* 

## Current bugs that need fixing:
*


