# Trabalho_POO

## Partes de cada um no trabalho de POO

- **Emanuel** - Administrador, Parte Gráfica 1 e integração das telas com o `SistemaBanco`
- **Iasmin** - Cliente, Slide
- **Liane** - Main
- **Adryan** - ContaPJ, Parte Gráfica 2
- **Vinícius** - ContaPoupança
- **Murilo** - Funcionário

> **Observação:** As classes `Conta` e `ContaCorrente` foram criadas em conjunto durante a aula do dia 18/09.

---

## 1. Nome do Sistema: Banco ES

### Integrantes

Iasmin Dembinski da Silva, Liane Paim De Castro Genz, Emanuel Lopes Vieira, Adryan Oliveira de Oliveira, Vinícius Schaefer Sanchotene e Pedro Murillo Cruz Carvalho.

### Grupo 1

---

## 2. Descrição do Sistema

O sistema desenvolvido consiste em uma aplicação bancária destinada ao gerenciamento de clientes, funcionários, gerente, contas e operações bancárias.

O projeto foi estruturado utilizando conceitos de **Programação Orientada a Objetos em Java**, como classes, objetos, atributos, métodos, construtores, encapsulamento, herança, polimorfismo, abstração, modificadores de acesso, coleções, tratamento de exceções e reutilização de código.

O sistema possui diferentes tipos de usuários:

- **Cliente**
- **Funcionário**
- **Gerente**

Também possui diferentes modalidades de contas:

- **Conta Corrente**
- **Conta Poupança**
- **Conta Conjunta**
- **Conta PJ**

Além da versão original executada pelo console, o projeto possui uma versão gráfica desenvolvida com **Java Swing**. A interface gráfica possui telas de login, cadastro, consulta, alteração, exclusão e operações bancárias.

A classe `SistemaBanco` é utilizada para compartilhar os clientes, contas e funcionários entre as diferentes telas enquanto o programa está em execução.

---

## 3. Instruções para Executar o Projeto

1. Abrir o projeto em uma IDE com suporte ao Java, como Visual Studio Code, IntelliJ IDEA ou Eclipse.
2. Certificar-se de que o JDK está instalado e configurado.
3. Compilar todas as classes do projeto.
4. Executar a classe `MainGrafica.java`.
5. A classe `MainGrafica` abrirá a `TelaPrincipal`, permitindo o acesso às áreas de Gerente, Funcionário e Cliente.

Fluxo inicial da versão gráfica:

```text
MainGrafica
     ↓
TelaPrincipal
     ↓
Gerente / Funcionário / Cliente
```

A classe `Main.java` contém a versão original do sistema em modo console e serviu como referência para várias regras de negócio posteriormente utilizadas na interface gráfica.

---

## 4. Classes Principais

- **Gerente:** representa o gerente da instituição bancária. Na lógica atual do sistema, o gerente é responsável principalmente pelo gerenciamento dos funcionários, podendo cadastrar, consultar, alterar e excluir funcionários.

- **Funcionário:** representa os funcionários que atuam no banco. Na interface gráfica, o funcionário realiza login e pode cadastrar, consultar, alterar e excluir clientes.

- **Cliente:** representa os clientes da instituição bancária. O cliente possui dados como nome, CPF, data de nascimento, renda, telefone, endereço, nome do pai, nome da mãe e email. Um cliente pode possuir uma ou mais contas e realizar operações como depósito e saque.

- **Conta:** representa a estrutura geral de uma conta bancária, contendo características e comportamentos comuns às diferentes modalidades de conta, como saldo, titular, consulta de saldo, depósito e saque.

- **Conta Corrente:** representa uma modalidade de conta destinada às operações bancárias cotidianas dos clientes. Possui uma mensalidade associada.

- **Conta Poupança:** representa uma conta destinada à manutenção de valores e ao rendimento das economias do cliente. Possui rendimento de 0,5% sobre o saldo, de acordo com a regra definida no projeto.

- **Conta PJ:** representa uma conta destinada a pessoas jurídicas. Está associada a um cliente titular e possui informações específicas da empresa, como CNPJ, nome da empresa e faturamento.

- **Conta Conjunta:** representa uma conta compartilhada entre um titular e um cotitular. Ambos podem acessar a mesma conta e utilizar o mesmo saldo.

