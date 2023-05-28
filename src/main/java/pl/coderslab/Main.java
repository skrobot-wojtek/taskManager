package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        tabele = loadData(file); // wczytanie danych musi byc jako pierwsze, przed uzyciem ich w metodzie
        options();
        selectOption();


    }

    // variables
    // do wyjasnienie dlaczego scieżka ,,tasks.csv,, nie działa
    static final String file = "/home/ubuntu/Pulpit/codersLabRepo/javaprework/TaskManager/TaskManager/src/main/java/pl/coderslab/tasks.csv";
    static String[][] tabele;

    // options
    public static void options() {
        System.out.println("Welcome in Task Manager");
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Choose 1 of the 4 options and type it");
        System.out.print(ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");

    }

    // select option
    public static void selectOption() {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String wybór = scanner.nextLine();
            switch (wybór) {
                case "add":
                    System.out.println("Wybrałeś add - dodawanie zadania");
                    addTask();
                    break;
                case "remove":
                    System.out.println("remove");
                    //removeTask(tasks, getTheNumber());
                    break;
                case "list":
                    System.out.println("Wybrałeś list - wyswietlanie listy");
                    printArray(tabele);
                    break;
                case "exit":
                    System.out.println("Wybrałeś exit - koniec programu! Zapisuje zmiany w pliku");
                    saveData(file, tabele);
                    System.out.println(ConsoleColors.RED + "Do zobaczenia");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wybrałeś błędna opcję.");
            }
        }
    }


    public static String[][] loadData(String file) {
        // sprawdzammy czy dany plik o scieżce siedzącym w Stringu file istnieje
        Path path = Paths.get(file);
        if (Files.notExists(path))                                  // jesli taki plik nie istnieje to ->
        {
            System.out.println("Plik, który próbujesz wczytac nie istnieje. Koniec programu");
            System.exit(-1);
        }
        String[][] array1 = null;                                       // inicializuje tabelę w której będę zapisywał dane pobrane z pliku do którego scieżka siedzi w Stringu file
        try {
            List<String> strings = Files.readAllLines(path);           // tutaj tworzę obiekt który przyjmuje parametry typu String i przypisuje do niego linie z mojego pliku
            array1 = new String[strings.size()][strings.get(0).split(",").length]; // tworze tablice o wymiarach [wielkośc stringu] i [to nie do konca rozumiem ]
            // petla przypisująca do konkretnego indexu w tablicy konkretna literę
            for (int i = 0; i < strings.size(); i++) {
                String[] split = strings.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    array1[i][j] = split[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array1;
    }       // ta metoda  wczytuje dane z pliku do tabeli

    public static void printArray(String[][] array1) {              // bedzie to void ponieważ ta metoda nic nie zwraca tylko wypisuje
        for (int i = 0; i < array1.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < array1[i].length; j++) {
                System.out.print(array1[i][j] + " ");
            }
            System.out.println();
        }
    }   // ta metoda wypisuje na ekranie tabelę którą storzyła metoda loadData

    public static void addTask() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Dodaj Zadanie: ");
        String taskText = scan.nextLine();
        System.out.println("Dodaj datę do kiedy zadanie ma zostać wykonane:");
        String taskDate = scan.nextLine();
        System.out.println("Czy zawadnie jest ważne? Wprowadz true/false:");
        String taskPrior = scan.nextLine();
        tabele = Arrays.copyOf(tabele, tabele.length + 1);
        tabele[tabele.length - 1] = new String[3];
        tabele[tabele.length - 1][0] = taskText;
        tabele[tabele.length - 1][1] = taskDate;
        tabele[tabele.length - 1][2] = taskPrior;

    }                         // ta metoda dodaje Stringi  do tablicy ,,tabele,, - ( nie zapisuje do pliki to robi dopiero metoda powiazana z opcją exit )

    public static void saveData(String file, String[][] tabele) {
        Path path = Paths.get(file);  // wywołanie pliku z danymi

        String[] lines = new String[tabele.length]; // stworzenie nowej tabeli
        for (int i = 0; i < tabele.length; i++) {
            lines[i] = String.join(",", tabele[i]);
        }

        try {
            Files.write(Path.of(file), Arrays.asList(lines));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }    // ta metoda zapisuje aktualną tablice do pliku i jest wywoływana przez switcha exit
}