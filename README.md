# QuarkusCustomerPortal

This document provides an overview of the Customer API, including endpoints for creating and querying customers.

# Table of Contents

- [Customer Model](#customer-model)
- [Endpoints](#endpoints)
  - [Create Customer](#create-customer)
  - [Get Customers](#get-customers)

## Customer Model

The `Customer` model includes the following attributes:

- Customer Number
- First Name
- Last Name
- Spending Limit
- Addresses (List of Address objects)

The `Address` model includes:

- Address Type
- Street
- City
- State
- Zip


# Problem Statement 1

-  Create an API to create customers
-  Need an API to get Customers 
Query by Name , city , state 
If no parameter is supplied get all customers 


## Endpoints


### Create Customer

```json
POST /customers/create

{
  "firstName": "John",
  "lastName": "Doe",
  "spendingLimit": 1000.0,
  "addresses": [
    {
      "addressType": "Home",
      "street": "123 Main St",
      "city": "Cityville",
      "state": "CA",
      "zip": "12345"
    }
  ]
}
```

### Response
```json
Customer Created Successfully with customer Id : 65d8b0ff8b35683814a23dc4
```
# Kafka Publish 

Post the customer is created in Db , a message is published to kafka

<img width="986" alt="Screenshot 2024-02-23 at 8 22 59 PM" src="https://github.com/ankitpyc/QuarkusCustomerPortal/assets/13076644/eb3fd2d6-0591-4857-873e-53156b18c493">


### Search Customer

```json
POST /customers/search
{
    "firstName": "Ankit",
    "city": "Bengaluru"
}
```

#### Response
```json
[
    {
        "addresses": [
            {
                "address1": "address1",
                "address2": "address2",
                "addressType": "Home",
                "city": "Bengaluru",
                "state": "Karnataka",
                "zipcode": "208022"
            }
        ],
        "age": 24,
        "customerId": "65d8b0ff8b35683814a23dc4",
        "firstName": "Ankit",
        "lastName": "Saxena"
    },
    {
        "addresses": [
            {
                "address1": "Kormangala",
                "address2": "address2",
                "addressType": "Home",
                "city": "Bengaluru",
                "state": "Karnataka",
                "zipcode": "208022"
            }
        ],
        "age": 24,
        "customerId": "65d8b42f8b35683814a23dc5",
        "firstName": "Ankit",
        "lastName": "Verma"
    }
]
```
# Problem Statement 2

You are provided with two lists of Customers 

A and B as below 

List<Customer> A , List<Customer> B

Please code the for the following questions

- Customers only in list A
- Customers only List B
- Customers in both A and B
  
## Match Customers
```json

POST /customers/matchCustomers

{
    "customersListA": [
        {
            "firstName": "wwewed",
            "lastName": "wdxwdxwe",
            "age": 0,
            "city": "Panki",
            "addresses": [
                {
                    "addressType": "Home",
                    "address1": "address1",
                    "address2": "address2",
                    "city": "Bengaluru",
                    "state": "Karnataka",
                    "zipcode": "208022"
                }
            ]
        },
                {
            "firstName": "Ankit2",
            "lastName": "wdxwdxwe",
            "age": 0,
            "city": "Panki",
            "addresses": [
                {
                    "addressType": "Home",
                    "address1": "address1",
                    "address2": "address2",
                    "city": "Bengaluru",
                    "state": "Karnataka",
                    "zipcode": "208022"
                }
            ]
        }
    ],
    "customersListB": [
        {
            "firstName": "wwewed",
            "lastName": "wdxwdxwe",
            "age": 0,
            "city": "Panki",
            "addresses": [
                {
                    "addressType": "Home",
                    "address1": "address1",
                    "address2": "address2",
                    "city": "Bengaluru",
                    "state": "Karnataka",
                    "zipcode": "208022"
                }
            ]
        },
        {
            "firstName": "Ankit4",
            "lastName": "wdxwdxwe",
            "age": 0,
            "city": "Panki",
            "addresses": [
                {
                    "addressType": "Home",
                    "address1": "address1",
                    "address2": "address2",
                    "city": "Bengaluru",
                    "state": "Karnataka",
                    "zipcode": "208022"
                }
            ]
        }
    ]
}

```
### Response

```json
{
    "inBothList": [
        {
            "addresses": [
                {
                    "address1": "address1",
                    "address2": "address2",
                    "addressType": "Home",
                    "city": "Bengaluru",
                    "state": "Karnataka",
                    "zipcode": "208022"
                }
            ],
            "age": 0,
            "firstName": "wwewed",
            "lastName": "wdxwdxwe"
        }
    ],
    "onlyInListA": [
        {
            "addresses": [
                {
                    "address1": "address1",
                    "address2": "address2",
                    "addressType": "Home",
                    "city": "Bengaluru",
                    "state": "Karnataka",
                    "zipcode": "208022"
                }
            ],
            "age": 0,
            "firstName": "Ankit2",
            "lastName": "wdxwdxwe"
        }
    ],
    "onlyInListB": [
        {
            "addresses": [
                {
                    "address1": "address1",
                    "address2": "address2",
                    "addressType": "Home",
                    "city": "Bengaluru",
                    "state": "Karnataka",
                    "zipcode": "208022"
                }
            ],
            "age": 0,
            "firstName": "Ankit4",
            "lastName": "wdxwdxwe"
        }
    ]
}

```

