package ai.aprillabs.listingapp.web.dto.request;

import ai.aprillabs.listingapp.domain.UserType;

public class CreateUserRequestDto {
    private Integer id;
    private String name;
    private UserType type;
    private Integer age;
    private Integer numOfReferral;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNumOfReferral() {
        return numOfReferral;
    }

    public void setNumOfReferral(Integer numOfReferral) {
        this.numOfReferral = numOfReferral;
    }
}
