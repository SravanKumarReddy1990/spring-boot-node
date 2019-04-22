package com.server.websocket;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.logging.Logger;

/**
 * Created by Arip Hidayat on 21/02/2016.
 */
public class MessageDecoder implements Decoder.Text<Message> {
    private final Logger log = Logger.getLogger(getClass().getName());

    public Message decode(String s) throws DecodeException {
        log.info("incoming message : " + s);

        Gson gson = new Gson();
        Message message = gson.fromJson(s, Message.class);
        return message;
    }

    public boolean willDecode(String s) {
        return (s != null);
    }

    public void init(EndpointConfig endpointConfig) {
        // do nothing
    }

    public void destroy() {
        // do nothing
    }
}
