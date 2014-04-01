import java.util.ArrayList;


public class PuzzleSolver {



    private ArrayList<GuiButton[][]> gridQueue;
    private ArrayList<int []> snapShotList;



    private ArrayList<String> gameFile;	//an ArrayList that holds the gameFile line by line as strings

    private int snapShotArray[];

    private int targetRow;//all info for target piece
    private int targetCol;
    private int targetWidth;
    private int targetHeight;
    private char targetPosition;
    private char targetMovement;
    private int targetName;

    PuzzleSolver(GuiButton guiButtonGrid [][], ArrayList<String> fileList)
    {
        gridQueue = new ArrayList<GuiButton [][]>(); //initalize the grid queue

        gridQueue.add(guiButtonGrid);// add the guiButtonGrid to the queue

        snapShotList = new ArrayList<int []>();// initalize the snapshot list

        gameFile = fileList;

    }

    //go through the grid finding all fronts of pieces and send them to
    // method findAll_Possible_Moves()
    int findSolution()
    {

        int solutionFound = 0;

        while( gridQueue.size() != 0)
        {

            GuiButton tempGrid[][] = gridQueue.get(0); //temp grid is the first in the queue


            fill_snapShotArray(tempGrid);// make a snapShot of the grid

            snapShotList.add(snapShotArray);

            // go through the grid and pick out front of all the pieces
            //send to check all possible moves
            for(int x = 1; x < 7; x++)
            {
                for(int y = 1; y < 7; y++)
                {

                    if(tempGrid[x][y].getThePosition() == 'f')
                    {

                        set_Target_Info(tempGrid[x][y]); //sets the target info for the piece

                        solutionFound = checkAll_Possible_Moves(tempGrid); //checks all possible moves for piece

                        if(solutionFound != 0)
                        {
                            return solutionFound;
                        }
                        //check all should return 1 or 0, if 1, solution is gridQueue[0]
                    }
                }
            }//end of double for loop

            gridQueue.remove(0);	//remove first item in queue

        }//end of while gridQueue is not empty

        return 0;

    }//end of method: findSolution

    GuiButton [][] getTheSolution()
    {
        return gridQueue.get(0);
    }

    void printGrid(GuiButton grid[][])
    {
        for(int x = 1; x < 7; x++)
        {
            for(int y= 1; y< 7; y++)
            {
                System.out.printf(grid[x][y].getTheName()+" ");
            }
            System.out.println("");
        }
    }

    //set up the target info
    void set_Target_Info(GuiButton temp)
    {
        targetRow = temp.getTheRow();
        targetCol = temp.getTheCol();
        targetWidth = temp.getTheWidth();
        targetHeight = temp.getTheHeight();
        targetPosition = temp.getThePosition();
        targetMovement = temp.getTheMovement();
        targetName = temp.getTheName();
    }


