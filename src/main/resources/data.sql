-- Este arquivo roda sozinho toda vez que a aplicacao liga

INSERT INTO departamento (nome) VALUES ('Tecnologia da Informacao');
INSERT INTO departamento (nome) VALUES ('Recursos Humanos');
INSERT INTO departamento (nome) VALUES ('Producao');
INSERT INTO departamento (nome) VALUES ('Comercial');

INSERT INTO colaborador (nome, cargo, data_admissao, departamento_id)
VALUES ('Ana Souza', 'Analista de Sistemas', '2023-03-15', 1);

INSERT INTO colaborador (nome, cargo, data_admissao, departamento_id)
VALUES ('Bruno Lima', 'Estagiario de TI', '2026-02-01', 1);

INSERT INTO colaborador (nome, cargo, data_admissao, departamento_id)
VALUES ('Carla Mendes', 'Analista de RH', '2021-08-10', 2);

INSERT INTO colaborador (nome, cargo, data_admissao, departamento_id)
VALUES ('Diego Alves', 'Supervisor de Producao', '2019-05-20', 3);