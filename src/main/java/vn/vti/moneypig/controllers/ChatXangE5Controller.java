package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.ChatTopicXangE5;
import vn.vti.moneypig.services.ChatService;
import vn.vti.moneypig.services.ChatTopicXangE5Service;

@CrossOrigin(origins = {
        "http://163.44.206.118:83",
        "http://163.44.206.118:80",
        "http://163.44.206.118:81",
        "http://localhost:3001",
        "http://localhost:3000",
        "http://150.95.113.18"
})
@RestController
@RequestMapping("/api/chats_xange5")
public class ChatXangE5Controller {
    private final ChatTopicXangE5Service chatService;

    @Autowired
    public ChatXangE5Controller(ChatTopicXangE5Service chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatTopicXangE5 createChat(@RequestBody ChatTopicXangE5 chat) {
        return chatService.create(chat);
    }

    @GetMapping("/findPaging")
    public Page<ChatTopicXangE5> getPaginatedChats(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "50") int size) {
        return chatService.getPaginatedChats(page, size);
    }

    @GetMapping("/deleteAll")
    public int deleteAll() {
        chatService.deleteAll();
        return 1;
    }


    @GetMapping("/findLastMessage")
    public ChatTopicXangE5 getPaginatedChats() {
        return chatService.getLastChat();
    }

}
