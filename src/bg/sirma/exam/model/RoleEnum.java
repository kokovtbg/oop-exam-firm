package bg.sirma.exam.model;

public enum RoleEnum {
    JUNIOR_DEVELOPER("Junior Developer"),
    REGULAR_DEVELOPER("Developer"),
    SENIOR_DEVELOPER("Senior Developer"),
    MANUFACTURER("Manufacturer"),
    CLEANER("Cleaner");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
