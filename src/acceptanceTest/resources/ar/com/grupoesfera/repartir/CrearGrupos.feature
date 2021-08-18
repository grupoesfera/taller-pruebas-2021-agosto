Feature: Crear grupos para repartir gastos

  Scenario: Crear el primer grupo para la cena de navidad
    Given no existe ningún grupo
    And el usuario inicia la aplicación
    When el usuario selecciona crear grupo
    And completa con el nombre 'Cena de navidad'
    And indica que los miembros son 'lucas', 'karen' y 'rocio'
    And guarda el grupo
    Then se muestra 1° el grupo 'Cena de navidad' con total '$  0,00'

  Scenario: Crear el segundo grupo para el campamento
    Given existe un grupo
    And el usuario inicia la aplicación
    When el usuario selecciona crear grupo
    And completa con el nombre 'Campamento en Chascomús'
    And indica que los miembros son:
      | virginia |
      | santiago |
      | magui    |
      | gian     |
      | teo      |
      | mariano  |
    And guarda el grupo
    Then se muestra 2° el grupo 'Campamento en Chascomús' con total '$  0,00'
