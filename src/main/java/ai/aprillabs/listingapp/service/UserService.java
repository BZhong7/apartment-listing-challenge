package ai.aprillabs.listingapp.service;

import ai.aprillabs.listingapp.domain.User;
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

    public User createUser(User user) { return userRepository.save(user); }

    public Optional<User> getUser(Integer id) { return userRepository.findById(id); }

    public Integer getDiscountRateForUser(Integer id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return 0;
        }
        User user = userOpt.get();

        switch (user.getType()) {
            case CONTRACTOR:
            case PARTNER:
                return calcDiscountBasedOnReferrals(user);
            case AGENT:
                if (user.getAge() < 25) {
                    return 10;
                } else {
                    return 20;
                }
            case LANDLORD: return 30;
            case BROKER: return 40;

            default: return 0;
        }
    }

    private Integer calcDiscountBasedOnReferrals(User user) {
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
}
