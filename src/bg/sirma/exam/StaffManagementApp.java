package bg.sirma.exam;

import bg.sirma.exam.service.Manager;
import bg.sirma.exam.service.Service;
import bg.sirma.exam.service.StaffManager;
import bg.sirma.exam.service.StaffService;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static bg.sirma.exam.constants.Constants.basePath;
import static bg.sirma.exam.constants.Constants.employeePath;

public class StaffManagementApp {
    public static void main(String[] args) {
        // implement fileReader/fileWriter to handle saving into csv/json
        try {
            Service service = new StaffService();
            Manager manager = new StaffManager(service);
            System.out.println("Welcome to Staff Management System");
            displayCommands();
            boolean isRunning = true;
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            while (isRunning) {
                manager.execute(command);
                if (command.equals("Save & Exit")) {
                    break;
                }
                command = scanner.nextLine();
                // Add Employee
                // 1, Peter Peterson, IT, Junior Java Developer, 1400.50
                // Add Employee
                // 2, Ivan Ivanson, IT, Junior Front-End Developer, 1400.00
                // Edit 1
                // 1, Peter Peterson, IT, Java Developer, 2500.00
                // List employees
                // Search Department Marketing
                // Search Id 1
                // Fire 1
                // Search Name Peter
                // Save & Exit
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with Service");
        }

    }

    private static void displayCommands() {

    }
}
