public abstract class Conta {
        private TipoConta tipoConta;
        private Cliente titular;
        private double saldo = 0;

        public Conta(){

        }
        public Conta (TipoConta tipoConta, Cliente titular){
            this.tipoConta = tipoConta;
            this.titular = titular;
        }

        public TipoConta getTipoConta() {
            return tipoConta;
        }

        public Cliente getTitular() {
            return titular;
        }
        public double getSaldo() {
            return saldo;
        }

        //para aumentar o saldo
        public void efetuarDeposito(double deposito){
            this.saldo += deposito;
        }

        //para diminuir o saldo
        public void efetuarSaque(double saque){
            this.saldo -= saque;
        }
}