/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package concerttours.setup;

import static concerttours.constants.ConcerttoursSecondConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import concerttours.constants.ConcerttoursSecondConstants;
import concerttours.service.ConcerttoursSecondService;


@SystemSetup(extension = ConcerttoursSecondConstants.EXTENSIONNAME)
public class ConcerttoursSecondSystemSetup
{
	private final ConcerttoursSecondService concerttoursSecondService;

	public ConcerttoursSecondSystemSetup(final ConcerttoursSecondService concerttoursSecondService)
	{
		this.concerttoursSecondService = concerttoursSecondService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		concerttoursSecondService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ConcerttoursSecondSystemSetup.class.getResourceAsStream("/concerttoursSecond/sap-hybris-platform.png");
	}
}
