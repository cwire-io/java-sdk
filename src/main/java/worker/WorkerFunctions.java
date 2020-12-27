package worker;

import main.CWire;
import worker.functions.FindAll;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class WorkerFunctions {
    private CWire cwire;
    private static WorkerFunctions instance;
    private HashMap<String, WorkerFunction> functions = new HashMap<>();

    private WorkerFunctions(CWire cwire) {
        this.cwire = cwire;
    }

    public static WorkerFunctions init(CWire cwire) {
        if (instance == null) {
            instance = new WorkerFunctions(cwire);

            try {
                instance.addFunction(FindAll.class);
            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
            // Init worker Functions
        } {
            return instance;
        }
    }

    public static WorkerFunctions getInstance() {
        return instance;
    }

    public void addFunction(Class<? extends WorkerFunction> function) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class[] cArg = new Class[1];
        cArg[0] = CWire.class;
        WorkerFunction fn = function.getDeclaredConstructor(cArg).newInstance(this.cwire);
        functions.put(fn.getName(), fn);
    }

    public boolean isFunctionExisting(String fnName) {
        return this.functions.containsKey(fnName);
    }

    public WorkerFunction getFunction(String fnName){
        return this.functions.get(fnName);
    }

    public WorkerFunction[] getFunctionList() {
        return (WorkerFunction[]) this.functions.values().toArray();
    }

    public void removeFunction(String fnName) {
        functions.remove(fnName);
    }
}