    int checkAll_Possible_Moves(GuiButton guiButtonGrid[][])
    {

        int isClear, solutionFound;

//**********************************if vertical size 2************************************************

        if(targetHeight == 2)// it is vertical, size 2
        {
            GuiButton [][] tempGrid = new GuiButton[guiButtonGrid.length][];
            for(int i = 0; i < guiButtonGrid.length; i++)
                tempGrid[i] = guiButtonGrid[i].clone();

            if(targetRow != 1)// if the target is not in the first row
            {
                for(int x= targetRow-1; x > 0; x--)	//check all spots above target
                {
                    GuiButton temp = guiButtonGrid[x][targetCol]; // button above front of target

                    isClear = GridOperations.checkPath_Height2_Vertical(temp, targetRow, targetCol,
                            targetPosition, guiButtonGrid);

                    if(isClear == 1)	// it is a valid move
                    {
                        solutionFound = addValidMove_NewGrid(tempGrid, temp);

                        if(solutionFound != 0)
                        {
                            return solutionFound;
                        }
                        //	System.out.println("inside of PuzzleSolver, it is a valid move, row: "+temp.getTheRow()+
                        //															" col: "+temp.getTheCol());

                    }

                }//end of loop checking spots above the target

            }//end of if target is not in 1st row

            if(targetRow != 5)	//target's bottom is not in last row
            {
                for(int x= targetRow+2; x < 7; x++)	//check all spots below target
                {
                    GuiButton temp = guiButtonGrid[x][targetCol]; // button above front of target

                    isClear = GridOperations.checkPath_Height2_Vertical(temp, targetRow, targetCol,
                            targetPosition, guiButtonGrid);

                    if(isClear == 1)	// it is a valid move
                    {
                        solutionFound = addValidMove_NewGrid(tempGrid, temp);

                        if(solutionFound != 0)
                        {
                            return solutionFound;
                        }

                        //	System.out.println("inside of PuzzleSolver, it is a valid move, row: "+temp.getTheRow()+
                        //														" col: "+temp.getTheCol());

                    }

                }//end of loop checking spots above the target

            }//end of if targetBottom is not the last row

        }//end of if vertical size 2

//*************************************if vertical size 3*******************************************

        if(targetHeight == 3)// it is vertical, size 3
        {
            GuiButton [][] tempGrid = new GuiButton[guiButtonGrid.length][];
            for(int i = 0; i < guiButtonGrid.length; i++)
                tempGrid[i] = guiButtonGrid[i].clone();

            if(targetRow != 1)// if the target is not in the first row
            {
                for(int x= targetRow-1; x > 0; x--)	//check all spots above target
                {
                    GuiButton temp = guiButtonGrid[x][targetCol]; // button above front of target

                    isClear = GridOperations.checkPath_Height3_Vertical(temp, targetRow, targetCol,
                            targetPosition, guiButtonGrid);

                    if(isClear == 1)	// it is a valid move
                    {
                        solutionFound = addValidMove_NewGrid(tempGrid, temp);

                        if(solutionFound != 0)
                        {
                            return solutionFound;
                        }

                        //	System.out.println("inside of PuzzleSolver, it is a valid move, row: "+temp.getTheRow()+
                        //														" col: "+temp.getTheCol());

                    }

                }//end of loop checking spots above the target

            }//end of if target is not in 1st row

            if(targetRow != 4)	//target's bottom is not in last row
            {
                for(int x= targetRow+3; x < 7; x++)	//check all spots below target
                {
                    GuiButton temp = guiButtonGrid[x][targetCol]; // button above front of target

                    isClear = GridOperations.checkPath_Height3_Vertical(temp, targetRow, targetCol,
                            targetPosition, guiButtonGrid);

                    if(isClear == 1)	// it is a valid move
                    {
                        solutionFound = addValidMove_NewGrid(tempGrid, temp);

                        if(solutionFound != 0)
                        {
                            return solutionFound;
                        }

                        //		System.out.println("inside of PuzzleSolver, it is a valid move, row: "+temp.getTheRow()+
                        //															" col: "+temp.getTheCol());

                    }

                }//end of loop checking spots below the target

            }//end of if targetBottom is not the last row

        }//end of if vertical size 3


        //if(targetHeight == 3)// it is vertical, size3
//**************************************if horizontal size 2******************************************

        if(targetWidth == 2)	// it is horizontal, size2
        {

            GuiButton [][] tempGrid = new GuiButton[guiButtonGrid.length][];
            for(int i = 0; i < guiButtonGrid.length; i++)
                tempGrid[i] = guiButtonGrid[i].clone();


            if(targetCol != 5)// if the target bottom is not in the last column
            {
                for(int x= targetCol+2; x < 7; x++)	//check all spots to the right target
                {
                    GuiButton temp = guiButtonGrid[targetRow][x]; // button above front of target

                    isClear = GridOperations.checkPath_Width2_Horizontal(temp, targetRow, targetCol,
                            targetPosition, guiButtonGrid);

                    if(isClear == 1)	// it is a valid move
                    {
				 		/* make new grid with valid move
				 		 * check for the win
				 		 * make new snap shot
				 		 * compare to other snap shots
				 		 * if seen before, return
				 		 * else add to snapshots and queue
				 		 */

                        solutionFound = addValidMove_NewGrid(tempGrid, temp);

                        if(solutionFound != 0)
                        {
                            return solutionFound;
                        }

                        //		System.out.println("inside of PuzzleSolver, it is a valid move, row: "+temp.getTheRow()+
                        //															" col: "+temp.getTheCol());

                    }

                }//end of loop checking spots to the right of the target

            }//end of if target bottom is not in last column



            if(targetCol != 1)	//target is not in first column
            {
                for(int x= targetCol-1; x > 0; x--)	//check all spots to the left target
                {
                    GuiButton temp = guiButtonGrid[targetRow][x]; // button above front of target

                    isClear = GridOperations.checkPath_Width2_Horizontal(temp, targetRow, targetCol,
                            targetPosition, guiButtonGrid);

                    if(isClear == 1)	// it is a valid move
                    {

                        addValidMove_NewGrid(guiButtonGrid, temp);
                        //	System.out.println("inside of PuzzleSolver, it is a valid move, row: "+temp.getTheRow()+
                        //														" col: "+temp.getTheCol());

                    }

                }//end of loop checking spots to the left of the target

            }//end of target is not in first column



        }//end of if horizontal size 2

//**********************************if horizontal size 3******************************

        if(targetWidth == 3)	// it is horizontal, size2
        {
            GuiButton [][] tempGrid = new GuiButton[guiButtonGrid.length][];
            for(int i = 0; i < guiButtonGrid.length; i++)
                tempGrid[i] = guiButtonGrid[i].clone();

            if(targetCol != 4)// if the target bottom is not in the last column
            {
                for(int x= targetCol+3; x < 7; x++)	//check all spots to the right target
                {
                    GuiButton temp = guiButtonGrid[targetRow][x]; // button above front of target

                    isClear = GridOperations.checkPath_Width3_Horizontal(temp, targetRow, targetCol,
                            targetPosition, guiButtonGrid);

                    if(isClear == 1)	// it is a valid move
                    {

                        solutionFound = addValidMove_NewGrid(tempGrid, temp);

                        if(solutionFound != 0)
                        {
                            return solutionFound;
                        }

                        //		System.out.println("inside of PuzzleSolver, it is a valid move, row: "+temp.getTheRow()+
                        //															" col: "+temp.getTheCol());

                    }

                }//end of loop checking spots to the right of the target

            }//end of if target bottom is not in last column

            if(targetCol != 1)	//target is not in first column
            {
                for(int x= targetCol-1; x > 0; x--)	//check all spots to the left target
                {
                    GuiButton temp = guiButtonGrid[targetRow][x]; // button above front of target

                    isClear = GridOperations.checkPath_Width3_Horizontal(temp, targetRow, targetCol,
                            targetPosition, guiButtonGrid);

                    if(isClear == 1)	// it is a valid move
                    {
                        solutionFound = addValidMove_NewGrid(tempGrid, temp);

                        if(solutionFound != 0)
                        {
                            return solutionFound;
                        }

                        //		System.out.println("inside of PuzzleSolver, it is a valid move, row: "+temp.getTheRow()+
                        //															" col: "+temp.getTheCol());

                    }

                }//end of loop checking spots to the left of the target

            }//end of target is not in first column

        }//end of if horizontal size 2


        //if(targetWidth ==3)// it is horizontal, size3
        //	System.out.println("row: "+targetRow+" col: "+targetCol+" position: "
        //					+targetPosition+" movement: "+targetMovement+"\n");
        return 0;

    }// end of method: check all possible moves


