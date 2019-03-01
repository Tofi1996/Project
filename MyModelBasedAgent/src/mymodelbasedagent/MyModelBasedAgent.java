/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymodelbasedagent;

import java.util.Scanner;
import java.util.Random;

public class MyModelBasedAgent {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rows, cols, dirt;
        System.out.println("Total rows: ");
        rows = input.nextInt();
        System.out.println("Total columns: ");
        cols = input.nextInt();
        System.out.println("Enter the number of Dirty cells: ");
        dirt = input.nextInt();

        Environment env = new Environment(rows, cols, dirt);

        int totalDirty = dirt;
        System.out.println("\nEnvironment before cleaning:");
        System.out.println(env.toString());

        Random rand = new Random();
        //Clean dirt
        boolean hitWall = false;
        while (totalDirty > 0) {
            while (!hitWall) {
                // Clean the dirty if its dirty
                totalDirty = cleanCell(env, totalDirty);
                if (totalDirty == 0) {
                    break;
                }
                int success = 2;
                // Move forward until we hit the wall.
                if (env.getRow() % 2 == 0) {
                    success = env.moveRight();
                    System.out.println("\nMove Right:");
                    System.out.println(env.toString());
                } else {
                    success = env.moveLeft();
                    System.out.println("\nMove Left:");
                    System.out.println(env.toString());
                }

                if (success == 1) {
                    hitWall = true;
                }
            }
            totalDirty = cleanCell(env, totalDirty);
            if (totalDirty == 0) {
                break;
            }
            // Move down if we've hit the wall to go to the next row
            // for cleanup
            env.moveDown();
            System.out.println("\nMove Down:");
            System.out.println(env.toString());
            hitWall = false;

        }

        System.out.println("\nEnvironment after cleaning:");
        System.out.println(env.toString());
        System.out.println("Score: " + env.getScore());
    }

    private static int cleanCell(Environment env, int totalDirty) {
        if (env.isDirty()) {
            env.suck();
            totalDirty--;
            System.out.println("\nClean:");
            System.out.println(env.toString());
        }
        return totalDirty;
    }

}
