package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.EuroExchangeRateData;
import vn.vti.moneypig.models.GoldSjcData;

import java.util.List;
import java.util.Optional;


public interface EuroExchangeRateDataRepository extends MongoRepository<EuroExchangeRateData, Long> {
    public Optional<EuroExchangeRateData> findByUtcTime(String utcTime);

    EuroExchangeRateData findFirstByOrderByIdDesc();


    List<EuroExchangeRateData> findFirst20ByOrderByIdDesc();
}