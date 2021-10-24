Feature: Iniciar una nueva partida
  Como usuario quiero iniciar una partida de truco y poder definir las configuraciones iniciales

  Background:
    Given vista del menu principal
    And ubicado en el menu principal

  Scenario Outline: Clickear el boton JUGAR, y seleccionar luego las reglas a aplicar en la partida que se va a iniciar
    When click en el boton JUGAR
    And selecciono el "<puntajeMaximo>", selecciono si se juega o no con "<flor>"
    And completo el campo "<nombre>"
    And click en boton ACEPTAR
    Then se inicia una partida con "<puntajeMaximo>", "<flor>" y "<nombre>" como parametros

    Examples:
      | puntajeMaximo | flor     | nombre |
      | 15            | Con Flor | juan   |
      | 30            | Sin Flor | pablo  |

Feature: Jugar una carta en mi turno
  Como usuario, quiero jugar una carta de mi mano

  Background:
    Given vista de la mesa de juego
    And partida iniciada
    And turno del jugador
    But no hay cantos con una respuesta pendiente

  Scenario Outline: Clickear una de las cartas de la mano del jugador
    When click en "<cartaManoDelJugador>"
    Then "<cartaManoDelJugador>" se retira de la mano del jugador
    And "<cartaManoDelJugador>" se juega y aparece ahora en la mesa
    And termina el turno del jugador

    Examples:
      | cartaManoDelJugador |
      | carta1              |
      | carta2              |
      | carta3              |


Feature: Cantar envido, real envido o falta envido

  Background:
    Given vista de la mesa de juego
    And partida iniciada
    And turno del jugador
    And primera ronda de la mano

  Scenario Outline: Clickear boton de envido
    When click en "<botonEnvido>"
    Then esperar respuesta del rival

    Examples:
      | botonEnvido  |
      | Envido       |
      | Real Envido  |
      | Falta Envido |


Feature: Abandonar partida

  Background:
    Given vista de la mesa de juego
    And partida iniciada

  Scenario Outline: Clickear boton ABANDONAR y decidir si se abandona o no la partida
    When click en boton ABANDONAR
    And click en alguna opcion de "<confirmacion>"
    Then se procede segun "<confirmacion>" y se abandona o no la partida

    Examples:
      | confirmacion |
      | Si           |
      | No           |

