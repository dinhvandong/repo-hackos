package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.ChatTopicGoldBtmc;
import vn.vti.moneypig.services.ChatService;
import vn.vti.moneypig.services.ChatTopicGoldBtmcService;

@CrossOrigin(origins = {
        "http://163.44.206.118:83",
        "http://163.44.206.118:80",
        "http://163.44.206.118:81",
        "http://localhost:3001",
        "http://localhost:3000",
        "http://150.95.113.18"
})
@RestController
@RequestMapping("/api/chats_gold_btmc")
public class ChatGoldBtmcController {
    private final ChatTopicGoldBtmcService chatService;

    @Autowired
    public ChatGoldBtmcController(ChatTopicGoldBtmcService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatTopicGoldBtmc createChat(@RequestBody ChatTopicGoldBtmc chat) {
        return chatService.create(chat);
    }

    @GetMapping("/findPaging")
    public Page<ChatTopicGoldBtmc> getPaginatedChats(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "50") int size) {
        return chatService.getPaginatedChats(page, size);
    }

    @GetMapping("/deleteAll")
    public int deleteAll() {
        chatService.deleteAll();
        return 1;
    }


    @GetMapping("/findLastMessage")
    public ChatTopicGoldBtmc getPaginatedChats() {
        return chatService.getLastChat();
    }

}
