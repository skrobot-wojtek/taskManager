package pl.coderslab;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        options();

    }

    static final String file = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static String[][] tasks;


    // options
    public static void options() {
        System.out.println("Welcome in Task Manager");
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Choose 1 of the 4 options and type it");
        System.out.println(ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");

    }
}

