# File Packer and Unpacker using Java

## Project Description
File Packer and Unpacker is a Java-based system utility project used to combine multiple text files into a single packed file and later restore them back to their original format.

The project demonstrates concepts of:
- File Handling
- Stream Processing
- Encryption and Decryption
- Buffer Management
- Header Management

The system uses XOR encryption technique to provide basic security for packed files.



# Features

## File Packer
- Packs multiple `.txt` files into one file
- Stores file name and file size in header
- Encrypts file data using XOR operation
- Uses buffer-based file reading

## File Unpacker
- Extracts all files from packed file
- Reads header information
- Decrypts file data
- Restores original text files


# Technologies Used

- Programming Language: Java
- Concepts:
  - File Handling
  - FileInputStream
  - FileOutputStream
  - XOR Encryption
  - Byte Buffering