- **SistemaBanco:** centraliza e compartilha os dados utilizados pelas telas. Mantém listas de clientes, contas e funcionários em memória e disponibiliza métodos para cadastrar, buscar e excluir objetos.

- **BotaoArredondado:** classe gráfica que herda de `JButton` e personaliza o desenho dos botões por meio da sobrescrita do método `paintComponent()`.

- **Main:** contém a versão original do sistema em modo console e as regras que serviram de base para o desenvolvimento das funcionalidades.

- **MainGrafica:** é responsável por iniciar a versão gráfica do sistema, abrindo a `TelaPrincipal`.

---

## 5. Interface Gráfica

A interface gráfica foi desenvolvida utilizando **Java Swing**.

Entre os principais componentes utilizados estão:

```java
JFrame
JPanel
JLabel
JTextField
JPasswordField
JButton
JComboBox
JOptionPane
```

Também são utilizados layouts como:

```java
BorderLayout
GridBagLayout
BoxLayout
```

### TelaPrincipal

É a tela inicial da versão gráfica.

Permite escolher entre:

- Gerente
- Funcionário
- Cliente
- Sair

### TelaLoginFuncionario

Realiza a autenticação do funcionário.

A tela utiliza o código informado para buscar o funcionário através de:

```java
SistemaBanco.buscarFuncionarioPorCodigo(codigo);
```

### TelaFuncionario

Funciona como menu da área do funcionário e permite acessar:

- `TelaCadastroCliente`
- `TelaConsultaCliente`
- `TelaAlterarCliente`
- `TelaExcluirCliente`

### TelaCadastroCliente

Permite cadastrar os dados pessoais do cliente e criar uma conta.

Dados do cliente:

- Nome
- CPF
- Data de nascimento
- Renda
- Telefone
- Endereço
- Nome do pai
- Nome da mãe
- Email

Tipos de conta disponíveis:

- Poupança
- Corrente
- Conjunta
- PJ

Para uma Conta Conjunta também é solicitado o CPF do cotitular.

Para uma Conta PJ também são solicitados:

- CNPJ
- Nome da empresa
- Faturamento

Após as validações, a tela cria os objetos e os adiciona ao `SistemaBanco`.

### TelaConsultaCliente

Permite procurar um cliente através do CPF.

A busca utiliza:

```java
SistemaBanco.buscarClientePorCpf(cpf);
```

Quando o cliente é encontrado, são apresentados o nome e o CPF.

### TelaAlterarCliente

Permite localizar um cliente pelo CPF e alterar:

- Nome
- Renda
- Telefone
- Endereço
- Email

A tela utiliza getters para obter os dados existentes e setters para alterá-los.

### TelaExcluirCliente

Permite procurar um cliente pelo CPF e, após uma confirmação, excluí-lo da lista de clientes mantida pelo `SistemaBanco`.

### TelaLoginCliente

O cliente é localizado pelo CPF.

Depois o sistema procura todas as contas relacionadas a ele:

```java
SistemaBanco.buscarContasDoCliente(cliente);
```

Caso existam contas, o cliente é encaminhado para a `TelaCliente`.

### TelaCliente

Representa a área bancária do cliente.

Permite:

- Selecionar uma conta
- Visualizar o saldo
- Depositar
- Sacar

Caso o cliente possua mais de uma conta, elas são apresentadas em um `JComboBox`.

---

## 6. Integração com SistemaBanco

A classe `SistemaBanco` possui listas compartilhadas semelhantes a:

```java
List<Cliente>
List<Conta>
List<Funcionario>
```

Entre seus principais métodos estão:

```java
adicionarCliente()
adicionarConta()
adicionarFuncionario()
buscarClientePorCpf()
buscarFuncionarioPorCodigo()
buscarContasDoCliente()
buscarContaPjPorCnpj()
excluirCliente()
getGerente()
```

### Classes que utilizam diretamente o SistemaBanco

**TelaLoginFuncionario**

```java
SistemaBanco.buscarFuncionarioPorCodigo()
```

**TelaCadastroCliente**

```java
SistemaBanco.buscarClientePorCpf()
SistemaBanco.buscarContaPjPorCnpj()
SistemaBanco.adicionarCliente()
SistemaBanco.adicionarConta()
```

**TelaConsultaCliente**

```java
SistemaBanco.buscarClientePorCpf()
```

