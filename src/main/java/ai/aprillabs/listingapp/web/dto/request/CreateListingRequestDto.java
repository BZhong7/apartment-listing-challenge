package ai.aprillabs.listingapp.web.dto.request;

import java.math.BigDecimal;


public class CreateListingRequestDto {

    private Integer id;
    private String address;
    private Integer beds;
    private Integer baths;
    private BigDecimal price;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getBaths() {
        return baths;
    }

    public void setBaths(Integer baths) {
        this.baths = baths;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
