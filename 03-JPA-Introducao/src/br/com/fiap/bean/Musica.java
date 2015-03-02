package br.com.fiap.bean;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="TAB_MUSICA") //Altera o nome da tabela no DB
@SequenceGenerator(name="seqMusica",
	sequenceName="SEQ_MUSICA",allocationSize=1)
public class Musica {

	@Id
	@Column(name="COD_MUSICA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
	generator="seqMusica")
	private int id;
	
	@Column(name="NM_MUSICA",nullable=false,length=50)
	private String nome;
	
	private String cantor;
	
	@Transient //Não será uma coluna no BD
	private String session;
	
	@Column(name="DT_LANCAMENTO")
	@Temporal(TemporalType.DATE)//Grava somente a Data
	private Calendar dataLancamento;
	
	@Lob//Armazena grande qtd de informação
	private byte[] capa;
	
	//Enum -> será armazenado como um NUMBER
	//@Enumerated(EnumType.STRING)
	private Genero genero;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCantor() {
		return cantor;
	}

	public void setCantor(String cantor) {
		this.cantor = cantor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		//Validação de quantidade de caracteres
		if (nome.length()>50){
			throw new IllegalArgumentException(
				"Nome não pode conter mais que 50 caracteres");
		}
		this.nome = nome;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public byte[] getCapa() {
		return capa;
	}

	public void setCapa(byte[] capa) {
		this.capa = capa;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	@PrePersist
	public void executaAntesDeCadastrar(){
		System.out.println("Cadastrando uma Musica");
	}
	
}
