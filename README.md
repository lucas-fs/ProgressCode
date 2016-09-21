# ProgressCode - Projeto de Software II
A partir do estudo de caso do Clube, constatou-se a necessidade de uma ferramenta que pudesse auxiliar os tutores no processo de acompanhamento dos alunos, bem como algo que centralizasse a grande quantidade de informações relacionadas as atividades e seus respectivos participantes. Sendo assim, o presente projeto visa o desenvolvimento de um sistema que atenda a esses dois requisitos de forma integrada.

O sistema proposto basicamente fará uso de duas tecnologias de forma conjunta, a primeira delas refere-se a um aplicativo mobile com o objetivo de auxiliar os tutores na supervisão dos alunos e a segunda trata-se de um pequeno sistema web em que se concentrarão as informações coletadas pelos tutores por meio do app.

A partir da aplicação mobile, cada tutor poderá registrar o progresso de cada aluno de uma forma rápida e simplificada. O aplicativo também possibilitará o apontamento de observações audíveis de cada participante por meio da gravação de áudio para agilizar a tarefa de registro e, ainda, poderá se ter uma visão simplificada do progresso de cada um dos alunos e da turma com um todo.

Já o sistema web, será responsável por agrupar as informações provenientes da observação de cada tutor em seu app. Esse sistema contará com as funções de criação de turma, criação de atividade, visualização do progresso de cada aluno, visualização do progresso da turma e criação de uma lista de chamada.


## Pacotes, libs e frameworks necessários

**Cliente MySQL**
```
sudo apt-get install libmysqlclient-dev
```

**Cliente MySQL para Python 3**
```
sudo pip3 install mysqlclient
```

**Django Framework (versão 10.1)**
```
pip3 install Django
```

**Django Tastypie RESTful API**
```
pip3 install django-tastypie==0.13.3

pip3 install defusedxml

pip3 install lxml
```


## Cronograma de atividades

| Data Limite | Atividade a ser desenvolvida | Status |
| --------|---------|-------|
| 16/09 | Criação do banco de dados | x |
| 23/09 | Criação do CRUD do banco de dados | x | 
| 23/09 | Criação do Web Service RESTful |   |
| 30/09 | Criação de funções de request do app mobile |   |
| 30/09 | Criação de funções de response do app mobile |   |
| 05/10 | Criação de funções de request do sistema web |   |
| 05/10 | Criação de funções de response do sistema web |   |
| 10/10 | Criação de funções de autenticação no app mobile e no sistema web |   |
| 10/10 | Criação das Activities basicas no app mobile |   |
| 14/10 | Criação dos Models, Views e Templates basicos no sistema web |   |
| 18/10 | Criação de funções de cadastro de evento e encontros no app mobile |   |
| 18/10 | Criação de funções de cadastro de evento e encontros no sistema web |   |
| 23/10 | Criação de funções de cadastro de tutores e equipes no app mobile |   |
| 23/10 | Criação de funções de cadastro de tutores e equipes no sistema web |   |
| 25/10 | Criação das funções de feedback de alunos |   |
| 05/11 | Criação das funções de manipulação e envio do áudio capturado com o dispositivo |   |
| 05/11 | Criação de funções de importação do .csv da listagem de participantes de cada evento |   |
| 10/11 | Melhoramento de UX e design da interface mobile |   |
| 12/11 | Melhoramento das páginas para visualização dos dados coletados com o app |   |
| 16/11 | Melhoramento de UX e design da interface web |   |
| 23/11 | Criação da função de chamada de alunos |   |
| 30/11 | Testes|   |

