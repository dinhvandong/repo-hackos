package vn.vti.moneypig.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.ChatTopicCoffeeVn;
import vn.vti.moneypig.models.ChatTopicEuro;

public interface ChatTopicEuroRepository extends MongoRepository<ChatTopicEuro, Long> {
    ChatTopicEuro findFirstByOrderByCreatedDateDesc();
    Page<ChatTopicEuro> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
