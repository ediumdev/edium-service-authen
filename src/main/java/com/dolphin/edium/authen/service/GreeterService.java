package com.dolphin.edium.authen.service;

import com.dolphin.edium.authen.proto.GreeterGrpc;
import com.dolphin.edium.authen.proto.HelloWorldProto;
import io.grpc.stub.StreamObserver;

public class GreeterService extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloWorldProto.HelloRequest request, StreamObserver<HelloWorldProto.HelloReply> responseObserver) {
        HelloWorldProto.HelloReply reply = HelloWorldProto.HelloReply.newBuilder().setMessage("Hello: " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}