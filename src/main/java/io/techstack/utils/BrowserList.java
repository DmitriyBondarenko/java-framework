package io.techstack.utils;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.techstack.providers.driver.DriverWrapper;

public class BrowserList {
    private final List<DriverWrapper> _drivers = new ArrayList<>();
    private Thread _pingDriversThread = null;
    private DriverWrapper driverInUse;

    public DriverWrapper getBrowser() {
        return driverInUse;
    }

    public DriverWrapper getBrowser(int id) {
        driverInUse = null;

        if (id > _drivers.size()) {
            throw new RuntimeException(String.format("Unable to get driver with '%d' id", id));
        }

        //Set new current driver
        driverInUse = _drivers.get(id);

        //Start ping thread if not yet started
        if (_pingDriversThread == null) {
            _pingDriversThread = new Thread(this::pingDrivers);
            _pingDriversThread.start();
        }
        return driverInUse;
    }

    public void addDriver(DriverWrapper driver) {
        _drivers.add(driver);

        //First browser that was added will be main in focus
        if (_drivers.size() == 1) {
            driverInUse = driver;
        }
    }

    public List<DriverWrapper> getAllBrowsers() {
        if (_pingDriversThread != null) {
            _pingDriversThread.interrupt();
        }
        return _drivers;
    }

    private void pingDrivers() {
        try {
            List<DriverWrapper> driversForPing = _drivers.stream()
                                                         .filter(x -> !x.equals(driverInUse))
                                                         .collect(Collectors.toList());

            for (DriverWrapper driver : driversForPing) {
                try {
                    driver.findElement(By.xpath(".//body"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(20000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

