package br.com.alura.forum.model;

public enum StatusTopico {

	NAO_RESPONDIDO, // criado ele esta no status nao respondido
	NAO_SOLUCIONADO, // quando alguem posta uma resposta no topico, ele vai como nao solucionado
	SOLUCIONADO, // quando o autor ou adm do forum marca aquela resposta como solucao muda o
					// status
	FECHADO; // fechado ninguem pode posta mais respostas

}
