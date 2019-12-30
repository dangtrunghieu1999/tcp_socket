package week6;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private static String ipAddress = "127.0.0.1";
	private static int port = 7070;

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket(ipAddress, port);
		System.out.println("Connection Established");
		System.out.println("Enter command");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String command = reader.readLine();
		DataOutputStream os = new DataOutputStream(socket.getOutputStream());
		os.writeUTF(command);
		os.flush();
		BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));		
		String data;
		while((data= is.readLine())!=null) {
			System.out.println(data);
			
		}
		socket.close();

	}
}
