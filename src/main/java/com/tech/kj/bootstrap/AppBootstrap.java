package com.tech.kj.bootstrap;

import com.tech.kj.domain.Contacts;
import com.tech.kj.domain.Emails;
import com.tech.kj.domain.Users;
import com.tech.kj.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class AppBootstrap implements CommandLineRunner {
    private UserRepository userRepository;
    public AppBootstrap(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        log.info("bootstrap started");
        //seedUserEntity();
    }
    public void seedUserEntity(){
        for(int i=0; i < 5; i++){
           Users userEntity = createUserEntityInstance(i);
           Users savedEntity = userRepository.save(userEntity);
           log.info("user saved with id: {}",savedEntity.getId());
        }
    }
    public Users createUserEntityInstance(int recordCount){
        return  Users.builder()
                .firstName("Kishan")
                .lastName("jaiswal")
                .userName("abc"+recordCount)
                .password("password")
                .isDeleted(false)
                .emails(Set.of(Emails.builder()
                        .email(String.format("abc%d@gmail.com",recordCount))
                        .isPrimary(true)
                        //TODO take isVerified from request, in future
                        .isVerified(true)
                        .build()))
                .contacts(Set.of(Contacts.builder()
                        .contact("12345678"+recordCount)
                        .isPrimary(true)
                        .isVerified(true)
                        .build()))
                .build();
    }
}
