# 💳 Banco de Transações - Projeto de Estudo com Spring Boot

Este é um projeto de estudo com **Java + Spring Boot** que simula transações bancárias entre instituições financeiras, implementando persistência de dados, validações e filtros customizados.

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot 3
- Spring Data JPA
- Jakarta Validation
- Lombok
- MySQL
- Maven

## 📦 Funcionalidades

- Cadastrar transações entre bancos (com saldo e limite verificados).
- Consultar quantidade de transações por banco de origem e destino.
- Buscar transações por valor mínimo e banco de origem.
- Consultar transações por saldo da conta ou saldo transferido.
- Mapeamento completo com DTOs, Enums e Entidades.
- Validações de entrada com mensagens personalizadas.

## 🗃️ Estrutura de Dados

**Transações**
- Banco de origem e destino
- Saldos antes e depois da transação
- Limite de cheque especial
- Valor transferido
- Tipo de transação (`EM_PROCESSAMENTO`, `PROCESSADO`, etc.)
- Data da transação

## 🛠️ Endpoints REST

| Método | Rota | Descrição |
|--------|------|-----------|
| `POST` | `/transacoes` | Cadastra uma nova transação |
| `GET` | `/transacoes/origem` | Conta transações por banco de origem |
| `GET` | `/transacoes/destino` | Conta transações por banco de destino |
| `GET` | `/transacoes/buscar-por-banco-e-minimo` | Filtra transações com valor mínimo e banco |
| `GET` | `/transacoes/buscar-por-saldo-transferido` | Busca por saldo de origem ou transferido |

## 📝 Como Executar

1. Clone o projeto:
```bash
git clone [https://github.com/seu-usuario/banco-transacoes.git](https://github.com/MSouza27/banco-transacoes.git)
cd banco-transacoes
