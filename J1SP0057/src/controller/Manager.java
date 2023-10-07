package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import menu.MenuModel;
import menu.MenuView;

public class Manager {

    private MenuModel model;
    private MenuView view;

    public Manager(MenuModel model, MenuView view) {
        this.model = model;
        this.view = view;
    }

    //display menu
    public void startMenu() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            view.Menu();
            int choice = view.UserChoice();

            switch (choice) {
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    loginSystem();
                    break;

                case 3:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //create a new account
    public static  void createNewAccount() {
        //check file data exist or not
        if (!Validate.checkFileExist()) {
            return;
        }
        String username = Validate.checkInputUsername();
        String password = Validate.checkInputPassword();
        //check username exist or not
        if (!Validate.checkUsernameExist(username)) {
            System.err.println("Username exist.");
            return;
        }
        addAccountData(username, password);
    }

    //login system
    public static void loginSystem() {
        //check file data exist or not
        if (!Validate.checkFileExist()) {
            return;
        }
        String username = Validate.checkInputUsername();
        String password = Validate.checkInputPassword();
        //check username exist or not
        if (!Validate.checkUsernameExist(username)) {
            if (!password.equalsIgnoreCase(passwordByUsername(username))) {
                System.err.println("Password incorrect.");
            }
            System.err.println("Login success.");
        }
    }

    //write new account to data
    public static  void addAccountData(String username, String password) {
        File file = new File("C:\\Users\\Pc\\Documents\\NetBeansProjects\\J1SP0057\\src\\j1sp0057\\user.dat");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(username + ";" + password + "\n");
            fileWriter.close();
            System.err.println("Create successly.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //get password by username
    public static String passwordByUsername(String username) {
        File file = new File("C:\\Users\\Pc\\Documents\\NetBeansProjects\\J1SP0057\\src\\j1sp0057\\user.dat");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] account = line.split(";");
                if (username.equalsIgnoreCase(account[0])) {
                    return account[1];
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
