package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.OilProductData;

import java.util.List;
import java.util.Optional;

public interface OilProductDataRepository extends MongoRepository<OilProductData, Long> {
    public Optional<OilProductData> findByUtcTime(String utcTime);

    OilProductData findFirstByOrderByIdDesc();


    List<OilProductData> findFirst20ByOrderByIdDesc();
}