# 📚 Enterprise Java Beans (EJB) – Disciplina

Este repositório reúne os principais conceitos estudados na disciplina de **Enterprise Java Beans (EJB)** no contexto da plataforma **Jakarta EE** (antigo Java EE), com foco em arquitetura corporativa, componentes de negócio e containers.


## 🏗️ Arquitetura Jakarta EE – Modelo Multi-Tier

A arquitetura adotada pela plataforma Jakarta EE é o modelo **Distributed Multi-Tier**, composto por:

- **Client Tier** – Aplicações cliente (browser, desktop, mobile)
- **Web Tier** – Servlets, JSP, JSF, Controllers
- **Business Tier** – EJBs e regras de negócio
- **EIS Tier** – Banco de dados e sistemas legados

O termo **Tier** representa separação física/distribuída, diferente de **Layer**, que representa separação lógica.

---

## 🧠 Conceito de EJB

EJB (Enterprise Java Beans) é um componente do **Business Tier** responsável por:

- Implementação de regras de negócio
- Gerenciamento de transações (JTA)
- Controle de segurança
- Gerenciamento de concorrência
- Processamento assíncrono

Os EJBs executam dentro de um **EJB Container**, que gerencia automaticamente:

- Ciclo de vida
- Pool de instâncias
- Injeção de dependência
- Transações
- Segurança


## 🔹 Tipos de EJB

### 1️⃣ Stateless Session Bean

- Não mantém estado entre requisições
- Ideal para serviços e consultas
- Alta escalabilidade e melhor performance
- Exemplo: cálculos, validações, buscas

```
@Stateless
public class CalculadoraBean {
    public int dobrar(int x) {
        return x * 2;
    }
}

```

### 2️⃣ Stateful Session Bean

- Mantém estado por cliente  
- Ideal para cenários como carrinho de compras  
- Cada cliente possui sua própria instância  
- Pode ser passivado pelo container  

**Passivation:**  
Processo em que o container serializa o bean e o armazena temporariamente em disco para liberar memória.  
Quando necessário, o bean é ativado novamente (*Activation*).


### 3️⃣ Singleton Session Bean

- Uma única instância para toda a aplicação  
- Estado global compartilhado  
- Ideal para cache, configurações e listas globais  

```
@Singleton
public class ConfiguracaoGlobal {
    private String ambiente = "Produção";
}
```

### 4️⃣ Message-Driven Bean (MDB)

- Atua como listener assíncrono
- Processa mensagens via JMS
- Não possui interface direta com cliente
- Implementa o método `onMessage()`

```
@MessageDriven
public class NotificacaoBean implements MessageListener {

@Override
public void onMessage(Message message) {
    // Processamento assíncrono
}

}

```


## 🔗 Tipos de Acesso ao EJB

- `@Local` → acesso dentro do mesmo servidor
- `@Remote` → acesso remoto (Java SE, Swing, outro servidor)

Uma interface remota pode ser acessada tanto remotamente quanto localmente (otimização de co-location).


## ⚙️ Containers na Plataforma

### Web Container

Responsável por:

- Servlets
- JSP
- JSF
- Controllers

### EJB Container

Responsável por:

- Stateless Beans
- Stateful Beans
- Singleton Beans
- Message-Driven Beans

### Exemplos de Servidores

- Tomcat → Apenas Web Container
- Payara / WildFly / GlassFish → Web + EJB Container


## 🧩 Integração com JSF

Exemplo de injeção de EJB em ManagedBean:
@ManagedBean
@RequestScoped
public class FacesTeste {
```
@EJB
private CalculadoraBean calculadora;

public int dobrar(int x) {
    return calculadora.dobrar(x);
}

}
```


## 🧾 POJO e EJB 3

Com o EJB 3, os beans passaram a ser baseados em **POJOs (Plain Old Java Objects)**.

### Antes do EJB 3:

- Uso obrigatório de XML
- Interfaces específicas complexas
- Maior acoplamento ao container

### Com EJB 3:

- Uso de anotações (`@Stateless`, `@Stateful`, etc.)
- Código mais simples e limpo
- Redução de configuração


## 📦 Ferramentas de Build

- Maven → Gerenciamento de dependências e build corporativo
- Gradle → Alternativa moderna e flexível
- Ant → Ferramenta anterior ao Maven


## 🔄 Evolução da Plataforma

- 1999 → J2EE
- 2006 → Java EE 5 (forte adoção de anotações)
- 2018 → Transição para Eclipse Foundation
- 2020 → Jakarta EE 9 (migração de `javax.*` para `jakarta.*`)


## 🛒 Estudo de Caso – E-commerce

Mapeamento de requisitos para tipos de EJB:

| Requisito                         | Tipo de Bean      |
|----------------------------------|-------------------|
| Consulta de produtos             | Stateless         |
| Carrinho de compras              | Stateful          |
| Lista global (mais vendidos)     | Singleton         |
| Envio automático de e-mail       | Message-Driven    |


## 🎯 Competências Desenvolvidas

- Compreensão da arquitetura Multi-Tier
- Modelagem de componentes Enterprise
- Aplicação prática de EJB Stateless, Stateful, Singleton e MDB
- Entendimento do ciclo de vida dos beans
- Conceito de passivation e activation
- Uso de injeção de dependência
- Integração com JSF
- Entendimento da evolução Java EE → Jakarta EE

