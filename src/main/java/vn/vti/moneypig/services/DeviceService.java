package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.models.Device;
import vn.vti.moneypig.repositories.DeviceRepository;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public Device insert(Device device){

        Long id = sequenceGeneratorService.generateSequence(Device.SEQUENCE_NAME);
        device.setId(id);
        return deviceRepository.insert(device);
    }

    public List<Device> findAll(){

        return deviceRepository.findAll();
    }
}
