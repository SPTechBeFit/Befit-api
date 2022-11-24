INSERT INTO usuario (nome, email, senha, person_id, xp, logado)
VALUES ('victor', 'victor@gmail.com', 'victorSenha', '5bb7a32c-20ff-42d2-b684-33bf61f6eb13', 0, 0),
       ('pedro', 'pedro@gmail.com', 'pedroSenha', '5bb7a32c-20ff-42d2-b684-33bf61f6ea12', 0, 0),
       ('gustavo', 'gustavo@gmail.com', 'gustavoSenha', '5bb7a32c-20ff-42d2-b684-33bf61f6ee15', 0, 0);

INSERT INTO ingrediente (nome, porcao, proteina, lipidio, carboidrato, sodio, caloria)
VALUES ('Batata doce', 100, 1.57, 0.05, 20.12, 55, 86.00),
       ('Abobrinha', 100, 1.20, 0.30, 3.11, 0.08, 17.00),
       ('Alface ', 100, 0.90, 0.13, 3.00, 0.10, 14.00),
       ('Arroz ', 100, 2.38, 0.2, 28.6, 0.00, 130.00),
       ('Aveia ', 100, 16.9, 6.9, 66.2, 2.00, 389.00),
       ('Banana ', 100, 1.3, 0.3, 25.8, 0.00, 99.00),
       ('Brocolis ', 100, 2.82, 0.37, 6.64, 33.00, 34.00),
       ('Carne de Soja ', 100.00, 6.00, 3.00, 8.00, 72.00, 83.00),
       ('Cenoura ', 100, 0.93, 0.24, 9.58, 69.00, 41.00),
       ('Chia ', 100, 17.00, 31.00, 42.00, 16.00, 486.00),
       ('Feijao ', 100, 9.5, 1.4, 29.00, 91.00, 164.00),
       ('File de tilapia ', 120, 21.00, 4.2, 0.00, 34.00, 122.00),
       ('Lentilha ', 100, 25.8, 1.00, 60.00, 6.00, 353.00),
       ('Grao de bico ', 100, 8.86, 2.59, 27.42, 7.00, 164.00),
       ('HiperCalorico', 160, 15.00, 1.00, 131.00, 260.00, 593.00),
       ('Iogurte Desnatado', 100, 2.6, 0.00, 3.5, 39.00, 25.00),
       ('Macarrao ', 100, 5.8, 0.93, 30.86, 1.00, 158.00),
       ('Maçã ', 100, 0.2, 0.1, 13.8, 1.00, 52.00),
       ('Milho ', 100, 3.22, 1.1, 19.00, 15.00, 86.00),
       ('Morango ', 100, 0.67, 0.3, 7.7, 1.00, 32.00),
       ('Ovo ', 100, 12.53, 10.57, 1.12, 278.00, 154.00),
       ('Patinho ', 100, 35.90, 7.30, 0.00, 60.00, 219.00),
       ('Pasta de amendoim ', 20, 4.3, 7.7, 6.7, 11.00, 113.00),
       ('Peito de frango ', 100, 31.00, 3.57, 0.00, 74.00, 165.00),
       ('Pera ', 100, 0.6, 0.1, 14.00, 0.00, 53.00),
       ('Queijo cottage ', 100, 12.5, 4.5, 2.68, 405.00, 103.00),
       ('Repolho ', 100, 1.28, 0.1, 5.8, 18.00, 25.00),
       ('Tomate ', 100, 0.88, 0.2, 3.92, 5.00, 18.00),
       ('Tofu ', 100, 6.9, 2.7, 2.4, 36.00, 62.00),
       ('Whey Protein ', 30, 23.00, 0.00, 5.00, 53.00, 116.00),
       ('Whey Protein Isolado ', 30, 27.00, 0.00, 1.00, 68.00, 112.00);

INSERT INTO dieta (nome, descricao, imagem, criador_id)
VALUES ('Low Carb',
        'Reduz o consumo de carboidrato',
        'https://static.tuasaude.com/media/article/si/kl/alimentos-low-carb_54599_l.jpg',
        1),
       ('Ganho de Massa', 'Dieta que você ingere mais calorias do que gasta',
        'https://www.saboravida.com.br/wp-content/uploads/2021/09/4-alimentos-que-ajudam-a-ganhar-massa-muscular.jpg',
        1),
       ('vegana', 'Dieta sem ingredientes de origem animal',
        'https://www.saudaveleforte.com.br/wp-content/uploads/2021/01/koJ-c2W-1nm-M7B-dieta-vegana-0-116433551_m.jpg',
        1);

