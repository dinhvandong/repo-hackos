package vn.vti.moneypig.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.ChatTopicCoffeeRobusta;
import vn.vti.moneypig.models.ChatTopicCoffeeVn;

public interface ChatTopicCoffeeRobustaRepository extends MongoRepository<ChatTopicCoffeeRobusta, Long> {
    ChatTopicCoffeeRobusta findFirstByOrderByCreatedDateDesc();
    Page<ChatTopicCoffeeRobusta> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
