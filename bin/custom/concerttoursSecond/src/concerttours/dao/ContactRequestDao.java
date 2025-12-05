package concerttours.dao;

import org.training.model.ContactRequestModel;

import java.util.List;

public interface ContactRequestDao {
    List<ContactRequestModel> getContactRequestsBySender(String sender);
}
