# API REST do Fórum

Esta é uma API REST para um fórum online, onde os usuários podem criar tópicos de discussão e interagir por meio de respostas.
Tecnologias Utilizadas

    Spring Boot
    Spring Security
    MySQL
    Lombok

# Endpoints Disponíveis
Criar Novo Tópico

    Método: POST
    Endpoint: /topicos
    Descrição: Cria um novo tópico no fórum.
    Parâmetros de Requisição:
        topico (String): Título do tópico.
        mensagem (String): Mensagem do tópico.
        curso (String): Nome do curso relacionado ao tópico.
    Autenticação: Requer autenticação de usuário.
    Exemplo de Requisição:

    json

    {
      "topico": "Dúvida sobre Spring Security",
      "mensagem": "Estou com dificuldades para configurar o Spring Security em meu projeto.",
      "curso": "Spring Boot"
    }

    Exemplo de Resposta de Sucesso: Status 200 OK

Listar Tópicos

    Método: GET
    Endpoint: /topicos
    Descrição: Retorna uma lista de todos os tópicos existentes no fórum.
    Autenticação: Não requer autenticação.
    Exemplo de Resposta de Sucesso:

    json

    [
      {
        "topico": "Dúvida sobre Spring Security",
        "mensagem": "Estou com dificuldades para configurar o Spring Security em meu projeto.",
        "curso": "Spring Boot",
        "login": "usuario1",
        "dataCriacao": "2024-06-01T10:30:00"
      },
      {
        "topico": "Problema com Hibernate",
        "mensagem": "Meu projeto não está persistindo os dados corretamente no banco usando o Hibernate.",
        "curso": "Java Avançado",
        "login": "usuario2",
        "dataCriacao": "2024-05-30T15:45:00"
      }
    ]

Excluir Tópico

    Método: DELETE
    Endpoint: /topicos/{id}
    Descrição: Exclui um tópico existente no fórum.
    Parâmetros de Requisição:
        id (Long): ID do tópico a ser excluído.
    Autenticação: Requer autenticação de usuário.
    Exemplo de Requisição: /topicos/1
    Exemplo de Resposta de Sucesso: Status 204 No Content

Detalhes do Tópico

    Método: GET
    Endpoint: /topicos/{id}
    Descrição: Retorna os detalhes de um tópico específico.
    Parâmetros de Requisição:
        id (Long): ID do tópico desejado.
    Autenticação: Não requer autenticação.
    Exemplo de Requisição: /topicos/1
    Exemplo de Resposta de Sucesso:

    json

    {
      "topico": "Dúvida sobre Spring Security",
      "mensagem": "Estou com dificuldades para configurar o Spring Security em meu projeto.",
      "curso": "Spring Boot",
      "login": "usuario1",
      "dataCriacao": "2024-06-01T10:30:00"
    }

Como Executar

    Clone este repositório.
    Configure o banco de dados MySQL.
    Execute o aplicativo Spring Boot.

# Autor

João Gabriel
