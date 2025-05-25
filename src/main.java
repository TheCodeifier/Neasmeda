import java.util.ArrayList;
import java.util.List;

interface Listener {
    void ontvangBericht(String bericht, String afzender);
    String getNaam();
}

class ChatKanaal {
    private List<Listener> listeners = new ArrayList<>();
    private String naam;

    public ChatKanaal(String naam) {
        this.naam = naam;
    }

    public void voegDeelnemerToe(Listener listener) {
        listeners.add(listener);
    }

    public void verwijderDeelnemer(Listener listener) {
        listeners.remove(listener);
    }

    public void verzendBericht(String bericht, String afzender) {
        System.out.println(afzender + " stuurt een bericht: " + bericht);
        for (Listener listener : listeners) {
            if (!listener.getNaam().equals(afzender)) {
                listener.ontvangBericht(bericht, afzender);
            }
        }
    }
}

class Gebruiker implements Listener {
    private String naam;

    public Gebruiker(String naam) {
        this.naam = naam;
    }

    @Override
    public void ontvangBericht(String bericht, String afzender) {
        System.out.println("[" + naam + "] Nieuwe melding van " + afzender + ": " + bericht);
    }

    public String getNaam() {
        return naam;
    }
}

class Main {
    public static void main(String[] args) {
        ChatKanaal kanaal = new ChatKanaal("Teamkanaal");

        Gebruiker gebruikerA = new Gebruiker("Patrick");
        Gebruiker gebruikerB = new Gebruiker("Olivia");
        Gebruiker gebruikerC = new Gebruiker("Sabrina");

        kanaal.voegDeelnemerToe(gebruikerA);
        kanaal.voegDeelnemerToe(gebruikerB);
        kanaal.voegDeelnemerToe(gebruikerC);

        kanaal.verzendBericht("Hallo team!", "Alice");
    }
}



