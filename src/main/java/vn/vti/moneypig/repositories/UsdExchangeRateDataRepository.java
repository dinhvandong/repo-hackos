package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.dto.UsdExchangeRate;
import vn.vti.moneypig.models.GoldSjcData;
import vn.vti.moneypig.models.UsdExchangeRateData;

import java.util.List;
import java.util.Optional;


public interface UsdExchangeRateDataRepository extends MongoRepository<UsdExchangeRateData, Long> {
    public Optional<UsdExchangeRateData> findByUtcTime(String utcTime);

    UsdExchangeRateData findFirstByOrderByIdDesc();


    List<UsdExchangeRateData> findFirst20ByOrderByIdDesc();
}