INSERT INTO ingredientes_dieta (dieta_id, ingrediente_id, quantidade)
VALUES (1, 3, 10.0),
       (1, 4, 5.0),
       (1, 7, 1.0),
       (1, 10, 2.0),
       (1, 12, 3.0),
       (1, 20, 2.0),
       (1, 21, 2.0),
       (1, 22, 3.0),
       (1, 24, 4.0),
       (1, 28, 5.0),
       (1, 31, 1.0),
       (2, 2, 3.0),
       (2, 4, 1.0),
       (2, 6, 2.0),
       (2, 7, 3.0),
       (2, 9, 4.0),
       (2, 14, 1.0),
       (2, 15, 2.0),
       (2, 16, 2.0),
       (2, 17, 2.0),
       (2, 20, 2.0),
       (2, 22, 2.0),
       (2, 23, 2.0),
       (2, 24, 2.0),
       (2, 26, 2.0),
       (2, 30, 2.0),
       (3, 4, 1.0),
       (3, 5, 1.0),
       (3, 6, 1.0),
       (3, 1, 2.0),
       (3, 7, 2.0),
       (3, 8, 2.0),
       (3, 11, 1.0),
       (3, 13, 1.0),
       (3, 18, 1.0),
       (3, 17, 2.0),
       (3, 19, 2.0),
       (3, 23, 2.0),
       (3, 25, 1.0),
       (3, 27, 1.0),
       (3, 29, 1.0),
       (3, 28, 3.0);

INSERT INTO dieta_favorita (usuario_id, dieta_id)
VALUES (1, 1),
       (3, 2),
       (2, 3);

