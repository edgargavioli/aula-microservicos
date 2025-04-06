# Tutorial para rodar a aplicação

## Requisitos
### Ter o docker e docker compose instalado
___
___
___

### O docker esta ajustado para realizar o build.
#### Acessar a pasta onde se encontra o docker compose e executar o comando:
```docker compose up -d --build```

#### Para parar os containers e excluir todas as imagens criadas executar o comando:
```docker compose down --rmi all```

### Utilizando o uso do kafka para comunicação com outros microsserviços