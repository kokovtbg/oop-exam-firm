package bg.sirma.exam.serializer;

import bg.sirma.exam.model.Employee;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDeserializer implements JsonDeserializer<Employee> {
    private String employeeTypeElementName;
    private Map<String, Class<? extends Employee>> employeeTypeRegistry;
    private Gson gson;

    public EmployeeDeserializer(String employeeTypeElementName) {
        this.employeeTypeElementName = employeeTypeElementName;
        this.gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer()).create();
        this.employeeTypeRegistry = new HashMap<>();
    }

    public EmployeeDeserializer registerEmployeeType(String employeeTypeName, Class<? extends Employee> employeeType) {
        employeeTypeRegistry.put(employeeTypeName, employeeType);
        return this;
    }

    @Override
    public Employee deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject employeeObject = jsonElement.getAsJsonObject();
        JsonElement employeeTypeElement = employeeObject.get(employeeTypeElementName);
        Class<? extends Employee> employeeType = employeeTypeRegistry.get(employeeTypeElement.getAsString());
        return gson.fromJson(employeeObject, employeeType);
    }

}
