# FInalProject - Food Truck Tracker

## Description

Food Truck Tracker is a web application that enables a user to track information on a food truck, including menu options, reviews, truck schedule, date and time and location.
User can see the list of all food trucks in the home page or simply search for a specific food truck by keyword. User can click on an individual food truck from the list of trucks or an individual truck found by keyword search. When selected, they can view the menu options, reviews and schedules and then request that truck.
User can also create account, login or logout in the home page. Food truck owner (when logged in) can add new menu item on their own food truck and they can update the newly added information or delete it from the list. Food truck owner can also add new schedule to their list of schedules and they can update the added information on their own food truck.
User can check and update their own information in their profile page when logged in. Food truck owners can check and update their own information and also their truck information and truck requests in their profile page when logged in.

## Backend process

### Steps & overview
Building all entity tables required for food truck project in MySQL workbench

Building all Java entity classes which match the entity tables in MySQL workbench

Building JUnitTests and testing all Java entity classes and relational mappings

Creating required Repositories, Services, ServiceImplementers, and Controllers

Creating the logic and methods in controllers and services to perform the basic CRUD operations of REST API

Testing these routes using Postman

### Technologies used and lessons learned
Creating a JPA Project
  1. Creating entity tables in MySQL workbench
  2. Creating Java entity classes POJO that models the database tables
  3. Mapping POJO using JPA

Configuring a Spring Boot app to publish REST API
  1. Using Spring REST annotations
  2. Using Spring Data JPA to perform all CRUD operations
  3. Sending and receiving JSON


### Technologies used and lessons learned

  | HTTP Verb | URI                           | Request Body | Response Body | Purpose |
  |-----------|-------------------------------|--------------|-----------------------------------------|---------|
  | GET       | `/api/trucks`              |              | Collection of representations of all food trucks  | **List** or **collection** endpoint |
  | GET       | `/api/trucks/1`            |              | Representation of food truck `1` | **Retrieve** endpoint |
  | GET       | `/api/trucks/search/1`   |              | Representation of food trucks with `elk` | **Retrieve** endpoint |
  | POST      | `/api/trucks`              | Representation of a new food truck | Description of the result of the operation | **Create** endpoint |
  | PUT       | `/api/trucks/1`            | Representation of a new food truck `1` | | **Replace** endpoint |
  | DELETE    | `/api/trucks/1`            |              | | **Delete** route |

## Frontend process

### Steps & overview

Adding angular functionality to Food Truck Tracker application

Configuring the application by bringing in dependencies and setting up the file structure

Sending asynchronous requests to the server using http

Using service in the components controller to display data

Building up Create/Read/Update/Delete functionality on the client

### Technologies used and lessons learned

1. Configuring an Angular application
2. Using Components
3. Using Services
4. Using Directives
5. Sending / receiving JSON
6. Sending asynchronous request to Java controller with http
