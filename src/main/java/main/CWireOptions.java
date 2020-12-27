package main;

public class CWireOptions {
    private String socketPath = "/workers/sync";
    private String apiURL = "http://api.cwire.io";

    public void setApiURL(String apiURL) {
        this.apiURL = apiURL;
    }

    public String getApiURL() {
        return apiURL;
    }

    public String getSocketPath() {
        return socketPath;
    }

    public void setSocketPath(String socketPath) {
        this.socketPath = socketPath;
    }
}
