1.  PetConnect
   
        PetConnect é uma aplicação desenvolvida para conectar ONGs parceiras a adotantes, facilitando o cadastro, visualização e adoção de animais.
        O sistema permite que as ONGs gerenciem seus animais disponíveis para adoção e que o público possa conhecê-los facilmente.

2.  Tecnologias utilizadas
   
    2.1 back-end
    
          -> Java 17: Versão LTS (Long Term Support) da linguagem Java utilizada no projeto. Ela oferece melhorias de performance, recursos modernos da linguagem e suporte estendido, garantindo estabilidade e segurança.
    
          -> Spring Boot: Framework que facilita a criação de aplicações Java. Ele permite configurar e iniciar o projeto rapidamente, com menos código e menos configurações manuais, além de fornecer um servidor embutido (como Tomcat) para rodar a aplicação.
    
          -> Spring Data MongoDB: Módulo do Spring que simplifica a integração da aplicação com o banco de dados MongoDB. Ele facilita operações como salvar, buscar, atualizar e deletar documentos, usando repositórios e abstrações prontas.
    
         -> Maven: Ferramenta de gerenciamento de dependências e automação de build. É responsável por baixar as bibliotecas necessárias, organizar o projeto e executar comandos como compilar, testar e rodar a aplicação.
    
        -> MongoDB Atlas: Serviço de banco de dados MongoDB na nuvem. Ele fornece um cluster gerenciado, seguro e escalável, permitindo que a aplicação acesse o banco de dados de qualquer lugar com alta disponibilidade e backups automáticos.

        -> Lombok: Biblioteca que reduz código repetitivo em Java. Com anotações como @Getter, @Setter, @Builder e @Data, ela cria automaticamente métodos como getters, setters, construtores e toString, deixando o código mais limpo.
          

  2.2 Front-end
  
       -> HTML: Linguagem usada para estruturar e organizar o conteúdo das páginas web. Define elementos como textos, títulos, links, botões, tabelas e formulários.
       -> CSS : Linguagem de estilização responsável pelo visual da interface. Controla cores, fontes, espaçamentos, layout, responsividade e aparência geral das páginas.
      -> JavaScript: Linguagem de programação que adiciona interatividade ao site. Permite criar comportamentos dinâmicos, validações, animações, atualizações de conteúdo sem recarregar a página e comunicação com o back-end.
      -> thymeleaf: Template engine usada no Spring Boot para gerar páginas HTML dinâmicas. Permite integrar variáveis do back-end diretamente no HTML de forma simples, criando páginas que exibem dados da aplicação.

  3.Funcionalidades
  
    Cadastro e login de parceiros (ONGs)
    Cadastro de animais com informações completas
    Listagem e filtro de animais por ONG
    Edição e exclusão de animais cadastrados

4. Estrutura do projeto
   
       PetConnect/
        ├── src/
        │   ├── main/
        │   │   ├── java/com/example/petconnect/
        │   │   │   ├── controller/   → Endpoints da API
        │   │   │   ├── model/        → Classes das entidades (Parceiro, Animal e Usuário )
        │   │   │   ├── repository/   → Interfaces MongoRepository
        │   │   │   └── service/      → Lógica de negócio
        │   │   └── resources/
        │   │       └── application.properties
        ├── pom.xml
        └── README.md

    5.Autoria

        Projeto desenvolvido por Bárbara Brandino, Clara Vecchio, Jacqueline da Silva, Matheus Rumão, Felipe Ferreira e Elisangela Madeleno
         “Conectando pessoas e animais com amor e tecnologia.”
