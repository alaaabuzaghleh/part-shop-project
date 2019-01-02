package com.partsshop.web.exception;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestErrorHandler implements ResponseErrorHandler {

	private static final Logger log = LoggerFactory.getLogger(RestErrorHandler.class);

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		log.error("Response error: {} {}", response.getStatusCode(), response.getStatusText());
	}

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {

		return (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR
				|| httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR);
	}

}
