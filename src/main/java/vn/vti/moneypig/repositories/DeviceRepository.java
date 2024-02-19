package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.Device;

public interface DeviceRepository extends MongoRepository<Device, Long> {
}
