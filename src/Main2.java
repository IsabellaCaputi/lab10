import java.security.*;
import javax.crypto.*;
import java.util.Scanner;

public class Main2 {

    private final static String ALGORITMO = "RSA";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //Reciba por teclado la entrada de un texto.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba un mensaje de texto:");
        String texto = scanner.nextLine();
        //Imprima el texto recibido por el teclado
        System.out.println("Input en texto plano: " + texto);

        //Imprima texto claro en byte []. Utilice el me todo getBytes() de la clase String para
        //convertir el mensaje a byte[]. 
        byte[] textoBytes = texto.getBytes();

        //Para imprimir el contenido del byte[] utilice el me todo imprimir(). 
        System.out.println("Input en bytes[]:");
        imprimir(textoBytes);

        //Genere un par de llaves asime tricas: una pu blica y otra privada, las cuales empleara para cifrar y descifrar.
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITMO);
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();
        PublicKey llavePublica = keyPair.getPublic();
        PrivateKey llavePrivada = keyPair.getPrivate();

        //Obtenga un byte [] con el texto cifrado, invocando al me todo cifrar() de la clase Asimetrico. 
        long tiempoInicialCifrado = System.nanoTime();
        byte[] textoCifrado = Asimetrico.cifrar(llavePrivada, ALGORITMO, texto);

        long tiempoFinalCifrado = System.nanoTime();
        long tiempoCifrado = tiempoFinalCifrado - tiempoInicialCifrado;
        System.out.println("Tiempo de cifrado: " + tiempoCifrado+ " ns.");


        System.out.println("Input cifrado en RSA con Llaves de 1024 bits en byte[]:");
        //Imprima el texto cifrado en byte []. Utilice el me todo imprimir(). 
        imprimir(textoCifrado);

        //Obtenga un byte [] con el texto descifrado, invocando al me todo descifrar() de la clase Asimetrico.
        long tiempoInicialDescifrado = System.nanoTime();
        byte[] textoDescifrado = Asimetrico.descifrar(llavePublica, ALGORITMO, textoCifrado);

        long tiempoFinalDescifrado = System.nanoTime();
        long tiempoDecifrado = tiempoFinalDescifrado - tiempoInicialDescifrado;
        System.out.println("Tiempo de descifrado: " + tiempoDecifrado+ " ns.");


        System.out.println("Input descifrado en byte[]:");
        //Imprima el texto descifrado en byte []. Utilice el me todo imprimir(). 
        imprimir(textoDescifrado);

        //Convierta el byte[] con el texto descifrado a String. Utilice el me todo constructor de la clase String. 
        String textoPlanoDescifrado = new String(textoDescifrado);
        System.out.println("Input descifrado convertido a texto plano: " + textoPlanoDescifrado);

        scanner.close();
    }

    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }
}

