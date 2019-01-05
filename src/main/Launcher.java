package main;

import javafx.application.Application;

/**
 * This class represent a launcher of the GUI application.It displays the UI.
 *
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 * @author Verena Girgis
 * @version 3/12/2018
 *
 */
public class Launcher {

    /**
     * This is the main method responsible for showing the User Interface. This
     * does so by "Launching" the UserInterface class.
     *
     * @param args args are the command line
     */
    public static void main(String[] args) {
        Application.launch(UserInterface.class);
    }

}
