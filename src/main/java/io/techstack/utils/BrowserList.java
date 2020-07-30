package io.techstack.utils;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.techstack.providers.driver.DriverWrapper;

public class BrowserList {
    public final List<DriverWrapper> _driversList = new ArrayList<>();
    private Thread _pingDriversThread = null;
    private DriverWrapper driverInUse;

    public DriverWrapper getBrowser() {
        return driverInUse;
    }

    public DriverWrapper getBrowser(int id) {
        driverInUse = null;

        if (id > _driversList.size()) {
            throw new RuntimeException(String.format("Unable to get driver with '%d' id", id));
        }

        //Set new current driver
        driverInUse = _driversList.get(id);

        //Start ping thread if not yet started
        if (_pingDriversThread == null) {
            _pingDriversThread = new Thread(this::pingDrivers);
            _pingDriversThread.start();
        }
        return driverInUse;
    }

    public void addDriver(DriverWrapper driver) {
        _driversList.add(driver);

        //First browser that was added will be main in focus
        if (_driversList.size() == 1) {
            driverInUse = driver;
        }
    }

    public void closeAllBrowsers() {
        if (_pingDriversThread != null) {
            _pingDriversThread.interrupt();
        }
        _driversList.forEach(DriverWrapper::quiteDriver);
    }

    private void pingDrivers() {
        try {
            List<DriverWrapper> driversForPing = _driversList.stream()
                                                             .filter(x -> !x.equals(driverInUse))
                                                             .collect(Collectors.toList());

            for (DriverWrapper driver : driversForPing) {
                try {
                    driver.findElement(By.xpath(".//body"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(15000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

