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

