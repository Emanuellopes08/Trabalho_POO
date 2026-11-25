package domain.contas;

/**
 * Modalidades de conta oferecidas pelo banco.
 */
public enum TipoConta {

    /** Conta poupança, com rendimento sobre o saldo. */
    POUPANCA,

    /** Conta corrente, com mensalidade. */
    CORRENTE,

    /** Conta compartilhada entre titular e cotitular. */
    CONJUNTA,

    /** Conta de pessoa jurídica. */
    PJ
}