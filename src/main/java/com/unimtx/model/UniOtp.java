package com.unimtx.model;

import java.util.HashMap;
import java.util.Map;

import com.unimtx.Uni;
import com.unimtx.UniClient;
import com.unimtx.UniException;
import com.unimtx.UniResponse;

public class UniOtp {
    private String to;
    private String code;
    private Integer ttl;
    private Integer digits;
    private String intent;
    private String channel;
    private String signature;
    private String templateId;

    /**
     * Create a new Uni Message.
     */
    private UniOtp() {}

    public static UniOtp build() {
        return new UniOtp();
    }

    /**
     * Set the recipient phone number.
     *
     * @param phoneNumber phone number (in E.164 format)
     * @return UniOtp object
     */
    public UniOtp setTo(final String phoneNumber) {
        this.to = phoneNumber;
        return this;
    }

    /**
     * Set the verification code.
     *
     * @param code verification code (4-8 digits)
     * @return UniOtp object
     */
    public UniOtp setCode(final String code) {
        this.code = code;
        return this;
    }

    /**
     * Set the time-to-live (ttl) time of the verification code.
     *
     * @param seconds time-to-live (s)
     * @return UniOtp object
     */
    public UniOtp setTtl(final Integer seconds) {
        this.ttl = seconds;
        return this;
    }

    /**
     * Set the number of digits for the verification code.
     *
     * @param digits digits (4-8)
     * @return UniOtp object
     */
    public UniOtp setDigits(final Integer digits) {
        this.digits = digits;
        return this;
    }

    /**
     * Set a intent label.
     *
     * @param intent intent label (e.g.: 'login', max length 36 chars)
     * @return UniOtp object
     */
    public UniOtp setIntent(final String intent) {
        this.intent = intent;
        return this;
    }

    /**
     * Set a messaging channel.
     *
     * @param channel 'auto' | 'sms' | 'call' | 'voice' | 'whatsapp'
     * @return UniOtp object
     */
    public UniOtp setChannel(final String channel) {
        this.channel = channel;
        return this;
    }

    /**
     * Set the message signature.
     *
     * @param signature message signature
     * @return UniOtp object
     */
    public UniOtp setSignature(final String signature) {
        this.signature = signature;
        return this;
    }

    /**
     * Set the template ID.
     *
     * @param templateId message template ID
     * @return UniOtp object
     */
    public UniOtp setTemplateId(final String templateId) {
        this.templateId = templateId;
        return this;
    }

    private Map<String, Object> getSendData()  {
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("to", this.to);

        if (this.code != null) {
            data.put("code", this.code);
        }

        if (this.ttl != null) {
            data.put("ttl", this.ttl);
        }

        if (this.digits != null) {
            data.put("digits", this.digits);
        }

        if (this.intent != null) {
            data.put("intent", this.intent);
        }

        if (this.channel != null) {
            data.put("channel", this.channel);
        }

        if (this.signature != null) {
            data.put("signature", this.signature);
        }

        if (this.templateId != null) {
            data.put("templateId", this.templateId);
        }

        return data;
    }

    /**
     * Send the one-time passcode.
     *
     * @return UniResult object
     * @throws UniException if catch error
     */
    public UniResponse send() throws UniException {
        if (this.to == null) {
            throw new UniException("MissingParamTo");
        }

        Map<String, Object> data = this.getSendData();
        UniClient client = Uni.getClient();
        return client.request("otp.send", data);
    }

    /**
     * Verify the one-time passcode.
     *
     * @return UniResult object
     * @throws UniException if catch error
     */
    public UniResponse verify() throws UniException {
        if (this.to == null) {
            throw new UniException("MissingParamTo");
        }

        if (this.code == null) {
            throw new UniException("MissingParamCode");
        }

        Map<String, Object> data = this.getSendData();
        UniClient client = Uni.getClient();
        UniResponse response = client.request("otp.verify", data);

        if (response.data != null && response.data.has("valid")) {
            response.valid = response.data.getBoolean("valid");
        }

        return response;
    }
}
