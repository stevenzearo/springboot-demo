package com.demo.api;

import com.demo.api.sender.ListMessageSenderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MessageCenterWebService {
    @Autowired(required = false)
    MessageSenderWebService messageSenderWebService;

    @GetMapping("/api/sender")
    public List<String> listAvailableSenderIds() {
        ListMessageSenderResponse listMessageSenderResponse = messageSenderWebService.listMessageSender();
        return Optional.ofNullable(listMessageSenderResponse.senders)
                       .orElse(new ArrayList<>())
                       .stream().map(s -> s.id).collect(Collectors.toList());
    }
}
