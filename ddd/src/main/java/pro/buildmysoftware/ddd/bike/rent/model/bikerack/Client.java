package pro.buildmysoftware.ddd.bike.rent.model.bikerack;

import lombok.Value;

@Value
public class Client {

	private boolean hasSufficientFunds;
	private String name;
}
