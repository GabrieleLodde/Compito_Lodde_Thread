public class Botte {
    private int litri_tot;
    private int rubinetti_liberi;
    private int quantitàBevuta;

    public Botte(){
        this.litri_tot = 100;
        this.rubinetti_liberi = 3;
        this.quantitàBevuta = 0;
    }

    public synchronized boolean bevi(){
        String nome = Thread.currentThread().getName();
        //controllo se ci sono rubinetti liberi e i litri sono disponibili
        while(rubinetti_liberi == 0 && litri_tot > 0){
            System.out.println(nome + " attende che si liberi un rubinetto...");
            try{
                wait();
            }catch(InterruptedException eccezioneIgnorata) {}
        }
        //controllo se ci sono litri disponibili per bere 
        if(litri_tot > 0){
            //ci sono litri disponibili, quindi bevo
            rubinetti_liberi--;
            generaNumeroLitriCasuali();
            //controllo se è rimasto solo un litro, in questo caso devo bere per forza 1 l
            if(litri_tot == 1){
                quantitàBevuta = 1;
            }
            litri_tot -= quantitàBevuta; 
            System.out.println("--> " + nome + " sta bevendo " + quantitàBevuta + " l");
            System.out.println("Litri rimanenti: " + litri_tot);
            quantitàBevuta = 0;
            return true;
        }
        //non ci sono litri disponibili
        else{
            return false;
        }  
    }

    public synchronized void terminaDiBere(){
        String nome = Thread.currentThread().getName();
        //controllo se i litri sono esauriti per stampa diversa ed interrompere il thread
        if(IslitriEsauriti()){
            System.out.println(nome + " termina definitivamente di bere");
            notifyAll();
            Thread.interrupted();
        }
        else{
            //interruzione temporanea dell'attività di bevuta
            rubinetti_liberi++;
            System.out.println("<-- " + nome + " termina di bere, perciò il rubinetto libero è: " + rubinetti_liberi);
            if(rubinetti_liberi == 1){
                //qualora ci sia un rubinetto libero, sveglio tutti i thread in attesa
                notifyAll();
            }
        }
        
    }

    public int getLitri_tot(){
        return litri_tot;
    }

    public boolean IslitriEsauriti(){
        if(getLitri_tot() > 0){
            return false;
        }
        return true;
    }

    public int generaNumeroLitriCasuali(){
        quantitàBevuta = (int) (Math.random() * 10 + 1);
        return quantitàBevuta;
    }
}