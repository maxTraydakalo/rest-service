package epam.rd.traydakalo;

import epam.rd.traydakalo.entity.Claim;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Component
    public class runner implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {

            Claim claim=new Claim();
            claim.setClaimedAt(ZonedDateTime.now().withZoneSameInstant(ZoneId.of("Asia/Calcutta")));
            System.out.println(claim.getClaimedAt());
        }
    }
}
