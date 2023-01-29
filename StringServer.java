import java.io.IOException;
import java.net.URI;

class StringHandler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String input = "";

    public String handleRequest(URI url) {
        
        // format of add-message path /add-message?s=<string>
        
        if (url.getPath().contains("/add-message")) {
            
            String[] parameters = url.getQuery().split("=");
            
            if (parameters[0].equals("s")) {
                if (input.length() == 0) {
                    input = parameters[1];
                } else {
                    input = input + "\n" + parameters[1];
                }
                return input;
            }
        }
        return "404 Not Found!";

    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new StringHandler());
    }
}
