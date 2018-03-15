/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

/**
 *
 * @author touir
 */
public class DatabaseEntity {
    
    protected DatabaseEntity(){};
    
    protected void test(){
        Class<?> enclosingClass = getClass().getEnclosingClass();
        if (enclosingClass != null) {
          System.out.println(enclosingClass.getSimpleName());
        } else {
          System.out.println(getClass().getSimpleName());
        }
    }
}
