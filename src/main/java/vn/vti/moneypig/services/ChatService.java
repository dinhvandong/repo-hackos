package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.repositories.ChatRepository;

@Service
public class ChatService {

    @Autowired
    ChatRepository chatRepository;


    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    UserService userService;

    public Chat create (Chat chat){

        Long id = sequenceGeneratorService.generateSequence(Chat.SEQUENCE_NAME);
        chat.setId(id);

        User user = userService.findById(chat.getSenderID());
        chat.setEmail(user.getEmail());
        return chatRepository.insert(chat);
    }

    public Page<Chat> getPaginatedChats(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return chatRepository.findAllByOrderByCreatedDateDesc(pageRequest);
    }
    public Chat getLastChat() {
        return chatRepository.findFirstByOrderByCreatedDateDesc();
    }
}


