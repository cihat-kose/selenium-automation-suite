# Test Cases for Demo Web Shop

## Test Scenario 01: Registration Test
- Go to the site
- Click on the Register button
- Fill out the personal information and click the Register button
- Verify that you have successfully registered

## Test Scenario 02: Negative Registration Test
- Go to the site
- Click on the Register button
- Try to register with the email you registered with earlier
- Click on the Register button
- Verify that the message "The specified email already exists" is displayed

## Test Scenario 03: Login Test
- Go to the site
- Click on the Log in button
- Enter a valid email and password
- Click on the Log in button and verify that you are logged in

## Test Scenario 04: Negative Login Test
- Go to the site
- Click on the Log in button
- Enter an invalid email or password
- Click on the Log in button and verify that you could not log in

## Test Scenario 05: Add Laptop to Cart
- Register a fresh account and log in
- Open Computers > Notebooks > 14.1-inch Laptop
- Add the product to the cart
- Verify the product-added confirmation

Full checkout is covered by the TestNG module, not this JUnit scenario.
