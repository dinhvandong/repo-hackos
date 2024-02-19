package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.vti.moneypig.models.Image;
import vn.vti.moneypig.repositories.ImageRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;


    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image saveImage(MultipartFile file) throws IOException {
        byte[] data = file.getBytes();
        Image image = new Image();
        image.setData(data);
        return imageRepository.save(image);
    }

    public Optional<Image> getImage(String id) {
        return imageRepository.findById(id);
    }
}
