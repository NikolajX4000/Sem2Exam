/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicLayer;

public class NoAccessException extends Exception  {

    public NoAccessException()
    {
        super("Adgang ikke tilladt, venligst log ind");
    }

}