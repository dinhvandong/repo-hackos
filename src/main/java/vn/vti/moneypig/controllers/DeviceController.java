package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.jwt.JwtInterceptor;
import vn.vti.moneypig.models.Category;
import vn.vti.moneypig.models.CategoryGroup;
import vn.vti.moneypig.models.Device;
import vn.vti.moneypig.repositories.DeviceRepository;
import vn.vti.moneypig.services.CategoryGroupService;
import vn.vti.moneypig.services.DeviceService;

@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @Autowired
    DeviceRepository deviceRepository;
    @PostMapping("/findAll")
    public ResponseEntity<?> findAll(@RequestParam String token)
    {
        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(202, null,"token is not valid"));
        }
        token = "Bearer " + token;

        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, deviceService.findAll(),"success"));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"device not exist"));
        }

    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll()
    {
        deviceRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "OK"," delete all data "));


    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Device device )
    {
        Device response =  deviceService.insert(device);
        if(response!= null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"device is added "));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"device is not exist"));
        }
    }


    @PostMapping("/copy")
    public ResponseEntity<?> insert(@RequestParam Long id )
    {
        Device response =  deviceService.copy(id);
        if(response!= null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"device is copied "));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"device is not copy"));
        }
    }
}
