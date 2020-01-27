package novoprojeto.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
public class Empresa  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, length = 80)
    private  String nomeFantasia;

    @NotEmpty
    @Column(nullable = false, length = 120)
    private String razaoSocial;

    @CNPJ
    @NotNull
    @Column(nullable = false, length = 18)
    private String cnpj;

    @NotNull
    @Past
    @Temporal(TemporalType.DATE)
    private Date dataFundacao;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private RamoAtividade ramoAtividade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoEmpresa tipo;

    public TipoEmpresa getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpresa tipo) {
        this.tipo = tipo;
    }

    public Empresa(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Empresa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public RamoAtividade getRamoAtividade() {
        return ramoAtividade;
    }

    public void setRamoAtividade(RamoAtividade ramoAtividade) {
        this.ramoAtividade = ramoAtividade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(id, empresa.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
