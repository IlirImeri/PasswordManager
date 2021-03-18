package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String UserName = "HelloWorld";
        String password = "630#@1_d";
        String email = "sde@example.com";
        String website = "www.google.com";
        String website2 = "google";

        Database database = new Database();
        //database.connect();
        database.createTable();
        // ! database.insertData(UserName,password,email,website);
        database.selectData(website2);

        /*Database database = new Database();
        Scanner input = new Scanner(System.in);
        String username, password, email, website;

        System.out.println("Valori da inserire nel database:");

        System.out.println("Username:");
        username = input.nextLine();
        System.out.println("Password:");
        password = input.nextLine();
        System.out.println("Email:");
        email = input.nextLine();
        System.out.println("Website:");
        website = input.nextLine();

        database.connect();
        database.createTable();
        database.insertData(username,password,email,website);
        System.out.println("Che password vuoi cercare");
        database.selectData();*/
    }
}
