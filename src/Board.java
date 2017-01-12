
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
    
    public Board(Piece[][][]p){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                for(int k=0;k<2;k++){
                    board[i][j][k]=p[i][j][k];
                }
            }
        }
    }
    public Board(){//t means if its ai, n is if ai is white or black        
        //for(int i=0;i<2;i++){
            board[0][0][0]=Piece.ROOK;
            board[0][7][0]=Piece.ROOK;
            board[0][1][0]=Piece.KNIGHT;
            board[0][6][0]=Piece.KNIGHT;
            board[0][2][0]=Piece.BISHOP;
            board[0][5][0]=Piece.BISHOP;
            for(int j=0;j<8;j++){
                board[1][j][0]=Piece.NEWPAWN;
            }
            board[7][0][1]=Piece.ROOK;
            board[7][7][1]=Piece.ROOK;
            board[7][1][1]=Piece.KNIGHT;
            board[7][6][1]=Piece.KNIGHT;
            board[7][2][1]=Piece.BISHOP;
            board[7][5][1]=Piece.BISHOP;
            for(int j=0;j<8;j++){
                board[6][j][1]=Piece.NEWPAWN;
            }
        //}
        board[0][3][0]=Piece.KING;
        board[0][3][1]=Piece.QUEEN;
        board[0][4][1]=Piece.KING;
        board[0][4][0]=Piece.QUEEN;
    }
     public int[][] findNumberPieces(){
        int[][] pieceArray = new int[6][2];
        for(int p = 0; p < 2; p++){
            for(int a = 0; a < 8; a++){
                for(int b = 0; b < 8; b++){
                    if(board[a][b][p] == Piece.PAWN){
                        pieceArray[0][p]++;
                    }else if(board[a][b][p] == Piece.KNIGHT){
                        pieceArray[1][p]++;
                    }else if(board[a][b][p] == Piece.BISHOP){
                        pieceArray[2][p]++;
                    }else if(board[a][b][p] == Piece.ROOK){
                        pieceArray[3][p]++;
                    }else if(board[a][b][p] == Piece.QUEEN){
                        pieceArray[4][p]++;
                    }else if(board[a][b][p] == Piece.KING){
                        pieceArray[5][p]++;
                    }
                }
            }
        }
        return pieceArray;
    }
    public int[][] cloneArray(int[][] array){
        int[][] returnArray = new int[array.length][array[0].length];
        
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                returnArray[i][j] = array[i][j];
            }
        }
        return returnArray;
    }
    public double findBoardValue(int p){
        //use number of pieces on the board to determine stage
        
        double combinationValue = 0;
        double totalPieceValues = 0;
        double totalValue = 0;
        
        int[][] pieceArray = cloneArray(findNumberPieces());
        
        if(pieceArray[5][0] == 0){
            return 1000000;
        }else if (pieceArray[5][1] == 0){
            return -1000000;
        }
        
        double value = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < pieceArray[i][j]; k++){
                    int side = j == 0? -1:+1;
                    int pieceValue = 0;
                    if(i == 0){
                        pieceValue = 1;                  
                    }else if(i == 1){
                        pieceValue = 3;
                    }else if(i == 2){
                        pieceValue = 3;
                    }else if(i == 3){
                        pieceValue = 5;
                    }else if(i == 4){
                        pieceValue = 9;
                    }
                    
                    totalPieceValues += pieceValue;
                    value += side * pieceValue;
                }
            }
        }
        
        return totalValue;
    }
    public boolean[][] analyzeBoard(int p,int a,int b){
        boolean[][]r=new boolean[8][8];
        boolean[][]temp=analyzeWholeBoard((p-1)*(p-1),a,b);
        if(board[a][b][p]==Piece.KING){
            temp=analyzeWholeBoard((p-1)*(p-1),-1,-1);
        }else{
            for(int m=0;m<8;m++){
                for(int n=0;n<8;n++){
                    if(temp[m][n]&&board[m][n][p]==Piece.KING){
                        return r;
                    }
                }
            }
        }
        
        switch(board[a][b][p]){
            case NEWPAWN:
                if(board[a+2-4*p][b][p]==null&&board[a+2-4*p][b][(p-1)*(p-1)]==null){
                    r[a+2-4*p][b]=true;
                }
            case PAWNINDANGER:
            case PAWN:
                if(board[a+1-2*p][b][p]==null&&board[a+1-2*p][b][(p-1)*(p-1)]==null){
                    r[a+1-2*p][b]=true;
                }
                if(b+1<8&&board[a+1-2*p][b+1][(p-1)*(p-1)]!=null){
                    r[a+1-2*p][b+1]=true;
                }
                if(b-1>=0&&board[a+1-2*p][b-1][(p-1)*(p-1)]!=null){
                    r[a+1-2*p][b-1]=true;
                }
                if(b-1>=0&&board[a][b-1][(p-1)*(p-1)]==Piece.PAWNINDANGER&&board[a+1-2*p][b-1][p]==null&&board[a+1-2*p][b-1][(p-1)*(p-1)]==null){
                    r[a+1-2*p][b-1]=true;
                }
                if(b+1<8&&board[a][b+1][(p-1)*(p-1)]==Piece.PAWNINDANGER&&board[a+1-2*p][b+1][p]==null&&board[a+1-2*p][b+1][(p-1)*(p-1)]==null){
                    r[a+1-2*p][b-1]=true;
                }
                break;
            case ROOK:
                int i,j,count=0;
                for(int k=1;count<2;k*=-1){
                    i=a;j=b;
                    while(i<8&&i>=0&&board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                        r[i][j]=true;
                        i+=k;
                    }
                    if(board[i][j][(p-1)*(p-1)]!=null){
                        r[i][j]=true;
                    }
                    i=a;j=b;
                    while(j<8&&j>=0&&board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                        r[i][j]=true;
                        j+=k;
                    }
                    if(board[i][j][(p-1)*(p-1)]!=null){
                        r[i][j]=true;
                    }
                    count++;
                }
                break;
            case KNIGHT:
                if(a+1<8&&b+2<8&&board[a+1][b+2][p]==null){
                    r[a+1][b+2]=true;
                }
                if(a+1<8&&b-2>=0&&board[a+1][b-2][p]==null){
                    r[a+1][b-2]=true;
                }
                if(a-1>=0&&b+2<8&&board[a-1][b+2][p]==null){
                    r[a-1][b+2]=true;
                }
                if(a-1>=0&&b-2>=0&&board[a-1][b-2][p]==null){
                    r[a-1][b-2]=true;
                }
                if(a+2<8&&b+1<8&&board[a+2][b+1][p]==null){
                    r[a+2][b+1]=true;
                }
                if(a+2<8&&b-1>=0&&board[a+2][b-1][p]==null){
                    r[a+2][b-1]=true;
                }
                if(a-2>=0&&b+1<8&&board[a-2][b+1][p]==null){
                    r[a-2][b+1]=true;
                }
                if(a-2>=0&&b-1>=0&&board[a-2][b-1][p]==null){
                    r[a-2][b-1]=true;
                }
                break;
            case BISHOP:
                count=0;
                for(int k=1;count<2;k*=-1){
                    i=a;j=b;
                    while(j<8&&j>=0&&i<8&&i>=0&&board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                        r[i][j]=true;
                        i+=k;
                        j+=k;
                    }
                    if(board[i][j][(p-1)*(p-1)]!=null){
                        r[i][j]=true;
                    }
                    
                    count++;
                }
                break;
            case QUEEN:
                count=0;
                for(int k=1;count<2;k*=-1){
                    i=a;j=b;
                    while(j<8&&j>=0&&i<8&&i>=0&&board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                        r[i][j]=true;
                        i+=k;
                        j+=k;
                    }
                    if(board[i][j][(p-1)*(p-1)]!=null){
                        r[i][j]=true;
                    }
                    i=a;j=b;
                    while(i<8&&i>=0&&board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                        r[i][j]=true;
                        i+=k;
                    }
                    if(board[i][j][(p-1)*(p-1)]!=null){
                        r[i][j]=true;
                    }
                    i=a;j=b;
                    while(j<8&&j>=0&&board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                        r[i][j]=true;
                        j+=k;
                    }
                    if(board[i][j][(p-1)*(p-1)]!=null){
                        r[i][j]=true;
                    }
                    count++;
                }
            case KING:
                if(a+1<8&&b+1<8&&board[a+1][b+1][p]==null){
                    r[a+1][b+1]=true;
                }
                if(b+1<8&&board[a][b+1][p]==null){
                    r[a][b+1]=true;
                }
                if(a-1>=0&&b+1<8&&board[a-1][b+1][p]==null){
                    r[a-1][b+1]=true;
                }
                if(a-1>=0&&board[a-1][b][p]==null){
                    r[a-1][b]=true;
                }
                if(a-1>=0&&b-1>=0&&board[a-1][b-1][p]==null){
                    r[a-1][b-1]=true;
                }
                if(b-1>=0&&board[a][b-1][p]==null){
                    r[a][b-1]=true;
                }
                if(a+1<8&&b-1>=0&&board[a+1][b-1][p]==null){
                    r[a+1][b-1]=true;
                }
                if(a+1<8&&board[a+1][b][p]==null){
                    r[a+1][b]=true;
                }
                for(int m=0;m<8;m++){
                    for(int n=0;n<8;n++){
                        if(temp[m][n]){
                            r[m][n]=false;
                        }
                    }
                }
            break;
        }
        return r;
    }
    public boolean[][] analyzeWholeBoard(int p,int x,int y){
        boolean[][]r=new boolean[8][8];
        Piece tempPiece=Piece.KING;
        if(x>0){
            tempPiece=board[x][y][(p-1)*(p-1)];
            board[x][y][(p-1)*(p-1)]=null;
        }
        for(int a=0;a<8;a++){
            for(int b=0;b<8;b++){
                switch(board[a][b][p]){
                    case NEWPAWN:
                        if(board[a+2-4*p][b][p]==null&&board[a+2-4*p][b][(p-1)*(p-1)]==null){
                            r[a+2-4*p][b]=true;
                        }
                    case PAWNINDANGER:
                    case PAWN:
                        if(board[a+1-2*p][b][p]==null&&board[a+1-2*p][b][(p-1)*(p-1)]==null){
                            r[a+1-2*p][b]=true;
                        }
                        if(b+1<8&&board[a+1-2*p][b+1][(p-1)*(p-1)]!=null){
                            r[a+1-2*p][b+1]=true;
                        }
                        if(b-1>=0&&board[a+1-2*p][b-1][(p-1)*(p-1)]!=null){
                            r[a+1-2*p][b-1]=true;
                        }
                        if(b-1>=0&&board[a][b-1][(p-1)*(p-1)]==Piece.PAWNINDANGER&&board[a+1-2*p][b-1][p]==null&&board[a+1-2*p][b-1][(p-1)*(p-1)]==null){
                            r[a+1-2*p][b-1]=true;
                        }
                        if(b+1<8&&board[a][b+1][(p-1)*(p-1)]==Piece.PAWNINDANGER&&board[a+1-2*p][b+1][p]==null&&board[a+1-2*p][b+1][(p-1)*(p-1)]==null){
                            r[a+1-2*p][b-1]=true;
                        }
                        break;
                    case ROOK:
                        int i,j,count=0;
                        for(int k=1;count<2;k*=-1){
                            i=a;j=b;
                            while(i<8&&i>=0&&board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                                r[i][j]=true;
                                i+=k;
                            }
                            if(board[i][j][(p-1)*(p-1)]!=null){
                                r[i][j]=true;
                            }
                            i=a;j=b;
                            while(j<8&&j>=0&&board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                                r[i][j]=true;
                                j+=k;
                            }
                            if(board[i][j][(p-1)*(p-1)]!=null){
                                r[i][j]=true;
                            }
                            count++;
                        }
                        break;
                    case KNIGHT:
                        if(a+1<8&&b+2<8&&board[a+1][b+2][p]==null){
                            r[a+1][b+2]=true;
                        }
                        if(a+1<8&&b-2>=0&&board[a+1][b-2][p]==null){
                            r[a+1][b-2]=true;
                        }
                        if(a-1>=0&&b+2<8&&board[a-1][b+2][p]==null){
                            r[a-1][b+2]=true;
                        }
                        if(a-1>=0&&b-2>=0&&board[a-1][b-2][p]==null){
                            r[a-1][b-2]=true;
                        }
                        if(a+2<8&&b+1<8&&board[a+2][b+1][p]==null){
                            r[a+2][b+1]=true;
                        }
                        if(a+2<8&&b-1>=0&&board[a+2][b-1][p]==null){
                            r[a+2][b-1]=true;
                        }
                        if(a-2>=0&&b+1<8&&board[a-2][b+1][p]==null){
                            r[a-2][b+1]=true;
                        }
                        if(a-2>=0&&b-1>=0&&board[a-2][b-1][p]==null){
                            r[a-2][b-1]=true;
                        }
                        break;
                    case BISHOP:
                        count=0;
                        for(int k=1;count<2;k*=-1){
                            i=a;j=b;
                            while(j<8&&j>=0&&i<8&&i>=0&&board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                                r[i][j]=true;
                                i+=k;
                                j+=k;
                            }
                            if(board[i][j][(p-1)*(p-1)]!=null){
                                r[i][j]=true;
                            }

                            count++;
                        }
                        break;
                    case QUEEN:
                        count=0;
                        for(int k=1;count<2;k*=-1){
                            i=a;j=b;
                            while(j<8&&j>=0&&i<8&&i>=0&&board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                                r[i][j]=true;
                                i+=k;
                                j+=k;
                            }
                            if(board[i][j][(p-1)*(p-1)]!=null){
                                r[i][j]=true;
                            }
                            i=a;j=b;
                            while(i<8&&i>=0&&board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                                r[i][j]=true;
                                i+=k;
                            }
                            if(board[i][j][(p-1)*(p-1)]!=null){
                                r[i][j]=true;
                            }
                            i=a;j=b;
                            while(j<8&&j>=0&&board[i][j][p]==null&&board[i][j][(p-1)*(p-1)]==null){
                                r[i][j]=true;
                                j+=k;
                            }
                            if(board[i][j][(p-1)*(p-1)]!=null){
                                r[i][j]=true;
                            }
                            count++;
                        }
                        break;
                    case KING:
                        if(a+1<8&&b+1<8&&board[a+1][b+1][p]==null){
                            r[a+1][b+1]=true;
                        }
                        if(b+1<8&&board[a][b+1][p]==null){
                            r[a][b+1]=true;
                        }
                        if(a-1>=0&&b+1<8&&board[a-1][b+1][p]==null){
                            r[a-1][b+1]=true;
                        }
                        if(a-1>=0&&board[a-1][b][p]==null){
                            r[a-1][b]=true;
                        }
                        if(a-1>=0&&b-1>=0&&board[a-1][b-1][p]==null){
                            r[a-1][b-1]=true;
                        }
                        if(b-1>=0&&board[a][b-1][p]==null){
                            r[a][b-1]=true;
                        }
                        if(a+1<8&&b-1>=0&&board[a+1][b-1][p]==null){
                            r[a+1][b-1]=true;
                        }
                        if(a+1<8&&board[a+1][b][p]==null){
                            r[a+1][b]=true;
                        }
                    break;
                    default:
                    break;
                }
            }
        }
        if(x>0){
            board[x][y][(p-1)*(p-1)]=tempPiece;
        }
        return r;
    }
    public Board clone(){
        Board b=new Board(board);
        return b;
    }
}
