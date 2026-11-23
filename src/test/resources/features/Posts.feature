@Posts
Feature: Pruebas de la API de posts

  Como usuario de la API
  Quiero consultar y crear posts
  Para validar que los servicios respondan correctamente

  @Positivo
  Scenario: Consultar todos los posts exitosamente
    Given el actor establece el endpoint de posts
    When el actor consulta todos los posts
    Then el codigo de respuesta debe ser 200
    And la respuesta debe contener posts

  @Positivo
  Scenario: Consultar un post existente exitosamente
    Given el actor establece el endpoint de posts
    When el actor consulta el post con id "1"
    Then el codigo de respuesta debe ser 200
    And el campo "id" de la respuesta debe ser "1"

  @Positivo
  Scenario: Crear un post exitosamente
    Given el actor establece el endpoint de posts
    When el actor crea un post con los siguientes datos
      | title          | body                 | userId |
      | Post de prueba | Contenido de prueba  | 1      |
    Then el codigo de respuesta debe ser 201
    And el campo "title" de la respuesta debe ser "Post de prueba"

  @Negativo
  Scenario: Consultar un post que no existe
    Given el actor establece el endpoint de posts
    When el actor consulta el post con id "101"
    Then el codigo de respuesta debe ser 404
