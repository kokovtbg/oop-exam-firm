package bg.sirma.exam.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SeniorDeveloper extends Employee {
    @SerializedName(value = "Role")
    private String role;
    public SeniorDeveloper(long id, String name, LocalDate startDate, Department department, BigDecimal salary) {
        super(id, name, startDate, department, salary);
        this.setRole(RoleEnum.SENIOR_DEVELOPER.getValue());
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
