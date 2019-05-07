
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.Serializable;




class Client {
    public static Socket socket;
    public static ObjectOutputStream out;
    public static ObjectInputStream in;
    public static String HOST = "server";
    public static int INIT_PORT = 9876;
	private static int count = 0;
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        // init socket with init port
        socket = new Socket(HOST, INIT_PORT);
        // send "start" to get new port
        out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject("start");

        // get new port to change connection
        in = new ObjectInputStream(socket.getInputStream());
        String newPort = (String) in.readObject();
        System.out.println("new Port: " + newPort);

        //// switch to new port
        socket = new Socket(HOST, Integer.parseInt(newPort));
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        out.writeObject("bgn");
        String data = (String) in.readObject();
        System.out.println("data :"+data);
        System.out.println("ans : "+data.substring(6,data.length()));
        int leftTime = Integer.parseInt(data.substring(4,5));
        int totalCharactors = data.substring(6,data.length()).length();
        System.out.println("leftTime : "+leftTime);
        System.out.println("totalCharactors : "+totalCharactors);
        while(true){
            try {
				count=leftTime;
				hangmanImage();
                System.out.print("Ans => ");
                String a = (new Scanner(System.in)).nextLine();
                out.writeObject("c01:"+a);
                String response = (String) in.readObject();
                leftTime = Integer.parseInt(response.substring(4,5));
                String ans = response.substring(6, response.length());

                if(response.substring(0, 3).equals("c03")){
                    System.out.println("incorrect , leftTime : " + leftTime);
                }
                if(response.substring(0, 3).equals("fsn")){
                    
                    System.out.println("--CORRECT!!--");
                }
                if(response.substring(0, 3).equals("f01")){
                    System.out.println(" == "+ans);
                    break;
                }
                if(response.substring(0, 3).equals("c04")){
                    System.out.println(" == "+ans);
                    break;
                }
                if(response.substring(0, 3).equals("c02")){
                    System.out.println("this charactor is already used , leftTime : " + leftTime);
                }
                System.out.println(" ==>>>> "+ans);
				System.out.println("=================================================================");


            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if(leftTime == 0){
			System.out.println("=================================================================");
			count=leftTime;
			hangmanImage();
            //System.out.println("YOU LOSE");
			System.out.println("===============================================");
			System.out.println("|  *   *  ***  *   *   *      ***  ***** **** |");
			System.out.println("|   * *  *   * *   *   *     *   * *     *    |");
			System.out.println("|    *   *   * *   *   *     *   * ***** **** |");
			System.out.println("|    *   *   * *   *   *     *   *     * *    |");
			System.out.println("|    *   *   * *   *   *     *   *     * *    |");
			System.out.println("|    *    ***   ***    *****  ***  ***** **** |");
			System.out.println("===============================================");
        }else{
            //System.out.println("YOU WIN");
			System.out.println("===============================================");
			System.out.println("|  *   *  ***  *   *    *     * *** *    *    |");
			System.out.println("|   * *  *   * *   *    *     *  *  **   *    |");
			System.out.println("|    *   *   * *   *    *  *  *  *  * *  *    |");
			System.out.println("|    *   *   * *   *    * * * *  *  *  * *    |");
			System.out.println("|    *   *   * *   *    **   **  *  *   **    |");
			System.out.println("|    *    ***   ***     *     * *** *    *    |");
			System.out.println("===============================================");
        }

        out.close();
        in.close();
        socket.close();
        
    }
	public static void hangmanImage() {
		if (count == 5) {
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (count == 4) {
			System.out.println("   ____________");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   | ");
			System.out.println("___|___");
		}
		if (count == 3) {
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (count == 2) {
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (count == 1) {
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
		}
		if (count == 0) {
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |          _|_");
			System.out.println("   |         / | \\");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
		}
	}
}


