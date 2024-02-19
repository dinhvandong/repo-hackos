package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.jwt.JwtInterceptor;
import vn.vti.moneypig.models.Image;
import vn.vti.moneypig.services.ImageService;

import java.io.IOException;
import java.util.Optional;
@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;


    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam String token, @RequestParam("file") MultipartFile file) {
        try {
            if(token.isBlank()){
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
            }
            token = "Bearer " + token;
            boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
            if(isAuthenticated){
                Image savedImage = imageService.saveImage(file);
                //return ResponseEntity.ok(savedImage.getId());
               return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, savedImage.getId(),"upload Ok"));
            }else {
               return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"upload not Ok"));
            }
        } catch (IOException e) {
           return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"upload not Ok"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        Optional<Image> imageOptional = imageService.getImage(id);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            //return ResponseEntity.ok().body(image.getData());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Set the appropriate media type, e.g., IMAGE_JPEG, IMAGE_PNG, etc.

            // Return the image byte array with the headers
            return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}