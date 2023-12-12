# for testing the app
# we'll use the selenium automation tools

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By

### EXAMPLE, QUICKSTART
DRIVER = webdriver.Firefox()
# driver.get("http://www.python.org")
# assert "Python" in driver.title
# elem = driver.find_element(By.NAME, "q")
# elem.clear()
# elem.send_keys("pycon") # send the keys input
# elem.send_keys(Keys.RETURN)
# assert "No results found." not in driver.page_source
# driver.close()

BASE_URL = "http://127.0.0.1:5000"

URL_CREATE_ACCOUNT = BASE_URL + "/create"
URL_LOGIN_ACCOUNT  = BASE_URL + "/login"
