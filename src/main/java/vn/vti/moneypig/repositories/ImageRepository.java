package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.Image;

public interface ImageRepository extends MongoRepository<Image, String> {
}
