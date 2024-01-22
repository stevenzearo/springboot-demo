package com.demo.api.sender;

import java.util.List;

public class ListMessageSenderResponse {
    public List<Sender> senders;

    public static class Sender {
        public String id;
        public String name;
    }
}
