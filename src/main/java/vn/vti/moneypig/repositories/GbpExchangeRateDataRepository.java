package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.GbpExchangeRateData;
import vn.vti.moneypig.models.GoldSjcData;

import java.util.List;
import java.util.Optional;


public interface GbpExchangeRateDataRepository extends MongoRepository<GbpExchangeRateData, Long> {
    public Optional<GbpExchangeRateData> findByUtcTime(String utcTime);

    GbpExchangeRateData findFirstByOrderByIdDesc();


    List<GbpExchangeRateData> findFirst20ByOrderByIdDesc();
}