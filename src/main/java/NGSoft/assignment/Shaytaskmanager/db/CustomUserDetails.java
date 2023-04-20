package NGSoft.assignment.Shaytaskmanager.db;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
public class CustomUserDetails implements UserDetails {


    private final User userAccount;

    public Collection<? extends GrantedAuthority> getAuthorities() {
       return Collections.singletonList(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "USER";
            }
        });
    }

    @Override
    public String getPassword() {
        return userAccount.getPassword();
    }

    @Override
    public String getUsername() {
        return userAccount.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userAccount.getIsActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userAccount.getIsActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userAccount.getIsActive();
    }

    @Override
    public boolean isEnabled() {
        return userAccount.getIsActive();
    }
}
