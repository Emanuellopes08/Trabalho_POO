# Trabalho_POO

Partes de cada um no trabalho de POO

Emanuel - Administrador, Parte Gráfica 1
Iasmin - Cliente, Slide
Liane - Main
Adryan - ContaPJ, Parte Gráfica 2
Vinícius - ContaPoupança
Murilo - Funcionário

Obs.: Conta e Conta Corrente, foi criada em conjunto durante a aula do dia 18/09.

### 3.3 Checklist para Nós

| Elemento | O que deve ser demonstrado | Como a UrbanXP poderia cumprir o item |  Onde cada conceito foi utilizado |
| --- | --- | --- | --- |
| Classes e objetos Java | Classes coerentes e instanciação de objetos do domínio | Instanciar Show, Cliente e Ingresso |
| Atributos de classe Java | Estado relevante armazenado nos objetos | titulo, dataHora, capacidadeMaxima e precoBase em Experiencia |
| Métodos de classe Java | Comportamentos associados às classes corretas | possuiVagas(), reservarVaga() e gerarResumo() |
| Desafio da aula de Java| Aplicação prática dos fundamentos vistos em aula | Resolver o desafio indicado e reaproveitar o raciocínio na modelagem do projeto |
| Construtores em Java | Inicialização obrigatória e criação de objetos válidos | Exigir título, data, capacidade e preço no construtor de Experiencia |
| Palavra-chave this | Referência inequívoca ao objeto atual | Usar this.titulo = titulo no construtor |
| Modificadores Java | Visibilidade adequada de classes, atributos e métodos | Manter atributos private e expor somente operações públicas necessárias |
| Encapsulamento em Java | Proteção do estado e alterações por métodos controlados | Impedir alteração direta das vagas e usar reservarVaga() |
| Pacotes Java / API | Organização do código por responsabilidade | Separar modelo, servico e interfacegrafica |
| Herança em Java | Especialização coerente de um conceito geral | Show, PasseioTuristico e Workshop estendem Experiencia |
| Polimorfismo em Java | Uso do tipo geral para manipular objetos diferentes | Percorrer List<Experiencia> e chamar gerarResumo() |
| Palavra-chave super | Reaproveitamento da inicialização ou comportamento da superclasse | Construtores das subclasses chamam super(...) |
| Abstração em Java | Representação de um conceito geral que não deve ser instanciado diretamente | Declarar Experiencia como abstrata |
|Interfaces em Java | Contrato com implementações substituíveis | PoliticaDesconto implementada por diferentes políticas |
|Enum em Java | Conjunto fechado de valores válidos | StatusIngresso { RESERVADO, PAGO, CANCELADO } |
|Tratamento de exceções em Java | Lançamento e tratamento adequado de erros, incluindo exceções próprias do domínio | Criar e lançar ExperienciaLotadaException e tratá-la na interface gráfica |
|Interface gráfica com Swing | Interface mínima funcional para entrada, execução de operações e apresentação de resultados | Usar JFrame, componentes de formulário, botões e JOptionPane para cadastro e reserva |
|Data e hora em Java | Uso da API java.time |Usar LocalDateTime na agenda das experiências |
