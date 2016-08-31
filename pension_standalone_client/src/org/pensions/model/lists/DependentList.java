
package org.pensions.model.lists;
import org.pensions.model.DependentDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement
public class DependentList {
	
	private List<DependentListItem> dependents = new ArrayList<DependentListItem>();
	
	public DependentList() {
		// TODO Auto-generated constructor stub
	}

	public List<DependentListItem> getDependents() {
		return dependents;
	}

	public void setDependents(List<DependentListItem> dependents) {
		this.dependents = dependents;
	}

    public List<DependentDTO> getList() {
        List<DependentDTO> dtoList = new ArrayList<>();

        for(DependentListItem item : this.getDependents()) {
            DependentDTO dto = new DependentDTO();

            dto.setId(item.getId());
            dto.setName(item.getName());
            dto.setAddress(item.getAddress());
            dto.setMaritalStatus(item.isMaritalStatus());
            dto.setRelation(item.getRelation());
            dto.setAccountNumber(item.getAccountNumber());
            dto.setNic(item.getNic());
            dto.setDob(item.getDob());

            dtoList.add(dto);
        }

        return dtoList;
    }

    /************************************************************************************/
	
}

@XmlRootElement
class DependentListItem {
    private long id;
    private String name;
    private String address;
    private boolean maritalStatus;
    private String relation;
    private String accountNumber;
    private String nic;
    private String dob;

    public DependentListItem() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
