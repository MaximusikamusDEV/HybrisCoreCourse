package concerttours.dao.impl;

import concerttours.dao.CustomSessionDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import concerttours.model.CustomSessionModel;
import java.util.List;
import java.util.Optional;

@Component(value = "customSessionDao")
public class DefaultCustomSessionDao implements CustomSessionDao {
    private final String QUERY_GET_TOKEN = "SELECT {pk} FROM {CustomSession}";
    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public Optional<CustomSessionModel> getCustomSession() {
        FlexibleSearchQuery query = new FlexibleSearchQuery(QUERY_GET_TOKEN);
        List<CustomSessionModel> result = flexibleSearchService.<CustomSessionModel> search(query).getResult();

        return CollectionUtils.emptyIfNull(result).stream().findFirst();
    }
}
