package epam.rd.traydakalo;

import epam.rd.traydakalo.entity.Animal;
import epam.rd.traydakalo.entity.Claim;
import epam.rd.traydakalo.entity.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

@SpringBootApplication
public class RestServiceApplication {
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Component
    public class runner implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            System.out.println(bCryptPasswordEncoder.encode("1"));
            System.out.println(bCryptPasswordEncoder.encode("pass"));
            System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).parallel().findFirst().get());
            Animal animal = new Animal();
            Dog dog = new Dog();
            animal.checkConceptClass(dog);
            Claim claim = new Claim();
            claim.setClaimedAt(ZonedDateTime.now().withZoneSameInstant(ZoneId.of("Asia/Calcutta")));
            System.out.println(claim.getClaimedAt());
        }
    }
}
