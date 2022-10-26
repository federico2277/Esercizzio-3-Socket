package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.xml.crypto.Data;

public class ClientHandler extends Thread {
    private Socket s;
    private PrintWriter pr = null;
    private BufferedReader br = null;
    private LocalDate dataServer = LocalDate.now();
    private LocalTime oraServer = LocalTime.now();
    public ClientHandler(Socket s) {
        this.s = s;
        try {
            // per parlare
            pr = new PrintWriter(s.getOutputStream(), true);
            // per ascoltare
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static int j= 0;

    public void run() {
        try {
            System.out.println(br.readLine());
            pr.println("Server: Dammi il tuo nome"); // invio messaggio
            String nome = br.readLine(); // ricevo: il nome
            System.out.println("nome ricevuto");

            pr.println( "ciao "+ nome.toUpperCase() +" sei l'utente numero " + j++);
            
            boolean controlloFine = true;
            
            while(controlloFine){
            pr.println(" Che cosa vuoi fare? ");

            String richiesta = (br.readLine()); // leggo la richiesta
                
            if(richiesta.equals("data")){
                pr.println(" la data è " + dataServer);
            }
            else if(richiesta.equals("ora")){
                pr.println(" l'ora del server :" + oraServer);
            }
            else if(richiesta.equals("nome")){
                pr.println(" il nome del utente :" + nome);
            }
            else if(richiesta.equals("id") ){
                pr.println(" sei il cliente numero :" + j);
            }
            else if(richiesta.equals("fine")){
                pr.println(" sto chiudendo");
                s.close();
                
            }else if(richiesta.equals("chiudi")){
                pr.println("tutte le connesioni saranno chiuse");
                
            }
        }
        System.out.println("addiooo");
        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
