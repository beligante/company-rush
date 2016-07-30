package company;

import java.util.ArrayList;
import java.util.HashMap;

class CompanyVariablesContainers {

    private ArrayList<CompanyVariable> variables;
    private HashMap<String, CompanyVariable> variablesMap;

    CompanyVariablesContainers() {
        variables = new ArrayList<>();
        variablesMap = new HashMap<>();
    }

    CompanyVariablesContainers(CompanyVariablesContainers companyVariablesContainers) {
        this();
        ArrayList<CompanyVariable> vars = companyVariablesContainers.variables;
        for (int i = 0; i < vars.size(); ++i) {
            CompanyVariable companyVar = vars.get(i);
            add(companyVar.clone());
        }
    }

    final void add(CompanyVariable variable) {
        variables.add(variable);
        variablesMap.put(variable.getName(), variable);
    }

    ArrayList<CompanyVariable> getVariables() {
        return variables;
    }

    CompanyVariable getVariableByName(String name) {
        if (variablesMap.containsKey(name)) {
            return variablesMap.get(name);
        }
        return null;
    }

    @Override
    public String toString() {
        
        StringBuffer sb = new StringBuffer();
        
        for(int i = 0; i < variables.size(); i++){
        	sb.append(variables.get(i).getName()).append(":").append(variables.get(i).getValue()).append(", ");
        }
        
        return "CompanyVariablesContainers{" + "variables=" + sb.toString() + '}';
    }
    
    
}
