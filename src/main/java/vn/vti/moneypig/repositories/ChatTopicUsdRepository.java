package vn.vti.moneypig.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.ChatTopicCoffeeVn;
import vn.vti.moneypig.models.ChatTopicUsd;

public interface ChatTopicUsdRepository extends MongoRepository<ChatTopicUsd, Long> {
    ChatTopicUsd findFirstByOrderByCreatedDateDesc();
    Page<ChatTopicUsd> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
