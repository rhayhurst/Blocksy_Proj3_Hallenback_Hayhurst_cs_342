import javax.swing.*;


public class GuiButton extends JButton{


    private int row;
    private int col;
    private int height;
    private int width;
    private char position;
    private char movement;
    private int name;

    //constructor used for initalization of buttons before game pieces are placed
    GuiButton(int r, int c, int h, int w, char p, char m, int n){

        row = r;
        col = c;
        height = h;
        width = w;
        position = p;
        movement = m;
        name = n;

    }

    void setTheRow(int x){

        row = x;
    }

    void setTheCol(int x){

        col = x;
    }

    void setTheHeight(int x){

        height = x;
    }

    void setTheWidth(int x){

        width = x;
    }

    void setThePosition(char x){

        position = x;
    }

    void setTheMovement(char x){

        movement = x;
    }

    void setTheName(int x){

        name = x;
    }

    //begining of all the gets

    int getTheRow(){

        return row;
    }

    int getTheCol(){

        return col;
    }

    int getTheHeight(){

        return height;
    }

    int getTheWidth(){

        return width;
    }

    char getThePosition(){

        return position;
    }

    char getTheMovement(){

        return movement;
    }

    int getTheName(){

        return name;
    }





}