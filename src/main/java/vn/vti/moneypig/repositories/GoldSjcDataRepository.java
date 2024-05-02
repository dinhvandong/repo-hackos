package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.CoffeeRobustaData;
import vn.vti.moneypig.models.GoldSjcData;

import java.util.List;
import java.util.Optional;


public interface GoldSjcDataRepository extends MongoRepository<GoldSjcData, Long> {
    public Optional<GoldSjcData> findByUtcTime(String utcTime);

    GoldSjcData findFirstByOrderByIdDesc();


    List<GoldSjcData> findFirst20ByOrderByIdDesc();
}