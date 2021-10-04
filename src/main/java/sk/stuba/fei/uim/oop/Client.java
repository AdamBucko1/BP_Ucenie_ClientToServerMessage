package sk.stuba.fei.uim.oop;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        Socket socket=null;
        InputStreamReader inputStreamReader= null; //to read characters
        OutputStreamWriter outputStreamWriter=null; //character based output stream
        BufferedReader bufferedReader= null; //processes a block of characters instead of one
        BufferedWriter bufferedWriter= null; //

        try {

            socket=new Socket("localhost",1234);

            inputStreamReader=new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader); //Wraping by buffers to improve efficiency
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in); //System.in means keyboard input

            while (true){

                String msgToSend = scanner.nextLine();
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine(); //sends new line character after message
                bufferedWriter.flush(); // flushes the stream. Does this automatically when buffer is full


                System.out.println("Server"+ bufferedReader.readLine()); // waiting for server to send response
                if (msgToSend.equalsIgnoreCase("BYE")){
                    break;
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket!= null){
                    socket.close();
                }
                if (inputStreamReader!=null){
                    inputStreamReader.close();
                }
                if (outputStreamWriter!=null){
                    outputStreamWriter.close();
                }
                if (bufferedReader!=null){
                    bufferedReader.close();
                }
                if (bufferedWriter!=null){
                    bufferedWriter.close();
                }

            } catch (IOException e){
                e.printStackTrace();
            }
        }


    }
}
