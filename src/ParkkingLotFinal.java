import java.io.*;


/**
 * @author Mark Hallenback
 * @author Bob Hayhurst
 * This file contains the "main" function for the third project for CS 342, Software Design,
 * taught by Professor Pat Troy.
 */



import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ParkkingLotFinal extends JFrame{

    private String gameFileName[]= {"newGameFile1.txt","newGameFile2.txt","newGameFile3.txt","newGameFile4.txt","newGameFile5.txt",
            "newGameFile6.txt","newGameFile7.txt","newGameFile8.txt","newGameFile9.txt","newGameFile10.txt"};

    private String gameSolutionFileName[]= {"gameSolutionFile1.txt","gameSolutionFile2.txt","gameSolutionFile3.txt","gameSolutionFile4.txt","gameSolutionFile5.txt",
            "gameSolutionFile6.txt","gameSolutionFile7.txt","gameSolutionFile8.txt","gameSolutionFile9.txt","gameSolutionFile10.txt"};

    private String buttonNameArray[] = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    private ArrayList<String> gameFile;	//an ArrayList that holds the gameFile line by line as strings

    private ArrayList<String> gameSolutionFile; //an ArrayList that holds the solution from the file

    private JPanel guiBoard, header; //holds the gui button grid and other buttons

    private GuiButton guiButtonGrid[][];

    private GuiButton hintButtonGrid[][];

    private JButton hint, solve, reset;

    private JLabel footer;//for messages to user

    private int clickCount;//keep track of selection for target or moveto

    private int solutionFound;

    private int gameFileNumber;//to advance from one game to the next

    private int targetRow;//all info for target piece
    private int targetCol;
    private int targetWidth;
    private int targetHeight;
    private char targetPosition;
    private char targetMovement;
    private int targetName;


    ParkkingLotFinal()			//constructor
    {

        super("Ultimate Parking Lot");

        setSize(600,600);	//set the size of out frame

        setJMenuBar(createMenu());	//sets the menu bar for the frame

        Container container = getContentPane();	//sets the content pane for the frame

        container.setLayout(new BorderLayout()); //content pane has a boarder layout

        clickCount=0;		//initalize click count to 0
        solutionFound=0;

        header = new JPanel(new FlowLayout());	//this panel has 3 buttons and will be at the top of our content pane

        reset = new JButton("Reset");

        reset.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display message dialog when user selects About...
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                                "WHAT EVER YOU WERE JUST DOING...\n"+
                                        "DO THE OPPOSITE!!!!!\nTRY THIS PUZZLE AGAIN FROM THE START!",
                                "reset", JOptionPane.PLAIN_MESSAGE );

                        guiButtonGrid = Button_MetaData.setGuiButtonValues(gameFile); //get and set values for the buttons from the file

                        guiBoard.removeAll();
                        guiBoard.revalidate();

                        int gameRows= File_Manipulation.getRowsFromList(gameFile);//get rows and cols from gameFile 6x6
                        int gameCols= File_Manipulation.getColsFromList(gameFile);

                        addButtonsTo_guiBoard(gameRows, gameCols);

                        guiBoard.repaint();

                        return;

                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        solve = new JButton("Solve");

        solve.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display message dialog when user selects About...
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                                "FINDING THE SOLUTION COULD TAKE A FEW SECONDS\n"+
                                        "SO, BE PATIENT\nCLOSE THIS DIALOG BOX TO BEGIN FINDING THE SOLUTION!",
                                "solve", JOptionPane.PLAIN_MESSAGE );

                        PuzzleSolver solveIt = new PuzzleSolver(guiButtonGrid, gameFile);

                        solutionFound = solveIt.findSolution();

                        if(solutionFound != 0)	//there is a solution to the puzzle
                        {

                            guiButtonGrid = solveIt.getTheSolution();
                            updateTheGuiButtons(6,6);
                            updateTheGuiBoard();

                            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                                    "A SOLUTION WAS FOUND!\nTHE BOARD SHOULD LOOK LIKE THIS "+
                                            "IF YOU SOLVE IT.\nMOVING ON TO THE NEXT PUZZLE!\n" +
                                            " (PLEASE DRAG THIS WINDOW OUT OF THE WAY TO SEE THE SOLUTION)",
                                    "solve", JOptionPane.PLAIN_MESSAGE );
                        }

                        else		//there is not a solution to this puzzle. Unsolvable
                        {

                            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                                    "THIS PUZZLE HAS NO SOLUTION!\nMOVING ON TO THE NEXT PUZZLE!",
                                    "solve", JOptionPane.PLAIN_MESSAGE );
                        }

                        gameFileNumber++;

                        if(gameFileNumber > 9)
                        {
                            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                                    "That was the last puzzle!\n The game will start over with the first puzzle.\n"+
                                            "Feel Free to exit the game.",
                                    "", JOptionPane.PLAIN_MESSAGE );

                            gameFileNumber = 0;

                        }

                        gameFile = new ArrayList<String> (File_Manipulation.readInGameFile(gameFileName[gameFileNumber]));//read in newGameFile

                        guiButtonGrid = Button_MetaData.setGuiButtonValues(gameFile); //get and set values for the buttons from the file

                        guiBoard.removeAll();
                        guiBoard.revalidate();

                        int gameRows= File_Manipulation.getRowsFromList(gameFile);//get rows and cols from gameFile 6x6
                        int gameCols= File_Manipulation.getColsFromList(gameFile);

                        addButtonsTo_guiBoard(gameRows, gameCols);

                        guiBoard.repaint();

                        return;

                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        hint = new JButton("Hint");

        hint.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display message dialog when user selects About...
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                                "NEVER TRUST ANYONE WITH TWO FIRST NAMES!!!",
                                "hint", JOptionPane.PLAIN_MESSAGE );

                    }//end of action performed

                }  // end anonymous inner class

        ); // end call to addActionListener

        header.add(reset);	//adding the buttons to the panel
        header.add(solve);
        header.add(hint);

        container.add(header, BorderLayout.NORTH);	//add the panel 'North' with 3 buttons to the content pane

        footer = new JLabel("this is the way to send messages to the user");	//footer will convey messages to the user

        container.add(footer, BorderLayout.SOUTH);	//add the footer 'South' for messages to the content pane

        gameFileNumber=0; //used to load in the next game file

        gameFile = new ArrayList<String> (File_Manipulation.readInGameFile(gameFileName[gameFileNumber]));//read in newGameFile

        //gameSolutionFile= new ArrayList<String> (readInGameSolutionFile(gameSolutionFileName[0]));

        guiButtonGrid = Button_MetaData.setGuiButtonValues(gameFile); //get and set values for the buttons from the file

        hintButtonGrid = Button_MetaData.setGuiButtonValues(gameFile); //get and set values for the buttons from the file

        int gameRows= File_Manipulation.getRowsFromList(gameFile);//get rows and cols from gameFile 6x6
        int gameCols= File_Manipulation.getColsFromList(gameFile);

        guiBoard = new JPanel();//set up the panel for the gui buttons

        guiBoard.setLayout(new GridLayout(gameRows,gameCols,2,2));//set the panel layout for the buttons

        //add buttons to the grid
        addButtonsTo_guiBoard(gameRows, gameCols);

        container.add(guiBoard, BorderLayout.CENTER);

        setVisible(true);	//set our frame visible

    }//end of ParkkingLotFinal constructor

    //Creates the menu bar, menus and adds all action listeners to the menu items
    JMenuBar createMenu()
    {
        JMenuBar theMenu= new JMenuBar();

        JMenu exit = new JMenu("Exit");
        JMenuItem allDone = new JMenuItem("I Want To Quit!");

        allDone.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display message dialog when user selects About...
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                                "THANKS FOR PLAYING!\nREMEMBER TO TIP THE WAITSTAFF!\nEXITING THE GAME......",
                                "allDone", JOptionPane.PLAIN_MESSAGE );
                        System.exit(NORMAL);
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        exit.add(allDone);


        JMenu about = new JMenu("About");
        JMenuItem whoMadeThis = new JMenuItem("Who Made This Game?");

        whoMadeThis.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display message dialog when user selects About...
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                                "THIS GAME IS BROUGHT TO YOU BY:\n\nROBERT HAYHURST\n&\nMARK HALLENBECK",
                                "whoMadeThis", JOptionPane.PLAIN_MESSAGE );
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        about.add(whoMadeThis);


        JMenu help = new JMenu("Help");

        JMenuItem howToPlay = new JMenuItem("I Don't Know How To Play!");

        howToPlay.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display message dialog when user selects About...
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                                "If you don't know how to play, Google It!",
                                "howToPlay", JOptionPane.PLAIN_MESSAGE );
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        JMenuItem dontLike = new JMenuItem("I Don't Like This Version!");

        dontLike.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display message dialog when user selects About...
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                                "Feel free to go online to play other versions!",
                                "dontLike", JOptionPane.PLAIN_MESSAGE );
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        JMenuItem hungry = new JMenuItem("I'm Hungry!");

        hungry.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display message dialog when user selects About...
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                                "Order some pizza or a sandwich! Bag of chips? Leftovers?",
                                "hungry", JOptionPane.PLAIN_MESSAGE );
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        help.add(howToPlay);
        help.add(dontLike);
        help.add(hungry);

        theMenu.add(help);
        theMenu.add(about);
        theMenu.add(exit);

        return theMenu;

    }

    //Used only when loading in a new game file. Sets the look of the buttons from the guiButton grid,
    //adds the mouselistener to each button and then adds the buttons to the guiBoard
    void addButtonsTo_guiBoard(int gameRows, int gameCols)
    {
        GuiButtonClick buttonClicked = new GuiButtonClick();

        for(int r=1; r < gameRows+1; r++)// add the gui buttons to the panel
        {
            for(int c=1; c < gameCols+1; c++)
            {
                int name = guiButtonGrid[r][c].getTheName();

                switch(name)
                {
                    case 0:

                        guiButtonGrid[r][c].setBackground(Color.WHITE);
                        guiButtonGrid[r][c].setText("");
                        guiButtonGrid[r][c].setOpaque(true);

                        break;
                    case 1:
                        guiButtonGrid[r][c].setBackground(Color.BLUE);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);

                        break;
                    case 2:
                        guiButtonGrid[r][c].setBackground(Color.GREEN);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);

                        break;
                    case 3:
                        guiButtonGrid[r][c].setBackground(Color.MAGENTA);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 4:
                        guiButtonGrid[r][c].setBackground(Color.ORANGE);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 5:
                        guiButtonGrid[r][c].setBackground(Color.PINK);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 6:
                        guiButtonGrid[r][c].setBackground(Color.YELLOW);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 7:
                        guiButtonGrid[r][c].setBackground(Color.RED);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 8:
                        guiButtonGrid[r][c].setBackground(Color.BLACK);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 999:
                        guiButtonGrid[r][c].setBackground(Color.DARK_GRAY);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText("Goal Piece");


                        break;
                    default:
                        break;
                }//end of switch statement

                guiButtonGrid[r][c].addMouseListener(buttonClicked);
                guiBoard.add(guiButtonGrid[r][c]);


            }
        }
        footer.setText("GOOD LUCK ON THIS PUZZLE!!!!");
    }//end of method: addaddButtonsTo_guiBoard()

    //method used to add guiButtons to the guiBoard after the hint button has been pressed
    //new gui will show the solved puzzle
    void updateTheGuiButtons2(int gameRows, int gameCols)
    {


        for(int r=1; r < gameRows+1; r++)// add the gui buttons to the panel
        {
            for(int c=1; c < gameCols+1; c++)
            {
                int name = hintButtonGrid[r][c].getTheName();

                switch(name)
                {
                    case 0:

                        hintButtonGrid[r][c].setBackground(Color.WHITE);
                        hintButtonGrid[r][c].setText("");
                        hintButtonGrid[r][c].setOpaque(true);
                        break;
                    case 1:
                        hintButtonGrid[r][c].setBackground(Color.BLUE);
                        hintButtonGrid[r][c].setOpaque(true);
                        hintButtonGrid[r][c].setText(buttonNameArray[name]);

                        break;
                    case 2:
                        hintButtonGrid[r][c].setBackground(Color.GREEN);
                        hintButtonGrid[r][c].setOpaque(true);
                        hintButtonGrid[r][c].setText(buttonNameArray[name]);

                        break;
                    case 3:
                        hintButtonGrid[r][c].setBackground(Color.MAGENTA);
                        hintButtonGrid[r][c].setOpaque(true);
                        hintButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 4:
                        hintButtonGrid[r][c].setBackground(Color.ORANGE);
                        hintButtonGrid[r][c].setOpaque(true);
                        hintButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 5:
                        hintButtonGrid[r][c].setBackground(Color.PINK);
                        hintButtonGrid[r][c].setOpaque(true);
                        hintButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 6:
                        hintButtonGrid[r][c].setBackground(Color.YELLOW);
                        hintButtonGrid[r][c].setOpaque(true);
                        hintButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 7:
                        hintButtonGrid[r][c].setBackground(Color.RED);
                        hintButtonGrid[r][c].setOpaque(true);
                        hintButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 8:
                        hintButtonGrid[r][c].setBackground(Color.BLACK);
                        hintButtonGrid[r][c].setOpaque(true);
                        hintButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 999:
                        hintButtonGrid[r][c].setBackground(Color.DARK_GRAY);
                        hintButtonGrid[r][c].setOpaque(true);
                        hintButtonGrid[r][c].setText("Goal Piece");


                        break;
                    default:
                        break;
                }//end of switch statement


                guiBoard.add(hintButtonGrid[r][c]);


            }
        }
    }

    //method used to add guiButtons to the guiBoard after a valid move has been made and the meta-data for the buttons
    //has been updated.
    void updateTheGuiButtons(int gameRows, int gameCols)
    {


        for(int r=1; r < gameRows+1; r++)// add the gui buttons to the panel
        {
            for(int c=1; c < gameCols+1; c++)
            {
                int name = guiButtonGrid[r][c].getTheName();

                switch(name)
                {
                    case 0:

                        guiButtonGrid[r][c].setBackground(Color.WHITE);
                        guiButtonGrid[r][c].setText("");
                        guiButtonGrid[r][c].setOpaque(true);
                        break;
                    case 1:
                        guiButtonGrid[r][c].setBackground(Color.BLUE);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);

                        break;
                    case 2:
                        guiButtonGrid[r][c].setBackground(Color.GREEN);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);

                        break;
                    case 3:
                        guiButtonGrid[r][c].setBackground(Color.MAGENTA);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 4:
                        guiButtonGrid[r][c].setBackground(Color.ORANGE);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 5:
                        guiButtonGrid[r][c].setBackground(Color.PINK);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 6:
                        guiButtonGrid[r][c].setBackground(Color.YELLOW);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 7:
                        guiButtonGrid[r][c].setBackground(Color.RED);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 8:
                        guiButtonGrid[r][c].setBackground(Color.BLACK);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText(buttonNameArray[name]);


                        break;
                    case 999:
                        guiButtonGrid[r][c].setBackground(Color.DARK_GRAY);
                        guiButtonGrid[r][c].setOpaque(true);
                        guiButtonGrid[r][c].setText("Goal Piece");


                        break;
                    default:
                        break;
                }//end of switch statement


                guiBoard.add(guiButtonGrid[r][c]);


            }
        }
    }


    //class used for clicks on the game pieces. Controls their movement on the board. Click count keeps track of whether the
    //user is selecting a piece to move (target piece) or a spot to move to (move to piece)
    class GuiButtonClick implements MouseListener{  //listener for the play again button



        @Override
        public void mouseClicked(MouseEvent e)
        {
            GuiButton temp= (GuiButton)e.getSource();	//get the button clicked

            if(clickCount%2 == 0)
            {
                if(temp.getTheName() == 0)
                {
                    printTempInfo(temp);
                    JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                            "You can not move an open space. Chose another piece to move",
                            "", JOptionPane.PLAIN_MESSAGE );

                    return;
                }

                setTargetData(temp);

                footer.setText("You have selected a piece to move. Click where you want it to go!");

                System.out.println("setting target info r: "+targetRow+" c: "+targetCol+
                        " width: "+targetWidth
                        +" height "+targetHeight+ " position: "+ targetPosition+
                        " move: "+targetMovement+" targetName: "+targetName);

                clickCount++;

            }//end of if it is a target selection

            else// it's a move to selection
            {
                printTempInfo(temp);

                if(temp.getTheName() != 0)		//the move-to piece is not empty, do not increment clickCount
                {
                    printTempInfo(temp);
                    JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                            "1) This is not an open space! Chose another piece to move.",
                            "", JOptionPane.PLAIN_MESSAGE );
                    clickCount++;
                    return;
                }


                if(temp.getTheRow() != targetRow && temp.getTheCol() != targetCol)
                {
                    JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                            "You can only move vertically or Horizontally. Chose another piece to move.",
                            "", JOptionPane.PLAIN_MESSAGE );
                    clickCount++;
                    return;
                }

