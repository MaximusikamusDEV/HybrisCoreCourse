/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package concerttours.service;

public interface ConcerttoursSecondService
{
	String getHybrisLogoUrl(String logoCode);

	void createLogo(String logoCode);
}
