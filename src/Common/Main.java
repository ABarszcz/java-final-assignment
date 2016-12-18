/*
 * Assignment 2 - Part 2
 */
package Common;

import GUI.*;


/**
 * Driver class for the assignment
 * @author Anthony
 */
public class Main {
    public static void main(String[] args) {
	try {
        // debug on
        Utils.setDebugMode(true);
	new Login();
	} catch(Exception ioEx) {
	    Utils.logError(ioEx);
	}
    } //end of main
} //end of class