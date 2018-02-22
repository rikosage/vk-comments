package com.company;

import api.components.Comment;
import api.components.ConsoleWriter;
import api.components.Post;
import api.request.Group;
import api.request.Wall;
import api.request.WallComments;

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

        Wall wall = new Wall(group);

        System.out.println("Готовимся смеяться!\n\n\n");

        for (int i = 0; i < wall.getPosts().size(); i++) {
            Post post = (Post) wall.getPosts().get(i);
            WallComments wallComments = new WallComments(group, wall.getPosts().get(i));

            ConsoleWriter.writeDashline();

            System.out.println("Лайков у поста: " + post.getLikes());
            System.out.println(post.getText());
            if (post.hasAttachment()) {
                System.out.println(post.getAttachmentLink());
            }

            System.out.println("\nЛучшие комменты:\n");

            for (int j = 0; j < wallComments.getComments().size(); j++) {
                Comment comment = (Comment) wallComments.getComments().get(j);
                System.out.println("Лайков у комментария: " + comment.getLikes());
                System.out.println(comment.getText() + "\n");
            }
        }

    }

}