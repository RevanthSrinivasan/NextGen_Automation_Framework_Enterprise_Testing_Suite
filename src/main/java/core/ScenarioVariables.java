//-> Is a container for test data
//-> accepts key value pair use pojo classes objects as value for more efficiency
//-> Ensures seamless data sharing across multiple step definitions specifically during parallel executions
package core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ScenarioVariables {
    private Map<String, Object> variables = new HashMap<>();
    public void set(String key,Object value)
    {
        variables.put(key,value);
    }
    public Object get(String key)
    {
        return variables.get(key);
    }
    public boolean contains(String key)
    {
        return variables.containsKey(key);
    }

}
