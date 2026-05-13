package org.becastellani;

import org.becastellani.validador.documento.Cnpj;
import org.becastellani.validador.documento.Cpf;
import org.becastellani.validador.documento.Curp;
import org.becastellani.validador.documento.Documento;
import org.becastellani.validador.documento.Nif;
import org.becastellani.validador.documento.Ssn;
import org.becastellani.validador.factory.ValidadorDocumentoFactory;
import org.becastellani.validador.service.ServicoValidadorDocumento;
import org.becastellani.validador.service.ServicoValidadorDocumento.ResultadoValidacao;

import java.util.List;

/**
 * Demonstração da biblioteca validador-core.
 *
 * Exercita:
 *  - Sealed interfaces + Records (Documento, Cpf, Cnpj, Ssn, Curp, Nif)
 *  - ServicoValidadorDocumento com pattern matching switch Java 21
 *  - ValidadorDocumentoFactory para resolução dinâmica por string bruta
 */
public class Main {

    public static void main(String[] args) {
        ServicoValidadorDocumento servico = new ServicoValidadorDocumento();

        List<Documento> documentos = List.of(
                new Cpf("529.982.247-25"),          // CPF válido
                new Cpf("111.111.111-11"),           // CPF inválido (sequência repetida)
                new Cnpj("11.222.333/0001-81"),      // CNPJ válido
                new Cnpj("00.000.000/0000-00"),      // CNPJ inválido
                new Ssn("219-09-9999"),              // SSN válido
                new Ssn("000-00-0000"),              // SSN inválido
                new Curp("BADD110313HCMLNS09"),      // CURP válido
                new Nif("123456789")                 // NIF (Portugal)
        );

        System.out.println("=== ServicoValidadorDocumento — pattern matching switch ===");
        for (Documento doc : documentos) {
            ResultadoValidacao resultado = servico.validar(doc);
            System.out.printf("  [%-4s] %s%n", resultado.valido() ? "OK" : "FAIL", resultado.mensagem());
        }

        System.out.println();
        System.out.println("=== ValidadorDocumentoFactory — resolução por string bruta ===");
        List<String> strings = List.of("529.982.247-25", "11.222.333/0001-81", "219-09-9999", "BADD110313HCMLNS09");
        for (String s : strings) {
            boolean valido = ValidadorDocumentoFactory.obter(s)
                    .map(v -> v.validar(s))
                    .orElse(false);
            System.out.printf("  [%-4s] %s%n", valido ? "OK" : "FAIL", s);
        }
    }
}
