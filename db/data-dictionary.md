# Dicionário de Dados — Life RPG Tracker (MVP)

Banco de dados: MySQL (via WampServer)

## Tabela `users`

| Coluna | Tipo | Nulo? | Chave | Descrição |
|--------|------|-------|-------|-------------|
| id     | INT AUTO_INCREMENT | Não | PK | Identificador único do usuário |
| email  | VARCHAR(255) | Não | UNIQUE | E-mail do usuário |
| name   | VARCHAR(100) | Não | - | Nome do usuário |
| nickname | VARCHAR(40) | Não | - | Apelido exibido no app |

## Tabela `tasks`

| Coluna | Tipo | Nulo? | Chave | Descrição |
|---|---|---|---|---|
| id | INT AUTO_INCREMENT | Não | PK | Identificador único da tarefa |
| user_id | INT | Não | FK → users.id (ON DELETE CASCADE) | Dono da tarefa |
| name | VARCHAR(100) | Não | - | Título da tarefa |
| content | TEXT | Sim | - | Descrição/detalhes opcionais |
| created_at | DATETIME | Não | - | Quando o registro foi criado |
| start_at | DATETIME | Não | - | Data e horário do lembrete |
| completed_at | DATETIME | Sim | - | Nulo = pendente; preenchido = concluída |

## Tabela `notes`

| Coluna | Tipo | Nulo? | Chave | Descrição |
|---|---|---|---|---|
| id | INT AUTO_INCREMENT | Não | PK | Identificador único da nota |
| user_id | INT | Não | FK → users.id (ON DELETE CASCADE) | Dono da nota |
| name | VARCHAR(60) | Não | - | Título da nota |
| content | TEXT | Sim | - | Texto da nota |
| created_at | DATETIME | Não | - | Quando a nota foi criada |

## Tabela `xp`

| Coluna | Tipo | Nulo? | Chave | Descrição |
|---|---|---|---|---|
| id | INT AUTO_INCREMENT | Não | PK | Identificador único do registro de XP |
| user_id | INT | Não | FK → users.id (ON DELETE CASCADE), UNIQUE | Dono do XP/nível — um registro por usuário |
| current_xp | INT | Não | - | XP acumulado no nível atual |
| current_level | INT | Não | - | Nível atual |

## Notas de modelagem

- Todas as FKs usam `ON DELETE CASCADE`: se um usuário for excluído, suas tarefas, notas e registro de XP são excluídos automaticamente junto.
- `xp.user_id` tem `UNIQUE` para garantir, a nível de banco, que cada usuário tenha no máximo um registro de XP/nível.
- Tarefas recursivas ficaram fora do MVP (decisão registrada): cada linha de `tasks` representa uma ocorrência única.
- Ideias registradas para versões futuras, fora do escopo atual: entidade `Conta` (contas pro/gratuita) e entidade `Personagem` (múltiplos personagens por usuário, sistema de masmorra).