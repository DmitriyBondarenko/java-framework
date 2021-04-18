package io.techstack.providers.driver.interfaces;

import io.techstack.providers.driver.DriverWrapper;

/**
 * This interface provides ability to interact with web driver instance.
 */
public interface IDriverProvider {

    DriverWrapper getInstance();

    DriverWrapper createDriverInstance();

}
