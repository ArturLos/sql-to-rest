package def.rest;

import def.pojo.Kolumna;
import def.pojo.Struktura;
import def.pojo.Tabela;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

public class XmlOutputHelperService implements OutputHelperService{
    private static final Logger LOG = Logger.getLogger(XmlOutputHelperService.class.getName());

    //TODO: zamienić po testach spowrotem na protected albo wymyśleć coś innego
    public Writer writer;
    protected HttpServletResponse response;
    protected Struktura struktura;

    //TODO: Zdecydować czy strukturę ustawiać przez konstruktor
    public Struktura getStruktura() {
        return struktura;
    }

    public void setStruktura(Struktura struktura) {
        this.struktura = struktura;
    }
    
    public XmlOutputHelperService(HttpServletResponse response) throws Exception{
        this(
            response,
            new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8))
        );
    }

    //TODO: przemyśleć czy oprucz testów ten konstruktor powinien być publiczny
    public XmlOutputHelperService(HttpServletResponse response, Writer writer) {
        this.writer = writer;
        this.response = response;
    }

    @Override
    public void pre() throws Exception{
        response.addHeader("ContentType", "text/xml");
        response.setCharacterEncoding("UTF-8");
        writeLine("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        if(struktura!=null)
            if(struktura!=null)writeLine("<"+struktura.getNazwaKolekcji()+">");
    }

    @Override
    public void post() throws Exception{
        if(struktura!=null)
            writeLine("</"+struktura.getNazwaKolekcji()+">");
        writer.flush();
    }

    /**
     * Implementacja zapisu wiersza danych interfejsu RowCallbackHandler
     * @param rs
     * @throws SQLException 
     */
    @Override
    public void processRow(ResultSet rs) throws SQLException {
        try {
            writeLine("<"+struktura.getNazwaElementu()+">");
            {
                Set<Tabela> tabele = struktura.getTabele();
                for(Tabela tabela: tabele){
                    for(Kolumna kolumna : tabela.getKolumny()){
                        write("<"+kolumna.getNazwa()+">");
                        write(rs.getString(kolumna.getNazwa()));
                        writeLine("</"+kolumna.getNazwa()+">");
                    }
                }
            }
            writeLine("</"+struktura.getNazwaElementu()+">");
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Błąd przy dodawaniu danych na writer", ex);
        }
    }

    protected void writeLine(String tekst) throws IOException{
        if(tekst!=null)
            writer.write(tekst);
        writer.write('\n');
    }
    protected void write(String tekst) throws IOException{
        if(tekst!=null)
            writer.write(tekst);
    }
}
