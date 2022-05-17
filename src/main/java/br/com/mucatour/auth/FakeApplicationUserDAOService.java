package br.com.mucatour.auth;

import br.com.mucatour.security.ApplicationUserRole;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import static br.com.mucatour.security.ApplicationUserRole.*;

import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeApplicationUserDAOService implements ApplicationUserDAO {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDAOService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "caio",
                        passwordEncoder.encode("password"),
                        STUDENT.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "debora",
                        passwordEncoder.encode("password123"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }
}
