package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.CoffeeProductData;
import vn.vti.moneypig.models.CoffeeRobustaData;

import java.util.List;
import java.util.Optional;


public interface CoffeeRobustaDataRepository extends MongoRepository<CoffeeRobustaData, Long> {
    public Optional<CoffeeRobustaData> findByUtcTime(String utcTime);

    CoffeeRobustaData findFirstByOrderByIdDesc();


    List<CoffeeRobustaData> findFirst20ByOrderByIdDesc();
}