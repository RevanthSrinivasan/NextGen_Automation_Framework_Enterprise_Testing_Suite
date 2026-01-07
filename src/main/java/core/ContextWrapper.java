//-> Acts as a wrapper for step definition needs
package core;

public class ContextWrapper {
    private ScenarioVariables scenarioVariables = new ScenarioVariables();

    public ScenarioVariables getScenarioVariables() {
        return scenarioVariables;
    }
}
