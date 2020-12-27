package api;

import kong.unirest.Unirest;
import main.CWire;

public class DataModelAPI extends BaseAPI {
    public DataModelAPI(CWire cwire) {
        super(cwire, "/models");
    }

    public void init() {
        this.clearAllDataModels();
    }

    public void clearAllDataModels() {
        Unirest.post(this.getURL("/clear")).asJson();
    }
}
