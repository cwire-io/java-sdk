package main;

import api.CWireAPI;
import worker.WorkerFunctions;

public class CWire {
    private CWireAPI cwireAPI;
    private String accessToken;
    private CWireSocket socket;
    private static CWire instance = null;
    private CWireOptions options = new CWireOptions();

    private CWire(String accessToken) {
        this.accessToken = accessToken;
    };

    private CWire(String accessToken, CWireOptions options) {
        this.accessToken = accessToken;
        this.options = options;
    };

    public static CWire init(String accessToken) {
        if (CWire.instance == null) {
            CWire.instance = new CWire(accessToken);
        }

        CWire cwire = CWire.instance;
        WorkerFunctions.init(cwire);

        cwire.cwireAPI = new CWireAPI(cwire);
        cwire.socket = new CWireSocket(cwire);

        cwire.cwireAPI.init();
        cwire.socket.sync();

        return CWire.instance;
    }

    public static CWire init(String accessToken, CWireOptions options) {
        if (CWire.instance == null) {
            CWire.instance = new CWire(accessToken, options);
            CWire.init(accessToken);
        }

        return CWire.instance;
    }


    public static CWire getInstance() {
        return instance;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public CWireOptions getOptions() {
        return options;
    }

}
