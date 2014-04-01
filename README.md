Blocksy_Proj3_Hallenback_Hayhurst_CS_342
========================================

How to Load This Project
_______________________________________________________________________________________________________

This file comes with all of the files for a complete project, so all that one should need to do to
load this project up is to import the entire project into your favorite IDE.

If that doesn't work, try this:
- create a new project
- inside of that project, create the .java files needed for this project and literally copy/paste the
  contents of the downloaded .java class files into the project
- then import the text files (the puzzles themselves) into the folder that hlds the source folder



How To Create Your Own Puzzle
________________________________________________________________________________________________________

The text files included in the project are the puzzles.  They are in the same format that Professor Troy
suggested that students use in the write-up, with some added constraints:

- the board has to be a 6x6 puzzle
- all of the pieces in the puzzle can be either:
  - width  = 1, height = 2 or 3
  - height = 1, width = 2 or 3
- number of pieces:
  - one goal piece
  - up to 8 regular pieces
- the order of the integers for each piece is as follows:
- row, column, width, height, movement
  
Here's an example (the first puzzle in the game):

6 6
3 2 2 1 h
1 5 1 3 v
1 6 1 2 v
2 4 1 2 v
3 1 1 3 v
3 6 1 2 v
4 2 1 3 v
4 3 3 1 h
5 4 3 1 h

Please note the single spaces between each number, and please note that the program assumes perfect file input:
there is NO error checking for the format of the file.


Miscellaneous
____________________________________________________________________________________________________________

When using the "solve" button using the supplied puzzles, finding the solutions can take up to fifteen seconds.
If you choose to add a puzzle, please be patient.

Finally, thanks for grading this project!



