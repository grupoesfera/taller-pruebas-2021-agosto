Feature: Agregar gastos a los grupos

  Scenario: Agregar gasto a un grupo nuevo
    Given existe el grupo #21 'Picnic en Palermo' sin gastos
    And el usuario inicia la aplicación
    When el usuario selecciona agregar gasto al grupo #21
    And completa con el monto de $ '4000,95'
    And guarda el gasto
    Then ve la confirmación 'Gasto agregado al grupo'
    And se actualiza el total del grupo #21 a '$  4.000,95'

