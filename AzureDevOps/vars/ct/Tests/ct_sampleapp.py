from selenium import webdriver
import sys
import os
sys.path.append(os.path.join(os.path.dirname(__file__), '../../..'))
ELB = os.environ.get("ELB")
from vars.ct.Pages.PageWelcome import PageWelcome
from vars.ct.Util.BrowserConfig import BrowserConfig
import unittest
class ctHelm(unittest.TestCase):
    print (__file__)
    print (os.path)
    print (os.path.dirname(__file__))
    print ("ELB-", ELB)
    objBrowserConfig=BrowserConfig()
    driver=objBrowserConfig.setBrowser()
    # driver.get("https://opensource-demo.orangehrmlive.com/")
    print ("****************************")
    driver.get("http:"+ELB+"/")
    #driver.get("http://34.71.182.196/sampleapp")
    print ("****************************")
    try:
        print ("****************************")
        print (driver.title)
        assert "Pythonapp" in driver.title
        # assert "OrangeHRM" in driver.title
        print ("Assertion test pass")
    except AssertionError:
        print("Assertion failed. Actual value is %s" % driver.title)
    #login=PageWelcome(driver)
    #login.enterUsername("Admin")
    #login.enterPassword("admin123")
    #login.clickLogin()


    driver.quit()
    print("Test completed")




