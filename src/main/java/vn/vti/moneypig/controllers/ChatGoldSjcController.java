package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.ChatTopicGoldSjc;
import vn.vti.moneypig.services.ChatService;
import vn.vti.moneypig.services.ChatTopicGoldSjcService;

@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/chats_gold_sjc")
public class ChatGoldSjcController {
    private final ChatTopicGoldSjcService chatService;

    @Autowired
    public ChatGoldSjcController(ChatTopicGoldSjcService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatTopicGoldSjc createChat(@RequestBody ChatTopicGoldSjc chat) {
        return chatService.create(chat);
    }

    @GetMapping("/findPaging")
    public Page<ChatTopicGoldSjc> getPaginatedChats(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "50") int size) {
        return chatService.getPaginatedChats(page, size);
    }

    @GetMapping("/deleteAll")
    public int deleteAll() {
        chatService.deleteAll();
        return 1;
    }


    @GetMapping("/findLastMessage")
    public ChatTopicGoldSjc getPaginatedChats() {
        return chatService.getLastChat();
    }

}
