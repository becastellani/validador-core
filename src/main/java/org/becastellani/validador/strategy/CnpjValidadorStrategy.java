package org.becastellani.validador.strategy;

public final class CnpjValidadorStrategy implements ValidadorDocumentoStrategy {

    @Override
    public boolean validar(String documento) {
        if (documento == null || documento.isBlank()) return false;
        return isCnpjValido(documento.replaceAll("[^0-9]", ""));
    }

    private boolean isCnpjValido(String cnpj) {
        if (cnpj.length() != 14) return false;
        if (cnpj.matches("(\\d)\\1{13}"))
            return false;

        int primeiroDigito = calcularDigitoCnpj(cnpj, 12);
        if (primeiroDigito != Character.getNumericValue(cnpj.charAt(12)))
            return false;

        int segundoDigito = calcularDigitoCnpj(cnpj, 13);
        return segundoDigito == Character.getNumericValue(cnpj.charAt(13));
    }

    /**
     * Os pesos ciclam de 2 a 9 da direita para a esquerda:
     * 12 primeiros dígitos → pesos [5,4,3,2,9,8,7,6,5,4,3,2]
     * 13 primeiros dígitos → pesos [6,5,4,3,2,9,8,7,6,5,4,3,2]
     */
    private int calcularDigitoCnpj(String cnpj, int length) {
        int soma = 0;
        int peso = length - 7;
        for (int i = 0; i < length; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso = (peso == 2) ? 9 : peso - 1;
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}
