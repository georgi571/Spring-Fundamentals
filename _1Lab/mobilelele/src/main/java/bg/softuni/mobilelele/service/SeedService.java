package bg.softuni.mobilelele.service;

import java.io.FileNotFoundException;

public interface SeedService {
    void seedRoles();

    void seedAdmin();

    void seedFirstOffers() throws FileNotFoundException;
}
