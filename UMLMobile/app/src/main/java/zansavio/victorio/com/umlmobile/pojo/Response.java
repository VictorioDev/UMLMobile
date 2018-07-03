package zansavio.victorio.com.umlmobile.pojo;

/**
 * Created by Victorio Zansavio on 01/07/2018.
 */

public class Response {
    private String tipo;
    private String problema;
    private Diagrama diagrama;
    private int altura;
    private int largura;



    public Diagrama getDiagrama() {
        return diagrama;
    }

    public void setDiagrama(Diagrama diagrama) {
        this.diagrama = diagrama;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }
}
