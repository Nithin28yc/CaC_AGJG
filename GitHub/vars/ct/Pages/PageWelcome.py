class PageWelcome():
    def __init__(self, driver):
        self.driver=driver

        self.username="txtUsername"
        self.password="txtPassword"
        self.loginbtn="btnLogin"

    def enterUsername(self, uname):
        self.driver.find_element_by_id(self.username).send_keys(uname)

    def enterPassword(self, pw):
        self.driver.find_element_by_id(self.password).send_keys(pw)

    def clickLogin(self):
        self.driver.find_element_by_id(self.loginbtn).click()