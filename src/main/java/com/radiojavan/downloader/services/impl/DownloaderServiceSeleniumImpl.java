package com.radiojavan.downloader.services.impl;

import com.google.common.base.Strings;
import com.radiojavan.downloader.models.ResponseModel;
import com.radiojavan.downloader.services.DownloaderService;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DownloaderServiceSeleniumImpl implements DownloaderService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final String FIREFOX_GECKO_DRIVER_SYSTEM_PROPERTY_NAME = "webdriver.gecko.driver";

    @Value("${config.selenium.webdriver.firefox.geckodriver.executable:#{\"\"}}")
    private String GECKO_DRIVER_EXECUTABLE_PATH;

    @PostConstruct
    public void init() {
        if (Strings.isNullOrEmpty(GECKO_DRIVER_EXECUTABLE_PATH)) {
            LOGGER.warn("init(). geckodriver executable path is null or empty.");
        }

        System.setProperty(FIREFOX_GECKO_DRIVER_SYSTEM_PROPERTY_NAME, GECKO_DRIVER_EXECUTABLE_PATH);
    }

    @Override
    public String getFileUrl(String playerUrl) {

        if (Strings.isNullOrEmpty(playerUrl)) {
            LOGGER.info("getFileUrl(). playerUrl is either null or empty. Aborting request...");
            return null;
        }

        LOGGER.info("getFileUrl(). starting the process for playerUrl <{}>", playerUrl);

        WebDriver webDriver = new FirefoxDriver();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;

        webDriver.get(playerUrl);
        String soundUrl = (String) javascriptExecutor.executeScript("return RJ.currentSound.url");

        webDriver.quit();

        LOGGER.info("getFileUrl(). successful operation on finding file url of <{}> with result <{}>", playerUrl, soundUrl);

        return soundUrl;
    }
}
