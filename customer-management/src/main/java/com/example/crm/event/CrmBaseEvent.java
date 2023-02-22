package com.example.crm.event;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Document(collection="crmevents")
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
	@Id
	private String id= UUID.randomUUID().toString();
}
