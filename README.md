<h1 align="center">🚀 Desafio: Serviço HTTP (API) 🚀</h1>
<p align="center">  Resolvendo a funcionalidade de validação de CEP.</p>

### Índice :pushpin:

<!--ts-->
- <a href="#feature">Feature</a>
- <a href="#tech">Tecnologias e Dependências</a>
- <a href="#swagger">Execução no Swagger</a>
- <a href="#test">Testes</a>
- <a href="#conclusion">Conclusão</a>
<!--te-->

<div id="feature">
    <h3> Feature :computer: </h3>
</div>

- [x] Uma das funcionalidades mais importantes no checkout de um
e-commerce é validação do endereço do consumidor. No
e-commerce o cliente deve informar um CEP brasileiro e o
checkout deve validar e retornar o endereço completo.

<div id="tech">
  <h3> Tecnologias e Dependências 🛠  </h3>
</div>

* Git
* Java 11
* Maven
* Springboot 2.5.2
* Lombok
* MongoDB - Opcional
* Mockito
* Swagger 2.9.2
  
<div id="swagger">
  <h3> Execução no Swagger :arrow_forward:  </h3>
</div>
- Execute o projeto;
- Abra o Swagger UI: http://localhost:8080/swagger-ui.html

#### :white_check_mark: Busca o endereço
Input: GET:"/api/cep/{cep}"
 ```bash
{
    "cep": "12345-678",
    "logradouro": "Rua Fictícia",
    "bairro": "Também Fictício",
    "localidade": "São Paulo",
    "uf": "SP"
}

```
<div id="test">
  <h3> Testes :ok: </h3>
</div>
<p>São 6 cénarios:</p>

[:heavy_check_mark:] **givenAValidCepNumberWhenGetEnderecoByThenReturnEndereco** -> Dado um CEP válido retorna o endereço correspondente;

[:heavy_check_mark:] **givenANonExistentCepNumberWhenGetEnderecoByThenReturnBadRequest** -> Dado um CEP inexistente retorna o código HTTP 400 (_Bad Request_);

[:heavy_check_mark:] **givenAMutableCepNumberWhenGetEnderecoByThenReturnAnotherCep** -> Dado um CEP válido que não exista o endereço, deve substituir um dígito da direita para a esquerda por zero até que o endereço seja localizado (Exemplo: Dado 22333999 tentar com 22333990, 22333900 etc);

[:heavy_check_mark:] **givenAEmptyCepNumberWhenGetEnderecoByThenReturnBadRequest** -> Dado um cep vazio retorna o código HTTP 400 (_Bad Request_);

[:heavy_check_mark:] **givenACepSizeDifferentEightWhenGetEnderecoByThenReturnBadRequest** -> Dado um cep com tamanho diferente de 8 caracteres retorna o código HTTP 400 (_Bad Request_);

[:heavy_check_mark:] **givenAAlphanumericCepWhenGetEnderecoByThenReturnBadRequest** -> Dado um cep alfanúmerico (com caracteres alfabéticos) retorna o código HTTP 400 (_Bad Request_).

<div id="conclusion">
  <h3> Conclusão :speech_balloon: </h3>
</div>
 
Estratégia utilizada para a criação da aplicação:

Meu objetivo principal foi focar no desenvolvimento dos requisitos funcionais com os testes. Pensando em qual seria a melhor abordagem de implementação, essa foi a primeira vez que desenvolvi uma aplicação Orientada a Testes (TDD).

Todas as validações tem suas respectivas mensagens de erro padronizadas com o código HTTP no corpo.

Para melhorar a qualidade do código, generalizei a validação (utilizando de uma implementação Validator) e separei uma parte que valida o CEP em uma classe específica para tal (CepValidator).

Um ponto que me atentei foi tentar diminuir as linhas de código, não somente melhorando alguns métodos para que ficassem mais coesos e com a lógica mais objetiva, também removi algumas linhas em branco pensando na acessibilidade de desenvolvedores com deficiência visual (linhas vazias são citadas também pelas ferramentas de leitura de tela, dificultando o processo de entedimento do código).

E documentei a API utilizando o Swagger.


A arquitetura utilizada:

O padrão arquitetural utilizado na estrutura dos pacotes e classes foi uma recomendação da documentação do [Spring](https://docs.spring.io/spring-boot/docs/2.5.0-SNAPSHOT/reference/html/using.html#using.structuring-your-code), com o qual utiliza algumas das melhores práticas de desenvolvimento. Acabei me interessando bastante sobre Clean Architecture também (com o qual já estava na minha listinha de “leituras obrigatórias”), mas por ser um assunto mais vasto e que demanda um certo nível de expertise e conhecimento mais aprofundado (sobre conceitos que já venho até estudando e tentando implementar no meu código, como por exemplo: reusabilidade de código, alta coesão, baixo acoplamento, entre outros), acabei não abordando-o nesse momento. Mas fica como dever de casa (e super aceito o livro “Arquitetura limpa” do Uncle Bob de presente de aniversário atrasado. #ficaadica).

Analisando a que foi utilizada para desenvolver essa API, como é um serviço com menos entidades e centralizado e uma única necessidade, fica muito claro a atribuição e separação das responsabilidades. Visto que havia até começado a implementar a parte de Endereço do Cliente (vide branch develop) - para salvar informações como: “número” e “complemento” no mongoDB (acabei me empolgando com o projeto, talvez se torne uma sequência dos meus estudos :thought_balloon:) -, ainda assim fica muito sucinto a distribuição do que cada pacote contém e o que cada uma das classes/entidades devem fazer.


Os padrões:

Padrão REST: a princípio fiquei reflexiva em relação aos métodos, nomenclatura dos paths, se uma determinada requisição deve ser passada a informação da consulta por parâmetro ou pelo corpo, mas após ler diversos artigos sobre o assunto, encontrei materiais bem interessantes em relação ao desenvolvimento de um API REST. O próprio Swagger segue essa interface de programação de aplicações à risca, seguindo até no momento do consumo do endpoint o conjunto de princípios e definições do estilo de arquitetura REST. Por fim decidi seguir com a busca do CEP com o método GET passando a informação por parâmetro na URI.

Bom, vou finalizando por aqui esse textão. Para mais sugestões, críticas (construtivas), elogios, dúvidas e/ou presentes, estou à disposição.
Até a próxima!