//***********************************if target piece is height is 2 and moves horizontally************************************

                if(targetMovement == 'h' && targetHeight == 2)
                {
                    check2Horizontal(temp);

                    int winCheck = GridOperations.checkForTheWin(gameFile, guiButtonGrid);

                    if(winCheck == 1)
                    {
                        nextPuzzle();
                    }

                    return;
                }

//*************************************if target piece is height is 2 and moves vertically********************************************

                if(targetMovement == 'v' && targetHeight == 2)
                {
                    checkH2V(temp);

                    int winCheck = GridOperations.checkForTheWin(gameFile, guiButtonGrid);

                    if(winCheck == 1)
                    {
                        nextPuzzle();
                    }
                    return;
                }

//*********************************if target piece is height is 3 and move horizontally********************************************

                if(targetMovement == 'h' && targetHeight == 3)
                {
                    check3Horizontal(temp);

                    int winCheck = GridOperations.checkForTheWin(gameFile, guiButtonGrid);

                    if(winCheck == 1)
                    {
                        nextPuzzle();
                    }

                    return;
                }

//************************************if target piece is height is 3 and moves vertically***************************************

                if(targetMovement == 'v' && targetHeight == 3)
                {
                    checkH3V(temp);

                    int winCheck = GridOperations.checkForTheWin(gameFile, guiButtonGrid);

                    if(winCheck == 1)
                    {
                        nextPuzzle();
                    }

                    return;
                }



