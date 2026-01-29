# MongoDB JSON to XML Conversion System

This project contains a Java application designed to retrieve documents from a MongoDB database, parse them as JSON, and convert them into XML format.  
The program connects to a local MongoDB instance, reads all documents from a specific collection, and prints the converted XML output to the console.

This project demonstrates how to work with:
- MongoDB using the Java Driver
- BSON and JSON document handling
- Data format transformation (JSON → XML)
- Exception handling in Java

## Development Environment Information

- **Operating System:** Windows 10 / 11  
- **IDE used:** Visual Studio Code or IntelliJ IDEA  
- **Java version:** Java 24  
- **Database:** MongoDB  
- **Libraries used:**  
  - MongoDB Java Driver (`com.mongodb.client`)  
  - `org.json` for JSON parsing  
  - `org.bson` for BSON document handling  

## What This Program Does

The program performs the following operations:

### 1. Connects to MongoDB  
Establishes a connection to a local MongoDB server using the following URI:

### 2. Reads documents from a collection  
Accesses the database  and retrieves all documents stored in the collection.

### 3. Converts JSON to XML  
Each MongoDB document is:
- Retrieved as a BSON `Document`
- Converted to a JSON string
- Parsed into a `JSONObject`
- Transformed into XML format using the `FormatConverter` class

### 4. Displays the result  
The generated XML for each document is printed to the console for verification.

## Project Structure

- `Main.java`: Handles the MongoDB connection, document iteration, and error management.
- `FormatConverter.java`: Contains the logic to convert JSON objects into XML format.

## How to Run the Program

1. Clone or download this repository.  
2. Make sure MongoDB is running locally on port `27017`.  
3. Ensure the MongoDB Java Driver and `org.json` library are included in the project dependencies.  
4. Create a database named `practica_java` with a collection called `elements`.  
5. Insert JSON documents into the `elements` collection.  
6. Run the `Main.java` file.  
7. The program will read all documents from MongoDB and print their XML representation to the console.


## Error Handling

- Connection errors to MongoDB are caught and displayed in the console.
- JSON parsing errors are handled individually for each document, allowing the program to continue processing the remaining data.

## Learning Objectives

- Understand how to connect Java applications to MongoDB
- Work with BSON and JSON formats
- Perform data format conversion
- Apply structured exception handling in Java