    int addValidMove_NewGrid(GuiButton guiButtonGrid[][], GuiButton temp)
    {
		/* make new grid with valid move
 		 * check for the win
 		 * make new snap shot
 		 * compare to other snap shots
 		 * if seen before, return
 		 * else add to snapshots and queue
 		 *
 		 *
 		 */
        //make a new grid ie copy of passed in one

        int winCheck, doesSnapShotExist;

//*************************************if horizontal size 2**********************************************

        if(targetWidth == 2 && targetMovement == 'h')	//if horizontal size 2
        {


            GuiButton guiButtonGrid1[][] = new GuiButton[guiButtonGrid.length][guiButtonGrid[0].length];

            guiButtonGrid1 = copyGuiButtonGrid(guiButtonGrid);

            if(targetCol < temp.getTheCol())	//if the moveto is to the right of  the target
            {
                if(targetPosition == 'f')	//if target is the front piece
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol()-1,guiButtonGrid1);
                }

                else		//if target is the back piece
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                            temp.getTheCol()-1,guiButtonGrid1);

                }

            }

            else		//move to is to the left of  the target
            {
                if(targetPosition == 'f')		//if the target is the front of the piece
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1= GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                            temp.getTheCol()+1,guiButtonGrid1);

                }//end of if the target is a front piece

                else		//the target is the back piece
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol()+1,guiButtonGrid1);


                }//end of else its a back piece

            }//end of else moveto is to the left of  the target

            winCheck = GridOperations.checkForTheWin(gameFile, guiButtonGrid1);


            //	System.out.println("the result of winCheck in PuzzleSolver: "+winCheck);

            if(winCheck == 1)
            {
                //System.out.printf("*************************************************\n\n"+
                //				"The win has been found!!!!!!!!!!!!!!!!!!!!!!!\n\n"+
                //			"******************************************************");

                gridQueue.add(0,guiButtonGrid1);
                return winCheck;
            }

            fill_snapShotArray(guiButtonGrid1);

            doesSnapShotExist = compare_SnapShotArray_ToList();

            //	print_snapShotArray(snapShotArray);

            //	System.out.println("");
            //	System.out.println("This is the return value of doesSnapShotExist: "+ doesSnapShotExist);



            if(doesSnapShotExist != 0)
            {
                snapShotList.add(snapShotArray);

                //	printGrid(guiButtonGrid1);

                gridQueue.add(guiButtonGrid1);
            }



        }//end of if horizontal size 2

