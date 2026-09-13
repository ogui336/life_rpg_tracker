# ADR 001 - Optional para busca, exceção propagada para escrita, e User como parâmetro de create

## Contexto
UserRepository precisa comunicar dois tipos de situação diferentes:
métodos de busca (findById, findByEmail) podem legitimamente não
encontrar nada, o que é um resultado normal, não um erro. Métodos de
escrita (create, updateNickname, updateName) podem falhar por
motivos reais (ex: e-mail duplicado, violando o UNIQUE de users).
Além disso, a primeira versão de create recebia email/name/nickname
como Strings soltas, o que permitia que dados inválidos chegassem
até o INSERT antes de qualquer validação de domínio ocorrer.

## Decisão
findById e findByEmail retornam Optional<User>, tratando ausência de
resultado como um valor válido, não uma exceção. create, updateNickname
e updateName declaram throws SQLException e não capturam o erro
internamente — a exceção propaga para quem chamar o método. A
assinatura de create foi alterada para receber um User já construído
(User create(User user)), em vez de três Strings soltas — isso garante
que a validação de domínio (feita nos setters de User) sempre acontece
antes de qualquer SQL ser executado.

## Consequências
+ Elimina o risco de NullPointerException ao esquecer de checar null
  em métodos de busca — o tipo Optional força a checagem explícita.
+ Separa claramente "não encontrou" (Optional vazio) de "algo deu
  errado" (exceção) — são situações conceitualmente diferentes.
+ Impossível inserir um usuário com dado inválido no banco, porque a
  validação ocorre na construção do User, antes de create ser chamado.
- Quem chama create/updateNickname/updateName precisa lidar com
  SQLException (try/catch ou throws) — ainda sem tratamento definitivo,
  fica para quando a camada de Domínio orquestrar chamadas ao Repository.