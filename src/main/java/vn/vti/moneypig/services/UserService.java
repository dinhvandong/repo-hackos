package vn.vti.moneypig.services;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.jwt.JWTUtility;
import vn.vti.moneypig.jwt.JwtInterceptor;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.repositories.UserRepository;
import vn.vti.moneypig.utils.DateUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public UserService(UserRepository userRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> returnList = userRepository.findAll();
        returnList.sort(Comparator.comparingInt(User::getStatus).reversed());
        return returnList;
    }

    public User createUser(User user) {
        long _id = sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME);
        user.setId(_id);
        user.setStatus(1);
        user.setCreatedDate(DateUtils.getCurrentDate());
        return userRepository.insert(user);
    }

    public User findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElse(null);
        //  return userRepository.findByUsername(username).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            System.out.println("FindbyID:" + id);
            return userRepository.findById(id).get();
        }
        return null;
    }

    public Optional<User> findByUserId(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(User updatedUser) {
        Long id = updatedUser.getId();
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setAddress(updatedUser.getAddress());
            user.setAvatar(updatedUser.getAvatar());
            user.setStatus(updatedUser.getStatus());
            // Update other fields if needed
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
    }

    public User findUserByToken(String token) {
       // token = "Bearer " + token;
        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        User user = null;
        if (isAuthenticated) {
            Claims claims = JWTUtility.getInstance().parseToken(token);
            String username = claims.getSubject();
            if (username != null) {
                user = findByUsername(username);
                return user;
            }
        }
        return null;
        // Add more methods as per your requirements
    }

}