INSERT INTO exercicio (nome, descricao, imagem)
VALUES ('Supino reto',
        'Deixar o peso descer lentamente em direção ao peito, até que a barra chegue bem perto do corpo. Depois, basta estender os cotovelos até a barra retornar à posição original, quando é contada uma repetição',
        'https://grandeatleta.com.br/wp-content/uploads/2018/09/supino-reto.jpg'),
       ('Supino inclinado',
        'O supino inclinado consiste essencialmente em uma flexão de ombro horizontal seguida por uma extensão de cotovelo na barra, deitado em um banco levemente inclinado para cima. A pegada na barra deve ser feita em uma distancia ligeiramente maior que a distancia entre os ombros.',
        'https://blog.gsuplementos.com.br/wp-content/uploads/2021/04/iStock-1246046696.jpg'),
       ('Supino declinado',
        'O supino reto consiste essencialmente em uma flexão de ombro horizontal seguida por uma extensão de cotovelo na barra, deitado em um banco levemente declinado para baixo, negativando a execuçao do movimeto e tensionando mais o musculo. A pegada na barra deve ser feita em uma distancia ligeiramente maior que a distancia entre os ombros.',
        'https://i.ytimg.com/vi/ifWEwZDWMAw/maxresdefault.jpg'),
       ('Peck Deck',
        'É fundamental que o cotovelo fique abaixo do ombro, o movimento deve ser feito sem que o cotovelo desencoste do apoio o movimento deve ser feito ate o final da amplitude do aparelho para garantir uma boa execução.',
        'http://aparelhodemusculacaosaj.com.br/image/cache/data/Produtos/peitoral/GPM65_Ex_Fly-700x700.png'),
       ('Crucifixo com halteres',
        'Deite se em um banco apoie bem os pes no chao, o movimento consiste em uma amplitude com halteres abrir e fechar os bracos como o movimento do peck deck porem com halteres. Na hora de abrir dobre levemente o cotovelo e na hora de levantar o peso no final do movimento estique os bracos para contracao maxima.',
        'https://i.ytimg.com/vi/_kpKlYexyXs/maxresdefault.jpg'),
       ('Crucifixo na polia',
        'o movimento deve ser feito em pe, mantenha a coluna reta. E com um braco em cada polia execute o movimento de amplitude muscular do peito lembrando de na parte final manter os bracos esticados para contracao maxima do musculo.',
        'https://i.ytimg.com/vi/sbMJLbVN7So/maxresdefault.jpg'),
       ('Pull Over',
        'Deitar em um banco, deixando a cabeça com um espaço pequeno para fora do banco. Os pés devem permanecer apoiados no solo, a coluna deve permanecer com sua curvatura fisiológica preservada. Segurar o halter, apoiando as palmas das mãos sobre este. Neste momento o braço encontra-se perpendicular ao tronco Com os cotovelos ligeiramente flexionados, descer o halter de modo que este fique na mesma linha do corpo. Retornar de maneira controlada à posição inicial.',
        'https://www.dicasdetreino.com.br/wp-content/uploads/2020/01/Pullover-%E2%80%93-Execu%C3%A7%C3%A3o-Varia%C3%A7%C3%B5es-Erros-e-Dicas.jpg'),
       ('Supino com halteres',
        'Sente-se no banco e apoie os halteres em cima da coxa. Deitado com os braços estendidos (Posição inicial) inicie a execução do movimento flexionando os braços (Faça um ângulo de aproximadamente 90º graus). Retorne a posição inicial e repita até completar sua série.',
        'https://i.ytimg.com/vi/SWVO95XzxKg/maxresdefault.jpg'),
       ('Puxada frente',
        'Segure a barra com as palmas das mãos viradas para frente.As mãos devem ficar um pouco mais afastadas do que a distância dos ombros. Ao segurar a barra por cima da cabeça, seus cotovelos devem ficar bem retos.  A melhor forma de segurar a barra é exatamente nas dobras dela. Tenha cuidado para não deixar os braços muito largos, pois isso diminui a amplitude de movimento e exerce muita pressão nos ombros. Incline-se um pouco para trás e envolva o abdômen. Puxe a barra para baixo. Ao exalar, puxe as escápulas para baixo e abaixe a barra, mantendo os cotovelos apontados para o chão. Evite arquear as costas ao puxar a barra para o queixo ou um pouco abaixo desse ponto.',
        'https://i.ytimg.com/vi/Xn-fIQw08q4/maxresdefault.jpg'),
       ('Remada cavalinho',
        'Segurar a barra com pegada neutra e os braços estendidos. Flexionar os cotovelos e trazer a barra em direção ao tronco, sempre mantendo os braços o mais perto possível do corpo. Contrair o máximo os músculos das costas e depois lentamente retornar à posição inicial.',
        'https://i.ytimg.com/vi/TRqQamJvKY8/maxresdefault.jpg'),
       ('Remada curvada',
        'Mantenha os pés afastados, paralelos aos ombros, e os joelhos levemente flexionados. O pescoço fica sempre alinhado à coluna, que deve permanecer reta. A posição dos cotovelos vai depender da orientação do exercício: na remada curvada fechada e na supinada, eles ficam mais próximos ao tronco; enquanto na aberta, eles se afastam formando um arco conforme você levanta o peso. Nos três casos, mantenha-os semiflexionados. Desça o tronco e faça movimentos de remada. Puxe os pesos atentando ao movimento das escápulas: é importante que a região “abra e feche”',
        'https://i.ytimg.com/vi/TRqQamJvKY8/maxresdefault.jpg'),
       ('Pull Down',
        'Ajuste a polia na regulagem mais alta do cross e use a corda, segure a barra usando uma pegada pronada(neste caso as palmas ficarao apontadas para frente) usando uma largura igual a dos ombros. De um passo para tras e incline-se levemente para frente dobrando o quadril e mantendo a curvatura natural da coluna(sem ficar corcunda). Mantendo os cotovelos levemente flexionados e fixos no mesmo angulo inicie o exercicio abaixando o braco em direcao ao corpo e repita o movimento. ',
        'https://www.hipertrofia.org/blog/wp-content/uploads/2018/11/pull-down-tronco-curvado.jpg'),
       ('Pull Over',
        'Deitar em um banco, deixando a cabeça com um espaço pequeno para fora do banco. Os pés devem permanecer apoiados no solo, a coluna deve permanecer com sua curvatura fisiológica preservada.Segurar o halter, apoiando as palmas das mãos sobre este. Neste momento o braço encontra-se perpendicular ao tronco Com os cotovelos ligeiramente flexionados, descer o halter de modo que este fique na mesma linha do corpo. Retornar de maneira controlada à posição inicial.',
        'https://www.dicasdetreino.com.br/wp-content/uploads/2020/01/Pullover-%E2%80%93-Execu%C3%A7%C3%A3o-Varia%C3%A7%C3%B5es-Erros-e-Dicas.jpg'),
       ('Remada supinada',
        'Remada Curvada executado com pegada supinada (com a palma das mãos para cima),o indivíduo deverá inclinar o tronco a frente mantendo as curvaturas naturais e fisiológicas da coluna vertebral. O quadril deverá estar flexionado, os joelhos com uma leve flexão, e os cotovelos estendidos.',
        'https://fitnessmagazine.com.br/wp-content/uploads/2016/08/Remada-Supinada-na-Polia-Baixa.jpg'),
       ('Extensao lombar',
        'comece deitando-se de barriga para baixo, mantenha as pernas estendidas e, então, apoie as duas palmas das mãos no piso. Use a força dos braços para erguer o tronco (mantendo o quadril e as pernas no chão). A ideia é que as mãos fiquem na mesma direção dos ombros e a cabeça fique erguida, com o olhar voltado para a frente. Fique nessa posição por pelo menos 30 segundos, depois deite-se de bruços novamente e repita o alongamento. ',
        'https://www.fiqueinforma.com/wp-content/uploads/2012/03/Exerc%C3%ADcio-de-extensao-do-tronco-1.jpg'),
       ('Puxada alta com triangulo',
        'Segure o triangulo. Tenha cuidado para não deixar os braços muito largos, pois isso diminui a amplitude de movimento e exerce muita pressão nos ombros. Incline-se um pouco para trás e envolva o abdômen. Puxe o triangulo para baixo. Ao exalar, puxe as escápulas para baixo e abaixe o triangulo, mantendo os cotovelos apontados para o chão. Evite arquear as costas ao puxar o triangulo para o queixo ou um pouco abaixo desse ponto.',
        'https://www.dicasdetreino.com.br/wp-content/uploads/2017/07/Puxada-com-tri%C3%A2ngulo-barra-fixa-com-tri%C3%A2ngulo-no-treino-de-costas.jpg'),
       ('Testa',
        'Mantenha os cotovelos flexionados em 90 graus entre o braço e o antebraço; · Puxe a barra para a frente até realizar a extensão dos cotovelos; · Não altere a posição inicial dos braços e dos ombros durante o movimento; · Repita voltando devagar, sempre estendendo o cotovelo até contrair ao máximo o músculo tríceps.',
        'https://i.ytimg.com/vi/AY0kcO-tx30/maxresdefault.jpg'),
       ('Frances',
        'Sentado, segurar a barra acima da cabeça com os braços estendidos. O punho deve estar firme e neutro durante todo o exercício. Flexionar os braços até a barra estar atrás da cabeça. Estender ao máximo os cotovelos, até o limite de contração do tríceps. Durante todo o movimento manter os cotovelos o mais próximo possível da linha dos ombros, o tronco deve estar ereto durante o exercício.',
        'https://guiadocorpo.com/wp-content/uploads/2019/09/triceps-frances-beneficios.png'),
       ('Coice',
        'Para iniciar o exercício é necessário estar com um halter em uma das mãos, depois é necessário curvar o tronco a frente e o braço apoiado a lateral do tronco e paralelo ao solo. O movimento inicia-se com o braço flexionado e de maneira controlada estender o cotovelo até contrair ao máximo o tríceps e depois retornar lentamente à posição inicial.',
        'https://i.ytimg.com/vi/Mgf3BBYWpRA/maxresdefault.jpg'),
       ('Corda na Polia',
        'Ajuste a corda no ponto mais alto da polia. Em pé, fique de frente para o equipamento, incline o corpo para frente em 30 graus e mantenha-o levemente afastado da corda, mantenha os pés paralelos em látero-lateral ou com uma perna semiflexionada para frente e a outra estendida para trás, mais próxima do equipamento, os joelhos podem estar semiflexionados, segure a corda com as duas mãos e os polegares para cima, lembre-se de manter a cabeça alinhada com a coluna. posicione os cotovelos nas laterais do corpo e os antebraços paralelos ao chão.Mantenha o abdômen contraído e puxe a corda para baixo em direção à cintura até realizar a extensão dos cotovelos. Ao descer, torça levemente a corda para dentro. Durante o movimento, não altere a posição inicial dos braços e dos ombros.',
        'https://www.hipertrofia.org/blog/wp-content/uploads/2018/10/execu%C3%A7%C3%A3o-do-triceps-corda.jpg'),
       ('Triceps no banco',
        'Primeiramente para executar o exercicio da maneira correta voce devera utilizar dois bancos retos da mesma altura, alinhe-os um ao lado do outro em uma distancia que seja possivel se apoiar com as mãos em um e colocar os calcanhares no outro, mantendo a coluna vertical, inicie o exercicio ao dobrar o cotovelo e descer o corpo controladamente ate que o corpo fique num ângulo de 90 graus. Evite que os ombros caiam para frente e que sua lombar perca a curvatura natural. Agora suba ate que os seus braços estejam esticados.',
        'https://www.queroviverbem.com.br/wp-content/uploads/2017/08/triceps-banco.jpg'),
       ('Triceps paralelo na maquina',
        'Ajuste o banco de acordo com sua altura, selecione o peso que consiga fazer 8-12 repetiçoes, mantenha a coluna totalmente encostada no banco, e inicie o exercicio com o cotovelo dobrado e estique o braço e siga repetindo o movimento, mantendo a postura sem o ombro ir pra frente.',
        'https://fitnessmagazine.com.br/wp-content/uploads/2016/08/Tr%C3%ADceps-em-Paralela-na-M%C3%A1quina.jpg'),
       ('Rosca direta',
        'Partindo de uma posição em pé, com os pés afastados pela largura dos ombros e os joelhos pouco flexionados, segure a barra com os braços estendidos — pegada com afastamento igual ou um pouco maior que a distância entre os ombros e com o dorso das mãos voltado para baixo. Leve a barra até o nível dos ombros flexionando os cotovelos. Abaixe a barra de volta à posição inicial. Ao modificar o afastamento das mãos, solicitamos mais intensamente.',
        'https://i.ytimg.com/vi/s4B8UW3BMqk/hqdefault.jpg'),
       ('Rosca Alternada',
        'Para executar a rosca direta com halteres, o indivíduo começa em pé, segurando um halter em cada mão. Com a coluna ereta, os punhos firmes e a carga em mãos, realize a flexão de cotovelo até contrair ao máximo o bíceps, lembrando de manter os cotovelos encostados e imóveis na lateral do tronco e depois retornar lentamente à posição inicial.',
        'https://www.queroviverbem.com.br/wp-content/uploads/2018/04/rosca-alternada-como-fazer.jpg'),
       ('Rosca Martelo',
        'é realizada com a pegada neutra (polegares voltados para cima), o que enfatiza o braquiorradial em relação aos outros músculos do bíceps. Com os halteres em mãos com a pegada neutra, cotovelos apoiados na região lateral do tronco e alinhados com os ombros, realize a flexão de cotovelos até a contração máxima do bíceps e, depois, estenda novamente os braços até a posição inicial.',
        'https://i.ytimg.com/vi/RUZ7Med2Mg4/maxresdefault.jpg'),
       ('Rosca barra W',
        'Com a coluna ereta e os punhos firmes e com a carga em mãos, realizar a flexão de cotovelo até contrair ao máximo o bíceps, lembrando de manter os cotovelos encostados e imóveis na lateral do tronco e depois retornar lentamente à posição inicial.',
        'https://i.ytimg.com/vi/RUZ7Med2Mg4/maxresdefault.jpg'),
       ('Corda na polia',
        'é realizado com pegada neutra polegares para cima, selecione o peso ideal com a corda na polia, e com a pegada neutra estenda o biceps e contraia, mantendo a coluna reta.',
        'https://i.ytimg.com/vi/1lR_dT07wBM/maxresdefault.jpg'),
       ('Rosca concentrada',
        'Sentado em um banco com o braço a ser treinado e apoiado na região interna da coxa, com o halter em mãos e a pegada supinada, o movimento inicia-se com o braço estendido. Depois, é realizada a flexão de cotovelo até a contração máxima do bíceps, retornando à posição inicial. Durante o movimento, mantenha a coluna ereta, evitando o arqueamento desta.',
        'https://i.ytimg.com/vi/PcwdHVhWY3s/maxresdefault.jpg'),
       ('Leg press',
        'Sente-se e apoie as costas no banco do aparelho, mantenha a coluna ereta, respeitando a curvatura fisiológica da mesma. Apoie os pés na plataforma, mantendo-os alinhados com o joelho e o quadril. Destravar o aparelho e realizar a flexão do joelho, descendo até alcançar o ângulo de 90 graus.  Estender os joelhos quase que completamente, retornando assim a posição inicial. Com as costas bem apoiadas, inicie o exercício com a extensão dos joelhos até que ocorra a contração máxima das coxas Volte à posição inicial lentamente e repita o movimento',
        'https://www.dicasdetreino.com.br/wp-content/uploads/2017/05/posi%C3%A7%C3%A3o-dos-p%C3%A9s-no-Leg-Pres.jpg'),
       ('Cadeira extensora',
        'Sente-se na cadeira extensora e regule o banco de acordo com a sua altura Confira se a parte inferior dos joelhos está localizada na dobra da cadeira Apoie os pés nas almofadas, seguindo a linha do tornozelo Mantenha as solas dos pés retas Escolha a carga que será utilizada, respeitando seus limites Com as costas bem apoiadas, inicie o exercício com a extensão dos joelhos até que ocorra a contração máxima das coxas Volte à posição inicial lentamente e repita o movimento',
        'https://thumb.mais.uol.com.br/16772110.jpg'),
       ('Cadeira flexora',
        'Sentar no aparelho, apoiando bem a coluna, os pés devem estar apoiados em cima da almofada, com esta na altura da linha dos tornozelos. Prestar atenção também ao travar a almofada que apóia na coxa, pois esta deve estar logo acima do joelho. Flexionar os joelhos até contrair ao máximo o músculo da posterior da coxa. Retornar lentamente a posição inicial estendendo os joelhos de maneira controlada.',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTolYqUbPu9V8biS7jOzY875AvXqFyihr0rWA&usqp=CAU'),
       ('Abdutor',
        'Para fazer o exercício, você deve se sentar no aparelho e colocar as pernas dobradas num ângulo de 90º e juntas, ao invés de separadas. As almofadas devem ficar na altura do joelho. Nesta posição, comece o exercício abrindo e fechando as pernas o máximo possível.',
        'https://i.ytimg.com/vi/c1Bmt-K65tw/maxresdefault.jpg'),
       ('Adutor',
        'Para iniciar o exercício, após configurar a carga indicada pelo instrutor, você deve se sentar na cadeira adutora. Colocar as pernas abertas e dobradas para fora do aparelho, em um ângulo de 90º. As almofadas da máquina devem ficar bem na altura dos joelhos. A partir daí, você já pode começar as movimentações.',
        'https://fitnessmagazine.com.br/wp-content/uploads/2016/07/Banco-Adutor.jpg'),
       ('Agachamento livre',
        ' Coloque o peso na barra. Com os pés firmes no chão, afaste as pernas um pouco além da largura do quadril Coloque a ponta dos pés levemente para fora, seguindo a linha dos joelhos; Deixe os joelhos paralelos, sem entortar para dentro; Com a barra nas costas e as maos abertas segurando. Ao agachar, flexione os joelhos e projete o quadril e os glúteos para trás, como se fosse sentar em um banco; Atenção ao peitoral: mantenha o tronco firme projetado para frente, assim como a direção do olhar; O abdome deve estar sempre contraído, para proteger a lombar de lesões.',
        'https://www.dicasdetreino.com.br/wp-content/uploads/2018/09/Agachamento-%E2%80%93-Execu%C3%A7%C3%A3o-Varia%C3%A7%C3%B5es-Erros-e-Dicas.jpg'),
       ('Afundo',
        'O pé da frente precisa ser completamente apoiado no chão, enquanto o de trás deve ficar com o calcanhar levantado sem tocar no solo; Após, abaixar lentamente o quadril até a articulação da frente formar um ângulo de 90º e o joelho da perna que ficou para trás aproximar-se do chão.',
        'https://i.ytimg.com/vi/ALP9JIXA-PA/maxresdefault.jpg'),
       ('Avanço',
        'Em pé com os pés afastados na largura dos ombros; segure dois halteres fixos com os braços estendidos aos lados do corpo. Dê um passo para a frente e flexione o joelho até que a coxa da perna que avanço esteja paralela com o chão. Retorne à posição inicial e repita, usando a outra perna.',
        'https://treinomestre.com.br/wp-content/uploads/2019/11/avan%C3%A7o-exercicio-passada-varia%C3%A7%C3%B5es.jpg'),
       ('Panturrilha em pe',
        'Firme os dedos dos pés na anilha; Movimente o calcanhar de apoio para cima e faça uma pequena pausa; Em seguida, contraia o músculo da panturrilha e retorne lentamente à posição inicial.',
        'https://s2.glbimg.com/tnaSh3wAF5oxKocIQu7e0xYS1fw=/0x0:1280x1280/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_bc8228b6673f488aa253bbcb03c80ec5/internal_photos/bs/2021/A/K/gsPUyITPS8cjfJCMjoeQ/waldyr-executando-elevacao-em-pe-no-smith.jpeg'),
       ('Panturrilha sentado',
        'Sentar em um banco de forma a que os joelhos fiquem dobrados em um ângulo de 90º, levantar o calcanhar, mantendo a ponta do pé no chão. Segurar a posição por 1 segundo e regressar à posição inicial com os pés bem apoiados.',
        'https://i.ytimg.com/vi/Bx6elAOgxMI/maxresdefault.jpg'),
       ('Elevação pelvica',
        'Uma vez na posição inicial, é preciso contrair os músculos do abdômen, coloque os pesos em cima do coccix e em seguida você deve levantar o cóccix do colchonete e fazer pressão na musculatura dos glúteos, elevando-os. Ao descer, faça-o lentamente, baixando vértebra por vértebra. Posteriormente, faça algumas repetições pausadamente, mas sem levantar demais o quadril.',
        'https://i.ytimg.com/vi/SY1eYXrCPzg/maxresdefault.jpg'),
       ('Abdominal remador',
        'Deite-se no solo, em um colchonete, com a barriga virada para cima, em seguida, flexione as pernas ao mesmo tempo que contrai o abdômen em direção aos joelhos, abraçando-os. Retorne de forma controlada à posição inicial',
        'https://aprovataf.com.br/wp-content/uploads/2016/10/ABDOMINAL-REMADOR.jpg'),
       ('Prancha lateral',
        'Deite se de lado no chão, posicione o antebraço apoiado com o cotovelo abaixo da linha do ombro, levante o corpo de forma que fique somente apoiado em seus pés e antebraço, mantendo-o em diagonal. Os pes podem ficar um em cima do outro ou estaticos no chão. Permaneca estatico na posicao pelo tempo determinado.',
        'https://i.ytimg.com/vi/OdmzLTCYKgA/maxresdefault.jpg'),
       ('Prancha',
        'Os cotovelos devem estar alinhados aos ombros e apoiados no chão. As pernas ficam esticadas e o apoio é, novamente, nas pontas dos pés.',
        'https://www.smartfit.com.br/news/wp-content/uploads/2022/02/prancha-com-cotovelos-no-chao-300x151.jpg'),
       ('Abdominal tesourinha',
        'Deitado no solo, levante as pernas cerca de 10 a 15 centímetros do chão e faça uma tesoura , levante a perna esquerda, depois a direita, depois a esquerda e repita.',
        'https://runnersworld.com.br/wp-content/uploads/sites/4/2020/02/abdominal-tesoura-1280x720.jpg'),
       ('Abdominal reto',
        'Deite-se de costas no chão, flexione os joelhos e mantenha os pés separados na largura dos ombros e apoiados no chão. Posicione as mãos atrás da cabeça ou mantenha-as cruzadas em frente do corpo. Contraia o musculo do abdomen e erga o tronco ate aproximar do joelho ou tirar o tronco completamente do chão, não force o pescoco, a força deve vir do seu abdomen',
        'https://i.ytimg.com/vi/9eeeUjV4r_I/maxresdefault.jpg'),
       ('Abdominal canivete',
        'Deite-se no chão e estenda os braços e as pernas. Levante os braços para cima da cabeça e deixe a coluna em uma posição neutra, sem arquear a lombar.Tambem ao mesmo tempo levante as pernas de maneira que consiga tocar a ponta da sua perna a ponta dos seus dedos ambos se encontrando sem arquear a lombar e os braços e as pernas retos.',
        'https://corpoacorpo.com.br/upload/galeria/large/4542.jpg'),
       ('Abdominal superman',
        'Inspirar contrair o abdômen quando começar a soltar o ar (soltar de forma lenta) elevar o tronco para o alto juntamente com os braços esticados (mesma linha da orelha) e as pernas. Atenção a cabeça, para manter a posição correta mantenha os olhos sempre voltados para o chão.',
        'https://boaforma.abril.com.br/wp-content/uploads/sites/2/2016/09/exercicio-superman1.jpg?quality=90&strip=info'),
       ('Elevacao lateral',
        'Em pé com halteres na mao levante os bracos lateralmente com os cotovelos dobrados levemente',
        'https://conteudo.imguol.com.br/c/entretenimento/2f/2019/10/14/elevacao-lateral-1571066104977_v2_615x300.jpg'),
       ('Desenvolvimento sentado',
        'Sentado no banco com halteres na mao levante os halteres como se estivesse fazendo um supino com enfase no deltoide',
        'https://static.movimente.me/media/imgs/exercises/IMG_8464net_1.JPG');

INSERT INTO treino (nome, descricao, imagem, criador_id)
VALUES ('treino peito e triceps', 'treino para hipertrofia peito e triceps',
        'https://www.dicasdetreino.com.br/wp-content/uploads/2017/10/Treino-de-Peito-e-Triceps-com-Treino-ABC.jpg', 1),
       ('treino costas e biceps', 'treino para hipertrofia dorsais e biceps',
        'https://www.dicasdetreino.com.br/wp-content/uploads/2017/10/Treino-de-Costas-e-B%C3%ADceps.jpg', 1),
       ('treino perna', 'treino para hipertrofia membros inferiores',
        'https://www.dicasdetreino.com.br/wp-content/uploads/2017/11/Treino-de-Pernas-e-Ombros.jpg', 1),
       ('treino abdomen', 'treino para hipertrofia definir a barriga',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2vu2Pr5l_QnFCajtyZzfA2LZlWddJuvOLEw&usqp=CAU', 1);

INSERT INTO serie (treino_id, exercicio_id, quantidade, tempo, repeticao)
VALUES (1, 1, 15, '00:01:00', 3),
       (1, 2, 12, '00:01:00', 3),
       (1, 3, 12, '00:01:00', 3),
       (1, 4, 12, '00:01:00', 3),
       (1, 5, 12, '00:01:00', 3),
       (1, 6, 12, '00:01:00', 3),
       (1, 17, 12, '00:01:00', 3),
       (1, 18, 12, '00:01:00', 3),
       (1, 19, 12, '00:01:00', 3),
       (1, 20, 12, '00:01:00', 3),
       (1, 21, 12, '00:01:00', 3),
       (1, 22, 12, '00:01:00', 3),
       (2, 10, 12, '00:01:00', 3),
       (2, 11, 12, '00:01:00', 3),
       (2, 12, 12, '00:01:00', 3),
       (2, 13, 12, '00:01:00', 3),
       (2, 14, 12, '00:01:00', 3),
       (2, 15, 12, '00:01:00', 3),
       (2, 16, 12, '00:01:00', 3),
       (2, 23, 12, '00:01:00', 3),
       (2, 24, 12, '00:01:00', 3),
       (2, 25, 12, '00:01:00', 3),
       (2, 26, 12, '00:01:00', 3),
       (2, 27, 12, '00:01:00', 3),
       (2, 28, 12, '00:01:00', 3),
       (3, 30, 12, '00:01:00', 3),
       (3, 31, 12, '00:01:00', 3),
       (3, 32, 12, '00:01:00', 3),
       (3, 33, 12, '00:01:00', 3),
       (3, 34, 12, '00:01:00', 3),
       (3, 35, 12, '00:01:00', 3),
       (3, 36, 12, '00:01:00', 3),
       (3, 37, 12, '00:01:00', 3),
       (3, 38, 12, '00:01:00', 3),
       (3, 39, 12, '00:01:00', 3),
       (3, 47, 12, '00:01:00', 4),
       (3, 48, 12, '00:01:00', 4),
       (4, 40, 12, '00:01:00', 4),
       (4, 41, 12, '00:01:00', 4),
       (4, 42, 12, '00:01:00', 4),
       (4, 43, 12, '00:01:00', 4),
       (4, 44, 12, '00:01:00', 4),
       (4, 45, 12, '00:01:00', 4);


INSERT INTO treino_favorito (usuario_id, treino_id)
VALUES (1, 1);