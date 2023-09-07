Feature: LoginPage
  @Test
  Scenario: Acceder al sitio por properties
    Given El cliente se encuentra en la pantalla de logeo
    Then Cargo la informacion del DOM register_page.json

  @testReport
  Scenario: Se ingresa a un sitio por parametro
    Given Se ingresa al sitio http://automationpractice.pl/index.php