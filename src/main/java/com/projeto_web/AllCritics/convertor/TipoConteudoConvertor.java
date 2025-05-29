package com.projeto_web.AllCritics.convertor;

import com.projeto_web.AllCritics.dominio.enums.TipoConteudo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoConteudoConvertor implements AttributeConverter<TipoConteudo, String> {

    @Override
    public String convertToDatabaseColumn(TipoConteudo tipo) {
        if (tipo == null) return null;
        switch (tipo) {
            case FILME: return "Filme";
            case SERIE: return "Série";
            case JOGO: return "Jogo";
            default: throw new IllegalArgumentException("TipoConteudo inválido: " + tipo);
        }
    }

    @Override
    public TipoConteudo convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        switch (dbData) {
            case "Filme": return TipoConteudo.FILME;
            case "Série": return TipoConteudo.SERIE;
            case "Jogo": return TipoConteudo.JOGO;
            default: throw new IllegalArgumentException("Valor inválido no banco: " + dbData);
        }
    }
}
