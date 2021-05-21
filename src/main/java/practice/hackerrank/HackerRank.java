package practice.hackerrank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by adarsh.sharma on 13/11/17.
 */
public class HackerRank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("03 10103538 2222 1233 6160 0142");
        while(scanner.hasNext())
        {
            System.out.println(scanner.next());
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("xanadu.txt"));
            bufferedReader.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
