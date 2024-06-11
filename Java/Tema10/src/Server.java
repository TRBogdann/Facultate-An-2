import org.json.JSONArray;
import org.json.JSONObject;
import seminar.seminar2.g1065.Categorie;
import seminar.seminar2.g1065.Locatie;
import seminar.seminar2.g1065.Main;
import seminar.seminar2.g1065.MijlocFix;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Server implements AutoCloseable{

    private ServerSocket serverSocket;
    private boolean stopped = false;
    public List<MijlocFix> mijloaceFixe;

    public Server() throws IOException {
        serverSocket = new ServerSocket(2020);
    }

    public void start() throws SocketException {
        System.out.println("Server has started");
        readData();
        serverSocket.setSoTimeout(5000);
        while(!stopped)
        {
            try
            {
                Socket socket = serverSocket.accept();
                Thread workingThread = new Thread(()->preluacrareCerere(socket));
                workingThread.start();
            }
            catch (Exception e)
            {

            }
        }
    }

    private void preluacrareCerere(Socket socket) {
        try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()))
        {
            String mesaj = in.readObject().toString();
            switch(mesaj)
            {
                case "stop":
                    stopped = true;
                    break;
                case "cerere 1":
                    long nrInv = (Long)in.readObject();
                    Optional<MijlocFix> mijlocFix = mijloaceFixe.stream().filter(mf->mf.getNrInventar()==nrInv).findFirst();
                    if(mijlocFix.isPresent())
                    {
                        out.writeObject(mijlocFix);
                    }
                    else
                    {
                        out.writeObject(null);
                    }
                    break;
                case "cerere 2":
                    String locatie = in.readObject().toString();
                    List<MijlocFix> li = (List<MijlocFix>) mijloaceFixe.stream().filter(mf->mf.getLocatie().toString().equals(locatie));
                    out.writeObject(li);


            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("Server Connection Closed");
        if(!serverSocket.isClosed())
        {
            serverSocket.close();
        }
    }
    private List<MijlocFix> lista;

    public void readData()
    {
        try(Connection c = DriverManager.getConnection("jdbc:sqlite:g1065.db"))
        {
            System.out.println("Conexiune Creata");
            DatabaseMetaData dbmd = c.getMetaData();
            try(ResultSet rs = dbmd.getTables(null,null,"MIJLOACE_FIXE",new String[]{"TABLE"}) )
            {
                if(!rs.next())
                {
                    System.out.println("Tabela nu exista");
                    System.out.println("Creare Tabel...");
                    try(Statement statement = c.createStatement())
                    {
                        String comandaCreare = "create table MIJLOACE_FIXE("+
                                "denumire varchar(30),"+
                        "nr_inventar  bigint primary key,"+
                        "valoare double,"+
                        "data_achizitie  varchar(10),"+
                        "categorie varchar(30),"+
                        "durata_normata integer,"+
                        "locatie varchar(50))";
                        statement.execute(comandaCreare);
                        System.out.println("Tabela creata");
                        mijloaceFixe = Main.citireDate("mf.csv");
                        for(MijlocFix mijlocFix:mijloaceFixe)
                        {
                            String comanda = "insert into MIJLOACE_FIXE values('"+
                                    mijlocFix.getDenumire()+"',"+mijlocFix.getNrInventar()+","+
                                    mijlocFix.getValoare()+",'"+ Main.fmt.format(mijlocFix.getDataAchizitie())+
                                    "','"+mijlocFix.getCategorie()+"',"+mijlocFix.getDurataNormata()+",'"+mijlocFix.getLocatie().toString()+"');";
                            System.out.println(comanda);
                            statement.execute(comanda);
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Comanda Gresita");
                        System.out.println(e.getMessage());
                    }
                }
                else
                {
                    try(Statement statement = c.createStatement())
                    {
                        ResultSet r = statement.executeQuery("SELECT * FROM MIJLOACE_FIXE");
                        mijloaceFixe = new ArrayList<MijlocFix>();
                        while(r.next())
                        {
                            MijlocFix mijlocFix = new MijlocFix();
                            mijlocFix.setDenumire(r.getString(1));
                            mijlocFix.setNrInventar(r.getLong(2));
                            mijlocFix.setValoare(r.getDouble(3));
                            mijlocFix.setDataAchizitie(Main.fmt.parse(r.getString(4)));
                            mijlocFix.setCategorie(Categorie.valueOf(r.getString(5)));
                            mijlocFix.setDurataNormata(r.getInt(6));
                            mijlocFix.setLocatie(new Locatie(r.getString(7),""));
                            mijloaceFixe.add(mijlocFix);
                        }
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("Comanda Gresita");
            }
        }
        catch (Exception e)
        {

        }
    }
}
