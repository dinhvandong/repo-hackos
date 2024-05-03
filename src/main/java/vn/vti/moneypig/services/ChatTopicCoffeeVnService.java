package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.ChatTopicCoffeeVn;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.repositories.ChatRepository;
import vn.vti.moneypig.repositories.ChatTopicCoffeeVnRepository;
import vn.vti.moneypig.utils.DateUtils;

@Service
public class ChatTopicCoffeeVnService {

    @Autowired
    ChatTopicCoffeeVnRepository chatRepository;


    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    UserService userService;

    public ChatTopicCoffeeVn create (ChatTopicCoffeeVn chat){

        Long id = sequenceGeneratorService.generateSequence(ChatTopicCoffeeVn.SEQUENCE_NAME);
        chat.setId(id);

        chat.setCreatedDate(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        chat.setCreateDateUTCString(DateUtils.getCurrentTimeUTC());
        User user = userService.findById(chat.getSenderID());
        chat.setEmail(user.getEmail());
        return chatRepository.insert(chat);
    }

    public Page<ChatTopicCoffeeVn> getPaginatedChats(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return chatRepository.findAllByOrderByCreatedDateDesc(pageRequest);
    }

    public void deleteAll()
    {
        chatRepository.deleteAll();;
    }
    public ChatTopicCoffeeVn getLastChat() {
        return chatRepository.findFirstByOrderByCreatedDateDesc();
    }
}


