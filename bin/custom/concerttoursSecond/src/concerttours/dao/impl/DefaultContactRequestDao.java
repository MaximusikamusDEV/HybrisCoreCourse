package concerttours.dao.impl;

import concerttours.dao.ContactRequestDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.springframework.stereotype.Repository;
import org.training.model.ContactRequestModel;

import java.util.List;

@Repository
public class DefaultContactRequestDao implements ContactRequestDao {
    private final FlexibleSearchService flexibleSearchService;
    private final String QUERY_GET_CONTACT_REQ_BY_NAME = "SELECT {PK} FROM {ContactRequest} WHERE {sender} = ?sender";

    public DefaultContactRequestDao(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<ContactRequestModel> getContactRequestsBySender(String sender) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(QUERY_GET_CONTACT_REQ_BY_NAME);
        query.addQueryParameter("sender", sender);
        final SearchResult<ContactRequestModel> result = this.flexibleSearchService.search(query);

        return result.getResult();
    }
}
