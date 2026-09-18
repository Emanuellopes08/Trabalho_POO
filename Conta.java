public abstract class Conta {
        private String tipoConta;
        private double saldo = 0;
        protected Cliente cliente; //protected serve para proteger, mas pode ser acessado


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



