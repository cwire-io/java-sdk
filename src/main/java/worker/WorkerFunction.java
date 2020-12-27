package worker;

import main.CWire;

public abstract class WorkerFunction<TParameters> {
    protected CWire cwire;

    public WorkerFunction(CWire cwire) {
        this.cwire = cwire;
    }

    public abstract String getName();
    public abstract WorkerAPIFunctionParameters getWorkerAPIFunctionParameters();
    public abstract WorkerFunctionResponse controller(TParameters parameters);
}
