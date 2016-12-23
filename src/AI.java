
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SyBye8898
 */
public class AI extends Player{
    public ArrayList AI(Board b,int n,Game g){
        if(n==0){
            ArrayList<Integer>tree=new ArrayList<>();
            tree.add(b.findBoardValue(playerNum));
        }else{
            ArrayList<ArrayList>tree=new ArrayList<>();
            for(int i=-4;i<=4;i++){
                b.analyzeBoard();
                for(int j=0;j<8;j++){
                    for(int k=0;k<8;k++){
                        if(validMove[i][j][k]){
                            tempMove(i,j,k);
                            tree.add(AI(b,n-1,g));
                        }
                    }
                }
            }
        }
    }
    public void move(Board b,int n,Game g){
        ArrayList<ArrayList>a=AI(b,n,g);
        int[] max=new int[a.size()];
        int min=9999,index=0,num=0;
        for(int i=0;i<a.size();i++){
            //...
            if(a.get(i).get(i1)...>max[i]){
                //max[i]=...;
            }
        }
        for(int i=0;i<a.size();i++){
                if(max[i]<min){
                    min=max[i];
                    index=i;
                }
        }
        for(int i=-4;i<=4;i++){
            b.analyzeBoard();
            for(int j=0;j<8;j++){
                for(int k=0;k<8;k++){
                    if(validMove[i][j][k]){
                        num++;
                    }
                    if(num==index){
                        move(i,j,k);
                        break;
                    }
                }
            }
        }
    }
}
