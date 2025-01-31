CREATE TABLE USERS
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT, -- Coluna ID é a chave primária, do tipo INTEGER, e será auto-incrementada automaticamente pelo banco de dados.
    NAME  VARCHAR(60),                        -- Coluna NAME armazena o nome do usuário, com um máximo de 255 caracteres.
    EMAIL VARCHAR(60),
    PASSWORD VARCHAR(32),
    IS_ACTIVE BOOLEAN DEFAULT TRUE NOT NULL,
    DOCUMENT VARCHAR(14)
);
