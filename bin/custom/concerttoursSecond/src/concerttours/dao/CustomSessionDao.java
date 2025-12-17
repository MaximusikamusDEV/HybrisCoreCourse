package concerttours.dao;

import java.util.Optional;
import concerttours.model.CustomSessionModel;

public interface CustomSessionDao {
    Optional<CustomSessionModel> getCustomSession();
}
