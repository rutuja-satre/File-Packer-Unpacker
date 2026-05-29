// Importing required packages for file handling and user input
import java.io.*;
import java.util.*;

// Main class
class File_Packer
{
    public static void main(String A[]) throws Exception
    {
        // Variable to store file header information
        String Header = null;

        // Encryption key used for XOR operation
        byte Key = 0x11;
        
        // Variables for reading file data
        int iRet = 0;
        int i = 0, j = 0;

        // Buffer array to store file data temporarily
        byte Buffer[] = new byte[1024];

        // Array to store header in byte format
        byte bHeader[] = new byte[100];

        // Scanner object for user input
        Scanner sobj = new Scanner(System.in);

        // Accept folder name from user
        System.out.println("Enter the name of folder : ");
        String FolderName = sobj.nextLine();

        // Accept packed file name from user
        System.out.println("Enter the name of packed file : ");
        String PackName = sobj.nextLine();

        // Create File object for entered folder
        File fobj = new File(FolderName);

        // Check whether folder exists and it is a directory
        if((fobj.exists()) && (fobj.isDirectory()))
        {
            // Create packed file object
            File PackObj = new File(PackName);

            // Create new packed file
            PackObj.createNewFile();

            // Output stream to write data into packed file
            FileOutputStream foobj = new FileOutputStream(PackObj);

            // Input stream reference
            FileInputStream fiobj = null;

            System.out.println("Folder is present");

            // Get all files from folder
            File fArr[] = fobj.listFiles();

            // Display number of files
            System.out.println("Number of files in the folder are : " + fArr.length);

            // Traverse each file from folder
            for(i = 0; i < fArr.length; i++)
            {
                // Open current file for reading
                fiobj = new FileInputStream(fArr[i]);

                // Process only text files
                if(fArr[i].getName().endsWith(".txt"))
                {
                    // Create header with file name and file size
                    Header = fArr[i].getName() + " " + fArr[i].length();

                    // Make header size fixed to 100 bytes
                    for(j = Header.length(); j < 100; j++)
                    {
                        Header = Header + " ";
                    }

                    // Convert header string into bytes
                    bHeader = Header.getBytes();

                    // Write header into packed file
                    foobj.write(bHeader, 0, 100);

                    // Read data from input file
                    while((iRet = fiobj.read(Buffer)) != -1)
                    {
                        // Encryption logic using XOR operation
                        for(j = 0; j < iRet; j++)
                        {
                            Buffer[j] = (byte)(Buffer[j] ^ Key);
                        }

                        // Write encrypted data into packed file
                        foobj.write(Buffer, 0, iRet);
                    }
                }

                // Close input file
                fiobj.close();    
            }

            // Close packed file
            foobj.close();
        }
        else
        {
            // Display message if folder not found
            System.out.println("There is no such Folder");
        }
    }
}
