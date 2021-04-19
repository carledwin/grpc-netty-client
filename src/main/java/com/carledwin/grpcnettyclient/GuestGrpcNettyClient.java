package com.carledwin.grpcnettyclient;

import com.carledwinti.grpcnetty.Guest;
import com.carledwinti.grpcnetty.guestGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GuestGrpcNettyClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        guestGrpc.guestBlockingStub guestBlockingStub = guestGrpc.newBlockingStub(managedChannel);

        Guest.Empty empty = Guest.Empty.getDefaultInstance();
        Guest.DefaultResponse defaultResponse = guestBlockingStub
                .greeting(empty);

        System.out.println("Greetins Response");
        System.out.println("Code: " + defaultResponse.getCode());
        System.out.println("Information: " + defaultResponse.getInformation());
        System.out.println("\n------------------------------------------------------------");

        Guest.RegisterRequest registerRequest = Guest.RegisterRequest
                .newBuilder()
                .setName("Carl")
                .setSurname("Edwin")
                .build();

        Guest.RegisterResponse registerResponse = guestBlockingStub
                .register(registerRequest);

        System.out.println("Register Response");
        System.out.println("Code: " + registerResponse.getCode());
        System.out.println("Fullname: " + registerResponse.getFullname());
        System.out.println("Message: " + registerResponse.getMessage());
        System.out.println("\n------------------------------------------------------------");

        Guest.RegisterRequest registerRequestBadRequest = Guest.RegisterRequest
                .newBuilder()
                .setName("")
                .setSurname("")
                .build();

        Guest.RegisterResponse registerResponseBadRequest = guestBlockingStub
                .register(registerRequestBadRequest);

        System.out.println("Register Response BadRequest");
        System.out.println("Code: " + registerResponseBadRequest.getCode());
        System.out.println("Fullname: " + registerResponseBadRequest.getFullname());
        System.out.println("Message: " + registerResponseBadRequest.getMessage());
        System.out.println("\n------------------------------------------------------------");
    }
}
