/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SyBye8898
 */
public class Game {
    
    Board b ;
    Board tempBoard;
    private Player player1,player2;
    public Game(boolean t,int n){
        b= new Board(t,n);
                tempBoard= new Board(t,n);
    }
    public boolean[][][]validMove=new boolean[16][8][8];
    public void Move(int pNum,int n,int x,int y){//n is piece number
        b.analyzeBoard(pNum);
        if(validMove[n][x][y]){
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(b.board[pNum][i][j]==n){
                        b.board[pNum][i][j]=0;
                    }
                }
            }
            board[pNum][x][y]=n;
        }
    }
}
