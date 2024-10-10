# Dynamic PDF Generator

## Overview

This project is a simple Spring Boot application that generates PDF invoices based on provided seller and buyer information and a list of items. The generated PDF can be downloaded via an API endpoint.

## Technologies Used

- Spring Boot
- iText PDF library
- Java

## API Endpoints

### Generate PDF

- **URL:** `/api/pdf/generate`
- **Method:** `POST`
- **Content-Type:** `application/json`

#### Request Body

To generate a PDF, send a JSON object containing the seller, buyer, and item details in the request body.

**Example JSON Request:**

```json
{
"seller": "XYZ Pvt. Ltd.",
"sellerGstin": "29AABBCCDD121ZD",
"sellerAddress": "New Delhi, India",
"buyer": "Vedant Computers",
"buyerGstin": "29AABBCCDD131ZD",
"buyerAddress": "New Delhi, India",
"items": [
  {
   "name": "Product 1",
   "quantity": "12 Nos",
   "rate": 123.00,
   "amount": 1476.00
  }
 ]
}
