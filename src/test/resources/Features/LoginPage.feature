Feature: LoginPage
  @test1
  Scenario: Acceder al sitio por properties
    Given El cliente se encuentra en la pantalla de logeo
    Then Cargo la informacion del DOM register_page.json
    And Hago un clic en el elemento SignIn
    And Hago un clic en el elemento Email
    And Configuro el elemento Email con el texto testMati@gabumon.com

  @test2
  Scenario: Se ingresa a un sitio por parametro
    Given Se ingresa al sitio http://automationpractice.pl/index.php
    Then Confirmo si el elemento MsgNoFeatured contiene No featured products at this time.
