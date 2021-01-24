# desafio-casa-do-codigo

Prática deliberada com o objetivo de treinar habilidades em Java, Spring e JPA\Hibernate

## Requisitos

### Cadastro de um novo autor

+ Necessidades

        É necessário cadastrar um novo autor no sistema. Todo autor tem um nome, email e uma descrição. Também queremos saber o instante exato que ele foi registrado.

+ Restrições

        O instante não pode ser nulo
        O email é obrigatório
        O email tem que ter formato válido
        O nome é obrigatório
        A descrição é obrigatória e não pode passar de 400 caracteres

+ Resultado esperado

        Um novo autor criado e status 200 retornado

### Email do autor é único

+ Necessidades

        O email do autor precisa ser único no sistema

+ Resultado esperado

        Erro de validação no caso de email duplicado

### Cadastro de uma categoria

+ Necessidades

        Toda categoria precisa de um nome

+ Restrições

        O nome é obrigatório
        O nome não pode ser duplicado

+ resultado esperado

        Uma nova categoria cadastrada no sistema e status 200 retorno
        Caso alguma restrição não seja atendida, retorne 400 e um json informando os problemas de validação

### Cadastrar um livro

+ Necessidades

        Um título
        Um resumo do que vai ser encontrado no livro
        Um sumário de tamanho livre. O texto deve entrar no formato markdown, que é uma string. Dessa forma ele pode ser formatado depois da maneira apropriada.
        Preço do livro
        Número de páginas
        Isbn(identificador do livro)
        Data que ele deve entrar no ar(de publicação)
        Um livro pertence a uma categoria
        Um livro é de um autor

+ Restrições

        Título é obrigatório
        Título é único
        Resumo é obrigatório e tem no máximo 500 caracteres
        Sumário é de tamanho livre.
        Preço é obrigatório e o mínimo é de 20
        Número de páginas é obrigatória e o mínimo é de 100
        Isbn é obrigatório, formato livre
        Isbn é único
        Data que vai entrar no ar precisa ser no futuro
        A categoria não pode ser nula
        O autor não pode ser nulo

+ Resultado esperado

        Um novo livro precisa ser criado e status 200 retornado
        Caso alguma restrição não seja atendida, retorne 400 e um json informando os problemas de validação

### Exibir lista de livros

+ Necessidade

        Para que seja fácil pegar um id do livro, vamos exibir a lista de livros cadastrados.

+ resultado esperado

        Um json com a lista de livros com id e nome do livro

### Exibir a página de detalhe de um livro

+ Necessidades

        Ter um endpoint que em função de um id de livro retorne os detalhes necessários para montar a página.

+ Restrições

        Se o id não existir é para retornar 404

+ Resultado esperado

        Que o front possa montar a página

### Cadastro de país e estados do país

+ Necessidades

        Precisamos de um cadastro simples de países e seus respectivos estados. Cada país tem um nome e cada estado tem um nome e pertence a um país.

+ Restrições para país

        O nome é obrigatório
        O nome é único

+ Restrição para estados

        O nome é obrigatório
        O nome é único
        O país é obrigatório

+ Resultado esperado

        Dois endpoints para que seja possível cadastrar países e estados. Pode existir país sem estados associados.
        Caso alguma restrição não seja atendida, retornar 400 e json com os problemas de validação.

### Fluxo de pagamento - Parte 1

+ Necessidades

        Uma coisa importante. Na Casa do Código, você não faz um cadastro e tem suas compras associadas. Toda vez você coloca seu email, cpf/cnpj etc. Como isso vai ser implementado depende da aplicação.

+ Os seguintes campos precisam ser preenchidos:

        email
        nome
        sobrenome
        documento(cpf/cnpj)
        endereco
        complemento
        cidade
        pais
        estado(caso aquele pais tenha estado)
        telefone
        cep

+ Restrição

        email obrigatório e com formato adequado
        nome obrigatório
        sobrenome obrigatório
        documento(cpf/cnpj) obrigatório e só precisa ser um cpf ou cnpj
        endereco obrigatório
        complemento obrigatório
        cidade obrigatório
        país obrigatório
        se o país tiver estados, um estado precisa ser selecionado
        estado(caso aquele pais tenha estado) - apenas se o país tiver cadastro de estados
        telefone obrigatório
        cep é obrigatório

+ Resultado esperado

        Compra parcialmente gerada, mas ainda não gravada no banco de dados. Falta os dados do pedido em si que vão ser trabalhados no próximo fluxo.

### Fluxo de pagamento - Parte 2

+ Necessidades

        Receber também o parâmetro relativo ao carrinho de compras no formulário final. O json montado pelo cliente relativo ao carrinho tem o seguinte formato:

```
{
  total": decimal,
  "itens":[
     {
      "idLivro":number,
      "quantidade": "number"
    },
     {
      "idLivro":number,
      "quantidade": number
    }
  ]
}
```

+ Restrição

        o total é não nulo
        o total é maior que zero
        tem pelo menos um item no carrinho
        idLivro é obrigatório e precisa existir
        quantidade é obrigatória
        quantidade é maior que zero
        o total calculado no servidor precisa ser igual ao total enviado

+ Resultado esperado

        Compra gerada com um status de iniciada
        status 201 gerado com o endereço de detalhe da compra

### Cadastro de cupom de desconto

+ Necessidades

        A casa do código pode liberar cupons de desconto com valores e validade variados. Precisamos ter suporte a isso.

        Todo cupom tem:

        um código para ser entendido por pessoas
        um percentual de desconto
        uma validade para ser associado a uma compra

+ Restrições

        o código é obrigatório
        o código é único
        o percentual é obrigatório e positivo
        a validade é no futuro

+ Resultado esperado

        status 400 e json de erros em caso de falha de validação
        status 200 e cupom gerado

### Aplicar cupom na compra

+ Necessidades

        Agora, no começo do processo de finalização de compra, um cupom pode ser aplicado.

+ Restrições

        o código do cupom precisa ser válido
        o cupom precisa ser válido ainda
        uma vez associado o cupom, uma compra nunca pode ter essa informação alterada.
        O cupom só pode ser associada com uma compra que ainda não foi registrada no banco de dados (esse daqui eu não implementei)

+ Resultado esperado

        Manter o mesmo resultado que já existia

### Detalhes de uma compra

+ Necessidades

        Agora é preciso retornar todos os dados necessários para que a pessoa visualize os dados da compra dela. Para confirmar que tudo está certinho.

        Caso tenha cupom aplicado, além do valor original da compra é necessário mostrar o valor final com o cupom aplicado.

        O endpoint recebe apenas o id gerado pela compra.

+ Resultado esperado

        Status 200 e json de detalhes de uma compra
        Sempre teremos dois campos no json de saída: Um campo informando se existe o cupom e outro informando o valor da compra com o possível cupom aplicado. Caso não tenha cupom, os valores dos campos devem ser false e null respectivamente. 
