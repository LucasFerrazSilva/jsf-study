# jsf-study
Project created to study Java Server Faces (JSF) and Prime Faces

## Descrição

O JSF é uma especificação. As principais implementações do JSF são o Mojarra e o MyFaces.

O JSF é utilizado principalmente em aplicações corporativas, pois seu foco é na criação e utilização de componentes reutilizáveis.

### Vantagens

 * Utiliza o modelo MVC
 * Baseado em componentes
 * Especificação aceita pelo mercado
 * Integra visual e código Java
 * Facilita na conversão e validação dos dados
 * Fácil internacionalização
 * Tem um framework de templates imbutido
 
## Escopos de um Managed Bean

Definimos que uma classe será um Managed Bean usando a anotação @Named.

Tipos de escopo:

 * **@RequestScoped**: escopo de requisição. Ou seja, é criado a cada nova requisição.
 * **@ViewScoped**: carrega quando a página é carregada e termina quando a sessão acaba ou quando é feita navegação no JSF. Talvez precise implementar Serializable pra funcionar.
 * **@SessionScoped**: escopo de sessão do usuário.
 * **@ApplicationScoped**: escopo de aplicação. Ou seja, o Bean é criado quando a aplicação é inicializada.
 
## Navegação implícita e explícita

* **Implícita**: a URL não muda quando a página muda, é mantido o histórico de navegação (não é feito o redirect)
* **Explícita**: a URL representa a página atual (é feito o redirect)

Para fazer a navegação explícita, precisamos passar como parâmetro na String "faces-redirect=true" (ex: "NomeDaPagina?faces-redirect=true").

## Mudando o idioma da aplicação

Basta criar o arquivo *faces-config.xml* dentro da pasta WEB-INF com o seguinte conteúdo:

```XML
<?xml version="1.0" encoding="UTF-8"?>
<faces-config xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_2.xsd"
	version="2.2">
	
	<application>
		<locale-config>
			<default-locale>pt_BR</default-locale>
		</locale-config>
	</application>
	
</faces-config>
```

## Ciclo de vida
 
Ciclo de vida da primeira requisição:

 1. Requisição 
 2. Restaurar a visão: cria a árvore de componentes
 3. Renderizar a resposta
 4. Resposta

Ciclo de vida de um Postback (interação do usuário com a aplicação):

 1. Restaurar visão: recupera a árvore de componentes
 2. Aplicar valores da requisição: cada string enviada é armazenada no respectivo componente
 3. Processar requisições: o JSF faz as conversões (ex: de String para BigDecimal, String para Date, etc.) utilizando os conversoes especificados. Caso não consiga realizar a conversão, pula direto para o *Renderizar a resposta*
 4. Atualizar os valores do modelo: atualiza os valores do objeto mapeado
 5. Invocar a aplicação: realiza o método em si
 6. Renderizar a resposta: renderiza o componente com os novos valores

## Prime Faces

Para que o Prime Faces funcione no projeto, basta adicionar a dependência no pom.xml:

```XML
<dependency>
	<groupId>org.primefaces</groupId>
	<artifactId>primefaces</artifactId>
	<version>6.0</version>
	<scope>compile</scope>
</dependency>
```

Nos arquivos .xhtml precisamos fazer a importação do mesmo:

```HTML
<html xmlns="http://www.w3.org/1999/xhtml" 
	...
	xmlns:p="http://primefaces.org/ui">
```

Agora, para usarmos seus componentes, basta usar a tag p:nomeDoComponente:

```HTML
<p:dataTable value="#{[0, 0, 0, 0]}">
	<p:column headerText="Razão Social">
		<h:outputText value="Empresa X LTDA" />
	</p:column>
	<p:column headerText="Nome Fantasia">
		<h:outputText value="Empresa X" />
	</p:column>
	<p:column headerText="Tipo Empresa">
		<h:outputText value="LTDA" />
	</p:column>
</p:dataTable>
```