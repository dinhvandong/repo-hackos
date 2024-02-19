package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.dto.GeoLocationInfo;
import vn.vti.moneypig.models.Device;
import vn.vti.moneypig.repositories.DeviceRepository;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    GeoLocationService geoLocationService;

    public Device insert(Device device){

        String ipaddr = device.getIp();

        GeoLocationInfo geoLocationInfo = geoLocationService.getGeoLocationInfo(ipaddr);
        device.setCountry(geoLocationInfo.getCountry() +"-"+ geoLocationInfo.getCity());
        Long id = sequenceGeneratorService.generateSequence(Device.SEQUENCE_NAME);
        device.setId(id);
        return deviceRepository.insert(device);
    }

    public List<Device> findAll(){

        return deviceRepository.findAll();
    }
}
