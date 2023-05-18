public class Bevitore extends Thread{
    private Botte b;

    public Bevitore(String nome, Botte b){
        super(nome);
        this.b = b;
    }

    @Override
    public void run(){
        String nome = Thread.currentThread().getName();
        //bevitore arriva al bar
        System.out.println(nome + " arriva al bar");
        while(b.getLitri_tot() > 0){
            try{
                //bevitore attende di bere
                System.out.println(nome + " sta aspettando di bere....");
                long sleepTime = (int) (Math.random() * 2000) + 1000;
                Thread.sleep(sleepTime);
            
                //bevitore tenta di bere
                b.bevi();
                long tempoBevuta = (int) (Math.random()* 3000) + 2000;
                Thread.sleep(tempoBevuta);
            }catch(InterruptedException eccezioneIgnorata) {}
            //bevitore termina di bere
            b.terminaDiBere();
        }
        //bevitore esce dal bar
        System.out.println(nome + " esce dal bar");
    }
}