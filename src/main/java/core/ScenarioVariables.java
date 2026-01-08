//-> Is a container for test data
//-> accepts key value pair use pojo classes objects as value for more efficiency
//-> Ensures seamless data sharing across multiple step definitions specifically during parallel executions
package core;

import java.util.HashMap;
import java.util.Map;

public class ScenarioVariables {


    private static final Map<String, Object> variables = new HashMap<>();

    // Set a value
    public void set(String key, Object value) {
        variables.put(key, value);
    }

    // Get a value with type-safety
    public <T> T get(String key, Class<T> clazz) {
        Object value = variables.get(key);
        if (value == null) return null;
        if (!clazz.isInstance(value)) {
            throw new RuntimeException("Type mismatch for key: " + key + ". Expected: "
                    + clazz.getName() + ", found: " + value.getClass().getName());
        }
        return clazz.cast(value);
    }

    // Check if key exists
    public boolean contains(String key) {
        return variables.containsKey(key);
    }

    // Remove a key if needed
    public void remove(String key) {
        variables.remove(key);
    }

    // Clear all scenario variables
    public void clear() {
        variables.clear();
    }
}


