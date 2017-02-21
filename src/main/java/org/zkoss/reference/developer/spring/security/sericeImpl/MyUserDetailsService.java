package org.zkoss.reference.developer.spring.security.sericeImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zkoss.reference.developer.spring.security.model.User;
import org.zkoss.reference.developer.spring.security.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    // must return a value or throw UsernameNotFoundException
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        User user = userService.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),true,
                true, true, true, getGrantedAuthorities());
    }

    private List<GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        for (UserProfile userProfile : user.getUserProfiles()) {
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        }
        return authorities;
    }
}
