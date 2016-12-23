/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SyBye8898
 */
public class Board {
    
    public int[][][] board = new int[8][8][2];
    
    public Board(boolean t,int n){//t means if its ai, n is if ai is white or black        
        for(int i=0;i<2;i++){
            for(int j=0;j<4;j++){
                board[0][0][i]=Piece.ROOK;
                board[0][7-j][i]=j-1;
                if(i==1&&j==3){
                    board[0][j][i]*=-1;
                    board[0][7-j][i]*=-1;
                    }
            }
        }
    }
    
    public int findBoardValue(Player player){
        int value;
        return value;
    }
    
    
}
