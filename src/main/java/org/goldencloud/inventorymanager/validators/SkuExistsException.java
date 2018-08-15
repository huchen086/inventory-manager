package org.goldencloud.inventorymanager.validators;

@SuppressWarnings("serial")
public class SkuExistsException extends Throwable {

    public SkuExistsException(final String message) {
        super(message);
    }
}
