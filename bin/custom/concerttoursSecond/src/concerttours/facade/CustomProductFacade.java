package concerttours.facade;

import concerttours.data.CustomProductData;

public interface CustomProductFacade {
    CustomProductData getCustomProductData(final String code, final String name);
}
