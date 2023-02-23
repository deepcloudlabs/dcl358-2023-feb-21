package com.example.crm.event;

import java.util.UUID;

import org.springframework.data.annotation.Id;

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
	@Type(value=CustomerAcquiredEvent.class, name="CUSTOMER_ACQUIRED"),
	@Type(value=CustomerReleasedEvent.class, name="CUSTOMER_RELEASED")
})
public abstract class CrmBaseEvent {
	@Id
	private String id= UUID.randomUUID().toString();
}
