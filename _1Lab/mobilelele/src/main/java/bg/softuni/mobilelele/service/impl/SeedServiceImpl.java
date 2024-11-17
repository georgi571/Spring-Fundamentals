package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.dtos.inputs.SeedOfferDTO;
import bg.softuni.mobilelele.model.entities.Offer;
import bg.softuni.mobilelele.model.entities.Role;
import bg.softuni.mobilelele.model.entities.User;
import bg.softuni.mobilelele.model.enums.*;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.repository.RoleRepository;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.SeedService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class SeedServiceImpl implements SeedService {
    private final String PATH = "_1Lab/mobilelele/src/main/resources/preparation/offers.json";

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public SeedServiceImpl(OfferRepository offerRepository, UserRepository userRepository, RoleRepository roleRepository, Gson gson, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedRoles() {
        if (roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setRole(UserRole.ADMIN);
            Role user = new Role();
            user.setRole(UserRole.USER);
            this.roleRepository.saveAndFlush(admin);
            this.roleRepository.saveAndFlush(user);
        }
    }

    @Override
    public void seedAdmin() {
        if (this.userRepository.count() == 0) {
            User user = new User();

            Role admin = this.roleRepository.findByRole(UserRole.ADMIN);

            user.setRole(admin);
            user.setCreated(LocalDateTime.now());
            user.setAge(30);
            user.setFirstName("Admin");
            user.setLastName("Admin");
            user.setEmail("admin@abv.bg");
            user.setPassword("b7f1cc29d3be07bf4022cbff7ad6f77b1982b98de4d28530edeec3503e1267f89798487b3b7ad29a50e9eb4608928b0e");
            user.setUsername("admin");
            user.setImageUrl("https://cdn.myanimelist.net/images/characters/2/82075.jpg");
            user.setBanned(false);
            user.setActive(false);
            this.userRepository.saveAndFlush(user);
        }
    }

    @Override
    public void seedFirstOffers() throws FileNotFoundException {
        if (this.offerRepository.count() == 0) {
            SeedOfferDTO[] seedOfferDTOS = this.gson.fromJson(new FileReader(PATH), SeedOfferDTO[].class);
            for (SeedOfferDTO seedOffer : seedOfferDTOS) {
                Offer offer = this.modelMapper.map(seedOffer, Offer.class);
                BrandType brandType = BrandType.valueOf(seedOffer.getBrand());
                EngineType engineType = EngineType.valueOf(seedOffer.getEngine());
                ModelType modelType = ModelType.valueOf(seedOffer.getModel());
                TransmissionType transmissionType = TransmissionType.valueOf(seedOffer.getTransmission());
                User user = this.userRepository.findById(seedOffer.getUserId()).get();
                offer.setBrand(brandType);
                offer.setEngine(engineType);
                offer.setModel(modelType);
                offer.setTransmission(transmissionType);
                offer.setUser(user);
                offer.setCreated(Instant.now());
                offer.setUpdated(Instant.now());
                this.offerRepository.saveAndFlush(offer);
            }
        }
    }
}
