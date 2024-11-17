package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.dtos.inputs.AddOfferDTO;
import bg.softuni.mobilelele.model.dtos.inputs.UpdateOfferDTO;
import bg.softuni.mobilelele.model.dtos.outputs.OfferDetailsDTO;
import bg.softuni.mobilelele.model.dtos.outputs.OfferSummaryDTO;
import bg.softuni.mobilelele.model.dtos.outputs.OffersBrandDTO;
import bg.softuni.mobilelele.model.entities.Offer;
import bg.softuni.mobilelele.model.entities.User;
import bg.softuni.mobilelele.model.enums.BrandType;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.service.CurrentUser;
import bg.softuni.mobilelele.service.OfferService;
import bg.softuni.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserService userService;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, CurrentUser currentUser, UserService userService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @Override
    public long createOffer(AddOfferDTO addOfferDTO) {
        Offer offer = this.modelMapper.map(addOfferDTO, Offer.class);
        offer.setCreated(Instant.now());
        offer.setUpdated(Instant.now());
        User user = this.userService.getUserByUsername(this.currentUser.getUsername()).get();
        offer.setUser(user);
        return this.offerRepository.saveAndFlush(offer).getId();
    }

    @Override
    public void updateOffer(long id, UpdateOfferDTO updateOfferDTO) {
        Offer offer = this.offerRepository.findById(id).get();

        this.modelMapper.map(updateOfferDTO, offer);

        offer.setUpdated(Instant.now());

        this.offerRepository.saveAndFlush(offer);
    }

    @Override
    public void deleteOffer(long orderId) {
        this.offerRepository.deleteById(orderId);
    }

    @Override
    public OfferDetailsDTO getOfferDetails(Long id) {
        Optional<Offer> offerOptional = this.offerRepository.findById(id);

        if (offerOptional.isPresent()) {
            Offer offer = offerOptional.get();
            return this.modelMapper.map(offer, OfferDetailsDTO.class);
        } else {
            throw new RuntimeException("Offer not found");
        }
    }

    @Override
    public List<OfferSummaryDTO> getAllOffers() {
        return this.offerRepository
                .findAll()
                .stream()
                .map(offer -> this.modelMapper.map(offer, OfferSummaryDTO.class))
                .toList();
    }

    @Override
    public Map<BrandType, List<OffersBrandDTO>> getAllOffersGroupedByBrand() {
        List<Offer> offers = offerRepository.findAll();

        return offers.stream()
                .map(offer -> {
                    OffersBrandDTO dto = new OffersBrandDTO();
                    dto.setId(offer.getId());
                    dto.setBrand(offer.getBrand());
                    dto.setModel(offer.getModel());
                    dto.setYear(offer.getYear());
                    dto.setEndYear(offer.getYear() + 3);
                    dto.setImageUrl(offer.getImageUrl());
                    return dto;
                })
                .collect(Collectors.groupingBy(OffersBrandDTO::getBrand));
    }

    @Override
    public Optional<Offer> getOfferById(Long id) {
        return this.offerRepository.findById(id);
    }
}
