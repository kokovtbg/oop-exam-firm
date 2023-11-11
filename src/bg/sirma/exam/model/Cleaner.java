package bg.sirma.exam.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cleaner extends Employee {
    @SerializedName(value = "Role")
    private String role;
    public Cleaner(long id, String name, LocalDate startDate, Department department, BigDecimal salary) {
        super(id, name, startDate, department, salary);
        this.setRole(RoleEnum.CLEANER.getValue());
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return super.toString() + "role=" + role;
    }
}