//**********************************if target piece is width is two and it moves vertically**************************************

                if(targetMovement == 'v' && targetWidth == 2)
                {
                    check2Vertical(temp);

                    int winCheck = GridOperations.checkForTheWin(gameFile, guiButtonGrid);

                    if(winCheck == 1)
                    {
                        nextPuzzle();
                    }

                    return;
                }

//**********************************if target piece is width is two and moves horizontally******************************************

                if(targetMovement == 'h' && targetWidth == 2)
                {
                    checkW2H(temp);

                    int winCheck = GridOperations.checkForTheWin(gameFile, guiButtonGrid);

                    if(winCheck == 1)
                    {
                        nextPuzzle();
                    }

                    return;
                }

//***********************************if target piece is width is three and moves horizontally****************************************

                if(targetMovement == 'h' && targetWidth == 3)
                {
                    checkW3H(temp);

                    int winCheck = GridOperations.checkForTheWin(gameFile, guiButtonGrid);

                    if(winCheck == 1)
                    {
                        nextPuzzle();
                    }

                    return;
                }


//**********************************if target piece is width is three and moves vertically****************************************

                if(targetMovement == 'v' && targetWidth == 3)
                {

                    check3Vertical(temp);

                    int winCheck = GridOperations.checkForTheWin(gameFile, guiButtonGrid);

                    if(winCheck == 1)
                    {
                        nextPuzzle();
                    }

                    return;

                }

