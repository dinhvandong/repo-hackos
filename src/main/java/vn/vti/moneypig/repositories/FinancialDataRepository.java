package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.FinancialData;

import java.util.Optional;

public interface FinancialDataRepository extends MongoRepository<FinancialData, Long>
{
    public Optional<FinancialData> findByUtcTime(String utcTime);

    FinancialData findFirstByOrderByIdDesc();

}
