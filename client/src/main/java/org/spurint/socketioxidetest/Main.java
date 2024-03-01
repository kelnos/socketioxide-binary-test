package org.spurint.socketioxidetest;

import io.socket.client.IO;
import io.socket.engineio.client.transports.WebSocket;
import okhttp3.OkHttpClient;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        var httpBuilder = new OkHttpClient.Builder();

        var options = new IO.Options();
        options.transports = new String[] {WebSocket.NAME };
        options.callFactory = httpBuilder.build();
        options.webSocketFactory = httpBuilder.build();

        var io = IO.socket("http://127.0.0.1:8987", options)
            .connect();

        while (true) {
            io.emit("test", (Object)"binary".getBytes(StandardCharsets.UTF_8));
            Thread.sleep(2000);
        }
    }
}
