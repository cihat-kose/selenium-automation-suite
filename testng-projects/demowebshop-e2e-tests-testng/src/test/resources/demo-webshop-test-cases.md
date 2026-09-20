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

## Test Scenario 05: Order Test
- Go to the site
- Log in
- Click on the product named "14.1-inch Laptop" under Computers > Notebook
- Click on the Add to Cart button and verify that the product has been successfully added
- Click on the Shopping cart button to go to your cart and verify that the product is visible
- Click on the Agree checkbox to accept the terms
- Click on the Checkout button
- Fill out all the information on the page that opens and click the Confirm Order button
- Verify that the message "Your order has been successfully processed!" is displayed
