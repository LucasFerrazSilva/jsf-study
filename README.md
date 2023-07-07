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