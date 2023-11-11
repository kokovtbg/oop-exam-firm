package bg.sirma.exam.service;

import bg.sirma.exam.model.*;
import bg.sirma.exam.serializer.EmployeeDeserializer;
import bg.sirma.exam.serializer.LocalDateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static bg.sirma.exam.constants.Constants.basePath;
import static bg.sirma.exam.constants.Constants.employeePath;


public class StaffService implements Service {
    private Set<Employee> employees = new TreeSet<>(Comparator.comparingLong(Employee::getId));
    private static final EmployeeDeserializer deserializer = new EmployeeDeserializer("role")
            .registerEmployeeType("Junior Developer", JuniorDeveloper.class)
            .registerEmployeeType("Developer", RegularDeveloper.class)
            .registerEmployeeType("Senior Developer", SeniorDeveloper.class)
            .registerEmployeeType("Cleaner", Cleaner.class)
            .registerEmployeeType("Manufacturer", Manufacturer.class);

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .registerTypeAdapter(Employee.class, deserializer)
            .setPrettyPrinting()
            .create();

    public StaffService() throws IOException {
        this.employees.addAll(getAll());
    }

    @Override
    public Employee add(long id, String name, String department, String role, BigDecimal salary) {
        Employee employeeFromList = getById(id);
        if (employeeFromList != null) {
            System.out.printf("Employee with ID (%d) already exist%n", id);
            return null;
        }

        Employee employee = validateEmployee(id, name, department, role, salary, false);
        if (employee == null) return null;

        employees.add(employee);

        return employee;
    }

    @Override
    public Employee edit(long id, String name, String department, String role, BigDecimal salary) {
        Employee employeeFromList = getById(id);
        if (employeeFromList == null) {
            System.out.println("Employee with ID not exist");
            return null;
        }
        Employee employee = validateEmployee(id, name, department, role, salary, true);
        if (employee == null) {
            return null;
        }

        employees.add(employee);

        return employee;
    }

    @Override
    public Employee delete(long id) {
        Employee employee = getById(id);
        return employees.remove(employee) ? employee : null;
    }

    @Override
    public Employee getById(long id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Employee> getAll() throws IOException {
        Reader reader = Files.newBufferedReader(Path.of(basePath + employeePath));
        Employee[] employees = gson.fromJson(reader, Employee[].class);

        return Arrays.stream(employees).collect(Collectors.toSet());
    }

    @Override
    public Set<Employee> getByDepartment(String department) {
        return employees.stream()
                .filter(e -> e.getDepartment().getValue().equals(department))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Employee> getByName(String name) {
        return employees.stream()
                .filter(e -> e.getName().equals(name))
                .collect(Collectors.toSet());
    }

    @Override
    public void saveToFile() throws IOException {
        Writer writer = Files.newBufferedWriter(Path.of(basePath + employeePath));
        gson.toJson(employees, writer);
        writer.close();
    }

    @Override
    public Set<Employee> getEmployees() {
        return employees;
    }

    private Employee validateEmployee(long id, String name, String department, String role, BigDecimal salary, boolean isEdit) {
        if (name.isBlank()) {
            System.out.println("Name can not be blank");
            return null;
        }

        Department departmentEnum = Arrays.stream(Department.values())
                .filter(d -> d.getValue().equals(department))
                .findFirst()
                .orElse(null);
        if (departmentEnum == null) {
            System.out.printf("No such department. Valid departments are-%s%n",
                    Arrays.stream(Department.values())
                            .map(Department::getValue)
                            .collect(Collectors.joining(",")));
            return null;
        }

        if (salary.compareTo(BigDecimal.valueOf(0)) <= 0) {
            System.out.println("Salary must be positive");
            return null;
        }

        Employee employeeFromList = employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
        if (employeeFromList == null && isEdit) {
            System.out.println("No such Employee");
            return null;
        }

        Employee employee;
        if (isEdit) {
            delete(id);
        }

        if (role.contains("Junior") && role.contains("Developer")) {
            employee = new JuniorDeveloper(id, name, LocalDate.now(), departmentEnum, salary);
        } else if (role.contains("Senior") && role.contains("Developer")) {
            employee = new SeniorDeveloper(id, name, LocalDate.now(), departmentEnum, salary);
        } else if (role.contains("Developer")) {
            employee = new RegularDeveloper(id, name, LocalDate.now(), departmentEnum, salary);
        } else if (role.contains("Cleaner")) {
            employee = new Cleaner(id, name, LocalDate.now(), departmentEnum, salary);
        } else if (role.contains("Manufacturer")) {
            employee = new Manufacturer(id, name, LocalDate.now(), departmentEnum, salary);
        } else {
            System.out.println("No such role");
            return null;
        }

        return employee;
    }
}
