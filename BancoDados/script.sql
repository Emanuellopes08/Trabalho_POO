
-- =========================================
-- BANCO ES
-- Criação das tabelas
-- =========================================

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
    email TEXT
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

    FOREIGN KEY (titular_cpf)
        REFERENCES clientes(cpf)
        ON DELETE RESTRICT,

    FOREIGN KEY (cotitular_cpf)
        REFERENCES clientes(cpf)
        ON DELETE RESTRICT,

    CHECK (
        (tipo_conta = 'CONJUNTA' AND cotitular_cpf IS NOT NULL)
        OR
        (tipo_conta != 'CONJUNTA' AND cotitular_cpf IS NULL)
    ),

    CHECK (
        (tipo_conta = 'PJ'
            AND cnpj IS NOT NULL
            AND nome_empresa IS NOT NULL
            AND fatura_empresa IS NOT NULL)
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