//******************************************if vertical size 2******************************************

        if(targetHeight == 2 && targetMovement == 'v')
        {
            GuiButton guiButtonGrid1[][] = new GuiButton[guiButtonGrid.length][guiButtonGrid[0].length];

            guiButtonGrid1 = copyGuiButtonGrid(guiButtonGrid);

            if(targetRow < temp.getTheRow())	//if the moveto is below the target
            {
                if(targetPosition == 'f')	//if target is the front piece
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow()-1,
                            temp.getTheCol(),guiButtonGrid1);

                }

                else		//if target is the back piece
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow()-1,
                            temp.getTheCol(),guiButtonGrid1);

                }

            }
            else		//move to is above the target
            {
                if(targetPosition == 'f')		//if the target is the front of the piece
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow()+1,
                            temp.getTheCol(),guiButtonGrid1);


                }//end of if the target is a front piece

                else		//the target is the back piece
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow()+1,
                            temp.getTheCol(),guiButtonGrid1);


                }//end of else its a back piece

            }//end of else moveto is above the target

            winCheck = GridOperations.checkForTheWin(gameFile, guiButtonGrid1);


            //	System.out.println("the result of winCheck in PuzzleSolver: "+winCheck);

            if(winCheck == 1)
            {
                //	System.out.printf("*************************************************\n\n"+
                //					"The win has been found!!!!!!!!!!!!!!!!!!!!!!!\n\n"+
                //				"******************************************************");

                gridQueue.add(0,guiButtonGrid1);
                return winCheck;
            }

            fill_snapShotArray(guiButtonGrid1);

            doesSnapShotExist = compare_SnapShotArray_ToList();

            //	print_snapShotArray(snapShotArray);

            //	System.out.println("");
            //	System.out.println("This is the return value of doesSnapShotExist: "+ doesSnapShotExist);



            if(doesSnapShotExist != 0)
            {
                snapShotList.add(snapShotArray);

                //	printGrid(guiButtonGrid1);

                gridQueue.add(guiButtonGrid1);
            }


        }//end of if vertical size 2

