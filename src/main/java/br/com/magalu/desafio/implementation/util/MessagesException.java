package br.com.magalu.desafio.implementation.util;

/**
 * @author Pedro Henrique Veiga
 * @created 05/06/24
 * @lastModified 05/06/24
 */
public enum MessagesException {

    INVALID_DATA("Dados inválidos."),

    RESOURCE_NOT_FOUND("Recurso não encontrado."),

    USER_WITHOUT_PERMISSION("Usuário sem permissão."),

    SOME_INVALID_FIELD("Algum campo inválido."),

    INTERNAL_SERVER_ERROR("Erro interno no servidor"),

    NOT_AUTHORIZED("Não autorizado"),

    DATA_NOT_FOUND("Dados não encontrados."),

    DATA_FILE_IS_NULL("Arquivo está vazio ou é Nulo."),

    VALIDATION_FAILED_FOR_CLASSES("A validação falhou para classes."),

    DATA_FILE_IS_MANDATORY("File é obrigatório."),

    NOT_FOUND_FILE("Arquivo não encontrado."),

    CONTRAINT_MUST_BE_GLOB_EQUI_OR_REST("Restrição deve ser: GLOB, EQUI OU REST");


    private final String value;

    MessagesException(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