**TelaAlterarCliente**

```java
SistemaBanco.buscarClientePorCpf()
```

**TelaExcluirCliente**

```java
SistemaBanco.buscarClientePorCpf()
SistemaBanco.excluirCliente()
```

**TelaLoginCliente**

```java
SistemaBanco.buscarClientePorCpf()
SistemaBanco.buscarContasDoCliente()
```

A `TelaCliente` não precisa buscar novamente os dados no `SistemaBanco`, pois recebe o objeto `Cliente` e a `List<Conta>` diretamente da `TelaLoginCliente`.

---

## 7. Regras de Negócio

### RN1 - Saldo inicial da conta

Uma conta bancária é iniciada com saldo igual a zero.

### RN2 - Depósito

O valor do depósito deve ser maior que zero. Quando válido, o valor é acrescentado ao saldo atual da conta.

### RN3 - Saque

O valor do saque deve ser maior que zero e não pode ultrapassar o saldo disponível da conta. Quando válido, o valor é subtraído do saldo atual.

### RN4 - Rendimento da Conta Poupança

A Conta Poupança possui uma taxa de rendimento de 0,5% e o rendimento é calculado sobre o saldo da conta.

### RN5 - Conta Conjunta

Uma Conta Conjunta possui obrigatoriamente um titular e um cotitular. O cotitular deve ser um cliente já cadastrado no sistema.

Tanto o titular quanto o cotitular podem localizar e utilizar a mesma Conta Conjunta, compartilhando o mesmo saldo.

### RN6 - Mensalidade da Conta Corrente

A Conta Corrente possui uma mensalidade associada à conta, cujo valor pode ser consultado.

### RN7 - Conta para Pessoa Jurídica

A Conta PJ está associada a um cliente titular e possui informações específicas da empresa, como CNPJ, nome da empresa e faturamento.

### RN8 - Identificação do Funcionário

O funcionário possui usuário, senha, código e cargo para representar suas informações no sistema.

### RN9 - Identificação do Gerente

O gerente possui nome, senha e código para sua identificação no sistema.

### RN10 - Gerenciamento de Funcionários

O gerente é responsável pelo gerenciamento dos funcionários, podendo cadastrar, consultar, alterar e excluir funcionários.

### RN11 - Múltiplas Contas

Um cliente pode possuir mais de uma conta. Na área do cliente, as contas relacionadas ao seu CPF podem ser apresentadas para seleção.

### RN12 - Acesso do Cotitular

Na busca das contas de um cliente, o sistema também verifica se ele é cotitular de uma `ContaConjunta`.

### RN13 - CPF Único

Durante o cadastro, o sistema verifica se já existe um cliente utilizando o CPF informado.

### RN14 - CNPJ Único

Durante a criação de uma Conta PJ, o sistema verifica se já existe uma Conta PJ utilizando o mesmo CNPJ.

### RN15 - Validação de Valores

Valores como renda, faturamento, depósito e saque precisam possuir formato numérico válido.

---

## 8. Conceitos de Programação Orientada a Objetos

### Classes e Objetos

O projeto possui classes como:

```java
Cliente
Funcionario
Gerente
Conta
ContaCorrente
ContaPoupanca
ContaConjunta
ContaPJ
```

Os objetos são criados com `new`.

Exemplos:

```java
new Cliente(...)
new ContaCorrente(...)
new ContaPoupanca(...)
new ContaConjunta(...)
new ContaPJ(...)
```

### Atributos

Os atributos representam o estado dos objetos.

Exemplo em `Cliente`:

```text
nome
cpf
dataNascimento
renda
telefone
endereco
nomePai
nomeMae
email
```

### Métodos

Os métodos representam comportamentos.

Exemplos:

```java
getSaldo()
efetuarDeposito()
efetuarSaque()
buscarClientePorCpf()
buscarContasDoCliente()
adicionarCliente()
adicionarConta()
```

### Construtores

Os construtores são executados quando um objeto é criado.

Exemplos:

```java
new Cliente(...)
new ContaCorrente(cliente)
new ContaPoupanca(cliente)
new ContaConjunta(cliente, cotitular)
new ContaPJ(cnpj, nomeEmpresa, faturamento, cliente)
```

### Palavra-chave `this`

A palavra-chave `this` representa o objeto atual.

