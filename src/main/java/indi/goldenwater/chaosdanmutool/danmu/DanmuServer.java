package indi.goldenwater.chaosdanmutool.danmu;

import com.google.gson.Gson;
import indi.goldenwater.chaosdanmutool.ChaosDanmuTool;
import indi.goldenwater.chaosdanmutool.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class DanmuServer extends WebSocketServer {
    private static final Logger logger = LogManager.getLogger(DanmuServer.class);

    private static DanmuServer instance;

    public DanmuServer(int port) throws InterruptedException {
        super(new InetSocketAddress(port));
        if (instance != null) instance.stop();
        instance = this;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        InetSocketAddress remoteAddress = webSocket.getRemoteSocketAddress();
        logger.info(String.format("[DanmuServer] Client %s connected", remoteAddress.toString()));
        updateConfig(ChaosDanmuTool.getConfig());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        InetSocketAddress remoteAddress = webSocket.getRemoteSocketAddress();
        logger.info(String.format("[DanmuServer] Client %s closed", remoteAddress.toString()));
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {

    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }

    public void updateConfig(Config config) {
        broadcast(String.format("updateConfig(%s);", new Gson().toJson(config)));
    }

    public static DanmuServer getInstance() {
        return instance;
    }
}
