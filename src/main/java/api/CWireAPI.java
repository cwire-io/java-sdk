package api;

import kong.unirest.Unirest;
import main.CWire;

public class CWireAPI {
    private CWire cwire;
    private DataModelAPI dataModelAPI;

    public CWireAPI(CWire cwire) {
        this.cwire = cwire;
        Unirest.config().setDefaultHeader("x-access-token", this.cwire.getAccessToken());


        this.dataModelAPI = new DataModelAPI(cwire);
    }

    public void init() {
        this.dataModelAPI.init();
    }

    public DataModelAPI getDataModelAPI() {
        return dataModelAPI;
    }
}
