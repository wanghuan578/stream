package com.spirit.common.web.response.utils;

public interface ErrorTuple {
	<T> T code();
	String text();
}