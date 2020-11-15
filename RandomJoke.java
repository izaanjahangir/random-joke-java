import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RandomJoke {
    public static void main(String[] args) {
        String url = "https://official-joke-api.appspot.com/random_joke";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
                .thenAccept(data -> {
                    String[] lines = parseResponse(data);

                    System.out.println("Joke: " + lines[0]);
                    System.out.println("Punch Line: " + lines[1]);
                }).join();
    }

    public static String[] parseResponse(String data) {
        int indexOfSetup = data.indexOf("\"setup\"", 0);
        String parsableString = data.substring(indexOfSetup, data.length() - 1);

        int indexOfSetupComma = parsableString.indexOf(",");
        String setup = parsableString.substring(0, indexOfSetupComma);
        String punch = parsableString.substring(indexOfSetupComma + 1, parsableString.length());

        int indexOfFirstComma = setup.indexOf(":\"");
        String actualSetupLine = setup.substring(indexOfFirstComma + 2, setup.length() - 1);

        int indexOfSecondComma = punch.indexOf(":\"");
        String actualPunchLine = punch.substring(indexOfSecondComma + 2, punch.length() - 1);

        String[] lines = { actualSetupLine, actualPunchLine };

        return lines;
    }
}
