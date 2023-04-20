package NGSoft.assignment.Shaytaskmanager.db;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Data
public class DBDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null){
            throw new UsernameNotFoundException("user ["+username+"] not found in DB");
        }
        return new CustomUserDetails(user);
    }

}
