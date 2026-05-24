package rs.ac.singidunum.isa.projekat.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.singidunum.isa.projekat.dto.ApiError;
import rs.ac.singidunum.isa.projekat.dto.LoginRequest;
import rs.ac.singidunum.isa.projekat.dto.LoginResponse;
import rs.ac.singidunum.isa.projekat.dto.RegisterRequest;
import rs.ac.singidunum.isa.projekat.entities.Role;
import rs.ac.singidunum.isa.projekat.entities.User;
import rs.ac.singidunum.isa.projekat.repositories.RoleRepositoryInterface;
import rs.ac.singidunum.isa.projekat.repositories.UserRepositoryInterface;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private static final String SECRET_STRING = "asihronomultinemackakapampulica-sa-cincilatorskomdigidojzericom";
    private final SecretKey signingKey = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));
    private final UserRepositoryInterface userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepositoryInterface roleRepository;

    public UserService(UserRepositoryInterface userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepositoryInterface roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword_hash())) {
                String jwt = generateRealJwt(user.getUsername(), user.getRole().getName());

                LoginResponse successResponse = new LoginResponse(jwt, user.getUsername(), user.getRole().getName(), user.getId());
                return new ResponseEntity<>(successResponse, HttpStatus.OK);
            }
        }
        ApiError errorResponse = new ApiError("Invalid username or password", HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (!userRepository.findByUsername(request.getUsername()).isPresent()) {
            User newUser = new User();
            newUser.setUsername(request.getUsername());
            newUser.setEmail(request.getEmail());

            String hashedPassword = passwordEncoder.encode(request.getPassword());
            newUser.setPassword_hash(hashedPassword);

            Optional<Role> defaultRole = roleRepository.findByName("USER");
            newUser.setRole(defaultRole.get());

            userRepository.save(newUser);

            return new ResponseEntity<>("User registered successfully! Please login", HttpStatus.CREATED);
        }else if(userRepository.findByUsername(request.getUsername()).isPresent()){
            return new ResponseEntity<>(new ApiError("Username already exists", HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiError("Couldnt connect",HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String generateRealJwt(String username, String role) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        long expirationMillis = nowMillis + (2 * 60 * 60 * 1000);
        Date expirationDate = new Date(expirationMillis);

        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(signingKey)
                .compact();
    }
}
