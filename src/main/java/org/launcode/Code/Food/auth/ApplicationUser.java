//package org.launcode.Code.Food.auth;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//public class ApplicationUser implements UserDetails {
//
//    private final String password;
//    private  final String username;
//    private final List<? extends GrantedAuthority> grantedAuthorities;
//    private final boolean isAccountNonExpired;
//    private final boolean isAccountNonLocked;
//    private final boolean isCredentialsNonExpired;
//    private final boolean isEnabled;
//
//    public ApplicationUser(String password,
//                           String username,
//                           List<? extends GrantedAuthority> grantedAuthorities,
//                           boolean isAccountNonExpired,
//                           boolean isAccountNonLocked,
//                           boolean isCredentialsNonExpired,
//                           boolean isEnabled) {
//        this.grantedAuthorities = grantedAuthorities;
//        this.password = password;
//        this.username = username;
//        this.isAccountNonExpired = isAccountNonExpired;
//        this.isAccountNonLocked = isAccountNonLocked;
//        this.isCredentialsNonExpired = isCredentialsNonExpired;
//        this.isEnabled = isEnabled;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return grantedAuthorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return isAccountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return isAccountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return isCredentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isEnabled;
//    }
//}
