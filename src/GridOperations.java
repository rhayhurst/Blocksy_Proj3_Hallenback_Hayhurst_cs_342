import java.util.ArrayList;

import javax.swing.JOptionPane;


public class GridOperations {




    static int checkPath_Height2_Horizontal(GuiButton temp, int targetRow, int targetCol, char targetPosition,
                                            GuiButton guiButtonGrid[][])
    {
		/*
		 * if the move to piece is to the right of the target, check spaces in-between to make sure
		 * the path is clear
		 */
        if(temp.getTheCol() > targetCol)// move to is to the right of target
        {
            int tRow = targetRow;

            for(int y= targetCol+1; y< temp.getTheCol(); y++)
            {
                if(targetPosition == 'f')// if the target is the top piece
                {
                    if(guiButtonGrid[tRow][y].getTheName() != 0 || guiButtonGrid[tRow+1][y].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if top piece end

                else// target is the bottom piece
                {
                    if(guiButtonGrid[tRow][y].getTheName() != 0 || guiButtonGrid[tRow-1][y].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if bottom piece end
            }//end of for loop

            return 1;

        }//end of move is to the right of target

		/*
		 * if the move to piece is to the left of the target, check spaces in-between to make sure
		 * the path is clear
		 */
        else//move is to the left of target
        {
            int tRow = targetRow;

            for(int y= temp.getTheCol(); y< targetCol; y++)
            {
                if(targetPosition == 'f')// if the target is the top piece
                {
                    if(guiButtonGrid[tRow][y].getTheName() != 0 || guiButtonGrid[tRow+1][y].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if top piece end

                else// target is the bottom piece
                {
                    if(guiButtonGrid[tRow][y].getTheName() != 0 || guiButtonGrid[tRow-1][y].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if bottom piece end

            }//end of for loop

            return 1;

        }//end of else it's to the left of target

    }//end of method checkPath_Height2_Horizontal


    static int checkPath_Height2_Vertical(GuiButton temp, int targetRow, int targetCol, char targetPosition,
                                          GuiButton guiButtonGrid[][])
    {
        int start;

        if(temp.getTheRow() > targetRow)	//the moveto is below the target
        {
            if(targetPosition == 'f')		//if the target piece is the top piece
            {
                start = targetRow+2;
            }
            else
            {
                start = targetRow+1;
            }

            for(int x = start; x< temp.getTheRow()+1; x++)
            {
                if(guiButtonGrid[x][targetCol].getTheName() != 0)
                {
                    return 0;
                }

            }

            return 1;
        }//end of if the moveto is below the target

        else	//moveto piece is above the target
        {
            if(targetPosition == 'f')		//if the target piece is the top piece
            {
                start = targetRow;
            }
            else
            {
                start = targetRow-1;
            }

            for(int x = temp.getTheRow(); x< start; x++)
            {
                if(guiButtonGrid[x][targetCol].getTheName() != 0)
                {
                    return 0;
                }

            }

            return 1;

        }
    }

    static int checkPath_Height3_Vertical(GuiButton temp, int targetRow, int targetCol, char targetPosition,
                                          GuiButton guiButtonGrid[][])
    {
        int start;

        if(temp.getTheRow() > targetRow)	//the moveto is below the target
        {
            if(targetPosition == 'f')		//if the target piece is the top piece
            {
                start = targetRow+3;
            }
            else if(targetPosition == 'm')
            {
                start = targetRow+2;
            }
            else
            {
                start = targetRow+1;
            }

            for(int x = start; x< temp.getTheRow()+1; x++)
            {
                if(guiButtonGrid[x][targetCol].getTheName() != 0)
                {
                    return 0;
                }

            }

            return 1;
        }//end of if the moveto is below the target

        else	//moveto piece is above the target
        {
            if(targetPosition == 'f')		//if the target piece is the top piece
            {
                start = targetRow;
            }
            else if(targetPosition == 'm')
            {
                start = targetRow-1 ;
            }
            else
            {
                start = targetRow-2;
            }

            for(int x = temp.getTheRow(); x< start; x++)
            {
                if(guiButtonGrid[x][targetCol].getTheName() != 0)
                {
                    return 0;
                }

            }

            return 1;

        }
    }



    static int checkPath_Width2_Horizontal(GuiButton temp, int targetRow, int targetCol, char targetPosition,
                                           GuiButton guiButtonGrid[][])
    {
        int start;

        if(temp.getTheCol() > targetCol)	//the moveto is to the right of the target
        {
            if(targetPosition == 'f')		//if the target piece is the front piece
            {
                start = targetCol+2;
            }
            else							//else the target piece is the back of the piece
            {
                start = targetCol+1;
            }

            for(int y = start; y< temp.getTheCol()+1; y++)
            {
                if(guiButtonGrid[targetRow][y].getTheName() != 0)
                {
                    return 0;
                }

            }

            return 1;
        }//end of if the moveto is to the right of the target

        else	//moveto piece is to the left of the target
        {
            if(targetPosition == 'f')		//if the target piece is the top piece
            {
                start = targetCol;
            }
            else
            {
                start = targetCol-1;
            }

            for(int y = temp.getTheCol(); y< start; y++)
            {
                if(guiButtonGrid[targetRow][y].getTheName() != 0)
                {
                    return 0;
                }

            }

            return 1;

        }
    }

    static int checkPath_Width3_Horizontal(GuiButton temp, int targetRow, int targetCol, char targetPosition,
                                           GuiButton guiButtonGrid[][])
    {
        int start;

        if(temp.getTheCol() > targetCol)	//the moveto is to the right of the target
        {
            if(targetPosition == 'f')		//if the target piece is the front piece
            {
                start = targetCol+3;
            }
            else if(targetPosition == 'm')
            {
                start = targetCol+2;
            }
            else							//else the target piece is the back of the piece
            {
                start = targetCol+1;
            }

            for(int y = start; y< temp.getTheCol()+1; y++)
            {
                if(guiButtonGrid[targetRow][y].getTheName() != 0)
                {
                    return 0;
                }

            }

            return 1;
        }//end of if the moveto is to the right of the target

        else	//moveto piece is to the left of the target
        {
            if(targetPosition == 'f')		//if the target piece is the top piece
            {
                start = targetCol;
            }
            else if(targetPosition == 'm')
            {
                start = targetCol-1;
            }
            else
            {
                start = targetCol-2;
            }

            for(int y = temp.getTheCol(); y< start; y++)
            {
                if(guiButtonGrid[targetRow][y].getTheName() != 0)
                {
                    return 0;
                }

            }

            return 1;

        }
    }


    static int checkPath_Width2_Vertical(GuiButton temp, int targetRow, int targetCol, char targetPosition,
                                         GuiButton guiButtonGrid[][])
    {
		/*
		 * if the move to piece is to the below the target, check spaces in-between to make sure
		 * the path is clear
		 */
        if(temp.getTheRow() > targetRow)// move to is to the right of target
        {
            int tCol = targetCol;

            for(int x= targetRow+1; x< temp.getTheRow()+1; x++)
            {
                if(targetPosition == 'f')// if the target is the top piece
                {
                    if(guiButtonGrid[x][tCol].getTheName() != 0 || guiButtonGrid[x][tCol+1].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if top piece end

                else// target is the bottom piece
                {
                    if(guiButtonGrid[x][tCol].getTheName() != 0 || guiButtonGrid[x][tCol-1].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if bottom piece end
            }//end of for loop

            return 1;

        }//end of moveto is to below the target

		/*
		 * if the move to piece is above the target, check spaces in-between to make sure
		 * the path is clear
		 */
        else//moveto is to the above the target
        {
            int tCol = targetCol;

            for(int x= temp.getTheRow(); x< targetRow; x++)
            {
                if(targetPosition == 'f')// if the target is the top piece
                {
                    if(guiButtonGrid[x][tCol].getTheName() != 0 || guiButtonGrid[x][tCol+1].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if top piece end

                else// target is the bottom piece
                {
                    if(guiButtonGrid[x][tCol].getTheName() != 0 || guiButtonGrid[x][tCol-1].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if bottom piece end

            }//end of for loop

            return 1;

        }//end of else it's to the left of target

    }//end of method checkPath_Height2_Horizontal

    static int checkPath_Height3_Horizontal(GuiButton temp, int targetRow, int targetCol, char targetPosition,
                                            GuiButton guiButtonGrid[][])
    {
		/*
		 * if the move to piece is to the right of the target, check spaces in-between to make sure
		 * the path is clear
		 */
        if(temp.getTheCol() > targetCol)// move to is to the right of target
        {
            int tRow = targetRow;

            for(int y= targetCol+1; y< temp.getTheCol(); y++)
            {

                // if the target is the top piece
                if(targetPosition == 'f')
                {
                    if(guiButtonGrid[tRow][y].getTheName() != 0 || guiButtonGrid[tRow+1][y].getTheName() != 0
                            || guiButtonGrid[tRow+2][y].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if top piece end

                // if the target is the bottom piece
                else if(targetPosition == 'b')
                {
                    if (guiButtonGrid[tRow][y].getTheName() != 0 || guiButtonGrid[tRow-1][y].getTheName() != 0
                            || guiButtonGrid[tRow-2][y].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if bottom piece end

                // the target is the middle piece
                else
                {
                    if (guiButtonGrid[tRow][y].getTheName() != 0 || guiButtonGrid[tRow-1][y].getTheName() != 0
                            || guiButtonGrid[tRow+1][y].getTheName() != 0)
                    {
                        return 0;
                    }
                }
            }//end of for loop

            return 1;

        }//end of move is to the right of target

		/*
		 * if the move to piece is to the left of the target, check spaces in-between to make sure
		 * the path is clear
		 */
        else
        {
            int tRow = targetRow;

            for(int y= temp.getTheCol(); y< targetCol; y++)
            {
                //the target piece is a top piece
                if(targetPosition == 'f')
                {
                    if(guiButtonGrid[tRow][y].getTheName() != 0 || guiButtonGrid[tRow+1][y].getTheName() != 0
                            || guiButtonGrid[tRow+2][y].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if top piece end

                //the target piece is a bottom piece
                else if(targetPosition == 'b')
                {
                    if(guiButtonGrid[tRow][y].getTheName() != 0 || guiButtonGrid[tRow-1][y].getTheName() != 0
                            || guiButtonGrid[tRow-2][y].getTheName() != 0)
                    {

                        return 0;
                    }
                }//if bottom piece end

                //the target piece is a middle piece
                else
                {
                    if(guiButtonGrid[tRow][y].getTheName() != 0 || guiButtonGrid[tRow-1][y].getTheName() != 0
                            || guiButtonGrid[tRow+1][y].getTheName() != 0)
                    {
                        return 0;
                    }

                }

            }//end of for loop

            return 1;

        }//end of move to piece is to the left of target


    }


    //***********************************************
    static int checkPath_Width3_Vertical(GuiButton temp, int targetRow, int targetCol, char targetPosition,
                                         GuiButton guiButtonGrid[][])
    {
		/*
		 * if the move to piece is below of the target, check spaces in-between to make sure
		 * the path is clear
		 */
        if(temp.getTheRow() > targetRow)// move to is to the right of target
        {
            int tCol = targetCol;

            for(int x= targetRow+1; x< temp.getTheRow()+1; x++)
            {

                // if the target is the top piece
                if(targetPosition == 'f')
                {
                    if(guiButtonGrid[x][tCol].getTheName() != 0 || guiButtonGrid[x][tCol+1].getTheName() != 0
                            || guiButtonGrid[x][tCol+2].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if top piece end

                // if the target is the bottom piece
                else if(targetPosition == 'b')
                {
                    if (guiButtonGrid[x][tCol].getTheName() != 0 || guiButtonGrid[x][tCol-1].getTheName() != 0
                            || guiButtonGrid[x][tCol-2].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if bottom piece end

                // the target is the middle piece
                else
                {
                    if (guiButtonGrid[x][tCol].getTheName() != 0 || guiButtonGrid[x][tCol-1].getTheName() != 0
                            || guiButtonGrid[x][tCol+1].getTheName() != 0)
                    {
                        return 0;
                    }
                }
            }//end of for loop

            return 1;

        }//end of moveto is below the target

		/*
		 * if the move to piece is above the target, check spaces in-between to make sure
		 * the path is clear
		 */
        else
        {
            int tCol = targetCol;

            for(int x= temp.getTheRow(); x< targetRow; x++)
            {
                //the target piece is a top piece
                if(targetPosition == 'f')
                {
                    if(guiButtonGrid[x][tCol].getTheName() != 0 || guiButtonGrid[x][tCol+1].getTheName() != 0
                            || guiButtonGrid[x][tCol+2].getTheName() != 0)
                    {
                        return 0;
                    }
                }//if top piece end

                //the target piece is a bottom piece
                else if(targetPosition == 'b')
                {
                    if(guiButtonGrid[x][tCol].getTheName() != 0 || guiButtonGrid[x][tCol-1].getTheName() != 0
                            || guiButtonGrid[x][tCol-2].getTheName() != 0)
                    {

                        return 0;
                    }
                }//if bottom piece end

                //the target piece is a middle piece
                else
                {
                    if(guiButtonGrid[x][tCol].getTheName() != 0 || guiButtonGrid[x][tCol-1].getTheName() != 0
                            || guiButtonGrid[x][tCol+1].getTheName() != 0)
                    {
                        return 0;
                    }

                }

            }//end of for loop

            return 1;

        }//end of move to piece is above target


    }//end of checkPath_Width3_Vertical

    static GuiButton [][] swapButtonValues(int targetRow, int targetCol,
                                           int moveToRow, int moveToCol, GuiButton guiButtonGrid[][])
    {
        // temps to store the target info
        int tWidth = guiButtonGrid[targetRow][targetCol].getTheWidth();

        int tHeight = guiButtonGrid[targetRow][targetCol].getTheHeight();

        char tPosition = guiButtonGrid[targetRow][targetCol].getThePosition();

        char tMovement = guiButtonGrid[targetRow][targetCol].getTheMovement();

        int tName =guiButtonGrid[targetRow][targetCol].getTheName();

        //set new values from moveTo into target

        guiButtonGrid[targetRow][targetCol].setTheWidth(guiButtonGrid[moveToRow][moveToCol].getTheWidth());

        guiButtonGrid[targetRow][targetCol].setTheHeight(guiButtonGrid[moveToRow][moveToCol].getTheHeight());

        guiButtonGrid[targetRow][targetCol].setThePosition(guiButtonGrid[moveToRow][moveToCol].getThePosition());

        guiButtonGrid[targetRow][targetCol].setTheMovement(guiButtonGrid[moveToRow][moveToCol].getTheMovement());

        guiButtonGrid[targetRow][targetCol].setTheName(guiButtonGrid[moveToRow][moveToCol].getTheName());

        //set target values into moveTo

        guiButtonGrid[moveToRow][moveToCol].setTheWidth(tWidth);

        guiButtonGrid[moveToRow][moveToCol].setTheHeight(tHeight);

        guiButtonGrid[moveToRow][moveToCol].setThePosition(tPosition);

        guiButtonGrid[moveToRow][moveToCol].setTheMovement(tMovement);

        guiButtonGrid[moveToRow][moveToCol].setTheName(tName);


        return guiButtonGrid;

    }

    static int checkForTheWin(ArrayList<String> gameFile, GuiButton guiButtonGrid[][])
    {
        int gameRows= File_Manipulation.getRowsFromList(gameFile);//get rows and cols from gameFile 6x6
        int gameCols= File_Manipulation.getColsFromList(gameFile);

        for(int x= 1; x< gameRows+1; x++)
        {
            if(guiButtonGrid[x][gameCols].getTheName() == 999)
            {
                return 1;
            }
        }
        return 0;
    }
}//end of class GridOperations