//********************************************if horizontal size 3***************************************

        if(targetWidth == 3 && targetMovement == 'h')	//if horizontal size 3
        {


            GuiButton guiButtonGrid1[][] = new GuiButton[guiButtonGrid.length][guiButtonGrid[0].length];

            guiButtonGrid1 = copyGuiButtonGrid(guiButtonGrid);



            if(targetCol < temp.getTheCol())	//if the moveto is to the right of  the target
            {
                if(targetPosition == 'f')	//if target is the front piece
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol+2, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                            temp.getTheCol()-1,guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol()-2,guiButtonGrid1);

                }

                else if(targetPosition == 'm')	//target is the middle and move to is to the right
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol()-1,guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                            temp.getTheCol()-2,guiButtonGrid1);

                }

                else		//if target is the back piece
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                            temp.getTheCol()-1,guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol-2, temp.getTheRow(),
                            temp.getTheCol()-2,guiButtonGrid1);


                }

            }
            else		//move to is to the left of  the target
            {
                if(targetPosition == 'f')		//if the target is the front of the piece
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                            temp.getTheCol()+1,guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol+2, temp.getTheRow(),
                            temp.getTheCol()+2,guiButtonGrid1);


                }//end of if the target is a front piece

                else if(targetPosition == 'm')	//if target is a middle piece and move to is to the left
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol()+1,guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                            temp.getTheCol()+2,guiButtonGrid1);



                }
                else		//the target is the back piece and move to is to the left
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol-2, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                            temp.getTheCol()+1,guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol()+2,guiButtonGrid1);


                }//end of else its a back piece

            }//end of else moveto is to the left of  the target

            winCheck = GridOperations.checkForTheWin(gameFile, guiButtonGrid1);


            //	System.out.println("the result of winCheck in PuzzleSolver: "+winCheck);

            if(winCheck == 1)
            {
                //System.out.printf("*************************************************\n\n"+
                //				"The win has been found!!!!!!!!!!!!!!!!!!!!!!!\n\n"+
                //			"******************************************************");

                gridQueue.add(0,guiButtonGrid1);
                return winCheck;
            }

            fill_snapShotArray(guiButtonGrid1);

            doesSnapShotExist = compare_SnapShotArray_ToList();

            //	print_snapShotArray(snapShotArray);

            //	System.out.println("");
            //	System.out.println("This is the return value of doesSnapShotExist: "+ doesSnapShotExist);



            if(doesSnapShotExist != 0)
            {
                snapShotList.add(snapShotArray);

                //	printGrid(guiButtonGrid1);

                gridQueue.add(guiButtonGrid1);
            }


        }//end of if horizontal size 3

