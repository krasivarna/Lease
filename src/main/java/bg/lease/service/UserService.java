package bg.lease.service;

import bg.lease.model.UserEntity;
import bg.lease.model.dto.UserRegisterDTO;
import bg.lease.model.mapper.UserMapper;
import bg.lease.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper){
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper=userMapper;
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO){
        UserEntity newUser= userMapper.userDtoToUserEntity(userRegisterDTO);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        newUser=userRepository.save(newUser);
    }
}
