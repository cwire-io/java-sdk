package worker.functions;

import main.CWire;
import worker.WorkerFunction;
import worker.WorkerFunctionResponse;
import worker.WorkerAPIFunctionParameters;

public class FindAll extends WorkerFunction {
    public FindAll(CWire cwire) {
        super(cwire);
    }

    @Override
    public String getName() {
        return "DATA_MODEL::FIND_ALL";
    }

    @Override
    public WorkerAPIFunctionParameters getWorkerAPIFunctionParameters() {
        return null;
    }

    @Override
    public WorkerFunctionResponse controller(Object o) {
        System.out.println("Call find all");

        return null;
    }
}