//********************************************if vertical size 3*******************************************

        if(targetHeight == 3 && targetMovement == 'v')	//if horizontal size 3
        {

            GuiButton guiButtonGrid1[][] = new GuiButton[guiButtonGrid.length][guiButtonGrid[0].length];

            guiButtonGrid1 = copyGuiButtonGrid(guiButtonGrid);


            if(targetRow < temp.getTheRow())	//if the moveto is below the target
            {
                if(targetPosition == 'f')	//if target is the front piece and move to is below
                {

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow+2, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow()-1,
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow()-2,
                            temp.getTheCol(),guiButtonGrid1);
                }

                else if(targetPosition == 'm')		//target is the middle piece and move to is below
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow()-1,
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow()-2,
                            temp.getTheCol(),guiButtonGrid1);
                }

                else		//if target is the back piece and move to is below
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow()-1,
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow-2, targetCol, temp.getTheRow()-2,
                            temp.getTheCol(),guiButtonGrid1);

                }

            }		//end of if move to is below the target

            else		//move to is above the target
            {
                if(targetPosition == 'f')		//if the target is the front of the piece and move to is above
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow()+1,
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow+2, targetCol, temp.getTheRow()+2,
                            temp.getTheCol(),guiButtonGrid1);

                }//end of if the target is a front piece

                else if(targetPosition == 'm')		//target is a middle piece and move to is above
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow()+1,
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow()+2,
                            temp.getTheCol(),guiButtonGrid1);

                }

                else		//the target is the back piece and move to is above
                {
                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow-2, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow()+1,
                            temp.getTheCol(),guiButtonGrid1);

                    guiButtonGrid1 = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow()+2,
                            temp.getTheCol(),guiButtonGrid1);


                }//end of else its a back piece

            }//end of else moveto is above the target

            winCheck = GridOperations.checkForTheWin(gameFile, guiButtonGrid1);


            //	System.out.println("the result of winCheck in PuzzleSolver: "+winCheck);

            if(winCheck == 1)
            {
                //	System.out.printf("*************************************************\n\n"+
                //					"The win has been found!!!!!!!!!!!!!!!!!!!!!!!\n\n"+
                //				"******************************************************");

                gridQueue.add(0,guiButtonGrid1);
                return winCheck;
            }

            fill_snapShotArray(guiButtonGrid1);

            doesSnapShotExist = compare_SnapShotArray_ToList();

            //	print_snapShotArray(snapShotArray);

            //	System.out.println("");
            //	System.out.println("This is the return value of doesSnapShotExist: "+ doesSnapShotExist);

            if(doesSnapShotExist != 0)
            {
                snapShotList.add(snapShotArray);

                //	printGrid(guiButtonGrid1);

                gridQueue.add(guiButtonGrid1);
            }




        }//end of if vertical size three

        //System.out.println("\n\nThis is the size of the queue: "+gridQueue.size());
        return 0;

    }//end of method: addValidMove_NewGrid()

	/*
	 * if snapShot array already exists in the snapShotList foundIt will be zero.
	 * A value greater than zero indicates that the snapShot is not in the list
	 */

    int compare_SnapShotArray_ToList()
    {
        int foundIt=0;
        int tempArray[];

        for(int x = 0; x < snapShotList.size(); x++)
        {
            tempArray = snapShotList.get(x);
            foundIt = 0;

            for(int index = 0; index < 36; index ++)
            {
                if(tempArray[index] != snapShotArray[index])
                {
                    foundIt++;
                }
            }

            //System.out.println("\nThis is FoundIt value: \n"+foundIt);

            if(foundIt == 0)
            {
                return 0;
            }


        }

        return foundIt;
    }


    GuiButton [][] copyGuiButtonGrid(GuiButton guiButtonGrid[][])
    {
        int row,col,height,width, name;
        char position, movement;

        GuiButton temp[][] = new GuiButton[guiButtonGrid.length][guiButtonGrid[0].length];
        for (int x = 1; x < temp.length-1; ++x)
        {
            for (int y = 1; y < temp[0].length-1; y++)
            {
                row = guiButtonGrid[x][y].getTheRow();
                col = guiButtonGrid[x][y].getTheCol();;
                height = guiButtonGrid[x][y].getTheHeight();
                width = guiButtonGrid[x][y].getTheWidth();
                position = guiButtonGrid[x][y].getThePosition();
                movement = guiButtonGrid[x][y].getTheMovement();
                name = guiButtonGrid[x][y].getTheName();

                //System.out.println("In copy button: "+name);
                temp[x][y] = new GuiButton(row,col,height,width,position,movement,name);

            }
        }
        return temp;


    }
    void fill_snapShotArray(GuiButton guiButtonGrid[][])
    {
        snapShotArray = new int[36];
        int count = 0;

        for(int x =1; x<7; x++)
        {
            for(int y =1; y<7; y++)
            {
                snapShotArray[count] = guiButtonGrid[x][y].getTheName();
                count++;
            }
        }

    }

    void print_snapShotArray(int snapShot[])
    {

        for(int x = 0; x<36; x++)
        {
            System.out.printf(snapShot[x]+" ");
        }
    }

}