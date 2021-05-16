package ai.aprillabs.listingapp.service;

import ai.aprillabs.listingapp.domain.Listing;
import ai.aprillabs.listingapp.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingService {

    private final ListingRepository listingRepository;

    @Autowired
    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }

    public Optional<Listing> getListing(Integer id) {
        return listingRepository.findById(id);
    }

    public List<Listing> getAllListings() { return (List<Listing>) listingRepository.findAll(); }
}
