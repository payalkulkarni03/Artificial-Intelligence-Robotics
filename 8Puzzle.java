Problem Statement: Solve 8-puzzle problem using A* algorithm. Assume any initial
configuration and define goal configuration clearly.
Programs:
Board.java
package eightpuzzle;
import java.util.Scanner;
import javax.swing.JOptionPane;
//board class for eight puzzle matrix
public class Board {
private String board[][];
private int blankX,blankY; // co-ordinates for blank tile
public Board()
{
this.board = new String[3][3];
}
public Board(Board b) //constructor to initialise Board
{
this.board = b.board;
this.blankX = b.blankX;
this.blankY = b.blankY;
}
public void initBoard() //initialize the board
{
Scanner inp = new Scanner(System.in);
System.out.println("\nEnter one tile as '-' ie. Blank tile\n");
for(int i=0; i<3; i++)
{
for(int j=0; j<3; j++)
{
board[i][j] = JOptionPane.showInputDialog("Enter the value of tile ["+i+"]["+(j)+"] : ");
if(board[i][j].equals("-")) //store the location of blank symbol
{
blankX=i;
blankY=j;
}
}
}
}
Problem Statement: Solve 8-puzzle problem using A* algorithm. Assume any initial
configuration and define goal configuration clearly.
Programs:
Board.java
package eightpuzzle;
import java.util.Scanner;
import javax.swing.JOptionPane;
//board class for eight puzzle matrix
public class Board {
private String board[][];
private int blankX,blankY; // co-ordinates for blank tile
public Board()
{
this.board = new String[3][3];
}
public Board(Board b) //constructor to initialise Board
{
this.board = b.board;
this.blankX = b.blankX;
this.blankY = b.blankY;
}
public void initBoard() //initialize the board
{
Scanner inp = new Scanner(System.in);
System.out.println("\nEnter one tile as '-' ie. Blank tile\n");
for(int i=0; i<3; i++)
{
for(int j=0; j<3; j++)
{
board[i][j] = JOptionPane.showInputDialog("Enter the value of tile ["+i+"]["+(j)+"] : ");
if(board[i][j].equals("-")) //store the location of blank symbol
{
blankX=i;
blankY=j;
}
}
}
}
public Board nextMove(int gn, Board goal) //method to check possible moves
and select optimum
{
Board temp = new Board();
Board next = new Board();
int minFn = 999;
System.out.println("\nPossible moves are : ");
if(blankY>0) // Condition for possible left move
{
temp.setBoard(board);
temp.swap(blankX, blankY, blankX, blankY-1); // Swap blank tile
int fn = (temp.getHn(goal)+gn); // Calculate fn = hn + gn
System.out.println("\nFor Fn = "+fn+" : ");
temp.display();
if(fn < minFn) // Check for minimum fn and set the next board
accordingly
{
minFn = fn;
next.setBoard(temp.board);
next.setBlankX(blankX);
next.setBlankY(blankY-1);
}
}
if(blankY<2) // Condition for possible right move
{
temp.setBoard(board);
temp.swap(blankX, blankY, blankX, blankY+1);
int fn = (temp.getHn(goal)+gn);
System.out.println("\nFor Fn = "+fn+" : ");
temp.display();
if(fn < minFn)
{
minFn = fn;
next.setBoard(temp.board);
next.setBlankX(blankX);
next.setBlankY(blankY+1);
}
}
if(blankX>0) // Condition for possible up move
{
temp.setBoard(board);
temp.swap(blankX, blankY, blankX-1, blankY);
int fn = (temp.getHn(goal)+gn);
System.out.println("\nFor Fn = "+fn+" : ");
temp.display();
if(fn < minFn)
{
minFn = fn;
next.setBoard(temp.board);
next.setBlankX(blankX-1);
public Board nextMove(int gn, Board goal) //method to check possible moves
and select optimum
{
Board temp = new Board();
Board next = new Board();
int minFn = 999;
System.out.println("\nPossible moves are : ");
if(blankY>0) // Condition for possible left move
{
temp.setBoard(board);
temp.swap(blankX, blankY, blankX, blankY-1); // Swap blank tile
int fn = (temp.getHn(goal)+gn); // Calculate fn = hn + gn
System.out.println("\nFor Fn = "+fn+" : ");
temp.display();
if(fn < minFn) // Check for minimum fn and set the next board
accordingly
{
minFn = fn;
next.setBoard(temp.board);
next.setBlankX(blankX);
next.setBlankY(blankY-1);
}
}
if(blankY<2) // Condition for possible right move
{
temp.setBoard(board);
temp.swap(blankX, blankY, blankX, blankY+1);
int fn = (temp.getHn(goal)+gn);
System.out.println("\nFor Fn = "+fn+" : ");
temp.display();
if(fn < minFn)
{
minFn = fn;
next.setBoard(temp.board);
next.setBlankX(blankX);
next.setBlankY(blankY+1);
}
}
if(blankX>0) // Condition for possible up move
{
temp.setBoard(board);
temp.swap(blankX, blankY, blankX-1, blankY);
int fn = (temp.getHn(goal)+gn);
System.out.println("\nFor Fn = "+fn+" : ");
temp.display();
if(fn < minFn)
{
minFn = fn;
next.setBoard(temp.board);
next.setBlankX(blankX-1);
for(int i=0; i<3; i++)
{
for(int j=0; j<3; j++)
{
if(!this.board[i][j].equals(goal.board[i][j]))
{
hn++;
}
}
}
return hn;
}
}


EightPuzzle.java
package eightpuzzle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
public class EightPuzzle {
/**
* @param args the command line arguments
*/
private int gn=0; // Initialize gn ie. no. of moves to 0
private Board start;
private Board goal;
public void initStart() //Accept and display start board
{
System.out.println("\n\n Enter start Board : ");
start=new Board();
start.initBoard();
System.out.println("\n\nThe given start board is : ");
start.display();
}
public void initGoal() //Accept and display goal board
{
System.out.println("\n\n Enter goal Board : ");
goal=new Board();
goal.initBoard();
System.out.println("\n\nThe given goal board is : ");
goal.display();
}
public void solve() // Solve puzzle using A* algorithm
{
Board cur = start;
while(true)
{
System.out.println("\n\nBoard after "+gn+" moves : ");
cur.display();
if(cur.equals(goal)) //Check if goal is achieved nad return
{
System.out.println("\nGoal state achieved.");
return;
}
gn++; // Increment gn as per moves
cur = cur.nextMove(gn, goal); // get the board after next move
}
}
public static void main(String[] args) {
// TODO code application logic here
EightPuzzle ep = new EightPuzzle(); // Instantiate and solve the puzzle
ep.initStart();
ep.initGoal();
System.out.println("\n\nThe board is solved as : \n");
ep.solve();
}
}
