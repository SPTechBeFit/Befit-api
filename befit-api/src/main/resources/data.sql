INSERT INTO usuario (nome, email, senha, person_id, xp, logado) VALUES
                                                                    ('victor', 'victor@gmail.com', 'victorSenha', '5bb7a32c-20ff-42d2-b684-33bf61f6eb13', 0, 0),
                                                                    ('pedro', 'pedro@gmail.com', 'pedroSenha', '5bb7a32c-20ff-42d2-b684-33bf61f6ea12', 0, 0),
                                                                    ('gustavo', 'gustavo@gmail.com', 'gustavoSenha', '5bb7a32c-20ff-42d2-b684-33bf61f6ee15', 0, 0);

INSERT INTO ingrediente (nome, porcao, proteina, lipidio, carboidrato, sodio, caloria) VALUES
                                                                                           ('frango', 100, 26.00, 2.5, 0.3, 0.08, 239.00),
                                                                                           ('batata doce', 100, 0.60, 0.03, 18.40, 0.03, 77.00);

INSERT INTO dieta (nome, descricao, imagem, criador_id) VALUES
                                                    ('dieta monstra', 'dieta que frango nao come', 'https://img.itdg.com.br/tdg/images/recipes/000/000/897/344249/344249_original.jpg?mode=crop&width=710&height=400', 1),
                                                    ('dieta de frango', 'dieta pra magrinho', 'https://www.kitano.com.br/wp-content/uploads/2019/07/SSP_2414-Frango-assado-com-alecrim-e-louro-1.jpg', 1);

INSERT INTO ingredientes_dieta (dieta_id, ingrediente_id, quantidade) VALUES
                                   (1, 1, 2.0),
                                   (1, 1, 3.0);

INSERT INTO dieta_favorita (usuario_id, dieta_id) VALUES
                                                      (1, 1);

INSERT INTO exercicio (nome, descricao, imagem) VALUES
                                                    ('supino reto', 'Deixar o peso descer lentamente em direção ao peito, até que a barra chegue bem perto do corpo. Depois, basta estender os cotovelos até a barra retornar à posição original, quando é contada uma repetição', 'https://grandeatleta.com.br/wp-content/uploads/2018/09/supino-reto.jpg'),
                                                    ('prancha', 'Os cotovelos devem estar alinhados aos ombros e apoiados no chão. As pernas ficam esticadas e o apoio é, novamente, nas pontas dos pés.', 'https://www.smartfit.com.br/news/wp-content/uploads/2022/02/prancha-com-cotovelos-no-chao-300x151.jpg');

INSERT INTO treino (nome, descricao, imagem, criador_id) VALUES
                                                             ('treino monstro', 'treino para ficar gigante', 'https://img.freepik.com/fotos-gratis/retrato-de-um-fisiculturista-masculino-sem-camisa-forte-muscular_171337-4570.jpg?w=2000', 1),
                                                             ('treino magro', 'treino para emagrecer', 'https://img.freepik.com/fotos-gratis/mulher-bonita-e-magra-expressando-emocoes-positivas-apos-a-dieta_197531-13142.jpg?w=2000', 1);

INSERT INTO serie (treino_id, exercicio_id, quantidade, tempo, repeticao) VALUES
                                                                              (1, 1, 15, '00:00:00', 3),
                                                                              (1, 2, 10, '00:01:00', 3),
                                                                              (2, 1, 20, '00:00:00', 3),
                                                                              (2, 2, 10, '00:02:00', 4);

INSERT INTO treino_favorito (usuario_id, treino_id) VALUES
                                                        (1, 1);