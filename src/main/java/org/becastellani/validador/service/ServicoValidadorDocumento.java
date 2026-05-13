package org.becastellani.validador.service;

import org.becastellani.validador.documento.Cnpj;
import org.becastellani.validador.documento.Cpf;
import org.becastellani.validador.documento.Curp;
import org.becastellani.validador.documento.Documento;
import org.becastellani.validador.documento.Nif;
import org.becastellani.validador.documento.Ssn;
import org.becastellani.validador.strategy.CnpjValidadorStrategy;
import org.becastellani.validador.strategy.CpfValidadorStrategy;
import org.becastellani.validador.strategy.CurpValidadorStrategy;
import org.becastellani.validador.strategy.NifValidadorStrategy;
import org.becastellani.validador.strategy.SsnValidadorStrategy;
import org.becastellani.validador.strategy.ValidadorDocumentoStrategy;

/**
 * Motor de validação de documentos.
 *
 * Utiliza Java 21 Pattern Matching com switch exaustivo sobre a sealed interface
 * {@link Documento}. O compilador garante que todos os subtipos conhecidos estão
 * cobertos — nenhum {@code default} é necessário ou permitido aqui.
 *
 * Componente: validador-core — serviço público da biblioteca.
 */
public class ServicoValidadorDocumento {

    /**
     * Resultado imutável da validação — Record Java 16+.
     */
    public record ResultadoValidacao(boolean valido, String tipo, String mensagem) {}

    /**
     * Valida um {@link Documento} usando pattern matching switch (Java 21).
     *
     * O switch é exaustivo por contrato do compilador: como {@code Documento} é uma
     * sealed interface com subtipos conhecidos (Cpf, Cnpj, Ssn, Curp, Nif), qualquer
     * novo subtipo adicionado sem cobertura aqui causará erro de compilação.
     *
     * @param documento instância concreta do documento a validar
     * @return {@link ResultadoValidacao} com o status, tipo e mensagem descritiva
     */
    public ResultadoValidacao validar(Documento documento) {
        return switch (documento) {
            case Cpf  cpf  -> executar(cpf.valor(),  new CpfValidadorStrategy(),  "CPF");
            case Cnpj cnpj -> executar(cnpj.valor(), new CnpjValidadorStrategy(), "CNPJ");
            case Ssn  ssn  -> executar(ssn.valor(),  new SsnValidadorStrategy(),  "SSN");
            case Curp curp -> executar(curp.valor(), new CurpValidadorStrategy(), "CURP");
            case Nif  nif  -> executar(nif.valor(),  new NifValidadorStrategy(),  "NIF");
        };
    }

    private ResultadoValidacao executar(String valor, ValidadorDocumentoStrategy strategy, String tipo) {
        boolean valido = strategy.validar(valor);
        String mensagem = valido
                ? tipo + " válido."
                : tipo + " inválido: \"" + valor + "\"";
        return new ResultadoValidacao(valido, tipo, mensagem);
    }
}
