package zansavio.victorio.com.umlmobile.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victorio Zansavio on 01/07/2018.
 */

public class Diagrama {
    private List<BaseElement> cases = new ArrayList<>();
    private List<BaseElement> atores = new ArrayList<>();
    private LigacaoNode ligacoes = new LigacaoNode();

    private List<Observacao> observacoes = new ArrayList<>();

    public List<Observacao> getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(List<Observacao> observacoes) {
        this.observacoes = observacoes;
    }

    public LigacaoNode getLigacoes() {
        return ligacoes;
    }

    public void setLigacoes(LigacaoNode ligacoes) {
        this.ligacoes = ligacoes;
    }



    public List<BaseElement> getCases() {
        return cases;
    }

    public void setCases(List<BaseElement> cases) {
        this.cases = cases;
    }

    public List<BaseElement> getAtores() {
        return atores;
    }

    public void setAtores(List<BaseElement> atores) {
        this.atores = atores;
    }

}
