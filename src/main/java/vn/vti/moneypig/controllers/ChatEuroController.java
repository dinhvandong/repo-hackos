package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.ChatTopicEuro;
import vn.vti.moneypig.services.ChatService;
import vn.vti.moneypig.services.ChatTopicEuroService;

@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/chats_euro")
public class ChatEuroController {
    private final ChatTopicEuroService chatService;

    @Autowired
    public ChatEuroController(ChatTopicEuroService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatTopicEuro createChat(@RequestBody ChatTopicEuro chat) {
        return chatService.create(chat);
    }

    @GetMapping("/findPaging")
    public Page<ChatTopicEuro> getPaginatedChats(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "50") int size) {
        return chatService.getPaginatedChats(page, size);
    }

    @GetMapping("/deleteAll")
    public int deleteAll() {
        chatService.deleteAll();
        return 1;
    }


    @GetMapping("/findLastMessage")
    public ChatTopicEuro getPaginatedChats() {
        return chatService.getLastChat();
    }

}
