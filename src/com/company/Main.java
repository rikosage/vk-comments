package com.company;

import api.request.Group;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] arguments) throws IOException {

        System.out.print("Hi there! Please, input group domain for continue: ");
        Scanner reader = new Scanner(System.in);

        Group group = new Group(reader.next());

        if (group.hasError()) {
            System.out.println(group.getError().getMessage());
            return;
        }

        System.out.println(String.format("Success!\nGroup id: %s\nGroup name: %s", group.getGid(), group.getName()));


    }

}