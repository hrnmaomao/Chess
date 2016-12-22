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
    
    Board board = new Board();
    Board TempBoard = new Board();
    private Player player1,player2;
    
    public void Move(Player p,int n,int x,int y){//n is piece number
        board.analyzeBoard(p);
        if(p.validMove[n][x][y]){
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(board[p.playerNum][i][j]==n){
                        board[p.playerNum][i][j]=0;
                    }
                }
            }
            board[p.playerNum][x][y]=n;
        }
    }
}
