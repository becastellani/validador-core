package org.becastellani.validador.documento;

/**
 * Tipo base sealed para documentos de identidade.
 *
 * LPS — Ponto de Variação: "Qual documento representa o cliente?"
 * Variantes conhecidas (permits): CPF (BR pessoa física), CNPJ (BR pessoa jurídica),
 *                                  SSN (EUA), CURP (México), NIF (Portugal).
 *
 * Componente: validador-core — interface pública da biblioteca.
 */
public sealed interface Documento permits Cpf, Cnpj, Ssn, Curp, Nif {
}
