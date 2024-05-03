package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.ChatTopicGoldBtmc;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.repositories.ChatRepository;
import vn.vti.moneypig.repositories.ChatTopicGoldBtmcRepository;
import vn.vti.moneypig.utils.DateUtils;

@Service
public class ChatTopicGoldBtmcService {

    @Autowired
    ChatTopicGoldBtmcRepository chatRepository;


    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    UserService userService;

    public ChatTopicGoldBtmc create (ChatTopicGoldBtmc chat){

        Long id = sequenceGeneratorService.generateSequence(ChatTopicGoldBtmc.SEQUENCE_NAME);
        chat.setId(id);

        chat.setCreatedDate(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        chat.setCreateDateUTCString(DateUtils.getCurrentTimeUTC());
        User user = userService.findById(chat.getSenderID());
        chat.setEmail(user.getEmail());
        chat.setSender(user.getUsername());

        return chatRepository.insert(chat);
    }

    public Page<ChatTopicGoldBtmc> getPaginatedChats(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return chatRepository.findAllByOrderByCreatedDateDesc(pageRequest);
    }

    public void deleteAll()
    {
        chatRepository.deleteAll();;
    }
    public ChatTopicGoldBtmc getLastChat() {
        return chatRepository.findFirstByOrderByCreatedDateDesc();
    }
}


