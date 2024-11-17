package bg.softuni.mobilelele;

import bg.softuni.mobilelele.service.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final SeedService seedService;

    public CommandLineRunnerImpl(SeedService seedService) {
        this.seedService = seedService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedRoles();
        this.seedService.seedAdmin(); // username: admin, password: admin
        this.seedService.seedFirstOffers();
    }
}
