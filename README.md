# Vital Registration System using RMI and RPC  

## Overview 
This project is all about building a distributed system that makes it easy to manage vital records like births, marriages, and deaths. It uses Remote Method Invocation (RMI) and Remote Procedure Call (RPC) to enable smooth communication between clients and servers, making sure data is managed efficiently. The repository includes everything you need: the client, the RMI server, and the RPC server implementations.


### Features  
- **Distributed System**: Seamless communication between client and server applications.  
- **RMI Implementation**: Utilizes Java RMI for object-oriented remote communication.  
- **RPC Implementation**: Provides a more general mechanism for remote procedure calls.  
- **Data Management**: Handles vital records efficiently, allowing for updates and retrievals.  

## Getting Started  

### Prerequisites  
- Java Development Kit (JDK) 8 or higher  
- Oracle Database (for handling vital records data)
- JDBC Driver (for connecting the Java application to Oracle Database)
- IDE of your choice (e.g., IntelliJ IDEA, Eclipse)  

### Installation  

1. **Clone the repository**:  
   ```sh  
   git clone https://github.com/kibromhft/distributed-vital-registration.git  
   cd distributed-vital-registration

2. **Set up Oracle Database**:  
   - Ensure Oracle Database is running and accessible.  
   - Configure the JDBC connection in the application.  

3. **Compile and run**:  
   - Use your IDE or command line to compile and run the client and server applications.  
 


distributed-vital-registration/
├── CLIENT/         # Client-side application
├── RMISERVER/      # RMI server implementation
└── RPCSERVER/      # RPC server implementation
   


### Technologies Used  
- **Java**: A high-level, object-oriented programming language for building applications.  
- **JavaFX**: A platform for creating rich desktop and internet applications with a modern UI.  
- **Oracle Database**: A robust database management system known for scalability and security.  
- **JDBC**: Enables connection between the Java application and Oracle Database.  
