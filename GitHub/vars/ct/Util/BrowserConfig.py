import os
from selenium import webdriver
from sys import platform
from selenium.webdriver.chrome.options import Options as ChromeOptions

class BrowserConfig():

    def setBrowser(self):
        if platform == "win32":
            driver = webdriver.Chrome("C:/Users/rajalakshmy.k.iyer/PycharmProjects/jenkins-helmdemoapp/vars/ct/Util/chromedriver.exe")

        else:
            options = ChromeOptions()
            options.set_capability("build", "Testing Chrome Options [Selenium 4]")
            options.set_capability("name", "Testing Chrome Options [Selenium 4]")
            options.set_capability("browserName", "chrome")
            driver = webdriver.Remote(
                command_executor="http://selenium-hub:4444",
                options=options
                )
        return driver
