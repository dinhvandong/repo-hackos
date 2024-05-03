package vn.vti.moneypig.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.ChatTopicCoffeeVn;
import vn.vti.moneypig.models.ChatTopicGbp;

public interface ChatTopicGbpRepository extends MongoRepository<ChatTopicGbp, Long> {
    ChatTopicGbp findFirstByOrderByCreatedDateDesc();
    Page<ChatTopicGbp> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
