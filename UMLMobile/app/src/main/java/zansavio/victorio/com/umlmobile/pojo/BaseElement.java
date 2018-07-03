package zansavio.victorio.com.umlmobile.pojo;

/**
 * Created by Victorio Zansavio on 01/07/2018.
 */

public class BaseElement {
    private int id;
    private String nome;
    private AtributoNode atributos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AtributoNode getAtributos() {
        return atributos;
    }

    public void setAtributos(AtributoNode atributos) {
        this.atributos = atributos;
    }

}
