// codigo corrigido 
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Quantas pessoas deseja cadastrar? ");
        int tamanho = input.nextInt();
        input.nextLine();

        Pessoa[] vetorPessoas = new Pessoa[tamanho];
        int qtd = 0;
        while (qtd < vetorPessoas.length) {
            System.out.println("\n--- Cadastro " + (qtd + 1) + " de " + vetorPessoas.length + " ---");
            qtd = cadastrarPessoas(vetorPessoas, qtd);
        }

        imprimirPessoas(vetorPessoas, qtd);
        System.out.println("Mais velha magra IMC: " + maisVelhaMagraIMC(vetorPessoas, qtd));
        insertionSortporNome(vetorPessoas, qtd);

        input.close();

    }

    public static int cadastrarPessoas(Pessoa[] v, int qtd) {
        Scanner input = new Scanner(System.in);

        if (qtd == v.length) {
            System.out.println("Vetor cheio!");
            return qtd;
        }

        Pessoa nova = new Pessoa();
        while (true) {
            System.out.println("Digite o nome da pessoa: ");
            nova.nome = input.nextLine();
            if (buscarNome(v, qtd, nova.nome) == -1) {
                break;
            } else {
                System.out.println("Já existe! Digite outro nome.");
            }
        }

        System.out.println("Digite a idade: ");
        nova.idade = input.nextInt();
        System.out.println("Digite o peso: ");
        nova.peso = input.nextDouble();
        System.out.println("Digite a altura: ");
        nova.altura = input.nextDouble();
        input.nextLine();

        v[qtd] = nova;
        return qtd + 1;
    }

    public static int buscarNome(Pessoa[] v, int qtd, String nomeBuscar) {
        for (int i = 0; i < qtd; i++) {
            if (v[i].nome.equalsIgnoreCase(nomeBuscar)) {
                return i;
            }
        }
        return -1;
    }

    public static void imprimirPessoas(Pessoa[] v, int qtd) {
        for (int i = 0; i < qtd; i++) {
            Pessoa p = v[i];
            System.out.println("Nome: " + p.nome);
            System.out.println("Idade: " + p.idade);
            System.out.println("Peso: " + p.peso);
            System.out.println("Altura: " + p.altura);
            System.out.println("IMC: " + calcularIMC(p.peso, p.altura));
            System.out.println();
        }
    }

    public static double calcularIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    public static int maisVelhaMagraIMC(Pessoa[] v, int qtd) {
        int indiceMaisVelhaM = -1;
        int maiorIdade = -1;

        for (int i = 0; i < qtd; i++) {
            double imc = calcularIMC(v[i].peso, v[i].altura);

            if (imc < 18.5 && v[i].idade > maiorIdade) {
                maiorIdade = v[i].idade;
                indiceMaisVelhaM = i;
            }
        }

        return indiceMaisVelhaM;
    }

    public static void insertionSortporNome(Pessoa[] v, int qtd) {
        for (int i = 1; i < qtd; i++) {
            Pessoa atual = v[i];
            int j = i - 1;

            while (j >= 0 && v[j].nome.compareToIgnoreCase(atual.nome) > 0) {
                v[j + 1] = v[j];
                j--;
            }

            v[j + 1] = atual;
        }
    }
}
