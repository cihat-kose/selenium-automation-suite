Feature: Bill payment

  Background:
    Given Navigate to the ParaBank
    When Enter credentials for a new account
    And Click login button
    Then User should successfully login to the system
    And Click bill pay link

  Scenario Outline: Bill Pay Functionality

    And User sending the keys in Locaters

      | payeeName         | <payeeName>     |
      | billAddress       | <address>       |
      | billCity          | <city>          |
      | billState         | <state>         |
      | billZipCode       | <zipCode>       |
      | billPhone         | <phone>         |
      | billAccount       | <account>       |
      | billVerifyAccount | <verifyAccount> |
      | billAmount        | <amount>        |

    And Click send payment button

    Then Bill Payment Complete message should be displayed

    Examples:

      | payeeName   | address             | city      | state     | zipCode | phone      | account | verifyAccount | amount |
      | Electric    | Viking street No:12 | Oslo      | Oslo      | 23000   | 9876543210 | 19005   | 19005         | 85     |
      | Water       | Viking street No:13 | Bergen    | Bergen    | 23001   | 9876543211 | 19005   | 19005         | 45     |
      | Natural Gas | Viking street No:14 | Trondheim | Trondheim | 23002   | 9876543212 | 19005   | 19005         | 120    |
