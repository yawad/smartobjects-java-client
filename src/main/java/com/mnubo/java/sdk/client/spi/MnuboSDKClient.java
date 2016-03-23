package com.mnubo.java.sdk.client.spi;

/**
 * Mnubo SDK Client. This interface gives access to all mnubo's sdk clients.
 *
 * @author Mauro Arias
 * @since 2015/07/22
 */
public interface MnuboSDKClient {

    /**
     * Returns SmartObject Client, giving access to handle mnubo smartObjects.
     *
     * @return ObjectSDK interface.
     *
     */
    ObjectsSDK getObjectClient();

    /**
     * Returns Event Client, giving access to handle mnubo events.
     *
     * @return EventSDK interface
     *
     */
    EventsSDK getEventClient();

    /**
     * Returns Owner Client, giving access to handle mnubo Owners.
     *
     * @return OwnerSDK interface
     *
     */
    OwnersSDK getOwnerClient();

}
