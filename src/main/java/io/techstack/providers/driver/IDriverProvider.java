package io.techstack.providers.driver;

/**
 * This interface provides ability to interact with web driver instance.
 */
public interface IDriverProvider {

    DriverWrapper getInstance();

    DriverWrapper createDriverInstance();

}
