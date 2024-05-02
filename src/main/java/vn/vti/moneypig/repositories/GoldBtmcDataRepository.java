package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.GoldBtmcData;
import vn.vti.moneypig.models.GoldSjcData;

import java.util.List;
import java.util.Optional;


public interface GoldBtmcDataRepository extends MongoRepository<GoldBtmcData, Long> {
    public Optional<GoldBtmcData> findByUtcTime(String utcTime);

    GoldBtmcData findFirstByOrderByIdDesc();


    List<GoldBtmcData> findFirst20ByOrderByIdDesc();
}