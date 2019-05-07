# Even3 - Elevator
Projeto de teste para seleção.

## Algoritmo
Foi utilizado o algoritmo de escalonamento SCAN, o qual varre as requisições no sentido da requisição mais próxima inicialmente,
navegando até a últma requisição nesse sentido, e em seguida atende as outras requisições no sentido contrário.

### Executando
Navegue na raiz do projeto para pasta "output". Uma vez na pasta, basta executar o seguinte comando:
java -jar even3-elevator.jar

#### 1º Questão
A quantidade de passos executados é:
(5 - 0) + (0 - 8) + (8 - 10) + (10 - 0) = 25

#### 2º Questão
A quantidade de passos executados é:
(3 - 0) + (0 - 7) + (7 - 8) + (8 - 15) + (15 - 0) = 33