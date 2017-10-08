package com.reconciliation.demo.service.config;

import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesResourceBundleLocator implements ResourceBundleLocator {

	@Override
	public ResourceBundle getResourceBundle(Locale locale) {
		ResourceBundle.clearCache(Thread.currentThread().getContextClassLoader());
		return ResourceBundle.getBundle("validation_message");
	}
}