Exemplo:

```java
this.cliente = cliente;
```

Ela permite diferenciar um atributo da classe de um parâmetro recebido.

### Modificadores de Acesso

O projeto utiliza modificadores como:

```java
private
public
protected
```

- `private`: restringe o acesso à própria classe.
- `public`: permite acesso por outras classes.
- `protected`: permite acesso pela própria classe e subclasses.

### Encapsulamento

O encapsulamento protege os dados dos objetos.

Exemplos:

```java
cliente.getNome();
cliente.setNome(...);

conta.getSaldo();
conta.efetuarDeposito(...);
conta.efetuarSaque(...);
```

### Herança

Os diferentes tipos de conta herdam da classe `Conta`.

```text
Conta
├── ContaCorrente
├── ContaPoupanca
├── ContaConjunta
└── ContaPJ
```

Também existe herança na interface gráfica:

```java
BotaoArredondado extends JButton
```

e nas telas que utilizam:

```java
extends JFrame
```

### Polimorfismo

Uma variável do tipo geral `Conta` pode armazenar diferentes subclasses.

Exemplo:

```java
Conta conta;

conta = new ContaCorrente(cliente);
```

ou:

```java
conta = new ContaPoupanca(cliente);
```

ou:

```java
conta = new ContaConjunta(cliente, cotitular);
```

ou:

```java
conta = new ContaPJ(cnpj, nomeEmpresa, faturamento, cliente);
```

### Sobrescrita de Métodos

A anotação `@Override` indica que um comportamento herdado foi redefinido.

Exemplo:

```java
@Override
protected void paintComponent(Graphics g)
```

na classe `BotaoArredondado`.

A `ContaPJ` também utiliza sobrescrita de saque conforme a implementação da classe, aplicando sua regra específica antes de reutilizar o comportamento geral.

### Palavra-chave `super`

A palavra-chave `super` permite utilizar construtores ou comportamentos da superclasse.

Na `ContaPJ`, por exemplo, pode ser utilizada para reaproveitar o comportamento de saque definido em `Conta`.

### Abstração

A classe `Conta` representa o conceito geral de uma conta bancária, enquanto `ContaCorrente`, `ContaPoupanca`, `ContaPJ` e `ContaConjunta` representam especializações desse conceito.

### Coleções

O projeto utiliza:

```java
List
ArrayList
```

para armazenar grupos de objetos, como clientes, contas e funcionários.

### `static`

O `SistemaBanco` utiliza membros estáticos para permitir o compartilhamento dos mesmos dados entre as telas.

Isso permite chamadas como:

```java
SistemaBanco.buscarClientePorCpf(cpf);
```

sem precisar criar uma instância de `SistemaBanco`.

### `instanceof`

É utilizado para verificar o tipo real de um objeto.

Exemplo:

```java
if (conta instanceof ContaConjunta)
```

### Cast

Depois de verificar o tipo com `instanceof`, o objeto pode ser tratado como a classe específica.

Exemplo:

```java
ContaConjunta contaConjunta = (ContaConjunta) conta;
```

---

## 9. Tratamento de Exceções

A interface utiliza `try/catch` para tratar entradas inválidas.

### Valores numéricos

Exemplo:

```java
try {
    renda = Double.parseDouble(rendaTexto.replace(",", "."));
} catch (NumberFormatException erro) {
    mensagemErro.setText("Digite uma renda válida.");
}
```

### Datas

A data de nascimento utiliza `LocalDate` e `DateTimeFormatter`.

Exemplo:

```java
DateTimeFormatter formato =
        DateTimeFormatter.ofPattern("dd/MM/yyyy");

LocalDate dataNascimento =
        LocalDate.parse(dataTexto, formato);
```

Uma data inválida pode ser tratada através de `DateTimeParseException`.

---

## 10. Armazenamento dos Dados

Atualmente os dados são armazenados em memória utilizando `ArrayList`.

Enquanto o programa permanece aberto, um cliente cadastrado pode ser encontrado em outras telas.

Exemplo:

```text
Programa inicia
      ↓
Cliente é cadastrado
      ↓
Cliente pode ser consultado
      ↓
Cliente pode ser alterado
      ↓
Cliente pode entrar na área bancária
```

Quando a aplicação é encerrada, os dados armazenados apenas em memória são perdidos.

