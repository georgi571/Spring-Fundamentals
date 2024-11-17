package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dtos.inputs.AddOfferDTO;
import bg.softuni.mobilelele.model.dtos.inputs.UpdateOfferDTO;
import bg.softuni.mobilelele.model.dtos.outputs.OfferDetailsDTO;
import bg.softuni.mobilelele.model.dtos.outputs.OfferSummaryDTO;
import bg.softuni.mobilelele.model.dtos.outputs.OffersBrandDTO;
import bg.softuni.mobilelele.model.entities.Offer;
import bg.softuni.mobilelele.model.enums.BrandType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OfferService {

    long createOffer(AddOfferDTO addOfferDTO);

    void deleteOffer(long orderId);

    OfferDetailsDTO getOfferDetails(Long id);

    List<OfferSummaryDTO> getAllOffers();

    Map<BrandType, List<OffersBrandDTO>> getAllOffersGroupedByBrand();

    Optional<Offer> getOfferById(Long id);

    void updateOffer(long id, UpdateOfferDTO updateOfferDTO);
}
