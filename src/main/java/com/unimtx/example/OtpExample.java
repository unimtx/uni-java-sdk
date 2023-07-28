package com.unimtx.example;

import com.unimtx.Uni;
import com.unimtx.UniException;
import com.unimtx.UniResponse;
import com.unimtx.model.UniOtp;

public class OtpExample {
    public static String ACCESS_KEY_ID = System.getenv().getOrDefault("UNIMTX_ACCESS_KEY_ID", "your access key id");
    private static String ACCESS_KEY_SECRET = System.getenv().getOrDefault("UNIMTX_ACCESS_KEY_SECRET", "your access key secret");

    // send a verification code to a recipient
    public static void sendOtp() {
        try {
            UniResponse res = UniOtp.build()
                .setTo("your phone number") // in E.164 format
                .send();
            System.out.println(res.data);
        } catch (UniException e) {
            System.out.println("Error: " + e);
            System.out.println("RequestId: " + e.requestId);
        }
    }

    // verify a verification code
    public static void verifyOtp() {
        try {
            UniResponse ret = UniOtp.build()
                .setTo("your phone number") // in E.164 format
                .setCode("the code you received")
                .verify();
            System.out.println(ret.valid);
        } catch (UniException e) {
            System.out.println("Error: " + e);
            System.out.println("RequestId: " + e.requestId);
        }
    }

    public static void main(String[] args) {
        Uni.init(ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        sendOtp();

        verifyOtp();
    }
}
