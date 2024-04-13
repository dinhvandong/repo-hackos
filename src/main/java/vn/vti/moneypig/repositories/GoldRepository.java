package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.Gold;

import java.util.List;

public interface GoldRepository extends MongoRepository<Gold, Long> {
    List<Gold> findAllByTime(Long time);
    List<Gold> findAllByAddress(String address);

    List<Gold> findAllByAddressAndTime(String address, Long time);

}
