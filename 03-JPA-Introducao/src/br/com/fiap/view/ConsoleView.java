package br.com.fiap.view;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.bean.Genero;
import br.com.fiap.bean.Musica;

public class ConsoleView {

	public static void main(String[] args) {
		//Criar uma fábrica de EntityManager
		EntityManagerFactory factory = 
			Persistence.
			createEntityManagerFactory("CLIENTE_ORACLE");
		
		EntityManager em = factory.createEntityManager();
		
		//Cadastrar uma música
		Musica musica = new Musica();
		musica.setNome("We are the champions");
		musica.setGenero(Genero.ROCK);
		musica.setCantor("Queen");
		musica.setDataLancamento(Calendar.getInstance());
		
		em.getTransaction().begin();
		em.persist(musica);
		em.getTransaction().commit();
		
		//Busca 
		Musica busca = em.find(Musica.class, 1);
		System.out.println("Busca:****************");		
		System.out.println(busca.getNome());
		System.out.println(busca.getGenero());
		System.out.println("**********************");
		
		//Atualização
		Musica atua = new Musica();
		atua.setId(1);
		atua.setCantor("Luan Santana");
		atua.setGenero(Genero.SERTANEJO);
		atua.setNome("Se topa");
		atua.setDataLancamento(
			new GregorianCalendar(2014,Calendar.AUGUST,8));
		
//		em.getTransaction().begin();
//		em.merge(atua);
//		em.getTransaction().commit();
		
		//Refresh
		Musica mu = em.find(Musica.class, 1);
		System.out.println("Banco: "+mu.getCantor());
		mu.setCantor("Shakira");
		System.out.println("Alterado: "+mu.getCantor());
		em.refresh(mu);//Atualiza o objeto com o BD
		System.out.println("Após o refresh "+mu.getCantor());
		
		//Remover
		Musica r = em.find(Musica.class, 1);
		em.getTransaction().begin();
		em.remove(r);
		em.getTransaction().commit();
		
		
		em.close();
	}
}