//****************************************************end of move checks********************************************************

                return;
            }//end of else its a move to piece

            return;
        }//end of method: mouseClicked()

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }
    }//end of guiButtonClick class

    //method used to switch to the next puzzle after a puzzle has been solved.
    //If the puzzle solved was the last puzzle, it will load in the first puzzle and start again.
    void nextPuzzle()
    {
        JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                "CONGRATULATIONS!!! YOU SOLVED THIS PUZZLE.\nHERE COMES THE NEXT ONE....",
                "", JOptionPane.PLAIN_MESSAGE );

        gameFileNumber++;

        if(gameFileNumber > 9)
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "YOU HAVE SOLVED EVERY PUZZLE!!!\nINCONCEIVABLE!!!\nTHE GAME WILL "+
                            "RESET TO THE FIRST PUZZLE.\nFEEL FREE TO EXIT THE GAME.",
                    "", JOptionPane.PLAIN_MESSAGE );

            gameFileNumber = 0;

        }

        gameFile = new ArrayList<String> (File_Manipulation.readInGameFile(gameFileName[gameFileNumber]));//read in newGameFile

        guiButtonGrid = Button_MetaData.setGuiButtonValues(gameFile); //get and set values for the buttons from the file

        guiBoard.removeAll();
        guiBoard.revalidate();

        int gameRows= File_Manipulation.getRowsFromList(gameFile);//get rows and cols from gameFile 6x6
        int gameCols= File_Manipulation.getColsFromList(gameFile);

        addButtonsTo_guiBoard(gameRows, gameCols);

        guiBoard.repaint();

        return;


    }

    //checks for valid move when the height of the piece is two and movement is horizontal
    void check2Horizontal(GuiButton temp)
    {
        if(temp.getTheRow() != targetRow) //not the same row, show error and return
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "You can only move to a spot in the same row as the piece you want to move."+
                            " Chose another space to move to",
                    "", JOptionPane.PLAIN_MESSAGE );
            return;
        }

        //checks for a clear path returns 1 if clear 0 if not
        int isClear = GridOperations.checkPath_Height2_Horizontal(temp,
                targetRow, targetCol, targetPosition,
                guiButtonGrid);

        if(isClear == 0)
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "The path is blocked, chose another piece to move",
                    "", JOptionPane.PLAIN_MESSAGE );
            clickCount++;
            return;
        }
        else
        {
            footer.setText("VALID MOVE!");

            if(targetPosition == 'f')
            {
                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                        temp.getTheCol(),guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow()+1,
                        temp.getTheCol(),guiButtonGrid);

                updateTheGuiBoard();
                clickCount++;
                return;

            }
            else
            {
                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                        temp.getTheCol(),guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow()-1,
                        temp.getTheCol(),guiButtonGrid);

                updateTheGuiBoard();
                clickCount++;
                return;

            }

        }
    }//end of method: check2Horizontal()


    //checks for valid move when the height of the piece is two and the movement is vertical
    void checkH2V(GuiButton temp)
    {
        if(temp.getTheCol() != targetCol) //not the same row, show error and return
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "You can only move to a spot in the same column as the piece you want to move."+
                            " Chose another space to move to",
                    "", JOptionPane.PLAIN_MESSAGE );
            return;
        }

        //checks for a clear path returns 1 if clear 0 if not
        int isClear = GridOperations.checkPath_Height2_Vertical(temp,
                targetRow, targetCol, targetPosition,
                guiButtonGrid);

        if(isClear == 0)
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "The path is blocked, chose another piece to move",
                    "", JOptionPane.PLAIN_MESSAGE );
            clickCount++;
            return;
        }
        else
        {
            footer.setText("VALID MOVE!");

            if(targetRow < temp.getTheRow())	//if the moveto is below the target
            {
                if(targetPosition == 'f')	//if target is the front piece
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow()-1,
                            temp.getTheCol(),guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;
                }

                else		//if target is the back piece
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow()-1,
                            temp.getTheCol(),guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }

            }
            else		//move to is above the target
            {
                if(targetPosition == 'f')		//if the target is the front of the piece
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow()+1,
                            temp.getTheCol(),guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }//end of if the target is a front piece

                else		//the target is the back piece
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow()+1,
                            temp.getTheCol(),guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }//end of else its a back piece

            }//end of else moveto is above the target

        }//end of else it's a valid move

    }//end of method: checkH2V()

    //checks for valid move when the width of the piece is two and the movement is horizontal
    void checkW2H(GuiButton temp)
    {
        if(temp.getTheRow() != targetRow) //not the same row, show error and return
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "You can only move to a spot in the same row as the piece you want to move."+
                            " Chose another space to move to",
                    "", JOptionPane.PLAIN_MESSAGE );
            return;
        }

        //checks for a clear path returns 1 if clear 0 if not
        int isClear = GridOperations.checkPath_Width2_Horizontal(temp,
                targetRow, targetCol, targetPosition,
                guiButtonGrid);

        if(isClear == 0)
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "The path is blocked, chose another piece to move",
                    "", JOptionPane.PLAIN_MESSAGE );
            clickCount++;
            return;
        }

        else		//it is a valid move, swap button values
        {
            footer.setText("VALID MOVE!");

            if(targetCol < temp.getTheCol())	//if the moveto is to the right of  the target
            {
                if(targetPosition == 'f')	//if target is the front piece
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol()-1,guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;
                }

                else		//if target is the back piece
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                            temp.getTheCol()-1,guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }

            }
            else		//move to is to the left of  the target
            {
                if(targetPosition == 'f')		//if the target is the front of the piece
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                            temp.getTheCol()+1,guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }//end of if the target is a front piece

                else		//the target is the back piece
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol()+1,guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }//end of else its a back piece

            }//end of else moveto is to the left of  the target

        }//end of else it's a valid move, swap button values

    }//end of method: checkW2H

    //checks for valid move when the width of the piece is three and the movement is horizontal
    void checkW3H(GuiButton temp)
    {
        if(temp.getTheRow() != targetRow) //not the same row, show error and return
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "You can only move to a spot in the same row as the piece you want to move."+
                            " Chose another space to move to",
                    "", JOptionPane.PLAIN_MESSAGE );
            return;
        }

        //checks for a clear path returns 1 if clear 0 if not
        int isClear = GridOperations.checkPath_Width3_Horizontal(temp,
                targetRow, targetCol, targetPosition,
                guiButtonGrid);

        if(isClear == 0)
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "The path is blocked, chose another piece to move",
                    "", JOptionPane.PLAIN_MESSAGE );
            clickCount++;
            return;
        }

        else		//it is a valid move, swap button values
        {
            footer.setText("VALID MOVE!");

            if(targetCol < temp.getTheCol())	//if the moveto is to the right of  the target
            {
                if(targetPosition == 'f')	//if target is the front piece
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol+2, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                            temp.getTheCol()-1,guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol()-2,guiButtonGrid);


                    updateTheGuiBoard();
                    clickCount++;
                    return;
                }

                else if(targetPosition == 'm')	//target is the middle and move to is to the right
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol()-1,guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                            temp.getTheCol()-2,guiButtonGrid);


                    updateTheGuiBoard();
                    clickCount++;
                    return;
                }

                else		//if target is the back piece
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                            temp.getTheCol()-1,guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol-2, temp.getTheRow(),
                            temp.getTheCol()-2,guiButtonGrid);


                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }

            }
            else		//move to is to the left of  the target
            {
                if(targetPosition == 'f')		//if the target is the front of the piece
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                            temp.getTheCol()+1,guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol+2, temp.getTheRow(),
                            temp.getTheCol()+2,guiButtonGrid);


                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }//end of if the target is a front piece

                else if(targetPosition == 'm')	//if target is a middle piece and move to is to the left
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol()+1,guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                            temp.getTheCol()+2,guiButtonGrid);


                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }
                else		//the target is the back piece and move to is to the left
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol-2, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                            temp.getTheCol()+1,guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol()+2,guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }//end of else its a back piece

            }//end of else moveto is to the left of  the target

        }//end of else it's a valid move, swap button values

    }//end of method: checkW3H()

    //checks for valid move when the height of the piece is three and the movement is horizontal
    void check3Horizontal(GuiButton temp)
    {
        if(temp.getTheRow() != targetRow) //not the same row, show error and return
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "You can only move to a spot in the same row as the piece you want to move."+
                            " Chose another space to move to",
                    "", JOptionPane.PLAIN_MESSAGE );
            return;
        }

        //checks for a clear path returns 1 if clear 0 if not
        int isClear2 = GridOperations.checkPath_Height3_Horizontal(temp,
                targetRow, targetCol, targetPosition,
                guiButtonGrid);

        if(isClear2 == 0)
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "The path is blocked, chose another piece to move",
                    "", JOptionPane.PLAIN_MESSAGE );
            clickCount++;
            return;
        }

        else		//path is clear, swap button data, update gui
        {
            footer.setText("VALID MOVE");

            if(targetPosition == 'f')
            {
                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                        temp.getTheCol(),guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow()+1,
                        temp.getTheCol(),guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow+2, targetCol, temp.getTheRow()+2,
                        temp.getTheCol(),guiButtonGrid);



                updateTheGuiBoard();
                clickCount++;
                return;

            }
            else if(targetPosition == 'b')
            {
                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                        temp.getTheCol(),guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow()-1,
                        temp.getTheCol(),guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow-2, targetCol, temp.getTheRow()-2,
                        temp.getTheCol(),guiButtonGrid);


                updateTheGuiBoard();
                clickCount++;
                return;

            }
            else
            {
                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                        temp.getTheCol(),guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow()-1,
                        temp.getTheCol(),guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow()+1,
                        temp.getTheCol(),guiButtonGrid);


                updateTheGuiBoard();
                clickCount++;
                return;
            }


        }// end of else, path is clear

    }//end of method: check3Horizontal()

    //checks for a valid move when the height of the piece is three and the movement is vertical
    void checkH3V(GuiButton temp)
    {
        if(temp.getTheCol() != targetCol) //not the same column, show error and return
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "You can only move to a spot in the same column as the piece you want to move."+
                            " Chose another space to move to",
                    "", JOptionPane.PLAIN_MESSAGE );
            return;
        }

        //checks for a clear path returns 1 if clear 0 if not
        int isClear = GridOperations.checkPath_Height3_Vertical(temp,
                targetRow, targetCol, targetPosition,
                guiButtonGrid);

        if(isClear == 0)
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "The path is blocked, chose another piece to move",
                    "", JOptionPane.PLAIN_MESSAGE );
            clickCount++;
            return;
        }
        else
        {
            footer.setText("VALID MOVE!");

            if(targetRow < temp.getTheRow())	//if the moveto is below the target
            {
                if(targetPosition == 'f')	//if target is the front piece and move to is below
                {

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow+2, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow()-1,
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow()-2,
                            temp.getTheCol(),guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;
                }

                else if(targetPosition == 'm')		//target is the middle piece and move to is below
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow()-1,
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow()-2,
                            temp.getTheCol(),guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }

                else		//if target is the back piece and move to is below
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow()-1,
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow-2, targetCol, temp.getTheRow()-2,
                            temp.getTheCol(),guiButtonGrid);


                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }

            }		//end of if move to is below the target

            else		//move to is above the target
            {
                if(targetPosition == 'f')		//if the target is the front of the piece and move to is above
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow()+1,
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow+2, targetCol, temp.getTheRow()+2,
                            temp.getTheCol(),guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }//end of if the target is a front piece

                else if(targetPosition == 'm')		//target is a middle piece and move to is above
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow()+1,
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow+1, targetCol, temp.getTheRow()+2,
                            temp.getTheCol(),guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }

                else		//the target is the back piece and move to is above
                {
                    guiButtonGrid = GridOperations.swapButtonValues(targetRow-2, targetCol, temp.getTheRow(),
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow-1, targetCol, temp.getTheRow()+1,
                            temp.getTheCol(),guiButtonGrid);

                    guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow()+2,
                            temp.getTheCol(),guiButtonGrid);

                    updateTheGuiBoard();
                    clickCount++;
                    return;

                }//end of else its a back piece

            }//end of else moveto is above the target

        }//end of else it's a valid move

    }//end of method: checkH3V()

    //checks for valid move when width is two and the movement is vertical
    void check2Vertical(GuiButton temp)
    {
        if(temp.getTheCol() != targetCol) //not the same column, show error and return
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "You can only move to a spot in the same column as the piece you want to move."+
                            " Chose another space to move to",
                    "", JOptionPane.PLAIN_MESSAGE );
            return;
        }

        //checks for a clear path returns 1 if clear 0 if not
        int isClear = GridOperations.checkPath_Width2_Vertical(temp,
                targetRow, targetCol, targetPosition,
                guiButtonGrid);

        //the path is not clear for the move
        if(isClear == 0)
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "The path is blocked, chose another piece to move",
                    "", JOptionPane.PLAIN_MESSAGE );
            clickCount++;
            return;
        }

        //the path is clear for the move, swap button values and update gui
        else
        {
            footer.setText("VALID MOVE");

            // target is the front piece of two
            if(targetPosition == 'f')
            {
                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                        temp.getTheCol(),guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                        temp.getTheCol()+1,guiButtonGrid);

                updateTheGuiBoard();
                clickCount++;
                return;

            }
            // target is the back piece of two
            else
            {
                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                        temp.getTheCol(),guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                        temp.getTheCol()-1,guiButtonGrid);

                updateTheGuiBoard();
                clickCount++;
                return;

            }

        }//end else, path is clear

    }//end of method: check2Vertical()

    //checks for valid move when the width of the piece is three and the movement is vertical
    void check3Vertical(GuiButton temp)
    {
        if(temp.getTheCol() != targetCol) //not the same row, show error and return
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "You can only move to a spot in the same column as the piece you want to move."+
                            " Chose another space to move to",
                    "", JOptionPane.PLAIN_MESSAGE );
            return;
        }

        //checks for a clear path returns 1 if clear 0 if not
        int isClear2 = GridOperations.checkPath_Width3_Vertical(temp,
                targetRow, targetCol, targetPosition,
                guiButtonGrid);

        if(isClear2 == 0)
        {
            JOptionPane.showMessageDialog( ParkkingLotFinal.this,
                    "The path is blocked, chose another piece to move",
                    "", JOptionPane.PLAIN_MESSAGE );
            clickCount++;
            return;
        }

        else		//path is clear, swap button data, update gui
        {
            footer.setText("VALID MOVE");

            if(targetPosition == 'f')
            {
                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                        temp.getTheCol(),guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                        temp.getTheCol()+1,guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol+2, temp.getTheRow(),
                        temp.getTheCol()+2,guiButtonGrid);



                updateTheGuiBoard();
                clickCount++;
                return;

            }
            else if(targetPosition == 'b')
            {
                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                        temp.getTheCol(),guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                        temp.getTheCol()-1,guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol-2, temp.getTheRow(),
                        temp.getTheCol()-2,guiButtonGrid);


                updateTheGuiBoard();
                clickCount++;
                return;

            }
            else
            {
                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol, temp.getTheRow(),
                        temp.getTheCol(),guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol-1, temp.getTheRow(),
                        temp.getTheCol()-1,guiButtonGrid);

                guiButtonGrid = GridOperations.swapButtonValues(targetRow, targetCol+1, temp.getTheRow(),
                        temp.getTheCol()+1,guiButtonGrid);


                updateTheGuiBoard();
                clickCount++;
                return;
            }


        }// end of else, path is clear

    }//end of method: check3Vertical()

    // sets the values for the target piece's meta-data
    void setTargetData(GuiButton temp)
    {
        targetRow= temp.getTheRow();		//get the info for the target button
        targetCol= temp.getTheCol();
        targetWidth= temp.getTheWidth();
        targetHeight= temp.getTheHeight();
        targetPosition= temp.getThePosition();
        targetMovement= temp.getTheMovement();
        targetName= temp.getTheName();
    }

    //prints out the meta-data of button pressed. Used for debugging purposes only
    void printTempInfo(GuiButton temp)
    {
        System.out.println("setting target info r: "+temp.getTheRow()+" c: "+temp.getTheCol()+
                " width: "+temp.getTheWidth()
                +" height "+temp.getTheHeight()+ " position: "+ temp.getThePosition()+
                " move: "+temp.getTheMovement()+" targetName: "+temp.getTheName()+
                "clickCount: "+clickCount);

    }

    //After a valid move is made, method removes buttons from the guiBoard panel. replaces them with buttons that have updated meta-data
    //and repaints the guiBoard
    void updateTheGuiBoard()
    {
        guiBoard.removeAll();
        guiBoard.revalidate();

        int gameRows= File_Manipulation.getRowsFromList(gameFile);//get rows and cols from gameFile 6x6
        int gameCols= File_Manipulation.getColsFromList(gameFile);

        updateTheGuiButtons(gameRows, gameCols);

        guiBoard.repaint();
    }

    //method used to update the gui board with the solution when hint button is pressed
    void updateTheGuiBoard2()
    {
        guiBoard.removeAll();
        guiBoard.revalidate();

        int gameRows= File_Manipulation.getRowsFromList(gameFile);//get rows and cols from gameFile 6x6
        int gameCols= File_Manipulation.getColsFromList(gameFile);

        updateTheGuiButtons2(gameRows, gameCols);

        guiBoard.repaint();
    }




    public static void main(String args[])
    {
        ParkkingLotFinal game= new ParkkingLotFinal();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }//end of main

}//end of ParkkingLotFinal class
