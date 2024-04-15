package com.kamenskiy.io;

import com.kamenskiy.grpc.GreetingServiceGrpc;
import com.kamenskiy.grpc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext().build();
        //Создаем Стаб
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest
                .newBuilder()
                .setName("Andrey")
                .build();

        Iterator<GreetingServiceOuterClass.HelloResponse> response = stub.greeting(request);
        while (response.hasNext()){
            System.out.println(response.next());
        }
        channel.shutdownNow();
    }
}
