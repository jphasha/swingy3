package com.wethinkcode.java.swingy;

import com.wethinkcode.java.swingy.controller.SwingyController;

public class Swingy {
    public static void main(String[] args) {
        if (args.length == 1) {
            validInput(args[0]);
        } else if (args.length > 1) {
            System.out.println("Too Many Arguments");
        } else {
            System.out.println("You Need One Of Either 'Console' Or 'Gui' As Your Argument");
        }
    }

    private static void validInput(String input) {
        try {
            SwingyController controller = new SwingyController();
            if (input.equalsIgnoreCase("console")) {
                controller.cliGamePlay();;
            } else if (input.equalsIgnoreCase("gui")) {
                controller.guiGamePlay();
            } else {
                System.out.println("You Need Either 'Console' Or 'Gui' As Your Argument");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
