package vn.vti.moneypig.controllers;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.config.IpServer;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.jwt.JWTUtility;
import vn.vti.moneypig.jwt.JwtInterceptor;
import vn.vti.moneypig.jwt.JwtTokenStore;
import vn.vti.moneypig.models.User;
//import vn.vti.moneypig.otp.OTPService;
import vn.vti.moneypig.security.PasswordEncoder;
import vn.vti.moneypig.services.UserService;
@CrossOrigin(origins = IpServer.ip)
@RestController
@RequestMapping("/api/user")
public class UserController {
    private  final UserService userService;
    private final JwtInterceptor jwtInterceptor;
    private final JwtTokenStore jwtTokenStore;
//    private final FirebaseService firebaseService;
    @Autowired
    public UserController(UserService userService, JwtInterceptor jwtInterceptor,
                          JwtTokenStore jwtTokenStore) {
        this.userService = userService;
        this.jwtInterceptor = jwtInterceptor;
        this.jwtTokenStore = jwtTokenStore;
//        this.otpService = otpService;
    }
    @GetMapping("/findByToken")
    public ResponseEntity<?> findByToken(HttpServletRequest request) {
        String token = JwtInterceptor.getInstance().extractTokenFromRequest(request);
        if (token != null) {
            Claims claims =  JWTUtility.getInstance().parseToken(token);
            String username = claims.getSubject();
            if (username != null) {
                User user = userService.findByUsername(username);
                if (user != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, user,"user exist"));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
    }

    @GetMapping("/getGold")
    public ResponseEntity<?> getGold(@RequestParam String username){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, userService.getGold(username),"ok"));
    }

    @GetMapping("/archivedGold")
    public ResponseEntity<?> archivedGold(@RequestParam String username, @RequestParam double gold){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, userService.archiveGold(gold,username),"ok"));
    }


    @GetMapping("/subGold")
    public ResponseEntity<?> subGold(@RequestParam String username, @RequestParam double gold){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, userService.subGold(gold,username),"ok"));
    }


    @GetMapping("/findToken")
    public ResponseEntity<?> findByTk(@RequestParam String token) {
       // String token = JwtInterceptor.getInstance().extractTokenFromRequest(request);
        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
        token = "Bearer " + token;
        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated){
            Claims claims = JWTUtility.getInstance().parseToken(token);
            String username = claims.getSubject();
            if (username != null) {
                User user = userService.findByUsername(username);
                if (user != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, user,"user exist"));
                }else {
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"cannot created"));
                }
            }
        }


        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestParam String token, @RequestBody User user)
    {
        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
        token = "Bearer " + token;

        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated){
            String rawPassword = user.getPassword();
            user.setPassword(PasswordEncoder.getInstance().encodePassword(rawPassword));
            User response =  userService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"user not exist"));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }

    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam String token, @RequestBody User user)
    {
        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
        token = "Bearer " + token;

        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated){
            User response =  userService.updateUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"user not exist"));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }

    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String token, @RequestParam Long id)
    {
        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
        token = "Bearer " + token;

        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated){
            User foundUser = userService.findById(id);
            foundUser.setStatus(0);
            User response =  userService.updateUser(foundUser);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"user not exist"));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }

    }


    @PostMapping("/findAll")
    public ResponseEntity<?> findAll(@RequestParam String token){

        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
        token = "Bearer " + token;

        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, userService.getAllUsers(),"success"));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
    }
    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam String token,@RequestParam Long id){

        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
        token = "Bearer " + token;

        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated)
        {
            if(userService.findById(id) == null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
            }else
            {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, userService.findById(id),"success"));
            }
        }else
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }

    }
    @GetMapping("/new-access-token")
    public ResponseEntity<?> newAccessToken(@RequestParam String token){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, userService.findAll(),"success"));
    }
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtInterceptor.extractUsername(token);
        if (jwtTokenStore.isTokenPresent(username, token)) {
            jwtTokenStore.removeToken(username);
            return ResponseEntity.ok("Logged out successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }

//    @PostMapping("/verify-otp")
//    public ResponseEntity<String> verifyOTP(@RequestParam("idToken") String idToken) {
//        try {
//            FirebaseToken firebaseToken = firebaseService.verifyOTP(idToken);
//            // Perform additional actions with the verified user's information if needed
//            return ResponseEntity.ok("OTP verification successful");
//        } catch (FirebaseAuthException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
//        }
//    }

//    @PostMapping("/send-otp")
//    public ResponseEntity<String> sendOTP(@RequestParam("phoneNumber") String phoneNumber) {
//        otpService.sendOTP(phoneNumber);
//        return ResponseEntity.ok("OTP sent successfully");
//    }
}