O projeto atualmente não utiliza banco de dados ou arquivo para persistência.

---

## 11. Fluxo do Funcionário

```text
TelaPrincipal
      ↓
Funcionário
      ↓
TelaLoginFuncionario
      ↓
TelaFuncionario
      │
      ├── Cadastrar Cliente
      │        ↓
      │   TelaCadastroCliente
      │
      ├── Consultar Cliente
      │        ↓
      │   TelaConsultaCliente
      │
      ├── Alterar Cliente
      │        ↓
      │   TelaAlterarCliente
      │
      └── Excluir Cliente
               ↓
         TelaExcluirCliente
```

---

## 12. Fluxo do Cliente

```text
TelaPrincipal
      ↓
Cliente
      ↓
TelaLoginCliente
      ↓
CPF
      ↓
SistemaBanco.buscarClientePorCpf()
      ↓
SistemaBanco.buscarContasDoCliente()
      ↓
TelaCliente
      ↓
Selecionar Conta
      │
      ├── Visualizar Saldo
      ├── Depositar
      └── Sacar
```

---

## 13. Funcionamento da Conta Conjunta

O método `buscarContasDoCliente()` primeiro verifica se o cliente é o titular:

```java
if (conta.getTitular().equals(cliente)) {
    contasCliente.add(conta);
    continue;
}
```

Caso a conta seja conjunta:

```java
if (conta instanceof ContaConjunta) {
    ContaConjunta contaConjunta = (ContaConjunta) conta;

    if (contaConjunta.getCotitular().equals(cliente)) {
        contasCliente.add(conta);
    }
}
```

Dessa forma, tanto o titular quanto o cotitular podem acessar o mesmo objeto `ContaConjunta`.

---

## 14. Checklist

