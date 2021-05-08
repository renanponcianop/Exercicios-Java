# Cadastro de veículos

## Informações importantes

Projeto construido com Spring 2.4.5 e Java 11

## Endpoints

```bash
/veiculos
Método: GET
Objetivo: Retorna todos os carros cadastrados
```
```bash
/veiculos/{id}
Método: GET
Objetivo: Retorna as informações de um único carro pelo seu ID
```
```bash
/veiculos/find
Método: GET
Objetivo: Filtra os veículos cadastrados
Parâmetros:
 vendidos= Boolean - True/False/Null - Exibe só os carros vendidos
 decada= Integer - Busca os carros de uma determinada década
 fabricante= String - Busca os carros por fabricante
 recentes= Boolean - True/False/Null - Exibe só os carros das ultimas 2 semanas
```
```bash
/veiculos
Método: POST
Objetivo: Cria um carro
Parâmetros: JSON:
{
    "id": Integer - id ,
    "veiculo": String - modelo,
    "marca": String - fabricante,
    "ano": Integer- ano,
    "descricao": String - descricao,
    "vendido": Boolean - true/false informa se o carro foi vendido
}
```
```bash
/veiculos/{id}
Método: PUT
Objetivo: Altera todos os dados de um veículo
Parâmetros: JSON:
{
    "id": Integer - id ,
    "veiculo": String - modelo,
    "marca": String - fabricante,
    "ano": Integer- ano,
    "descricao": String - descricao,
    "vendido": Boolean - true/false informa se o carro foi vendido
}
```
```bash
/veiculos/{id}
Método: PATCH
Objetivo: Altera somente os dados enviados
Parâmetros: JSON:
{
    "id": Integer - id ,
    "veiculo": String - modelo,
    "marca": String - fabricante,
    "ano": Integer- ano,
    "descricao": String - descricao,
    "vendido": Boolean - true/false informa se o carro foi vendido
}
```
```bash
/veiculos/{id}
Método: DELETE
Objetivo: Deleta um veículo do banco
```