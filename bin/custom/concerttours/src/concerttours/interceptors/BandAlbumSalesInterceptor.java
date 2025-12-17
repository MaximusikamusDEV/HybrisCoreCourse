package concerttours.interceptors;

import concerttours.events.BandAlbumSalesEvent;
import concerttours.model.BandModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import static de.hybris.platform.servicelayer.model.ModelContextUtils.getItemModelContext;

public class BandAlbumSalesInterceptor implements ValidateInterceptor, PrepareInterceptor {
    private final EventService eventService;
    private static final long BIG_SALES = 50000L;
    private static final long NEGATIVE_SALES = 0L;

    public BandAlbumSalesInterceptor(EventService eventService) {
        this.eventService = eventService;
    }


    @Override
    public void onPrepare(Object model, InterceptorContext interceptorContext) throws InterceptorException {
        if (model instanceof BandModel) {
            final BandModel band = (BandModel) model;
            final Long sales = band.getAlbumSales();
            if (sales != null && sales.longValue() < NEGATIVE_SALES) {
                throw new InterceptorException("Album sales must be positive");
            }
        }
    }

    @Override
    public void onValidate(Object model, InterceptorContext interceptorContext) throws InterceptorException {
        if(model instanceof BandModel) {
            final BandModel band = (BandModel) model;
            if(hasBecomeBig(band, interceptorContext)){
                eventService.publishEvent(new BandAlbumSalesEvent(band.getCode(), band.getName(), band.getAlbumSales()));
            }
        }
    }

    private boolean hasBecomeBig(final BandModel band, final InterceptorContext ctx) {
        final Long sales = band.getAlbumSales();
        if (sales != null && sales.longValue() >= BIG_SALES) {
            if (ctx.isNew(band)) {
                return true;
            } else {
                final Long oldValue = getItemModelContext(band).getOriginalValue(BandModel.ALBUMSALES);

                return oldValue == null || oldValue.intValue() < BIG_SALES;
            }
        }
        return false;
    }
}
