package concerttours.service.impl;

import concerttours.dao.CustomSessionDao;
import concerttours.service.CustomSessionService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import concerttours.model.CustomSessionModel;
import java.util.Optional;

@Component(value = "defaultCustomSessionService")
public class DefaultCustomSessionService implements CustomSessionService {
    @Autowired
    private CustomSessionDao customSessionDao;
    @Autowired
    private ModelService modelService;

    public void setCustomSessionDao(CustomSessionDao customSessionDao) {
        this.customSessionDao = customSessionDao;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public Optional<CustomSessionModel> getCustomSessionToken() {
        return customSessionDao.getCustomSession();
    }

    @Override
    public void updateCustomSessionToken(CustomSessionModel customSession, String token) {
        customSession.setToken(token);
        modelService.save(customSession);
    }
}
