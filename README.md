# POS TECH Fiap - Tech Challenge Fase2
## Desafio
Refazer a solução do parquímetro, utilizando os conceitos de APIs, Persistência de dados e tudo mais aprendido até agora. No entanto, é importante ressaltar que você não terá acesso ao código original, sendo livre para escrever o seu próprio código.

## Solucao
#### Foi utilizado na implementação da solução para o desafio do Tec Challenge, Spring boot, java 17, MongoDB, Docker, lombok e swagger.
#### Foi desenvolvida uma API REST com os endpoints necessários para realização do fluxo de trabalho, salvando as informações em um banco de dados NOSQL(mongoDB). A estrutura do Document foi pensada de maneira a facilitar sua leitura e evitar a realização de JOIN.
#### A aplicação está dockerizada, utilizando docker-compose, foram criados dois contêineres, um para a API e outro para o BD, permitindo e facilitando sua escalabilidade, podendo ser facilmente implantada em um ambiente Cloud.
#### Durante o desenvolvimento, surgiu uma dúvida de como seria a automatização do processo de monitoramento do tempo das vagas, que foi resolvido utilizando o Scheduled do Spring, para rodar um job que faz a varredura no banco procurando por reservas ativas.
#### Outra questão foi sobre a forma de pagamento Pix ou Cartão de débito ou crédito que foi resolvido fazendo uso do padrão de projeto Strategy.
O projeto faz uso do padrão MVC e está implementado pensando em isolar as regras de negócio e separar as responsabilidades do sistema, garantindo assim, sua fácil manutenção e evolução, seguindo conceitos como Responsabilidade única, Segregação por interface e Inversão de dependência.

## Execução
Na raiz do projeto executar o comando:
docker-compose up --build -d

## Documentação
http://localhost:8080/swagger-ui.html

## Repositório
https://github.com/mauricioaramos/fiapTechChallengeFase2




