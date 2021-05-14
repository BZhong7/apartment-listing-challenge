package ai.aprillabs.listingapp.web.dto.request;

import ai.aprillabs.listingapp.domain.Listing;

import java.math.BigDecimal;


public class CreateListingRequestDto {

    public void printStuff() {
        System.out.println("Hello World");
    }

    public Listing addListing() {
        Listing listing = new Listing(0, "Brooklyn, NY", 2, 1, BigDecimal.valueOf(2400));

        return listing;
    }

}
