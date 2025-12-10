package concerttours.service.impl;

import concerttours.dao.ContactRequestDao;
import concerttours.service.ContactRequestService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.stereotype.Service;
import org.training.model.ContactRequestModel;

import java.util.List;

@Service
public class DefaultContactRequestService implements ContactRequestService {
    private final ContactRequestDao contactRequestDao;

    public DefaultContactRequestService(ContactRequestDao contactRequestDao) {
        this.contactRequestDao = contactRequestDao;
    }

    @Override
    public ContactRequestModel getContactRequestBySender(String sender) {
        final List<ContactRequestModel> result = contactRequestDao.getContactRequestsBySender(sender);
        int resultCount = result.size();

        if (resultCount == 0)
        {
            throw new UnknownIdentifierException(
                    "ContactRequest with sender '" + sender + "' not found!"
            );
        }
        else if (resultCount > 1)
        {
            throw new AmbiguousIdentifierException(
                    "ContactRequest with sender '" + sender + "' is not unique, " + resultCount
                            + " requests found!"
            );
        }

        return result.get(0);
    }
}
