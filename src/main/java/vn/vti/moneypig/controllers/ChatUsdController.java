package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.ChatTopicUsd;
import vn.vti.moneypig.services.ChatService;
import vn.vti.moneypig.services.ChatTopicUsdService;

@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/chats_usd")
public class ChatUsdController {
    private final ChatTopicUsdService chatService;

    @Autowired
    public ChatUsdController(ChatTopicUsdService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatTopicUsd createChat(@RequestBody ChatTopicUsd chat) {
        return chatService.create(chat);
    }

    @GetMapping("/findPaging")
    public Page<ChatTopicUsd> getPaginatedChats(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "50") int size) {
        return chatService.getPaginatedChats(page, size);
    }

    @GetMapping("/deleteAll")
    public int deleteAll() {
        chatService.deleteAll();
        return 1;
    }


    @GetMapping("/findLastMessage")
    public ChatTopicUsd getPaginatedChats() {
        return chatService.getLastChat();
    }

}
