package vn.vti.moneypig.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.ChatTopicCoffeeVn;
import vn.vti.moneypig.models.ChatTopicXangRon95;

public interface ChatTopicXangRon95Repository extends MongoRepository<ChatTopicXangRon95, Long> {
    ChatTopicXangRon95 findFirstByOrderByCreatedDateDesc();
    Page<ChatTopicXangRon95> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
