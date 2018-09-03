@login
Feature: Login Action

  Background: Generic Steps

    #Given User is on Home Page
    #When User Navigate to LogIn Page

  Scenario: Successful Login with Valid Credentials

    Given User is on Home Page
     When User Navigate to LogIn Page
     And User enters UserName and Password
      | Username   | Password |
      | pitestamazon@gmail.com | 28juL2016 |