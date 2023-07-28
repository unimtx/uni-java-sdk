package com.unimtx.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.unimtx.Uni;
import com.unimtx.UniClient;
import com.unimtx.UniException;
import com.unimtx.UniResponse;

public class UniMessage {
    private String[] to;
    private String signature;
    private String templateId;
    private Map<String, String> templateData;
    private String content;
    private String text;

    /**
     * Create a new Uni Message.
     */
    private UniMessage() {}

    public static UniMessage build() {
        return new UniMessage();
    }

    /**
     * @deprecated Create a new Uni Message. Use `build()` instead.
     * @return UniMessage object
     */
    public static UniMessage buildMessage() {
        return new UniMessage();
    }

    /**
     * Set the recipient phone number.
     *
     * @param phoneNumber phone number (in E.164 format)
     * @return UniMessage object
     */
    public UniMessage setTo(final String phoneNumber) {
        String arr[] = {phoneNumber};
        this.to = arr;
        return this;
    }

    /**
     * Set multiple recipient phone numbers.
     *
     * @param phoneNumbers phone numbers (in E.164 format)
     * @return UniMessage object
     */
    public UniMessage setTo(final String[] phoneNumbers) {
        this.to = phoneNumbers;
        return this;
    }

    /**
     * Set the message signature.
     *
     * @param signature message signature
     * @return UniMessage object
     */
    public UniMessage setSignature(final String signature) {
        this.signature = signature;
        return this;
    }

    /**
     * Set the template ID.
     *
     * @param templateId message template ID
     * @return UniMessage object
     */
    public UniMessage setTemplateId(final String templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * Set the template data.
     *
     * @param templateData message template data
     * @return UniMessage object
     */
    public UniMessage setTemplateData(final Map<String, String> templateData) {
        this.templateData = templateData;
        return this;
    }

    /**
     * Set the message content.
     *
     * @param content message content (without signature)
     * @return UniMessage object
     */
    public UniMessage setContent(final String content) {
        this.content = content;
        return this;
    }

    /**
     * Set the message text.
     *
     * @param text message text (with signature)
     * @return UniMessage object
     */
    public UniMessage setText(final String text) {
        this.text = text;
        return this;
    }

    private Map<String, Object> getSendData()  {
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("to", Arrays.asList(this.to));

        if (this.signature != null) {
            data.put("signature", this.signature);
        }

        if (this.templateId != null) {
            data.put("templateId", this.templateId);
        }

        if (this.templateData != null) {
            data.put("templateData", this.templateData);
        }

        if (this.content != null) {
            data.put("content", this.content);
        }

        if (this.text != null) {
            data.put("text", this.text);
        }

        return data;
    }

    /**
     * Send the message.
     *
     * @return UniResponse object
     * @throws UniException if catch error
     */
    public UniResponse send() throws UniException {
        Map<String, Object> data = this.getSendData();
        UniClient client = Uni.getClient();
        return client.request("sms.message.send", data);
    }
}
