package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.CoffeeProductData;
import vn.vti.moneypig.models.OilProductRon95Data;

import java.util.List;
import java.util.Optional;

public interface CoffeeProductRepository extends MongoRepository<CoffeeProductData, Long> {
    public Optional<CoffeeProductData> findByUtcTime(String utcTime);

    CoffeeProductData findFirstByOrderByIdDesc();


    List<CoffeeProductData> findFirst20ByOrderByIdDesc();
}