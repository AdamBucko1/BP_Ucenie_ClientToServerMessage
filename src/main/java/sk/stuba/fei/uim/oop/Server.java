package sk.stuba.fei.uim.oop;

import javax.imageio.IIOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        Socket socket = null;
        InputStreamReader inputStreamReader= null; //to read characters
        OutputStreamWriter outputStreamWriter=null; //character based output stream
        BufferedReader bufferedReader= null; //processes a block of characters instead of one
        BufferedWriter bufferedWriter= null; //
        ServerSocket serverSocket= null; // waits for request to come over the network and it listens on a certain port number for these connections
        // using accept it returns a ''socket'' object
        serverSocket = new ServerSocket(1234); //number must match client

        while (true){

            try {
                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true){

                    String msgFromClient = bufferedReader.readLine();

                    System.out.println("Client: "+msgFromClient);

                    bufferedWriter.write("MSG Recieved.");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (msgFromClient.equalsIgnoreCase("BYE")){
                        break;
                    }

                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedWriter.close();
                bufferedWriter.close();

            } catch (IIOException e){
                e.printStackTrace();
            }

        }


    }
}
