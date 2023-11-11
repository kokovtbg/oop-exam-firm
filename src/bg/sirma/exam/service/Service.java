package bg.sirma.exam.service;

import bg.sirma.exam.model.Employee;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Set;

public interface Service {
    public abstract Employee add(long id, String name, String department, String role, BigDecimal salary);

    public abstract Employee edit(long id, String name, String department, String role, BigDecimal salary);
    public abstract Employee delete(long id);
    public abstract Employee getById(long id);
    public abstract Set<Employee> getAll() throws IOException;
    public abstract Set<Employee> getByDepartment(String department);
    public abstract Set<Employee> getByName(String name);
    public abstract void saveToFile() throws IOException;
    public abstract Set<Employee> getEmployees();

}
