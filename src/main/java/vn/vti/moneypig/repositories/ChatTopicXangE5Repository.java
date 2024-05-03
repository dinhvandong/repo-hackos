package vn.vti.moneypig.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.ChatTopicCoffeeVn;
import vn.vti.moneypig.models.ChatTopicXangE5;

public interface ChatTopicXangE5Repository extends MongoRepository<ChatTopicXangE5, Long> {
    ChatTopicXangE5 findFirstByOrderByCreatedDateDesc();
    Page<ChatTopicXangE5> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
