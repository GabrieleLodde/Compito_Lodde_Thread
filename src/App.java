public class App {
    public static void main(String[] args) throws Exception {
        Botte botteVinoRisorsa = new Botte();
        for (int i = 1; i <= 10; i++) {
            Bevitore bevitore = new Bevitore("Bevitore " + i, botteVinoRisorsa);
            bevitore.start();
        }
    }
}