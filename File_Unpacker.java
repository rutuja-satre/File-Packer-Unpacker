// Import required packages

import java.io.*;
import java.util.*;

class File_Unpacker
{
    public static void main(String A[]) throws Exception
    {
        //Variable creation
        int Filesize = 0;
        int i = 0;
        int iRet = 0;

        byte Key = 0x11;

        Scanner sobj = null;
        String FileName = null;
        String Tokens[] = null;
        String Header = null;

        FileInputStream fiobj  = null;
        FileOutputStream foobj = null;

        byte bHeader[] = new byte[100];
        byte Buffer[] = null;
        
        File fpackobj = null;
        File fobj = null;

        sobj = new Scanner(System.in);

        System.out.println("Enter the name of packed file : ");
        FileName = sobj.nextLine();
        
        fpackobj = new File(FileName);

        if(fpackobj.exists() == false)
        {
            System.out.println("Error : There is no such packed file");
            return;
        }

        fiobj = new FileInputStream(fpackobj);

        //Read the header
        while((iRet = fiobj.read(bHeader,0,100)) != -1) 
        {
            Header = new String(bHeader);

            Header = Header.trim();

            Tokens = Header.split(" ");

            System.out.println("File names : "+Tokens[0]);
            System.out.println("File size : "+Tokens[1]);

            fobj = new File(Tokens[0]);
            
            fobj.createNewFile();

            foobj = new FileOutputStream(fobj);

            Filesize = Integer.parseInt(Tokens[1]);

            //Buffer for reading the data
            Buffer = new byte[Filesize];

            //Read from pack file
            fiobj.read(Buffer,0,Filesize);

            //Decript the data
            for(i = 0; i< Filesize; i++)
            {
                Buffer[i] = (byte)(Buffer[i] ^ Key);
            }
            // Write into extracted file
            foobj.write(Buffer,0,Filesize);
        }
        
    }
}
