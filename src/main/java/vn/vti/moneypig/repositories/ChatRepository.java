package vn.vti.moneypig.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.Chat;

public interface ChatRepository extends MongoRepository<Chat, Long> {
    Chat findFirstByOrderByCreatedDateDesc();
    Page<Chat> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
