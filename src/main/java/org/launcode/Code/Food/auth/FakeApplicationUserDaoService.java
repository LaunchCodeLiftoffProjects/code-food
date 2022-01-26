//package org.launcode.Code.Food.auth;
//
//import com.google.common.collect.Lists;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.launcode.Code.Food.security.ApplicationUserRole.USER;
//
//public class FakeApplicationUserDaoService implements ApplicationUserDAO{
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
//        return Optional.empty();
//    }
//
//    private List<ApplicationUser> getApplicationUsers() {
//        List<ApplicationUser> applicationUsers = Lists.newArrayList(
//            new ApplicationUser(
//                    "annasmith",
//                    passwordEncoder.encode("password"),
//                    USER.getGrantedAuthorities(),
//
//            )
//        );
//
//        return applicationUsers;
//    }
//
//}
