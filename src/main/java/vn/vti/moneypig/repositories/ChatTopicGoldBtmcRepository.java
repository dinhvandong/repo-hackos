package vn.vti.moneypig.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.ChatTopicCoffeeVn;
import vn.vti.moneypig.models.ChatTopicGoldBtmc;

public interface ChatTopicGoldBtmcRepository extends MongoRepository<ChatTopicGoldBtmc, Long> {
    ChatTopicGoldBtmc findFirstByOrderByCreatedDateDesc();
    Page<ChatTopicGoldBtmc> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
