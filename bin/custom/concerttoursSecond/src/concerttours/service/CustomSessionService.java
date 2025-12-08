package concerttours.service;

import java.util.Optional;
import concerttours.model.CustomSessionModel;

public interface CustomSessionService {
    Optional<CustomSessionModel> getCustomSessionToken();
    void updateCustomSessionToken(CustomSessionModel customSession, String token);
}
