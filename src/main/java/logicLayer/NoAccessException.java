/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logicLayer;

/**
 * Used when a user is trying to access something they are not allowed to. Extends exception and says "Access not allowed"
 * @author Hupra Laptop
 */
public class NoAccessException extends Exception  {

    /**
     * Sets super to friendly msg, saying access not allowed
     */
    public NoAccessException()
    {
        super("Adgang ikke tilladt, venligst log ind");
    }

}