# Data Format Conversion System

This project contains a Java program designed to convert data between different structured formats: XML, JSON, and BSON.  
The application reads files in one format, processes their content, and generates equivalent output files in another format.  
It demonstrates how to handle file input/output, parse and generate JSON, transform XML structures, and work with BSON binary data.

## Development Environment Information

- **Operating System:** Windows 10 / 11  
- **IDE used:** Visual Studio Code or IntelliJ IDEA  
- **Java version:** Java 24  
- **Libraries used:**  
  - org.json for JSON parsing and generation  
  - java.io for file handling  
  - Custom class FormatConverter for format transformations  

## What This Program Does

The program performs the following operations:

### 1. XML to JSON  
Reads an XML file, converts its structure into a JSON object, and writes the result to a `.json` file.

### 2. JSON to XML  
Loads a JSON file, transforms it into XML format, and saves the output as `.xml`.

### 3. JSON to BSON  
Parses a JSON file and converts its content into BSON binary format, generating a `.bson` file.

### 4. BSON to JSON  
Reads a BSON file, decodes its binary data, and produces a readable JSON file.

Each conversion prints the intermediate content to the console for verification.

## How to Run the Program

1. Clone or download this repository.  
2. Ensure that the `org.json` library is included in your project's classpath.  
3. Place the input files (`messi.xml`, `neymar.json`, `messi.bson`) inside `src/main/resources`.  
4. Run the file `Main.java`.  
5. The program will automatically execute all conversions in sequence and generate the corresponding output files.
