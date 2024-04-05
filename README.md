# Customer
Only backend has been implemented

pre requisites 
mysql workbench and postman

prerequisite Queries

create database customer

create user 'shivu' idenified by password 'vm123'

grant all on customer.* to 'shivu';

Delete API : http://localhost:8080/customer/deleteCustomer/6c6e53ae-4873-406f-a400-44b04e80d287

Post API : http://localhost:8080/customer/addcustomer
request body 
{
    "firstname": "akshata",
    "lastname": "appaji",
    "street": "4th phase",
    "address": "jpvijay nagar",
    "city": "bengaluru",
    "state": "karnataka",
    "email": "shivu@gmail.com",
    "phone": "8792942600"
}

Get API : http://localhost:8080/customer/customers?lastName=appaji&sort=firstname,asc
this api is used to fetch all customers or it can be used to search customer by first name, city, email or phone

post API(to update customer) : http://localhost:8080/customer/updateCustomer/{id}
request body 
{
    "firstname": "akshata",
    "lastname": "appaji",
    "street": "4th phase",
    "address": "jpvijay nagar",
    "city": "bengaluru",
    "state": "karnataka",
    "email": "shivu@gmail.com",
    "phone": "8792942600"
}

Get API (To get customer by id) : http://localhost:8080/customer/searchCustomerById/{id}


