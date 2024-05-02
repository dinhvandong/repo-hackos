package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.CoffeeArabicaData;
import vn.vti.moneypig.models.CoffeeRobustaData;

import java.util.List;
import java.util.Optional;


public interface CoffeeArabicaDataRepository extends MongoRepository<CoffeeArabicaData, Long> {
    public Optional<CoffeeArabicaData> findByUtcTime(String utcTime);

    CoffeeArabicaData findFirstByOrderByIdDesc();


    List<CoffeeArabicaData> findFirst20ByOrderByIdDesc();
}