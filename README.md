# MyParser

This project implements a lexer and parser for a simple programming language using **JFlex** and **BYacc/J**. The lexer (generated by JFlex) tokenizes input code into meaningful symbols, while the parser (generated by BYacc/J) processes these tokens to build a syntax tree and validate the structure of the code.

## Technologies Used

- **Lexer**: [JFlex](https://jflex.de/) (version 1.9.1)
- **Parser**: [BYacc/J](https://byaccj.sourceforge.net) (version 1.15)
- **Programming Language**: Java (JDK 17 or higher). [Download JDK from Oracle](https://www.oracle.com/java/technologies/javase-downloads.html)

## Features

- **Lexical Analysis**: JFlex identifies tokens such as keywords, operators, identifiers, and literals.
- **Syntax Analysis**: BYacc/J validates code structure and builds a parse tree.
- **Support for Basic Operations**: Includes handling of arithmetic and logical expressions.
- **Error Handling**: Catches unrecognized characters and syntax errors, providing feedback to the user.

## Project Structure
```bash
MyParser
├── bin/              # Compiled .class files directory
├── src/              # Source code files
│   ├── Lexical.java
│   ├── Main.java
│   ├── Parser.java
│   └── ParserVal.java
├── lib/              # External libraries
│   └── jflex-full-1.9.1.jar
├── lexer.jflex       # JFlex lexer definition file
├── parser.y          # BYacc/J parser definition file
├── jflex/            # JFlex executable script
├── yacc/             # BYacc/J executable
└── input.txt         # Sample input file
```

## Prerequisites

Ensure you have the following installed:

- **Java Development Kit (JDK)** 17 or higher.
- **JFlex** version 1.9.1.
- **BYacc/J** version 1.15.

### Installation Notes

- Compile the BYacc/J using the provided Makefile **Note: The MakeFile needs to be modified based on your machine**. Ensure the `yacc` executable is added to your system path or project folder.
- Add the `jflex-full-1.9.1.jar` to the `lib` folder.
- Add the `jflex` executable to your system path or project folder. You can check if it’s properly set up by running `jflex -version`.

---

# Project Setup Instructions

## 1. Clone the Repository

```bash
git clone https://github.com/yourusername/your-repo-name.git
cd your-repo-name
```

## 2. Add JFlex and BYacc/J to the Project

- Ensure that the `yacc` executable is in your path.
- Add the `jflex-full-1.9.1.jar` file to the `lib` folder.
- Add the `jflex` executable to your path.

## 3. Generate Lexer and Parser Files

### Generate Lexer

Run JFlex on the lexer definition file (`lexer.jflex`) to generate the `Lexical.java` file.

```bash
java -jar lib/jflex-full-1.9.1.jar lexer.jflex -d src
```

### Generate Parser

Run BYacc/J on the parser definition file (`parser.y`) to generate `Parser.java` and `ParserVal.java`.

```bash
./yacc -J parser.y
```

## 4. Compile the Project

Compile all Java source files and output the compiled `.class` files into the `bin` directory:

```bash
javac -d bin -cp src/*.java
```

## Usage

### Run the Main Program

After compilation, execute the main program with the following command:

```bash
java -cp bin Main
```

The program will read from `input.txt`, tokenize it with the lexer, and parse it with the parser. 

### Modify Input

To test different inputs, edit `input.txt` with the code you wish to analyze. If there are syntax errors or unrecognized characters, the program will provide relevant feedback.

## License

[Include your license information here, if applicable.]

## Contribution Guidelines

[Add contribution guidelines if you would like others to contribute to your project.]
