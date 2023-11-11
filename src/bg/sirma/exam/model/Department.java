package bg.sirma.exam.model;

public enum Department {
    IT("IT"),
    CLEANING("Cleaning"),
    MANUFACTURING("Manufacturing");

    private String value;

    Department(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
