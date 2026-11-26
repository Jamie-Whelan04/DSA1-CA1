# Shop Management System

**DSA1 – CA1** | Jamie Whelan

A Java application for managing a shop's day-to-day data, built as part of our Data Structures & Algorithms 1 continuous assessment.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Data Structures & Algorithms Used](#data-structures--algorithms-used)
- [Author](#author)

---

## Overview

The Shop Management System lets a user manage a shop's records, such as products, stock and sales, through a Java application. The project focuses on choosing and implementing suitable data structures and algorithms to store, search and sort that data efficiently.


## Features

- Add, update and remove products
- Track stock levels
- Search for products (e.g. by name or ID)
- Sort products by different criteria (e.g. price, name, quantity)
- Record and view sales / transactions

## Project Structure

```
DSA1-CA1/
├── src/
   ├──main
      ├──Java
         ├──LinkedList
            ├──node.java
            ├──projectLinkedList.java
         ├──Main
            ├──app.java
         ├──controllers
            ├──menuController.java
         ├──models
            ├──Aisle.java
            ├──floorArea.java
            ├──good.java
            ├──shelf.java
            ├──supermarket.java
         ├──org/example
            class.java
      ├──resources
         ├──menu.fxml
   ├──test/java/models
      ├──FloorAreaTest.java
      ├──shelfTest.java
      ├──supermarketTest.java
├── pom.xml
├── .gitignore
└── README.md
```


## Getting Started

### Prerequisites

- Java JDK [version, e.g. 17 or later]
- Maven (or an IDE with Maven support, such as IntelliJ IDEA)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Jamie-Whelan04/DSA1-CA1.git
   cd DSA1-CA1
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application from your IDE by running the class that contains the `main` method, or from the command line:
   ```bash
   java -cp target/classes [package.]MainClass
   ```

## Usage


```
Example:
1) Add product
2) Remove product
3) Search
4) Add Aisles
...
```

<img width="1198" height="1090" alt="image" src="https://github.com/user-attachments/assets/8cbdb454-d70c-46e4-b491-c1b1dd8ae6c9" />

<img width="1195" height="1081" alt="image" src="https://github.com/user-attachments/assets/9eb50cbb-0a3c-4f04-ac67-92f222ad6509" />


## Author

**Jamie Whelan** – [@Jamie-Whelan04](https://github.com/Jamie-Whelan04)

---

*Created for DSA1 – CA1.*
