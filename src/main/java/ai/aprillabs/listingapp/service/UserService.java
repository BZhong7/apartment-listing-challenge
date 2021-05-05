package ai.aprillabs.listingapp.service;

import ai.aprillabs.listingapp.domain.User;
import ai.aprillabs.listingapp.domain.UserType;
import ai.aprillabs.listingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer getDiscountRateForUser(Integer id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return 0;
        }
        User user = userOpt.get();

        if (UserType.CONTRACTOR.equals(user.getType())) {
            if (user.getAge() < 25) {
                if (user.getNumOfReferral() > 10) {
                    return 20;
                } else {
                    return 10;
                }
            } else {
                return 5;
            }
        } else if (UserType.PARTNER.equals(user.getType())) {
            if (user.getAge() < 25) {
                if (user.getNumOfReferral() > 10) {
                    return 20;
                } else {
                    return 10;
                }
            } else {
                return 5;
            }
        }
        else if (UserType.AGENT.equals(user.getType())) {
            if (user.getAge() < 25) {
                return 10;
            } else {
                return 20;
            }
        } else if (UserType.LANDLORD.equals(user.getType())) {
            return 30;
        } else if (UserType.BROKER.equals(user.getType())) {
            return 40;
        }

        return 0;
    }
}
