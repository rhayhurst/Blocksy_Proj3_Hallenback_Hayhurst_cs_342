import java.util.ArrayList;




class Button_MetaData {

    static GuiButton[][] setGuiButtonValues(ArrayList<String> gameFile)
    {
        int gameRows= File_Manipulation.getRowsFromList(gameFile);//get rows and cols from gameFile 6x6
        int gameCols= File_Manipulation.getColsFromList(gameFile);

        GuiButton temp[][] = new GuiButton[gameRows+2][gameCols+2];

        temp = initalizeTheButtons(temp, gameRows, gameCols);
        temp = setTheGamePieces(gameFile, temp);

        return temp;
    }

    static GuiButton[][] initalizeTheButtons(GuiButton[][] temp,int rows, int cols){

        //GuiButtonClick buttonClicked = new GuiButtonClick();
        for(int x = 0; x < rows+2; x++)
        {
            for(int y = 0; y < cols+2; y++)
            {
                temp[x][y] = new GuiButton(x, y, 1, 1, 'x', 'x', 0);
                //temp[x][y].addMouseListener(buttonClicked);
            }
        }

        return temp;
    }

    static GuiButton[][] setTheGamePieces(ArrayList<String> gameFile, GuiButton [][] temp)
    {
        int name= 1;
        int startRow, startCol, width, height;
        char move;

        temp = setTheGoalPiece(gameFile, temp);		//initalize the goal piece on the game board

        int gameFile_Size= gameFile.size(); //lines in the file

        for(int i=2; i<gameFile_Size; i++)//run through arrayList, set game pieces
        {

            String gamePiece= gameFile.get(i);//get string at index i from gameFile

            char cRow= gamePiece.charAt(0);//extract values and convert to ints or leave as chars
            startRow= Character.getNumericValue(cRow);

            char cCol= gamePiece.charAt(2);
            startCol= Character.getNumericValue(cCol);

            char cWidth= gamePiece.charAt(4);
            width= Character.getNumericValue(cWidth);

            char cHeight= gamePiece.charAt(6);
            height= Character.getNumericValue(cHeight);

            move= gamePiece.charAt(8);

            temp[startRow][startCol].setTheWidth(width);//set the values in the gameGridNode
            temp[startRow][startCol].setTheHeight(height);
            temp[startRow][startCol].setTheMovement(move);
            temp[startRow][startCol].setTheName(name);
            temp[startRow][startCol].setThePosition('f');


            //if width is 2, it will always grow to the right
            if(width == 2)
            {
                temp[startRow][startCol+1].setTheWidth(width);
                temp[startRow][startCol+1].setTheHeight(height);
                temp[startRow][startCol+1].setTheMovement(move);
                temp[startRow][startCol+1].setTheName(name);
                temp[startRow][startCol+1].setThePosition('b');
            }

            //if height is 2, it will always grow down
            if(height == 2)
            {
                temp[startRow+1][startCol].setTheWidth(width);
                temp[startRow+1][startCol].setTheHeight(height);
                temp[startRow+1][startCol].setTheMovement(move);
                temp[startRow+1][startCol].setTheName(name);
                temp[startRow+1][startCol].setThePosition('b');

            }

            //if width is 3, it will always grow to the right
            if(width == 3)
            {
                //middle piece of the three
                temp[startRow][startCol+1].setTheWidth(width);
                temp[startRow][startCol+1].setTheHeight(height);
                temp[startRow][startCol+1].setTheMovement(move);
                temp[startRow][startCol+1].setTheName(name);
                temp[startRow][startCol+1].setThePosition('m');


                temp[startRow][startCol+2].setTheWidth(width);
                temp[startRow][startCol+2].setTheHeight(height);
                temp[startRow][startCol+2].setTheMovement(move);
                temp[startRow][startCol+2].setTheName(name);
                temp[startRow][startCol+2].setThePosition('b');
            }

            //if height is 3, it will always grow down
            if(height == 3)
            {
                //middle piece of the three
                temp[startRow+1][startCol].setTheWidth(width);
                temp[startRow+1][startCol].setTheHeight(height);
                temp[startRow+1][startCol].setTheMovement(move);
                temp[startRow+1][startCol].setTheName(name);
                temp[startRow+1][startCol].setThePosition('m');

                temp[startRow+2][startCol].setTheWidth(width);
                temp[startRow+2][startCol].setTheHeight(height);
                temp[startRow+2][startCol].setTheMovement(move);
                temp[startRow+2][startCol].setTheName(name);
                temp[startRow+2][startCol].setThePosition('b');
            }

            name++;

        }//end of for loop that initalizes game pieces on game board

        return temp;
    }

