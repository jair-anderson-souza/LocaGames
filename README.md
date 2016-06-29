# LocaGames
<h1>Descrição</h1>
A Pattern Games é uma loja de compra, venda e locação de jogos e necessita de um sistema para controlar os aluguéis de seus jogos. O usuário do sistema com o papel de atendente não irá  necessitar de login e senha. Antes de utilizar qualquer funcionalidade do sistema, será necessário um cadastro simples de clientes (nome, cpf e email). O sistema deve validar se o CPF digitado e o email são válidos.

Uma locação pode estar contido em dois cenários:

Locação comum (Dia da semana).
Preço: 3 reais
Duração: 1 dia

Locaçãoo especial (Fim de semana).
Preço: 5 reais
Duração: 2 dias

Jogo deve conter os seguintes atributos: ID, Nome do Jogo, Gênero. As operações que devem estar disponíveis no sistema são:

 Alugar um jogo

Muda o estado do jogo para ’Alugado’ e o funcionário pode então entregar o jogo para o cliente. O sistema deve verificar o dia em que a locação está sendo feita para determinar o cenário (comum ou especial).

Devolver um jogo

Ao término do períıodo de locação, o cliente devolve o jogo e o sistema verifica se há pendências como multa e juros. Em caso de locação comum, a multa equivale R$1.00 + R$3.00 por dia além da data de devolução, em caso de locação especial, a multa equivale R$ 3.00 + R$3.00 por dia al ́em da data de devolução;

Observar um jogo

O sistema vai associar um cliente a um jogo para que este observe suas alterações (especificamente a devolução). Isso é útil para que o cliente saiba quando o jogo está disponível, por exemplo. Qualquer alteração no jogo deve ser informado para o cliente através de email (que ser ́a solicitado no cadastro).
Formato da mensagem: ”Caro Sr. Nome, o jogo <NOME DO JOGO> est á disponível 
Para locação! Corra agora para a Loca Games para garantir sua jogatina!



Um jogo pode ter 2 estados:

Alugado

Se tentar alugar de novo, lança uma exceção informando a próxima data em que ele estará disponível (1 dia após a data fim do aluguel); 
Se devolver, retorna o jogo ao estado ’disponível’;

Disponível

No caso de alugar, altera o estado para ’alugado’; No caso de devolver, lança exceção informando que o jogo já está disponível;
