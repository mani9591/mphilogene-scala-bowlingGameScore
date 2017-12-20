# mphilogene-scala-bowlingGameScore

The Problem statement

Write a program in scala to score one game of Bowling.

Rules of the game.

Each game includes ten rolls and for each roll or frame the bowler get two throws. In each the two throws he needs to knock down all the 10 pins. If the first ball knocked down all ten pins, this is a "strike", the score is 10 plus the number of pins knocked down in the next two throws. when the number of pins knocked down with two throws is 10, this is a "spare", the score is 10 plus the number of pins knocked down in the next throw. The last case is when the number of pins knocked with both throws is less then 10, this a normal frame where the score is the total number of pins knocked down with the two throws.

The game score is the total of all score for the 10 frames plus the bonus frames if the bowler either get a strike or a spare in the last frame.

Game Scoring Format:

1-)  'X' indicates a strike

2-)  '/' indicates a spare

3-)  '-' indicates a miss  (0 pin knocked down)

4-)  a Space (' ') is a frame delimiter. 

My solution: provides a parser which is implimented as a recursive function.
             and a Score calculator which is also implimented as a recursive function.

