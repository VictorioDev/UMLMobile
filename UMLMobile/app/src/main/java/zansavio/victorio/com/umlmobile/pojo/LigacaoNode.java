package zansavio.victorio.com.umlmobile.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victorio Zansavio on 01/07/2018.
 */

public class LigacaoNode {
    private List<Ligacao> generalizacao = new ArrayList<>();
    private List<Ligacao> associacao = new ArrayList<>();
    private List<Ligacao> extend = new ArrayList<>();
    private List<Ligacao> include = new ArrayList<>();

    public List<Ligacao> getGeneralizacao() {
        return generalizacao;
    }

    public void setGeneralizacao(List<Ligacao> generalizacao) {
        this.generalizacao = generalizacao;
    }

    public List<Ligacao> getAssociacao() {
        return associacao;
    }

    public void setAssociacao(List<Ligacao> associacao) {
        this.associacao = associacao;
    }

    public List<Ligacao> getExtend() {
        return extend;
    }

    public void setExtend(List<Ligacao> extend) {
        this.extend = extend;
    }

    public List<Ligacao> getInclude() {
        return include;
    }

    public void setInclude(List<Ligacao> include) {
        this.include = include;
    }
}
