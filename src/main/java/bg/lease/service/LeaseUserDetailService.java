package bg.lease.service;

import bg.lease.model.UserEntity;
import bg.lease.model.user.LeaseUserDetails;
import bg.lease.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LeaseUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public LeaseUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with username " +username));
    }

    private UserDetails map(UserEntity userEntity){
        return new LeaseUserDetails(
                userEntity.getUsername(),
                userEntity.getPassword()
        );
    }
}
