package org.becastellani.validador.strategy;

/**
 * LPS — Ponto de Variação: "Como validar o documento do cliente?"
 *
 * Interface sealed: o compilador garante que apenas as variantes conhecidas
 * (CPF, CNPJ, SSN, CURP, NIF) podem implementar este contrato, fechando o ecossistema
 * de validadores suportados pela plataforma.
 *
 * Componente: validador-core — contrato público da biblioteca.
 */
public sealed interface ValidadorDocumentoStrategy
        permits CpfValidadorStrategy, CnpjValidadorStrategy, SsnValidadorStrategy, CurpValidadorStrategy, NifValidadorStrategy {

    boolean validar(String documento);
}
