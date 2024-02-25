package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.dto.AddressInfo;
import vn.vti.moneypig.dto.GeoLocationInfo;
import vn.vti.moneypig.models.Device;
import vn.vti.moneypig.repositories.DeviceRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    IPApiService apiService;

    public Device insert(Device device){
        String ipaddr = device.getIp();
        device.setStatus(1);
        Optional<Device> optionalDevice = deviceRepository.findByIp(ipaddr);
        if(optionalDevice.isEmpty()){
            device.setCreatedDate(DateUtils.getCurrentDate());
            device.setUpdatedDate(DateUtils.getCurrentDate());
            AddressInfo addressInfo = apiService.getAddressInfo(ipaddr);
            device.setCountry(addressInfo.getCountry() +"-"+ addressInfo.getCity());
            Long id = sequenceGeneratorService.generateSequence(Device.SEQUENCE_NAME);
            device.setId(id);
            return deviceRepository.insert(device);
        }else {
            Device foundDevice = optionalDevice.get();
            return update(foundDevice);
        }
    }
    public  Device update(Device updateDevice){
        Optional<Device> optionalDevice = deviceRepository.findByIp(updateDevice.getIp());
        if(optionalDevice.isEmpty()){
            return null;
        }
        Device foundDevice = optionalDevice.get();
        foundDevice.setFacebook(updateDevice.getFacebook());
        foundDevice.setGoogle(updateDevice.getGoogle());
        foundDevice.setTiktok(updateDevice.getTiktok());
        foundDevice.setUpdatedDate(DateUtils.getCurrentDate());
        return deviceRepository.save(foundDevice);
    }
    public Device copy(Long id){
        Optional<Device> optionalDevice = deviceRepository.findById(id);
        if(optionalDevice.isEmpty()){
            return null;
        }
        Device foundDevice = optionalDevice.get();
        foundDevice.setStatus(0);
        foundDevice.setUpdatedDate(DateUtils.getCurrentDate());
        return  deviceRepository.save(foundDevice);
    }
    public List<Device> findAll(){

        return deviceRepository.findAll();
    }
}
