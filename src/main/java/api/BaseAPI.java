package api;

import kong.unirest.Unirest;
import main.CWire;
import okhttp3.OkHttpClient;

public class BaseAPI {
    protected CWire cwire;
    protected String basePath = "";
    protected OkHttpClient client = new OkHttpClient();

    public BaseAPI(CWire cwire, String basePath) {
        this.cwire = cwire;
        this.basePath = basePath;
    }

    public String getURL(String url) {
        return this.cwire.getOptions().getApiURL() + this.basePath + url;
    }
}
