package com.demo.api;

import com.demo.api.sender.ListMessageSenderResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MessageSenderWebServiceImpl implements MessageSenderWebService {
    @Override
    public ListMessageSenderResponse listMessageSender() {
        ListMessageSenderResponse.Sender sender = new ListMessageSenderResponse.Sender();
        sender.id = "MessageSender-0001";
        sender.name = "MessageSenderName-0001";
        ArrayList<ListMessageSenderResponse.Sender> senders = new ArrayList<>();
        senders.add(sender);
        ListMessageSenderResponse response = new ListMessageSenderResponse();
        response.senders = senders;
        return response;
    }
}
