package com.dolphin.edium.authen;

import com.dolphin.edium.authen.service.GreeterService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

public class StartRpcServer {
    private static final Logger logger = Logger.getLogger(StartRpcServer.class.getName());

    private static Server server;

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = Integer.parseInt(System.getProperty("server.port"));
        server = ServerBuilder.forPort(port)
                .addService(new GreeterService())
                .build()
                .start();

        logger.info("Server started, listening on " + port);

        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            String ip;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()))) {

                //you get the IP as a String
                ip = in.readLine();
            }
            logger.info("Server started, listening on ip: " + ip);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            stop();
            System.err.println("*** server shut down");
        }));
    }

    protected static void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final StartRpcServer server = new StartRpcServer();
        server.start();
        server.blockUntilShutdown();
    }
}
