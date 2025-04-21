import java.io.*;
import java.security.*;

public class Main4 {

    private final static String ALGORITMO = "RSA";

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ClassNotFoundException {
        FileInputStream archivoLlavePublica = new FileInputStream("llavePublica");
        ObjectInputStream oisLlavePublica = new ObjectInputStream(archivoLlavePublica);
        PublicKey llavePublica= (PublicKey) oisLlavePublica.readObject();
        oisLlavePublica.close();

        FileInputStream archivoLlavePrivada = new FileInputStream("llavePrivada");
        ObjectInputStream oisLlavePrivada = new ObjectInputStream(archivoLlavePrivada);
        PrivateKey llavePrivada= (PrivateKey) oisLlavePrivada.readObject();
        oisLlavePrivada.close();

        FileInputStream archivoTextoCifrado = new FileInputStream("textoCifrado");
        ObjectInputStream oisTextoCifrado = new ObjectInputStream(archivoTextoCifrado);
        byte[] textoCifrado= (byte[]) oisTextoCifrado.readObject();
        oisTextoCifrado.close();

        //Obtenga un byte [] con el texto descifrado, invocando al me todo descifrar() de la clase Asimetrico.
        long tiempoInicialDescifrado = System.nanoTime();
        byte[] textoDescifrado = Asimetrico.descifrar(llavePrivada, ALGORITMO, textoCifrado);

        long tiempoFinalDescifrado = System.nanoTime();
        long tiempoDecifrado = tiempoFinalDescifrado - tiempoInicialDescifrado;
        System.out.println("Tiempo de descifrado: " + tiempoDecifrado+ " ns.");


        System.out.println("Input descifrado en byte[]:");
        //Imprima el texto descifrado en byte []. Utilice el me todo imprimir(). 
        imprimir(textoDescifrado);

        //Convierta el byte[] con el texto descifrado a String. Utilice el me todo constructor de la clase String. 
        String textoPlanoDescifrado = new String(textoDescifrado);
        System.out.println("Input descifrado convertido a texto plano: " + textoPlanoDescifrado);

    }

    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }
}

