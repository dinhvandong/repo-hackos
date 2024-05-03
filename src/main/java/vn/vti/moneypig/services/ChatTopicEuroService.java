package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.ChatTopicEuro;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.repositories.ChatRepository;
import vn.vti.moneypig.repositories.ChatTopicEuroRepository;
import vn.vti.moneypig.utils.DateUtils;

@Service
public class ChatTopicEuroService {

    @Autowired
    ChatTopicEuroRepository chatRepository;


    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    UserService userService;

    public ChatTopicEuro create (ChatTopicEuro chat){

        Long id = sequenceGeneratorService.generateSequence(ChatTopicEuro.SEQUENCE_NAME);
        chat.setId(id);

        chat.setCreatedDate(DateUtils.getCurrentDateYYYYMMDDHHmmss());
        chat.setCreateDateUTCString(DateUtils.getCurrentTimeUTC());
        User user = userService.findById(chat.getSenderID());
        chat.setEmail(user.getEmail());
        return chatRepository.insert(chat);
    }

    public Page<ChatTopicEuro> getPaginatedChats(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return chatRepository.findAllByOrderByCreatedDateDesc(pageRequest);
    }

    public void deleteAll()
    {
        chatRepository.deleteAll();;
    }
    public ChatTopicEuro getLastChat() {
        return chatRepository.findFirstByOrderByCreatedDateDesc();
    }
}