| Elemento | O que deve ser demonstrado | Como o Banco ES atende | Onde o conceito foi utilizado |
| --- | --- | --- | --- |
| Classes e objetos Java | Classes coerentes e instanciação de objetos do domínio | O sistema possui classes para clientes, funcionários, gerente, contas e telas. Objetos são criados com `new` | `Cliente.java`, `Funcionario.java`, `Gerente.java`, `Conta.java`, tipos de conta e telas Swing |
| Atributos de classe Java | Estado relevante armazenado nos objetos | As classes armazenam os dados necessários ao seu domínio | `Cliente`: nome, CPF, dataNascimento, renda, telefone, endereço, nomePai, nomeMae e email. `Conta`: saldo, titular e tipo. `Funcionario`: usuário, senha, código e cargo |
| Métodos de classe Java | Comportamentos associados às classes corretas | Métodos realizam operações bancárias e buscas/cadastros | `Conta`: `getSaldo()`, `efetuarDeposito()`, `efetuarSaque()`. `SistemaBanco`: `buscarClientePorCpf()`, `buscarContasDoCliente()`, `adicionarCliente()`, `adicionarConta()` e outros |
| Desafio da aula de Java | Aplicação prática dos fundamentos vistos em aula | Parte da modelagem inicial de `Conta` e `ContaCorrente` foi desenvolvida em conjunto durante a aula e reaproveitada no projeto | `Conta.java` e `ContaCorrente.java` |
| Construtores em Java | Inicialização obrigatória e criação de objetos válidos | Os objetos recebem dados essenciais em seus construtores | `Cliente(...)`, `ContaCorrente(cliente)`, `ContaPoupanca(cliente)`, `ContaConjunta(cliente, cotitular)`, `ContaPJ(cnpj, nomeEmpresa, faturamento, cliente)` e `Funcionario(...)` |
| Palavra-chave `this` | Referência ao objeto atual | Utilizada para diferenciar atributos de parâmetros recebidos | Exemplo: `this.cliente = cliente` e `this.contas = contas` |
| Modificadores Java | Visibilidade adequada de classes, atributos e métodos | Uso de `private`, `public` e `protected` de acordo com a necessidade | Classes de domínio e classes gráficas |
| Encapsulamento em Java | Proteção do estado e alterações por métodos controlados | Atributos são acessados por getters, setters e métodos de negócio | `getNome()`, `setNome()`, `getSaldo()`, `efetuarDeposito()` e `efetuarSaque()` |
| Pacotes Java / API | Uso de recursos da API Java e organização do código | O projeto utiliza APIs padrão como `java.util`, `java.time`, `javax.swing` e `java.awt` | Listas, datas, interface gráfica, cores e layouts. O projeto atualmente não está organizado em pacotes próprios |
| Herança em Java | Especialização coerente de um conceito geral | Os tipos de conta reutilizam o conceito geral `Conta` | `ContaCorrente`, `ContaPoupanca`, `ContaPJ` e `ContaConjunta` |
| Herança na interface gráfica | Reutilização de componentes Swing | Telas e botões personalizados herdam componentes da biblioteca | Telas com `extends JFrame` e `BotaoArredondado extends JButton` |
| Polimorfismo em Java | Uso do tipo geral para manipular objetos diferentes | Uma variável do tipo `Conta` pode receber objetos das diferentes subclasses | Cadastro das contas e listas do tipo `List<Conta>` |
| Sobrescrita de métodos | Redefinição de um comportamento herdado | Métodos herdados podem possuir implementação específica | `BotaoArredondado.paintComponent()` e regra específica de saque da `ContaPJ` |
| Palavra-chave `super` | Reaproveitamento da inicialização ou comportamento da superclasse | Subclasses podem reutilizar construtores e métodos de `Conta` | Utilização nas subclasses de `Conta`, incluindo o reaproveitamento do saque geral pela `ContaPJ` |
| Abstração em Java | Representação de um conceito geral | `Conta` representa a estrutura geral utilizada pelas contas específicas | `Conta.java` e suas subclasses |
| Interfaces em Java | Contrato implementado por diferentes classes | Não há uma interface Java própria confirmada na versão atual do projeto | Item ainda não demonstrado por uma `interface` criada pelo grupo |
| Enum em Java | Conjunto fechado de valores válidos | O projeto possui `TipoConta`; este item deve permanecer apenas se `TipoConta.java` estiver declarado como `enum` | `TipoConta.java` |
| Tratamento de exceções em Java | Tratamento adequado de entradas inválidas | A interface utiliza `try/catch` para números e datas inválidas | `NumberFormatException`, `DateTimeParseException`, telas de cadastro e operações |
| Interface gráfica com Swing | Interface funcional para entrada, execução de operações e apresentação de resultados | O sistema possui telas gráficas para Gerente, Funcionário e Cliente | `TelaPrincipal`, telas de login, cadastro, consulta, alteração, exclusão e `TelaCliente` |
| Data e hora em Java | Uso da API `java.time` | A data de nascimento do cliente utiliza `LocalDate` e `DateTimeFormatter` | `Cliente` e `TelaCadastroCliente` |
| Coleções | Armazenamento de vários objetos | Clientes, contas e funcionários são armazenados em listas | `List`, `ArrayList` e `SistemaBanco` |
| Lambda | Tratamento simplificado de eventos | Ações de botões utilizam expressões lambda | `addActionListener(e -> {...})` |
| `instanceof` | Identificação do tipo real de um objeto | Utilizado para identificar contas específicas | `ContaConjunta`, `ContaPJ` e `SistemaBanco` |
| Cast | Tratamento de um objeto geral como uma subclasse específica | Permite utilizar métodos exclusivos das subclasses após verificar o tipo | `(ContaConjunta) conta` e `(ContaPJ) conta` |
| `static` | Compartilhamento de membros pela classe | O `SistemaBanco` disponibiliza listas e métodos compartilhados entre as telas | `SistemaBanco.java` |

---

## 15. Observações Finais

- O sistema utiliza Java Swing para a interface gráfica.
- A classe `MainGrafica` é utilizada para iniciar a versão gráfica.
- A classe `Main` permanece como versão original em console e referência das regras.
- A classe `SistemaBanco` compartilha os dados entre as telas.
- Os dados atualmente são mantidos apenas em memória.
- Um cliente pode possuir mais de uma conta.
- A Conta Conjunta pode ser acessada pelo titular e pelo cotitular.
- Titular e cotitular compartilham o mesmo saldo.
- A Conta PJ possui cliente titular e dados específicos da empresa.
- O funcionário pode cadastrar, consultar, alterar e excluir clientes.
- O cliente pode visualizar saldo, depositar e sacar.
- A área do gerente é responsável pelo gerenciamento de funcionários.
- A persistência dos dados em banco de dados ou arquivo pode ser adicionada futuramente.
