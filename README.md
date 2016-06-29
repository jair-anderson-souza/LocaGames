# loca-games

A Pattern Games  ́e uma loja de compra, venda e loca ̧c ̃ao de jogos e necessita de um sistema p aracontrolar os algu ́eis de seus jogos. O usu ́ario do sistema com o papel de atendente n ̃ao ir ́a necessitarlogin e senha. Antes de utilizar qualquer funcionalidade do sistema, ser ́a necess ́ario um cadastro simples

de clientes (nome, cpf e email). O sistema deve validar se o CPF digitado e o email s ̃ao v ́alidos.

Uma loca ̧c ̃ao pode estar contido em dois cen ́arios:

• Loca ̧c ̃ao comum (Dia da semana).

Pre ̧co: 3 reais

Dura ̧c ̃ao: 1 dia

• Loca ̧c ̃ao especial (Fim de semana).

Pre ̧co: 5 reais

Dura ̧c ̃ao: 2 dias

Jogo deve conter os seguintes atributos: ID, Nome do Jogo, Gˆenero. As opera ̧c ̃oes que devem

estar dispon ́ıveis no sistema s ̃ao:

• Alugar um jogo

Muda o estado do jogo para ’Alugado’ e o funcion ́ario pode ent ̃ao entregar o jogo para o cliente. O

sistema deve verificar o dia em que a loca ̧c ̃ao est ́a sendo feita para determinar o cen ́ario (comum

ou especial)

• Devolver um jogo

Ao t ́ermino do per ́ıodo de loca ̧c ̃ao, o cliente devolve o jogo e o sistema verifica se h ́a pendˆencias

como multa e juros. Em caso de loca ̧c ̃ao comum, multa = R$1.00 + R$3.00 por dia al ́em da data

de devolu ̧c ̃ao, em caso de loca ̧c ̃ao especial, multa = R$ 3.00 + R$3.00 por dia al ́em da data de

devolu ̧c ̃ao;

• Observar um jogo

O sistema vai associar um cliente a um jogo para que este observe suas altera ̧c ̃oes (especificamente

a devolu ̧c ̃ao). Isso  ́e  ́util para que o cliente saiba quando o jogo est ́a dispon ́ıvel, por exemplo.

Qualquer altera ̧c ̃ao no jogo deve ser informado para o cliente atrav ́es de email (que ser ́a solicitado

no cadastro).

Formato da mensagem: ”Caro Sr. Nome, o jogo <NOME DO JOGO>est ́a dispon ́ıvel para

loca ̧c ̃ao! Corra agora para a Pattern Games para garantir sua jogatina!

Um jogo pode ter 2 estados:

• Alugado

Se tentar alugar de novo, lan ̧ca uma exce ̧c ̃ao informando a pr ́oxima data em que ele estar ́a dispon ́ıvel

(1 dia ap ́os a data fim do aluguel); Se devolver, retorna o jogo ao estado ’dispon ́ıvel’;

• Dispon ́ıvel

No caso de alugar, altera o estado para ’alugado’; No caso de devolver, lan ̧ca exce ̧c ̃ao informando

que o jogo j ́a est ́a dispon ́ıvel;

2 DETALHES DE IMPLEMENTAC ̧AO ̃

2 Detalhes de implementa ̧c ̃ao

• Para facilitar a valida ̧c ̃ao do CPF, uma vez que n ̃ao  ́e o foco do sistema, utilize a biblioteca Caelum

Stella, dispon ́ıvel em: stella.caelum.com.br/

• Para enviar o email, utilize-se da API JavaMail (Oficial da Oracle). Al ́em disso, alguns fra-
meworks podem ajudar na utiliza ̧c ̃ao do JavaMail (EmailBuilder (https://github.com/jduv/

EmailBuilder, por exemplo)

• Fique a vontade para adicionar qualquer framework que desejar!

• O cliente do sistema pode ser feito para desktop ou web, fique a vontade para escolher.

• O sistema pode utilizar qualquer mecanismo para persistˆencias de dados (Arquivo Texto, XML,

Bancos de Dados Relacionais ou OO)
