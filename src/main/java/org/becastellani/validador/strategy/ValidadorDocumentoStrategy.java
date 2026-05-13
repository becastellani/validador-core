package org.becastellani.validador.strategy;

/**
 * LPS — Ponto de Variação: "Como validar o documento do cliente?"
 *
 * Interface aberta (não-sealed): o padrão Strategy requer extensibilidade — consumidores
 * legítimos (testes, mocks, novos validadores) precisam implementar este contrato sem
 * restrição de compilador. O fechamento do conjunto de documentos é responsabilidade
 * da sealed interface {@link org.becastellani.validador.documento.Documento}, não da estratégia.
 *
 * Implementações canônicas: {@link CpfValidadorStrategy}, {@link CnpjValidadorStrategy},
 * {@link SsnValidadorStrategy}, {@link CurpValidadorStrategy}, {@link NifValidadorStrategy}.
 *
 * Componente: validador-core — contrato público da biblioteca.
 */
public interface ValidadorDocumentoStrategy {

    boolean validar(String documento);
}
