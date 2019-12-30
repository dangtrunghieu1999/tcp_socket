package week6;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private static int port = 7070;
	private static ArrayList<Student> studentList = new ArrayList<Student>();
	private Server() {
		Student student1 = new Student("123", "Nguyen Van A", "DH17DTB", 8.5,16);
		Student student2 = new Student("124", "Nguyen Van B", "DH17DTB", 5.6,18);
		Student student3 = new Student("106", "Le Thi D", "DH17DTC", 8,17);
		Student student4 = new Student("107", "Nguyen Hoang B", "DH17DTD", 7,22);
		studentList.add(student1);
		studentList.add(student2);
		studentList.add(student3);
		studentList.add(student4);

	}
	public static String findByName(String name) {
		String result = "" ;
		for(Student student: studentList) {
			if(student.getNameSt().equalsIgnoreCase(name)) {
				result += student.getIdSt() + "\t" + student.getNameSt() + "\t" + student.getClassSt() + "\t" + student.getScoreSt() + "\t" + student.getAge() + "\n";
			}
		}
		return result;
	}
	public static String findByLessScore(double score) {
		String result = "";
		for(Student student: studentList) {
			if(student.getScoreSt()<=score) {
				result += student.getIdSt() + "\t" + student.getNameSt() + "\t" + student.getClassSt() + "\t" + student.getScoreSt() + "\t" + student.getAge() + "\n";
			}
		}
		return result;
	}
	public static String findByLessAge(int age) {
	String result ="";
		for(Student student: studentList) {
			System.out.println("student");
			if(student.getAge()<=age) {
				result += student.getIdSt() + "\t" + student.getNameSt() + "\t" + student.getClassSt() + "\t" + student.getScoreSt() + "\t" + student.getAge() + "\n";
			}
		}
		return result;
	}
public static void main(String[] args) throws IOException {
	Server server = new Server();
	ServerSocket serverSocket = new ServerSocket(port);
	Socket socket = serverSocket.accept();
	DataInputStream is = new DataInputStream(socket.getInputStream());
	String command = is.readUTF();
	System.out.println(command);
	String[] array = command.split("\t");
	if(array[0].equalsIgnoreCase("findbyname")) {
		String result = findByName(array[1]);
		PrintWriter os = new PrintWriter(socket.getOutputStream());
		os.write(result);
		os.close();	
	}
	if(array[0].equalsIgnoreCase("findbylessscore")) {
		String result = findByLessScore(Double.parseDouble(array[1]));
		PrintWriter os = new PrintWriter(socket.getOutputStream());
		os.write(result);
		os.close();	
	}
	
	if(array[0].equalsIgnoreCase("findbylessage")) {
		String result = findByLessAge(Integer.parseInt(array[1]));
		PrintWriter os = new PrintWriter(socket.getOutputStream());
		os.write(result);
		os.close();
	}
	socket.close();
	serverSocket.close();
	
}
}
