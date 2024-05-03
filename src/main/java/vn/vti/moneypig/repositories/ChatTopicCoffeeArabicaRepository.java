package vn.vti.moneypig.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.ChatTopicCoffeeArabica;
import vn.vti.moneypig.models.ChatTopicCoffeeVn;

public interface ChatTopicCoffeeArabicaRepository extends MongoRepository<ChatTopicCoffeeArabica, Long> {
    ChatTopicCoffeeArabica findFirstByOrderByCreatedDateDesc();
    Page<ChatTopicCoffeeArabica> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
