package crazytrain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.runtime.rule.Activation;
import org.drools.runtime.rule.AgendaFilter;

public class ComputerAgendaFilter implements AgendaFilter {
    private Map<String, String> executedRules = new HashMap<String, String>();

    public boolean accept(Activation activation) {
        List<Object> list = activation.getObjects();
        
        //Use rule name and computer model name as the key name in the Hashmap.
        String ruleName = activation.getRule().getName() + ((Computer) list.get(0)).getModelName();
        
        if (this.executedRules.containsKey(ruleName)) {
            return false;
        } else {
            this.executedRules.put(ruleName, ruleName);
            return true;
        }
    }
}