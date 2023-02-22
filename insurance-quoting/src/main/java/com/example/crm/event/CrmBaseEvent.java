package com.example.crm.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@JsonTypeInfo(
		use= JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "eventType"
)
@JsonSubTypes({
	@Type(value=AddressChangedEvent.class, name="ADDRESS_CHANGED")
})
public abstract class CrmBaseEvent {
	private String id;
}
