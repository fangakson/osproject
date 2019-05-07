
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MultiThreadRespond implements Runnable{

    private ServerSocket server;
    private int port;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private static String [] movies = {"theavenger","ironman","deadpool","superman","aquaman","womderwoman","avartar","antman","batman","blackpanther","captainamerica","theflash","venom","titanic","readyplayerone","spiderman","conjuring","yourname","starwar","jurassicworld"};
    public MultiThreadRespond(int port){
        this.port = port;
        try{
           server = new ServerSocket(port);
        }catch(Exception e){
            
        }

    }

    @Override
    public void run(){
        int leftTime = 5;
        List<String> usedCharactor = new ArrayList<>();
        String ans = "";
        String currentMovie = "";
        while(true){
            try{
                Socket socket = server.accept();
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());
                while(true){
                    String message = (String) in.readObject();
                    switch(message.substring(0, 3)){
                        case "bgn" : { // GET_DATA
                            currentMovie = randomMovie();
                            for(int i = 0 ;i < currentMovie.length() ; i++){
                                String c = currentMovie.substring(i, i+1);
                                if(c == " "){
                                    ans += " ";
                                }else{
                                    ans += "_";
                                }
                            }
                            out.writeObject("fsn:" + leftTime + ":" + ans);
                            break;
                        }
                        case "c01" : { // ANS Format => 150:X
                            String answer = message.substring(4, 5);
                            System.out.println("answer : " + answer);
                            // check duplicate
                            Boolean isDup = false;
                            for(String d : usedCharactor){
                                if(d.equals(answer)){
                                    isDup = true;
                                    break;
                                }
                            }

                            if(isDup){
                                out.writeObject("c02:"+ leftTime + ":" + ans);
                            }else{
                                usedCharactor.add(answer);
                                // check correct 
                                Boolean isCorrect = false;
                                List<Integer> correctIndex = new ArrayList<>();
                                for(int i = 0 ; i < currentMovie.length() ; i++){
                                    String c = currentMovie.substring(i, i+1);
                                    if(c.equals(answer)){
                                        isCorrect = true;
                                        correctIndex.add(i);
                                        // break;

                                    }
                                }
                                for(int index : correctIndex){
                                    String s = answer;
                                    String last = ans.substring(index + 1, ans.length());
                                    if(index == 0){
                                        ans = s + last;
                                    }else{
                                        String first = ans.substring(0, index);
                                        ans = first + s + last;
                                    }
                                }
                                if(isCorrect){
                                    if(ans.replace("_", " ").equals(currentMovie)) out.writeObject("f01:"+ leftTime + ":" + ans);
                                    else out.writeObject("fsn:" + leftTime + ":" + ans);
                                }else{
                                    leftTime--;
                                    if(leftTime == 0) out.writeObject("c04:"+  leftTime + ":" + ans);
                                    else out.writeObject("c03:"+  leftTime + ":" + ans);
                                }
                            }
                        }
                        default : {}
                    }
                    if(message.equalsIgnoreCase("exit")) break;
                }
               }catch(Exception e){
                System.out.println(e);
            }   

        }
    }
    private String randomMovie(){
        return movies[(new Random()).nextInt(movies.length)];
    }
}
