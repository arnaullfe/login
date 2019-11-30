import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class login {
    public static void main(String[] args) {
        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        String[][] usuaris = new String[100][7];
        int usuarisCreats =0;
        int[] edat = new int[100];
        String[] dades = new String[7];
        String [][] registre = new String [100][2];
        admin(sc, usuaris, usuarisCreats, edat, dades, registre);
        usuarisCreats= totes(sc, usuaris, usuarisCreats, dades);
        while (!sortir) {
            int accio = opcio(sc, sortir);
            switch (accio) {
            case 1:
                newUser(sc, usuaris, usuarisCreats, edat,dades,registre);
                usuarisCreats= totes(sc, usuaris, usuarisCreats, dades);
                break;
            case 2:
                inici(sc, usuaris, edat,registre);
                break;
            case 3:
                buscar(usuaris, sc,usuarisCreats,edat);
                break;
            case 4:
                ordenarEdat(usuaris, sc, usuarisCreats, edat);
                break;
            case 5:
                sortir = true;
                break;
            }
        }
    }
    //admin
    public static void admin(Scanner sc, String[][] usuaris, int usuarisCreats, int[] edat,String [] dades,String [][] registre){
        System.out.println();
        System.out.println("Creació de l'admiistrador del sistema");
        System.out.println("Clica enter per procedir a crear l'usuari administrador");
        System.console().readLine();
        newUser(sc, usuaris, usuarisCreats, edat, dades,registre);
    }
    // selecció d'opció
    public static int opcio(Scanner sc, boolean sortir) {
        System.out.println("1- Crear un nou usuari");
        System.out.println("2- Login");
        System.out.println("3- Cercar");
        System.out.println("4- ordenar");
        System.out.println("5- Sortir");
        System.out.println("Selecciona el que vols fer: ");
        int accio = Integer.parseInt(System.console().readLine());
        return accio;
    }
    // login
    public static void inici(Scanner sc, String[][] usuaris, int[] edat,String [][] registre) {
        System.out.println("Introdueix el nom d'usuari amb el que vols accedir:");
        String user = sc.nextLine();
        int i = 0;
        for (i = 0; i < usuaris.length; i++) {
            if (user.equals(usuaris[i][5])) {
                System.out.println("Posa la contrasenya de l'usuari " + user);
                String passwd = sc.nextLine();
                if (passwd.equals(usuaris[i][6]) && i==0) {
                    System.out.println("Benvingut administrador "+usuaris[i][0]);
                    System.out.println("Aquest és el registre de logins incorrectes: ");
                    System.out.println("Usuaris:"+"\t"+"Contrasenyes intentades:");
                    System.out.println();
                    for (int a=0;a<registre.length;a++){
                        if (registre[a][0]==null && registre[a][1]==null){
                            System.out.println();
                            System.out.println("Ja no hi ha més registres de logins");
                            System.out.println("Clica enter per continuar");
                            System.console().readLine();
                            break;
                        } else {
                            System.out.println(registre[a][0]+"          "+registre[a][1]);

                        }
                    }
                 }
                else if (passwd.equals(usuaris[i][6])) {
                   informació(sc, usuaris, edat,i);
                } else{
                    System.out.println();
                    System.out.println("Contrasenya incorrecte");
                    for (int a=0;a<registre.length;a++){
                        if (registre[a][0] == null && registre[a][1]==null){
                            registre[a][0]=user;
                            registre[a][1]=passwd;
                            break;
                        }
                    }
                }
                break;
            }
        }
    }
    //informació 
    public static void informació(Scanner sc, String[][] usuaris, int[] edat,int i){
        System.out.println("Benvingut " + usuaris[i][0] + " " + usuaris[i][1]);
        System.out.println();
        System.out.println("L'adreça de l'usuari és "+usuaris[i][2]+ " de la població "+usuaris[i][3]);
        System.out.println("Ha nascut el "+usuaris[i][4]+" té "+edat[i]+" anys");
        System.out.println("El nom d'usuari és "+usuaris[i][5]);
        System.out.println("Clica enter per continuar");
        System.console().readLine();
        System.out.println();
    }
    // nom nou usuari
    public static void newUser(Scanner sc, String[][] usuaris, int usuarisCreats, int[] edat,String [] dades,String [][] registre) {
        System.out.println("Introdueix les dades del nou usuari");
        System.out.println("Nom de l'usuari:");
        dades[0] = sc.nextLine();
        lastname(sc, usuaris, usuarisCreats, dades, edat,registre);
    }
    // cognom nou usuari
    public static void lastname(Scanner sc, String[][] usuaris, int usuarisCreats, String[] dades, int[] edat,String [][] registre) {
        System.out.println("Cognoms de l'usuari:");
        dades[1] = sc.nextLine();
        adreça(sc, usuaris, usuarisCreats, dades, edat,registre);
    }
    // adeça usuari
    public static void adreça(Scanner sc, String[][] usuaris, int usuarisCreats, String[] dades, int[] edat,String [][] registre) {
        System.out.println("Indica l'Adreça:");
        dades[2] = sc.nextLine();
        població(sc, usuaris, usuarisCreats, dades, edat,registre);
    }
    // població
    public static void població(Scanner sc, String[][] usuaris, int  usuarisCreats, String[] dades, int[] edat,String [][] registre) {
        System.out.println("Digues la a la que pertany Població:");
        dades[3] = sc.nextLine();
        naixament(sc, usuaris, usuarisCreats, dades, edat,registre);
    }
    // data naixament
    public static void naixament(Scanner sc, String[][] usuaris, int usuarisCreats, String[] dades, int[] edat,String [][] registre) {
        System.out.println("Digues la data de naixament (dd/MM/yyyy)");
        dades[4] = sc.nextLine();
        String[] naixament = dades[4].split("/");
        LocalDateTime dataAra = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String databona = dataAra.format(dtf);
        String[] data = databona.split("-");
        edat(usuarisCreats, naixament, data, edat);
        user(sc, usuaris, usuarisCreats, dades,registre);
    }
    // usuari
    public static void user(Scanner sc, String[][] usuaris, int usuarisCreats, String[] dades,String [][] registre) {
        boolean repetit = false;
        do {
            repetit = false;
            System.out.println("Escriu el nom d'usuari que desitges tenir");
            dades[5] = sc.nextLine();
            for (int i = 0; i < usuaris.length; i++) {
                if (dades[5].equals(usuaris[i][5])) {
                    repetit = true;
                    System.out.println("Error, el nom d'usuari ja està en ús");
                }
            }
        } while (repetit);
        usuaris[usuarisCreats][5]= dades[5];
        passwd(sc, usuaris, usuarisCreats, dades,registre);
    }
    // passwd
    public static void passwd(Scanner sc, String[][] usuaris, int usuarisCreats, String[] dades,String [][] registre) {
        boolean error = true;
        do {
            System.out.println("Escriu la contrasenya de l'usuari");
            dades[6] = sc.nextLine();
            if (dades[6].matches("((?=.*[A-Z])(?=.*[a-z])).{8,}$")) {
                error = false;
            } else {
                System.out.println("Error, contrasenya massa dèbil");
            }
        } while (error);
    }
    //posar tots els valors
    public static int totes(Scanner sc, String[][] usuaris, int usuarisCreats, String[] dades) {
        for (int i = 0; i < dades.length; i++) {
            usuaris[usuarisCreats][i] = dades[i];
            System.out.println(usuaris[usuarisCreats][i]);
        }
        usuarisCreats++;
        return usuarisCreats;
    }
    // edat
    public static void edat(int usuarisCreats, String[] naixament, String[] data, int[] edat) {
        int[] dataNaix = new int[3];
        int[] dataAra = new int[3];
        for (int i = 0; i < data.length; i++) {
            dataNaix[i] = Integer.parseInt(naixament[i]);
            dataAra[i] = Integer.parseInt(data[i]);
        }
        edat[usuarisCreats] = (dataAra[2] - dataNaix[2]) - 1;
        if (dataNaix[1] < dataAra[1]) {
            edat[usuarisCreats]++;
        } else if (dataNaix[1] == dataAra[1] && dataNaix[0] <= dataAra[0]) {
            edat[usuarisCreats]++;
        }
    }
    //buscar
    public static void buscar(String [][] usuaris, Scanner sc, int usuarisCreats,int [] edat){
        System.out.println("Introdueix el que vols cercar:");
        String cerca = sc.nextLine();
        for (int i =0;i<usuarisCreats;i++){
            for (int j=0;j<usuaris[0].length;j++){
                if (usuaris[i][j].matches("(.*)"+cerca+"(.*)") && j!=6){
                    informació(sc, usuaris, edat, i);
                    break;
                }
            }
        }
    }
    //ordenar edat
    public static void ordenarEdat(String [][] usuaris, Scanner sc, int usuarisCreats, int [] edat){
        System.out.println("Vols ordenar ascendent o descendent? (asc/des)");
        String orde = sc.nextLine();
        if (orde.equalsIgnoreCase("asc")){
            for (int a=0;a<usuarisCreats;a++){
                for (int b=0;b<usuarisCreats;b++){
                    for (int i=1;i<usuarisCreats;i++){ 
                        if (edat[i]>edat[i+1] && i<(usuarisCreats-1)){
                            int nova = edat[i];
                            edat[i]=edat[i+1];
                            edat[i+1]= nova;
                            for (int p=0;p<usuaris[0].length;p++){
                                String dades= usuaris[i][p];
                                usuaris[i][p]=usuaris[i+1][p];
                                usuaris[i+1][p]= dades;
                            }
                        }
                        
                    }
                }
            }
        } else if (orde.equalsIgnoreCase("des")){
            for (int a=0;a<usuarisCreats;a++){
                for (int b=0;b<usuarisCreats;b++){
                    for (int i=2;i<usuarisCreats;i++){ 
                        if (edat[i]>edat[i-1] && i<(usuarisCreats) && i-1>0){
                            int nova = edat[i];
                            edat[i]=edat[i-1];
                            edat[i-1]= nova;
                            for (int p=0;p<usuaris[0].length;p++){
                                String dades= usuaris[i][p];
                                usuaris[i][p]=usuaris[i-1][p];
                                usuaris[i-1][p]= dades;
                            }
                        }
                        
                    }
                }
            }
        }
        System.out.println("El primer usuari és l'usuari administrador, que té el nom "+usuaris[0][0]+" "+usuaris[0][1]+" la seva població és "+usuaris[0][3]+" i té "+edat[0]+" anys");
        for (int i=1;i<usuarisCreats;i++){
            System.out.println("L'usuari amb el nom "+usuaris[i][0]+" "+usuaris[i][1]+" té "+edat[i]+" anys i la seva població és "+usuaris[i][3]);
        }
    }
}