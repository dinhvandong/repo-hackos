package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.ChatTopicGbp;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.repositories.ChatRepository;
import vn.vti.moneypig.repositories.ChatTopicGbpRepository;
import vn.vti.moneypig.utils.DateUtils;

@Service
public class ChatTopicGbpService {

    @Autowired
    ChatTopicGbpRepository chatRepository;


    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    UserService userService;

    public ChatTopicGbp create (ChatTopicGbp chat){

        Long id = sequenceGeneratorService.generateSequence(ChatTopicGbp.SEQUENCE_NAME);
        chat.setId(id);

        chat.setCreatedDate(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        chat.setCreateDateUTCString(DateUtils.getCurrentTimeUTC());
        User user = userService.findById(chat.getSenderID());
        chat.setEmail(user.getEmail());
        chat.setSender(user.getUsername());

        return chatRepository.insert(chat);
    }

    public Page<ChatTopicGbp> getPaginatedChats(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return chatRepository.findAllByOrderByCreatedDateDesc(pageRequest);
    }

    public void deleteAll()
    {
        chatRepository.deleteAll();;
    }
    public ChatTopicGbp getLastChat() {
        return chatRepository.findFirstByOrderByCreatedDateDesc();
    }
}


