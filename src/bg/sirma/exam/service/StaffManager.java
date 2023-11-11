package bg.sirma.exam.service;

import bg.sirma.exam.model.Employee;
import bg.sirma.exam.model.JuniorDeveloper;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;

public class StaffManager implements Manager {
    private final Service service;
    private final Scanner scanner = new Scanner(System.in);
    public StaffManager(Service service) {
        this.service = service;
    }

    @Override
    public void execute(String command) {
        String[] splitCommand = command.split("\\s+");
        String commandArgument = splitCommand[0];
        switch (commandArgument) {
            case "Add" -> {
                System.out.println("(id), (name), (department), (role), (salary)");
                String line = scanner.nextLine();
                String[] data = line.split("\\s*,\\s*");
                try {

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Must enter more parameters");
                }
                long id = Long.parseLong(data[0]);
                String name = data[1];
                String department = data[2];
                String role = data[3];
                BigDecimal salary = BigDecimal.valueOf(Double.parseDouble(data[4]));
                Employee employee = service.add(id, name, department, role, salary);
                if (employee != null) {
                    System.out.println(employee);
                }
            }
            case "Edit" -> {
                long id = 0;
                try {
                    id = Long.parseLong(splitCommand[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Must enter number!!!");
                }

                String line = scanner.nextLine();
                String[] data = line.split("\\s*,\\s*");
                String name = data[0];
                String department = data[1];
                String role = data[2];
                BigDecimal salary = BigDecimal.valueOf(Double.parseDouble(data[3]));
                Employee editEmployee = service.edit(id, name, department, role, salary);
                if (editEmployee != null) {
                    System.out.println(editEmployee);
                }
            }
            case "List" -> {
                Set<Employee> employees = service.getEmployees();
                employees.forEach(System.out::println);
            }
            case "Fire" -> {
                long id = 0;
                try {
                    id = Long.parseLong(splitCommand[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Must enter number!!!");
                }
                Employee deletedEmployee = service.delete(id);
                if (deletedEmployee != null) {
                    System.out.println(deletedEmployee);
                }
            }
            case "Search" -> {
                String variant = splitCommand[1];
                String searchParam = splitCommand[2];
                Set<Employee> employees = null;
                switch (variant) {
                    case "Department" -> {
                        employees = service.getByDepartment(searchParam);
                    }
                    case "Id" -> {
                        System.out.println(service.getById(Long.parseLong(searchParam)));
                    }
                    case "Name" -> {
                        employees = service.getByName(searchParam);
                    }
                }
                if (employees != null) {
                    employees.forEach(System.out::println);
                }
            }
            case "Save & Exit" -> {
                try {
                    service.saveToFile();
                } catch (IOException e) {
                    System.out.println("Could not save to file");
                }
            }
        }
    }
}
