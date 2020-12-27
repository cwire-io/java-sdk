package main;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.Transport;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import worker.WorkerAPIFunctionParameters;
import worker.WorkerFunction;
import worker.WorkerFunctions;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CWireSocket {
    private CWire cwire;

    public CWireSocket(CWire cwire) {
        this.cwire = cwire;
    }

    protected void onWorkerFunctionCalled(
            String functionName,
            WorkerAPIFunctionParameters[] params) {
        System.out.println("CALL");
    };

    public void sync() {
        try {
            IO.Options options = new IO.Options();
            options.path = this.cwire.getOptions().getSocketPath();
            Socket socket = IO.socket(this.cwire.getOptions().getApiURL(), options);

            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("CONNECT");
                }
            });
            socket.on("CALL_WORKER_FUNCTION_ACTION", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Ack ack = (Ack) args[args.length - 1];

                    try {
                        String fnName = (String) args[0];
                        JSONArray parameters = (JSONArray) args[1];

                        WorkerFunction workerFunction = WorkerFunctions.getInstance().getFunction(fnName);
                        if (!WorkerFunctions.getInstance().isFunctionExisting(fnName)) {
                            JSONObject res = new JSONObject();
                                res.put("error", "");
                                res.put("success", false);
                            ack.call(res);
                            return;
                        }

                        ;
                        workerFunction.controller(parameters);
                        ack.call();
                    } catch (Exception e) {
                        try {
                            JSONObject res = new JSONObject();
                            res.put("error", e.getStackTrace().toString());
                            res.put("success", false);
                            ack.call(res);
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }

                        e.printStackTrace();
                    }
                }
            });
            socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("DISCONNECT");
                }
            });
            socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("Error");
                }
            });

            socket.io().on(Manager.EVENT_TRANSPORT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Transport transport = (Transport) args[0];
                    transport.on(Transport.EVENT_REQUEST_HEADERS, new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            Map<String, List<String>> headers = (Map<String, List<String>>)args[0];
                            headers.put("x-access-token", Collections.singletonList(cwire.getAccessToken()));
                        }
                    });
                }
            });

            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
