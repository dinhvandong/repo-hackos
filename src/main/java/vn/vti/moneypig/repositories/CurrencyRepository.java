package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.Currency;
import vn.vti.moneypig.models.Gold;

import java.util.List;

public interface CurrencyRepository extends MongoRepository<Currency, Long>
{

    List<Currency> findAllByDate(Long date);

}
