package ai.aprillabs.listingapp.web;

import ai.aprillabs.listingapp.domain.Listing;
import ai.aprillabs.listingapp.service.ListingService;
import ai.aprillabs.listingapp.web.dto.request.CreateListingRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path="/listings")
public class ListingController {

    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    //Extract data from DTO and put it in a Listing object. Add it to ListingRepository when complete.
    @PostMapping
    public ResponseEntity<Listing> createListing(@Valid @RequestBody CreateListingRequestDto listingDto) {
        Listing listing = new Listing();

        //Get the current time, convert it into a string, then add it to listing
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"); //ISO 8601 format
        String formattedDateTime = currentDateTime.format(formatter);

        listing.setAddress(listingDto.getAddress());
        listing.setBeds(listingDto.getBeds());
        listing.setBaths(listingDto.getBaths());
        listing.setPrice(listingDto.getPrice());
        listing.setCreatedAt(formattedDateTime);

        Listing createdListing = listingService.createListing(listing);
        return new ResponseEntity<>(
                createdListing,
                HttpStatus.CREATED);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Listing> getListing(@Valid @PathVariable Integer id) {
        return listingService.getListing(id)
                .map(listing -> new ResponseEntity<>(
                        listing,
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Returns a list of listings
    @GetMapping(path="/everylisting")
    @ResponseBody
    public Collection<Listing> getAllListings() {
        List<Listing> everyListing = listingService.getAllListings();

        return everyListing;
    }

    @PostMapping(path="/delete")
    public ResponseEntity deleteListing(@Valid @RequestBody CreateListingRequestDto listingRequestDto) {
        try {
            listingService.deleteListing(listingRequestDto.getId());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
