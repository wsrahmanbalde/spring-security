package spring_security.Services.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring_security.Models.AppUser;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final AccountService accountService;

    public UserDetailsServiceImp(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = accountService.findByUsername(username);

        if (user==null){
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> auths = new ArrayList<>();
        user.getAppRoles().forEach(role -> {
            GrantedAuthority auhority = new SimpleGrantedAuthority(role.getRolename());
            auths.add(auhority);
        });
        return new org.springframework.security.core.
                userdetails.User(user.getUsername(),user.getPassword(),auths);
    }
}
