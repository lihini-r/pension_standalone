package org.pensions.data.dao;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pensions.data.CallManager;
import org.pensions.model.BranchDTO;
import org.pensions.model.Salary;

public class SalaryDAO {

	private WebTarget baseTarget = null;

    public SalaryDAO() {
        baseTarget = CallManager.getRootTarget().path("salary");
    }
    
    public Salary getSalary(String scale , String grade ,String circular ,String salary,String retireDate,String incrementDate) {
    	Salary dto = null;
        Response response = baseTarget
                .queryParam("scale",scale)
                .queryParam("grade", grade)
                .queryParam("circular", circular)
                .queryParam("salary", salary)
                .queryParam("retired_date", retireDate)
                .queryParam("increment_date", incrementDate)
                .queryParam("target", "2020")
                .request(MediaType.APPLICATION_JSON)
                .get();

        if(response.getStatus() != Response.Status.OK.getStatusCode()) {
            //NotificationManager.info("No match found!", "No matched found for the search criteria");
        	System.out.println("No matched found for the search criteria");
        	return dto;
        }

        //Salary dto = (Salary) response.readEntity(Salary.class);
        dto = (Salary) response.readEntity(Salary.class);

        return dto;
    }
    
}
