package org.becastellani.validador.strategy;

public final class CpfValidadorStrategy implements ValidadorDocumentoStrategy {

    @Override
    public boolean validar(String documento) {
        if (documento == null || documento.isBlank()) return false;
        return isCpfValido(documento.replaceAll("[^0-9]", ""));
    }

    private boolean isCpfValido(String cpf) {
        if (cpf.length() != 11) return false;
        if (cpf.matches("(\\d)\\1{10}"))
            return false;

        int primeiroDigito = calcularDigitoCpf(cpf, 9);
        if (primeiroDigito != Character.getNumericValue(cpf.charAt(9)))
            return false;

        int segundoDigito = calcularDigitoCpf(cpf, 10);
        return segundoDigito == Character.getNumericValue(cpf.charAt(10));
    }

    private int calcularDigitoCpf(String cpf, int length) {
        int soma = 0;
        for (int i = 0; i < length; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (length + 1 - i);
        }
        int resto = (soma * 10) % 11;
        return (resto == 10 || resto == 11) ? 0 : resto;
    }
}
