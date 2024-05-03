package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.ChatTopicCoffeeArabica;
import vn.vti.moneypig.services.ChatService;
import vn.vti.moneypig.services.ChatTopicCoffeeArabicaService;

@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/chats_coffee_arabica")
public class ChatCoffeeArabicaController {
    private final ChatTopicCoffeeArabicaService chatService;

    @Autowired
    public ChatCoffeeArabicaController(ChatTopicCoffeeArabicaService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatTopicCoffeeArabica createChat(@RequestBody ChatTopicCoffeeArabica chat) {
        return chatService.create(chat);
    }

    @GetMapping("/findPaging")
    public Page<ChatTopicCoffeeArabica> getPaginatedChats(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "50") int size) {
        return chatService.getPaginatedChats(page, size);
    }

    @GetMapping("/deleteAll")
    public int deleteAll() {
        chatService.deleteAll();
        return 1;
    }


    @GetMapping("/findLastMessage")
    public ChatTopicCoffeeArabica getPaginatedChats() {
        return chatService.getLastChat();
    }

}
