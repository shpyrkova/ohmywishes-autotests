package config.mobile;

import com.codeborne.selenide.Configuration;
import mobile.drivers.LocalMobileDriver;

public class MobileDriverProvider {

    public void setMobileConfig() {
        Configuration.browserSize = null;
        Configuration.browser = LocalMobileDriver.class.getName();
    }
}