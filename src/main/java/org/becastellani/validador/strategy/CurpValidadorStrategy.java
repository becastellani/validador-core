package org.becastellani.validador.strategy;

/**
 * LPS Variante — Validação de CURP (Clave Única de Registro de Población) do México.
 *
 * Formato: 18 caracteres alfanuméricos.
 * Para fins didáticos: valida o formato via regex, sem cálculo do dígito verificador.
 */
public final class CurpValidadorStrategy implements ValidadorDocumentoStrategy {

    private static final String CURP_REGEX =
            "^[A-Z]{4}\\d{6}[HM][A-Z]{2}[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d]\\d$";

    @Override
    public boolean validar(String documento) {
        if (documento == null || documento.isBlank()) return false;
        String limpo = documento.trim().toUpperCase();
        if (limpo.length() != 18) return false;
        return limpo.matches(CURP_REGEX);
    }
}
