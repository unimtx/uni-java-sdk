package com.unimtx.example;

import com.unimtx.Uni;
import com.unimtx.UniException;
import com.unimtx.UniResponse;
import com.unimtx.model.UniMessage;

public class SendExample {
    private static String ACCESS_KEY_ID = System.getenv().getOrDefault("UNIMTX_ACCESS_KEY_ID", "your access key id");
    private static String ACCESS_KEY_SECRET = System.getenv().getOrDefault("UNIMTX_ACCESS_KEY_SECRET", "your access key secret");

    public static void main(String[] args) {
        Uni.init(ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        // send a text message to a single recipient
        UniMessage message = UniMessage.build()
            .setTo("your phone number") // in E.164 format
            .setText("Your verification code is 2048.");

        try {
            UniResponse res = message.send();
            System.out.println(res.data);
        } catch (UniException e) {
            System.out.println("Error: " + e);
            System.out.println("RequestId: " + e.requestId);
        }
    }
}
