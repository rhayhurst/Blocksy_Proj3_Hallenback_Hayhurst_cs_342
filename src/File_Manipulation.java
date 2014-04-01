import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


class File_Manipulation{


    static ArrayList<String> readInGameFile(String filename)
    {
        ArrayList<String> list = new ArrayList<String>();
        try
        {
            FileInputStream dataIn = new FileInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(dataIn));
            String line;
            while((line = br.readLine()) != null)
            {
                list.add(line);
                System.out.println(line);
            }
            dataIn.close();
            System.out.println("\n");
        }
        catch(IOException e)
        {
            System.out.println("can't find the file");
        }
        return list;
    }

    static ArrayList<String> readInGameSolutionFile(String filename)
    {
        ArrayList<String> list = new ArrayList<String>();
        try
        {
            FileInputStream dataIn = new FileInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(dataIn));
            String line;
            while((line = br.readLine()) != null)
            {
                list.add(line);
                System.out.println(line);
            }
            dataIn.close();
            System.out.println("\n");
        }
        catch(IOException e)
        {
            System.out.println("can't find the file");
        }
        return list;
    }

    /*
     * gets the rows from first string in the  ArrayList that stores the gameFile
     */
    static int getRowsFromList(ArrayList<String> list)
    {
        String firstline= list.get(0);		//get the first string; it contains the rows and cols

        char cRow= firstline.charAt(0);		//get char from string

        int row= Character.getNumericValue(cRow);	//convert to integer

        return row;
    }

    /*
     * gets the cols from first string in the ArrayList that stores the gameFile
     */
    static int getColsFromList(ArrayList<String> list)
    {
        String firstline= list.get(0);		//get the first string; it contains the rows, and cols

        char cCol= firstline.charAt(2);		//get char from string

        int col= Character.getNumericValue(cCol);	//convert to integer

        return col;
    }

}