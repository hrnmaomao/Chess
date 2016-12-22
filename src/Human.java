/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SyBye8898
 */
public class Human extends Player{
    
    //@Override
    public void move(int n,int x,int y,Game g){
        g.Move(p, n, x, y);
    }
    
}
