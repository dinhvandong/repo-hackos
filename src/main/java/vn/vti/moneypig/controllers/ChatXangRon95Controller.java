package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.ChatTopicXangRon95;
import vn.vti.moneypig.services.ChatService;
import vn.vti.moneypig.services.ChatTopicXangRon95Service;

@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/chats_xangron95")
public class ChatXangRon95Controller {
    private final ChatTopicXangRon95Service chatService;

    @Autowired
    public ChatXangRon95Controller(ChatTopicXangRon95Service chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatTopicXangRon95 createChat(@RequestBody ChatTopicXangRon95 chat) {
        return chatService.create(chat);
    }

    @GetMapping("/findPaging")
    public Page<ChatTopicXangRon95> getPaginatedChats(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "50") int size) {
        return chatService.getPaginatedChats(page, size);
    }

    @GetMapping("/deleteAll")
    public int deleteAll() {
        chatService.deleteAll();
        return 1;
    }


    @GetMapping("/findLastMessage")
    public ChatTopicXangRon95 getPaginatedChats() {
        return chatService.getLastChat();
    }

}
