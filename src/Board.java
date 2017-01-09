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
    
    public Piece[][][] board = new Piece[8][8][2];
    
    public Board(boolean t,int n){//t means if its ai, n is if ai is white or black        
        for(int i=0;i<2;i++){
            board[0][0][i]=Piece.ROOK;
            board[0][7][i]=Piece.ROOK;
            board[0][1][i]=Piece.KNIGHT;
            board[0][6][i]=Piece.KNIGHT;
            board[0][2][i]=Piece.BISHOP;
            board[0][5][i]=Piece.BISHOP;
            for(int j=0;j<8;j++){
                board[1][j][i]=Piece.PAWN;
            }
        }
        board[0][3][0]=Piece.KING;
        board[0][3][1]=Piece.QUEEN;
        board[0][4][1]=Piece.KING;
        board[0][4][0]=Piece.QUEEN;
    }
    public boolean[][] analyzeBoard(int p,int a,int b){
        boolean[][]r=new boolean[8][8];
        switch(board[a][b][p]){
            case Piece.PAWN://GOTTA FIX THAT WEIRD RULE FOR PAWN
                if(board[a+1][b][p]==null&&board[a+1][b][(p-1)*(p-1)]==null){
                    r[a+1][b]=true;
                }
                if(board[a+1][b+1][(p-1)*(p-1)]!=null){
                    r[a+1][b+1]=true;
                }
                if(board[a+1][b-1][(p-1)*(p-1)]!=null){
                    r[a+1][b-1]=true;
                }
                break;
            case Piece.ROOK:
                int i,j,count=0;
                for(int k=1;count<2;k*=-1){
                    i=a;j=b;
                    while(board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                        r[i+k][j]=true;
                        i+=k;
                    }
                    if(board[i][j][(p-1)*(p-1)]!=null){
                        r[i][j]=true;
                    }
                    i=a;j=b;
                    while(board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                        r[i][j+k]=true;
                        j+=k;
                    }
                    if(board[i][j][(p-1)*(p-1)]!=null){
                        r[i][j]=true;
                    }
                    count++;
                }
        }
    }
    
    public double findBoardValue(int stage){
        
        double value = 0;
        double combinationValue = 0;
        double pieceValues = 0;
        
        boolean whiteKing = false;
        boolean blackKing = false;
        
        for(int p = 0; p < 2; p++){
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(board[i][j][p] != null){
                        if(board[i][j][p] == Piece.KING){
                            if(p == 0){
                                whiteKing = true;
                            }else{
                                blackKing = true;
                            }
                        }else{
                            pieceValues += findPieceValue(board[i][j][p]);
                        }
                    }
                }
            }
        }
        if(!whiteKing){
            pieceValues = -1000000;
        }else if(!blackKing){
            pieceValues = +1000000;
        }
        
        if(stage == 0){//begin game
        //B, R, Q better in open positions, worse in closed
        //N better in closed, bad on corners and edges    
            //less pieces / edges surrounding B, R, Q, value ^
            //opp for N except only w/ pieces
            
            //a check for if surrounding pieces exist, if they are friendly / not
        }else if(stage == 1){//midgame
            //
        }else if(stage == 2){//endgame
            
        }
        
        
        return value;
        }
    
    public int surroundingPieces(int a, int b, int p, Piece piece){
        
        boolean edge;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if((a+i < 0)||(a+i >= 8)||(b+i < 0)||(b+i >= 8)){
                    edge = true;
                }
            }
        }
        
    }
    
    public double findPieceValue(Piece piece, int stage){
        double value = 0;
        if(stage == 0){
            if(piece == Piece.PAWN){
                value = 1;
            }else if(piece == Piece.KNIGHT){
                value = 3;
            }else if(piece == Piece.BISHOP){
                value = 3;
            }else if(piece == Piece.ROOK){
                value = 5;
            }else if(piece == Piece.QUEEN){
                value = 9;
            }
        }else if(stage == 1){
            if(piece == Piece.PAWN){
                value = 1;
            }else if(piece == Piece.KNIGHT){
                value = 3.5;
            }else if(piece == Piece.BISHOP){
                value = 3.5;
            }else if(piece == Piece.ROOK){
                value = 5.25;
            }else if(piece == Piece.QUEEN){
                value = 10;
            }
        }else if(stage == 2){//endgame idk
            if(piece == Piece.PAWN){
                value = 1.5;
            }else if(piece == Piece.KNIGHT){
                value = 2.5;
            }else if(piece == Piece.BISHOP){
                value = 3.5;
            }else if(piece == Piece.ROOK){
                value = 6;
            }else if(piece == Piece.QUEEN){
                value = 8.5;
            }
        }
        
        return value;
    }
    
}
    
        /*Hans Berliner:: P = 1, N = 3.2, B = 3.33, R = 5.1, Q = 8.8
        B, R, Q better in open positions, worse in closed
        N better in closed, bad on corners and edges
        doubled pawns
        
        endgame: P, R ^^, B^
        N down, Q slightly down
        
        mid : Q == RR 
        end : RR > Q
        
        mid : RPP < BB||BN|==NN
        end
        */