    static GuiButton[][] setTheGoalPiece(ArrayList<String> gameFile, GuiButton[][] temp)
    {
        int startRow, startCol, width, height, name=999;
        char move;

        String goalPiece= gameFile.get(1);// gameFile index 1 always has info for the goalPiece

        char cRow= goalPiece.charAt(0);// get the values from the string and convert to ints if necessary
        startRow= Character.getNumericValue(cRow);

        char cCol= goalPiece.charAt(2);
        startCol= Character.getNumericValue(cCol);

        char cWidth= goalPiece.charAt(4);
        width= Character.getNumericValue(cWidth);

        char cHeight= goalPiece.charAt(6);
        height= Character.getNumericValue(cHeight);

        move= goalPiece.charAt(8);


        temp[startRow][startCol].setTheWidth(width);//set the values in the gameGridNode
        temp[startRow][startCol].setTheHeight(height);
        temp[startRow][startCol].setTheMovement(move);
        temp[startRow][startCol].setTheName(name);
        temp[startRow][startCol].setThePosition('f');


        //if width is 2, it will always grow to the right
        if(width == 2)
        {
            temp[startRow][startCol+1].setTheWidth(width);
            temp[startRow][startCol+1].setTheHeight(height);
            temp[startRow][startCol+1].setTheMovement(move);
            temp[startRow][startCol+1].setTheName(name);
            temp[startRow][startCol+1].setThePosition('b');
        }

        //if height is 2, it will always grow down
        if(height == 2)
        {
            temp[startRow+1][startCol].setTheWidth(width);
            temp[startRow+1][startCol].setTheHeight(height);
            temp[startRow+1][startCol].setTheMovement(move);
            temp[startRow+1][startCol].setTheName(name);
            temp[startRow+1][startCol].setThePosition('b');

        }

        //if width is 3, it will always grow to the right
        if(width == 3)
        {
            //middle piece of the three
            temp[startRow][startCol+1].setTheWidth(width);
            temp[startRow][startCol+1].setTheHeight(height);
            temp[startRow][startCol+1].setTheMovement(move);
            temp[startRow][startCol+1].setTheName(name);
            temp[startRow][startCol+1].setThePosition('m');


            temp[startRow][startCol+2].setTheWidth(width);
            temp[startRow][startCol+2].setTheHeight(height);
            temp[startRow][startCol+2].setTheMovement(move);
            temp[startRow][startCol+2].setTheName(name);
            temp[startRow][startCol+2].setThePosition('b');
        }

        //if height is 3, it will always grow down
        if(height == 3)
        {
            //middle piece of the three
            temp[startRow+1][startCol].setTheWidth(width);
            temp[startRow+1][startCol].setTheHeight(height);
            temp[startRow+1][startCol].setTheMovement(move);
            temp[startRow+1][startCol].setTheName(name);
            temp[startRow+1][startCol].setThePosition('m');

            temp[startRow+2][startCol].setTheWidth(width);
            temp[startRow+2][startCol].setTheHeight(height);
            temp[startRow+2][startCol].setTheMovement(move);
            temp[startRow+2][startCol].setTheName(name);
            temp[startRow+2][startCol].setThePosition('b');
        }

        return temp;

    }//end of initGoalPiece




}