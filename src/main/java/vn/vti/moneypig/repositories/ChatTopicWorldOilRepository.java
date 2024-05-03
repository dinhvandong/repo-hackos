package vn.vti.moneypig.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.ChatTopicCoffeeVn;
import vn.vti.moneypig.models.ChatTopicWorldOil;

public interface ChatTopicWorldOilRepository extends MongoRepository<ChatTopicWorldOil, Long> {
    ChatTopicWorldOil findFirstByOrderByCreatedDateDesc();
    Page<ChatTopicWorldOil> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
