Super Tac Toe

This project runs a Super Tac Toe game as per the following rules and specifications:
 - The small 3x3 tic-tac-toe boards are referred to as minor boards. (MinorBoard)
 - The big 3x3 board consists of 9 minor boards. This is referred to as the super board. (SuperBoard)
 - On a players turn, they place their mark on a valid spot. IE, player X places an "X". This is referred to as marking. 
 - The rules for winning a minor board are the same as normal tic-tac-toe.

 - The game begins with player X starting in the center minor board.
 - When a player marks one of the 9 spots on a minor board, the next player marks on the minor board that corresponds to that play.
   For example, if X marks the bottom left square of their minor board, then O will mark in the local board on the bottom left of the super board.
 - Once a minor board has been won or completely filled, it is considered "finshed"
   - No further marks can be placed on a finished board. A player forced to play on a finished board will be prompted to select a different board to play on.
 - Once a line of minor boards is won by a player in the super board, that player wins the game.
 - If all boards are finished with no winner, the game is a draw.
 
 This project also contains AI that can play this game. The following is the list of implemented AI:
  - Monkey AI aka "M" aka MonkeyAi
    This AI was inspired by Monkey sort or Bogo sort. It plays completely randomly with 0 intellegence.
  - Monkey AI 2 aka "M2" aka MonkeyAi2
    This AI is an upgraded version of the Monkey AI. It will win boards when presented the opportunity, and will play to prevent the opposition from finsihing theirs.
    This AI lacks any ability to see further than their current turn.
    
 Features to be implemented in future:
  - Allow the option to play by the optional rule where players can still play on a board that has already been won
    - This would allows the implementation of a guarantted win AI based on https://arxiv.org/abs/2006.02353v2
  - Implement a minmax AI
    - Allow different heuristics for the minmax AI
  - Implement a Monte-Carlo tree-search AI
