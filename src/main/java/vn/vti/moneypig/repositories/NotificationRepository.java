package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.Notification;

import java.util.List;

public interface NotificationRepository
        extends MongoRepository<Notification, Long>
{
    List<Notification> findAllBySenderId(Long senderId);
   // List<Notification> findAllByReceivedId(Long receivedId);
}
