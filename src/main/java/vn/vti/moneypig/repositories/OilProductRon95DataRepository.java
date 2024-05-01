package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.OilProductData;

import java.util.List;
import java.util.Optional;
import  vn.vti.moneypig.models.*;



public interface OilProductRon95DataRepository extends MongoRepository<OilProductRon95Data, Long> {
    public Optional<OilProductRon95Data> findByUtcTime(String utcTime);

    OilProductRon95Data findFirstByOrderByIdDesc();


    List<OilProductRon95Data> findFirst20ByOrderByIdDesc();
}