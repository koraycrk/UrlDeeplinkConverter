package com.Url.Deeplink.Converter.Enums;

public enum ErrorCodes {
	
	Success(0),
	GeneralError(1),
	UrlNotMatched(3),
	RecordNotFound(4);
	
	private final int value;

	ErrorCodes(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

}
