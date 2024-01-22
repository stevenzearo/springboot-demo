package com.demo.api;

import com.demo.api.sender.ListMessageSenderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "message-sender-service", url = "http://localhost:8401")
public interface MessageSenderWebService {

    @GetMapping("/api/message-sender")
    ListMessageSenderResponse listMessageSender();
}
