Feature: Bienvenida a la aplicación

  Scenario: Mostrar un mensaje de bienvenida
    When el usuario accede a la aplicación
    Then se muestra el mensaje de bienvenida

  Scenario: Iniciar el uso de la aplicación luego de la bienvenida
    Given que el usuario accedió a la aplicación
    When decidió iniciar
    Then puede empezar a usarla