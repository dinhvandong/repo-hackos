package vn.vti.moneypig.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.ChatTopicCoffeeVn;
import vn.vti.moneypig.models.ChatTopicGoldSjc;

public interface ChatTopicGoldSjcRepository extends MongoRepository<ChatTopicGoldSjc, Long> {
    ChatTopicGoldSjc findFirstByOrderByCreatedDateDesc();
    Page<ChatTopicGoldSjc> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
