package org.pensions.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"
		)
@JsonSubTypes({
	@Type(value = SpouseDTO.class, name = "spouseDTO"),
	@Type(value = DependentDTO.class, name = "dependentDTO")
})
public class SuperDependent {
	
	public SuperDependent() {
		// TODO Auto-generated constructor stub
	}

}
