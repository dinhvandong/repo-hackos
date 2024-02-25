package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.Device;

import java.util.Optional;

public interface DeviceRepository extends MongoRepository<Device, Long> {

    public Optional<Device> findByIp(String ip);
}
