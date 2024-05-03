package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.ChatTopicWorldOil;
import vn.vti.moneypig.services.ChatService;
import vn.vti.moneypig.services.ChatTopicWorldOilService;

@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/chats_world_oil")
public class ChatWorldOilController {
    private final ChatTopicWorldOilService chatService;

    @Autowired
    public ChatWorldOilController(ChatTopicWorldOilService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatTopicWorldOil createChat(@RequestBody ChatTopicWorldOil chat) {
        return chatService.create(chat);
    }

    @GetMapping("/findPaging")
    public Page<ChatTopicWorldOil> getPaginatedChats(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "50") int size) {
        return chatService.getPaginatedChats(page, size);
    }

    @GetMapping("/deleteAll")
    public int deleteAll() {
        chatService.deleteAll();
        return 1;
    }


    @GetMapping("/findLastMessage")
    public ChatTopicWorldOil getPaginatedChats() {
        return chatService.getLastChat();
    }

}
