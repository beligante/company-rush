package environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Environment {

    final private AdvertisingAcceptance advertisingAcceptance;
    final private EconimicSituation econimicSituation;

    public AdvertisingAcceptance getAdvertisingAcceptance() {
        return advertisingAcceptance;
    }

    public EconimicSituation getEconimicSituation() {
        return econimicSituation;
    }
    final private Map<Integer, Integer> regiaoClienteMap = new HashMap<>();
    final ArrayList<EnvironmentVariable> variables = new ArrayList<>();
    
    public Environment() {
        advertisingAcceptance = new AdvertisingAcceptance();
        econimicSituation = new EconimicSituation();
        
        variables.add(advertisingAcceptance);
        variables.add(econimicSituation);
    }

    public void randomVariables() {
        Random rand;
        for (int i = 0; i < variables.size(); ++i) {
            EnvironmentVariable variable = variables.get(i);
            rand = new Random(System.currentTimeMillis());
            int randInt = rand.nextInt(variable.getSize());
            variable.setValueByInt(randInt);
        }
        
        for(int i = 0; i < Config.QTDE_REGION; i++){
            regiaoClienteMap.put(i, getRandNumberOfClients());
        }
    }
    
    public Map<Integer, Integer> getRegiaoClienteMap(){
        return regiaoClienteMap;
    }
    
    private static int getRandNumberOfClients(){
        return Config.MIN_CLIENTES + (int)(Math.random() * (Config.MAX_CLIENTES - Config.MIN_CLIENTES));
    }

}
