-- =========================================
-- BANCO ES
-- Criação das tabelas
-- =========================================

-- conta gerente: usuario = gerente, senha = MinhaSenha123, codigo = 1

PRAGMA foreign_keys = ON;

-- =========================================
-- TABELA: CLIENTES
-- =========================================

CREATE TABLE IF NOT EXISTS clientes (
    cpf TEXT PRIMARY KEY,
    nome TEXT NOT NULL,
    data_nascimento TEXT NOT NULL,
    renda REAL NOT NULL CHECK (renda >= 0),
    telefone TEXT,
    endereco TEXT,
    nome_pai TEXT,
    nome_mae TEXT,
    email TEXT,
    senha TEXT NOT NULL
);

-- =========================================
-- TABELA: FUNCIONARIOS
-- =========================================

CREATE TABLE IF NOT EXISTS funcionarios (
    codigo INTEGER PRIMARY KEY,
    usuario TEXT NOT NULL UNIQUE,
    senha TEXT NOT NULL,
    cargo TEXT NOT NULL
);

-- =========================================
-- GERENTE INICIAL
-- =========================================

INSERT OR IGNORE INTO funcionarios (codigo, usuario, senha, cargo)
VALUES (1, 'gerente', 'MinhaSenha123', 'Gerente');

-- =========================================
-- TABELA: CONTAS
-- =========================================

CREATE TABLE IF NOT EXISTS contas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,

    tipo_conta TEXT NOT NULL
        CHECK (tipo_conta IN ('POUPANCA', 'CORRENTE', 'CONJUNTA', 'PJ')),

    titular_cpf TEXT NOT NULL,
    cotitular_cpf TEXT,

    saldo REAL NOT NULL DEFAULT 0,

    -- Dados específicos da ContaPJ
    cnpj TEXT UNIQUE,
    nome_empresa TEXT,
    fatura_empresa REAL,

    -- Relacionamento com os clientes
    FOREIGN KEY (titular_cpf)
        REFERENCES clientes(cpf)
        ON DELETE RESTRICT,

    FOREIGN KEY (cotitular_cpf)
        REFERENCES clientes(cpf)
        ON DELETE RESTRICT,

    -- Impede que titular e cotitular sejam a mesma pessoa
    CHECK (
        cotitular_cpf IS NULL
        OR cotitular_cpf != titular_cpf
    ),

    -- Exige cotitular somente para contas conjuntas
    CHECK (
        (tipo_conta = 'CONJUNTA' AND cotitular_cpf IS NOT NULL)
        OR
        (tipo_conta != 'CONJUNTA' AND cotitular_cpf IS NULL)
    ),

    -- Validação dos dados específicos de ContaPJ
    CHECK (
        (tipo_conta = 'PJ'
         AND cnpj IS NOT NULL
         AND nome_empresa IS NOT NULL
         AND fatura_empresa IS NOT NULL
         AND fatura_empresa >= 0)
        OR
        (tipo_conta != 'PJ'
         AND cnpj IS NULL
         AND nome_empresa IS NULL
         AND fatura_empresa IS NULL)
    )
);

-- =========================================
-- ÍNDICES
-- =========================================

CREATE INDEX IF NOT EXISTS idx_contas_titular
    ON contas(titular_cpf);

CREATE INDEX IF NOT EXISTS idx_contas_cotitular
    ON contas(cotitular_cpf);