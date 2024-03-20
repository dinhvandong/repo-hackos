package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.Account;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, Long>
{
    public Optional<Account> findByPhrase(String phrase);


}
