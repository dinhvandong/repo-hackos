package vn.vti.moneypig.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.Chat;
import vn.vti.moneypig.models.ChatTopicCoffeeVn;

public interface ChatTopicCoffeeVnRepository extends MongoRepository<ChatTopicCoffeeVn, Long> {
    ChatTopicCoffeeVn findFirstByOrderByCreatedDateDesc();
    Page<ChatTopicCoffeeVn> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
