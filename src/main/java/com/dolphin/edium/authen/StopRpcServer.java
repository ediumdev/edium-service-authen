package com.dolphin.edium.authen;

public class StopRpcServer {
    public static void main(String[] args) {
        System.err.println("*** shutting down gRPC server manual");
        StartRpcServer.stop();
        System.err.println("*** server shut down");
    }
}
