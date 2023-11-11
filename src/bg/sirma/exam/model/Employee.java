package bg.sirma.exam.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Employee {
    @SerializedName(value = "Id")
    private long id;
    @SerializedName(value = "Name")
    private String name;
    @SerializedName(value = "StartDate")
    private LocalDate startDate;
    @SerializedName(value = "Department")
    private Department department;
    @SerializedName(value = "Salary")
    private BigDecimal salary;

    public Employee(long id, String name, LocalDate startDate, Department department, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.department = department;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
