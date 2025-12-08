package concerttours.attributehandlers;

import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import org.training.model.ContactRequestModel;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SenderDaysAfterSendAttributeHandler extends AbstractDynamicAttributeHandler<Long, ContactRequestModel> {
    @Override
    public Long get(ContactRequestModel model) {
        if (model.getDate() == null){
            return null;
        }

        final ZonedDateTime now = ZonedDateTime.now();
        final ZonedDateTime sendDate = model.getDate().toInstant().atZone(ZoneId.systemDefault());

        if(sendDate.isAfter(now)){
            return 0L;
        }

        final Duration duration = Duration.between(now, sendDate);
        return duration.toDays();
    }
}
