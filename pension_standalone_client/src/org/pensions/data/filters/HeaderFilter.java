package org.pensions.data.filters;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.pensions.session.UserSession;

public class HeaderFilter implements ClientRequestFilter {

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		requestContext.getHeaders().add("session-key", UserSession.INSTANCE.getSessionId());
	}

}
