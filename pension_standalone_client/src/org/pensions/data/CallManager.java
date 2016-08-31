package org.pensions.data;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.pensions.data.filters.HeaderFilter;


public class CallManager {

	private static WebTarget rootTarget = null;

		public static WebTarget getRootTarget() {
			if(rootTarget == null) {
				try {
					final Client client = ClientBuilder.newBuilder()
							.register(JacksonFeature.class)
							.register(HeaderFilter.class)
							.build();


					//rootTarget = client.target("http://192.168.102.133:8080/pension_serverside/rest");
					//rootTarget = client.target("http://192.168.100.150:8080/pension_serverside/rest");
                    rootTarget = client.target("http://192.168.100.150:8080/restapi/");
                    //rootTarget = client.target("http://192.168.100.150:8080/pension_serverside_demo");
					//rootTarget = client.target("http://localhost:8080/pension_serverside/rest");
                    //rootTarget = client.target("http://localhost:8080/pension_serverside/rest");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return rootTarget;
		} //end of getClient;
		

}
